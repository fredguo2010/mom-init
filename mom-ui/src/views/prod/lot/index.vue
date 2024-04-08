<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="批次编号" prop="lotNo">
        <el-input
          v-model="queryParams.lotNo"
          placeholder="请输入批次编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批次数量" prop="quantity">
        <el-input
          v-model="queryParams.quantity"
          placeholder="请输入批次数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始日期">
        <el-date-picker
          v-model="daterangeStartDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="结束日期">
        <el-date-picker
          v-model="daterangeEndDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="操作工" prop="worker">
        <el-input
          v-model="queryParams.worker"
          placeholder="请输入操作工"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检验人员" prop="inspector">
        <el-input
          v-model="queryParams.inspector"
          placeholder="请输入检验人员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['prod:lot:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['prod:lot:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['prod:lot:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['prod:lot:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="lotList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键,自增长" align="center" prop="id" />
      <el-table-column label="批次编号" align="center" prop="lotNo" />
      <el-table-column label="产品编号" align="center" prop="productId" />
      <el-table-column label="批次数量" align="center" prop="quantity" />
      <el-table-column label="批次状态" align="center" prop="status" />
      <el-table-column label="开始日期" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束日期" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作工" align="center" prop="worker" />
      <el-table-column label="是否重工" align="center" prop="isReworked" />
      <el-table-column label="父级批次" align="center" prop="parentLot" />
      <el-table-column label="报废数量" align="center" prop="scrapQuantity" />
      <el-table-column label="报废原因" align="center" prop="scrapCause" />
      <el-table-column label="检验人员" align="center" prop="inspector" />
      <el-table-column label="检验日期" align="center" prop="inspectionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inspectionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="comments" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['prod:lot:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['prod:lot:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改批次管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="批次编号" prop="lotNo">
          <el-input v-model="form.lotNo" placeholder="请输入批次编号" />
        </el-form-item>
        <el-form-item label="批次数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入批次数量" />
        </el-form-item>
        <el-form-item label="开始日期" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束日期" prop="endDate">
          <el-date-picker clearable
            v-model="form.endDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作工" prop="worker">
          <el-input v-model="form.worker" placeholder="请输入操作工" />
        </el-form-item>
        <el-form-item label="是否重工" prop="isReworked">
          <el-input v-model="form.isReworked" placeholder="请输入是否重工" />
        </el-form-item>
        <el-form-item label="报废数量" prop="scrapQuantity">
          <el-input v-model="form.scrapQuantity" placeholder="请输入报废数量" />
        </el-form-item>
        <el-form-item label="报废原因" prop="scrapCause">
          <el-input v-model="form.scrapCause" placeholder="请输入报废原因" />
        </el-form-item>
        <el-form-item label="检验人员" prop="inspector">
          <el-input v-model="form.inspector" placeholder="请输入检验人员" />
        </el-form-item>
        <el-form-item label="检验日期" prop="inspectionDate">
          <el-date-picker clearable
            v-model="form.inspectionDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检验日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="comments">
          <el-input v-model="form.comments" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listLot, getLot, delLot, addLot, updateLot } from "@/api/prod/lot";

export default {
  name: "Lot",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 批次管理表格数据
      lotList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeStartDate: [],
      // 备注时间范围
      daterangeEndDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        lotNo: null,
        productId: null,
        quantity: null,
        status: null,
        startDate: null,
        endDate: null,
        worker: null,
        inspector: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        lotNo: [
          { required: true, message: "批次编号不能为空", trigger: "blur" }
        ],
        productId: [
          { required: true, message: "产品编号不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "批次状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询批次管理列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeStartDate && '' != this.daterangeStartDate) {
        this.queryParams.params["beginStartDate"] = this.daterangeStartDate[0];
        this.queryParams.params["endStartDate"] = this.daterangeStartDate[1];
      }
      if (null != this.daterangeEndDate && '' != this.daterangeEndDate) {
        this.queryParams.params["beginEndDate"] = this.daterangeEndDate[0];
        this.queryParams.params["endEndDate"] = this.daterangeEndDate[1];
      }
      listLot(this.queryParams).then(response => {
        this.lotList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        lotNo: null,
        productId: null,
        quantity: null,
        status: null,
        startDate: null,
        endDate: null,
        lineId: null,
        workstationId: null,
        worker: null,
        isReworked: null,
        parentLot: null,
        scrapQuantity: null,
        scrapCause: null,
        inspector: null,
        inspectionDate: null,
        comments: null,
        creator: null,
        createTime: null,
        updater: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeStartDate = [];
      this.daterangeEndDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加批次管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getLot(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改批次管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateLot(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLot(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除批次管理编号为"' + ids + '"的数据项？').then(function() {
        return delLot(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('prod/lot/export', {
        ...this.queryParams
      }, `lot_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
