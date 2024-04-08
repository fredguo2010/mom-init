<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工单编号" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入工单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计划开始时间">
        <el-date-picker
          v-model="daterangePlannedStartDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="计划结束时间">
        <el-date-picker
          v-model="daterangePlannedEndDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="计划产量" prop="quantity">
        <el-input
          v-model="queryParams.quantity"
          placeholder="请输入计划产量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优先级" prop="priority">
        <el-input
          v-model="queryParams.priority"
          placeholder="请输入优先级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下发人" prop="releasedBy">
        <el-input
          v-model="queryParams.releasedBy"
          placeholder="请输入下发人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下发时间" prop="releasedDate">
        <el-date-picker clearable
          v-model="queryParams.releasedDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择下发时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="闭单人 " prop="closedBy">
        <el-input
          v-model="queryParams.closedBy"
          placeholder="请输入闭单人 "
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="闭单时间" prop="closedDate">
        <el-date-picker clearable
          v-model="queryParams.closedDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择闭单时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="创建人" prop="creator">
        <el-input
          v-model="queryParams.creator"
          placeholder="请输入创建人"
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
          v-hasPermi="['prod:order:add']"
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
          v-hasPermi="['prod:order:edit']"
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
          v-hasPermi="['prod:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['prod:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键,自增长" align="center" prop="id" /> -->
      <el-table-column label="工单编号" align="center" prop="orderNo" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="计划开始时间" align="center" prop="plannedStartDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.plannedStartDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划结束时间" align="center" prop="plannedEndDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.plannedEndDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划产量" align="center" prop="quantity" />
      <el-table-column label="优先级" align="center" prop="priority" />
      <el-table-column label="工单状态  " align="center" prop="status" />
      <el-table-column label="下发人" align="center" prop="releasedBy" />
      <el-table-column label="下发时间" align="center" prop="releasedDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.releasedDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="闭单人 " align="center" prop="closedBy" />
      <el-table-column label="闭单时间" align="center" prop="closedDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.closedDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="creator" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['prod:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['prod:order:remove']"
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

    <!-- 添加或修改工单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工单编号" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="请输入工单编号" />
        </el-form-item>
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="计划开始时间" prop="plannedStartDate">
          <el-date-picker clearable
            v-model="form.plannedStartDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择计划开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="计划结束时间" prop="plannedEndDate">
          <el-date-picker clearable
            v-model="form.plannedEndDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择计划结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="计划产量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入计划产量" />
        </el-form-item>
        <el-form-item label="优先级" prop="priority">
          <el-input v-model="form.priority" placeholder="请输入优先级" />
        </el-form-item>
        <el-form-item label="下发人" prop="releasedBy">
          <el-input v-model="form.releasedBy" placeholder="请输入下发人" />
        </el-form-item>
        <el-form-item label="下发时间" prop="releasedDate">
          <el-date-picker clearable
            v-model="form.releasedDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择下发时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="闭单人 " prop="closedBy">
          <el-input v-model="form.closedBy" placeholder="请输入闭单人 " />
        </el-form-item>
        <el-form-item label="闭单时间" prop="closedDate">
          <el-date-picker clearable
            v-model="form.closedDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择闭单时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建人" prop="creator">
          <el-input v-model="form.creator" placeholder="请输入创建人" />
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/prod/order";

export default {
  name: "Order",
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
      // 工单管理表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 创建人时间范围
      daterangePlannedStartDate: [],
      // 创建人时间范围
      daterangePlannedEndDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNo: null,
        productName: null,
        plannedStartDate: null,
        plannedEndDate: null,
        quantity: null,
        priority: null,
        status: null,
        releasedBy: null,
        releasedDate: null,
        closedBy: null,
        closedDate: null,
        creator: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderNo: [
          { required: true, message: "工单编号不能为空", trigger: "blur" }
        ],
        productName: [
          { required: true, message: "产品名称不能为空", trigger: "blur" }
        ],
        quantity: [
          { required: true, message: "计划产量不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工单管理列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangePlannedStartDate && '' != this.daterangePlannedStartDate) {
        this.queryParams.params["beginPlannedStartDate"] = this.daterangePlannedStartDate[0];
        this.queryParams.params["endPlannedStartDate"] = this.daterangePlannedStartDate[1];
      }
      if (null != this.daterangePlannedEndDate && '' != this.daterangePlannedEndDate) {
        this.queryParams.params["beginPlannedEndDate"] = this.daterangePlannedEndDate[0];
        this.queryParams.params["endPlannedEndDate"] = this.daterangePlannedEndDate[1];
      }
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
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
        orderNo: null,
        productId: null,
        productName: null,
        plannedStartDate: null,
        plannedEndDate: null,
        quantity: null,
        lineId: null,
        workstationId: null,
        priority: null,
        status: null,
        releasedBy: null,
        releasedDate: null,
        closedBy: null,
        closedDate: null,
        version: null,
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
      this.daterangePlannedStartDate = [];
      this.daterangePlannedEndDate = [];
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
      this.title = "添加工单管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工单管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除工单管理编号为"' + ids + '"的数据项？').then(function() {
        return delOrder(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('prod/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
