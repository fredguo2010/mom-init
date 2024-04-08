<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工序名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入工序名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="画板名称" prop="displayName">
        <el-input
          v-model="queryParams.displayName"
          placeholder="请输入画板名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入创建者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新人" prop="lastModifiedBy">
        <el-input
          v-model="queryParams.lastModifiedBy"
          placeholder="请输入更新人"
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
          v-hasPermi="['prod:ProdStep:add']"
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
          v-hasPermi="['prod:ProdStep:edit']"
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
          v-hasPermi="['prod:ProdStep:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['prod:ProdStep:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ProdStepList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键,自增长" align="center" prop="id" /> -->
      <el-table-column label="工序名称" align="center" prop="name" />
      <!-- <el-table-column label="描述" align="center" prop="description" /> -->
      <el-table-column label="画板名称" align="center" prop="displayName" />
      <el-table-column label="工艺路线id" align="center" prop="routeId" />
      <el-table-column label="工序宽" align="center" prop="stepWidth" />
      <el-table-column label="工序高" align="center" prop="stepHeight" />
      <el-table-column label="左" align="center" prop="positionLeft" />
      <el-table-column label="上" align="center" prop="positionTop" />
      <el-table-column label="工作中心" align="center" prop="workCenters" />
      <el-table-column label="fifo" align="center" prop="fifo" />
      <el-table-column label="一般优先级" align="center" prop="normalPriority" />
      <el-table-column label="返工优先级" align="center" prop="reworkPriority" />
      <el-table-column label="拆分合并优先级" align="center" prop="splitMergePriority" />
      <el-table-column label="连载" align="center" prop="serialize" />
      <el-table-column label="连续添加部件" align="center" prop="continueAddPart" />
      <el-table-column label="batch_pass" align="center" prop="batchPass" />
      <el-table-column label="创建者" align="center" prop="createdBy" />
      <el-table-column label="创建时间" align="center" prop="createdDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="lastModifiedBy" />
      <el-table-column label="更新日期" align="center" prop="lastModifiedDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastModifiedDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="自动输入" align="center" prop="autoInput" />
      <!-- <el-table-column label="type" align="center" prop="type" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['prod:ProdStep:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['prod:ProdStep:remove']"
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

    <!-- 添加或修改工序管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工序名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入工序名称" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="画板名称" prop="displayName">
          <el-input v-model="form.displayName" placeholder="请输入画板名称" />
        </el-form-item>
        <el-form-item label="工艺路线id" prop="routeId">
          <el-input v-model="form.routeId" placeholder="请输入工艺路线id" />
        </el-form-item>
        <el-form-item label="工序宽" prop="stepWidth">
          <el-input v-model="form.stepWidth" placeholder="请输入工序宽" />
        </el-form-item>
        <el-form-item label="工序高" prop="stepHeight">
          <el-input v-model="form.stepHeight" placeholder="请输入工序高" />
        </el-form-item>
        <el-form-item label="左" prop="positionLeft">
          <el-input v-model="form.positionLeft" placeholder="请输入左" />
        </el-form-item>
        <el-form-item label="上" prop="positionTop">
          <el-input v-model="form.positionTop" placeholder="请输入上" />
        </el-form-item>
        <el-form-item label="工作中心" prop="workCenters">
          <el-input v-model="form.workCenters" placeholder="请输入工作中心" />
        </el-form-item>
        <el-form-item label="fifo" prop="fifo">
          <el-input v-model="form.fifo" placeholder="请输入fifo" />
        </el-form-item>
        <el-form-item label="一般优先级" prop="normalPriority">
          <el-input v-model="form.normalPriority" placeholder="请输入一般优先级" />
        </el-form-item>
        <el-form-item label="返工优先级" prop="reworkPriority">
          <el-input v-model="form.reworkPriority" placeholder="请输入返工优先级" />
        </el-form-item>
        <el-form-item label="拆分合并优先级" prop="splitMergePriority">
          <el-input v-model="form.splitMergePriority" placeholder="请输入拆分合并优先级" />
        </el-form-item>
        <el-form-item label="连载" prop="serialize">
          <el-input v-model="form.serialize" placeholder="请输入连载" />
        </el-form-item>
        <el-form-item label="连续添加部件" prop="continueAddPart">
          <el-input v-model="form.continueAddPart" placeholder="请输入连续添加部件" />
        </el-form-item>
        <el-form-item label="batch_pass" prop="batchPass">
          <el-input v-model="form.batchPass" placeholder="请输入batch_pass" />
        </el-form-item>
        <el-form-item label="创建者" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入创建者" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdDate">
          <el-date-picker clearable
            v-model="form.createdDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新人" prop="lastModifiedBy">
          <el-input v-model="form.lastModifiedBy" placeholder="请输入更新人" />
        </el-form-item>
        <el-form-item label="更新日期" prop="lastModifiedDate">
          <el-date-picker clearable
            v-model="form.lastModifiedDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="自动输入" prop="autoInput">
          <el-input v-model="form.autoInput" placeholder="请输入自动输入" />
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
import { listProdStep, getProdStep, delProdStep, addProdStep, updateProdStep } from "@/api/prod/ProdStep";

export default {
  name: "ProdStep",
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
      // 工序管理表格数据
      ProdStepList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        displayName: null,
        createdBy: null,
        lastModifiedBy: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "工序名称不能为空", trigger: "blur" }
        ],
        displayName: [
          { required: true, message: "画板名称不能为空", trigger: "blur" }
        ],
        createdBy: [
          { required: true, message: "创建者不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工序管理列表 */
    getList() {
      this.loading = true;
      listProdStep(this.queryParams).then(response => {
        this.ProdStepList = response.rows;
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
        name: null,
        description: null,
        displayName: null,
        operationId: null,
        routeId: null,
        stepWidth: null,
        stepHeight: null,
        positionLeft: null,
        positionTop: null,
        workCenters: null,
        fifo: null,
        normalPriority: null,
        reworkPriority: null,
        splitMergePriority: null,
        serialize: null,
        continueAddPart: null,
        batchPass: null,
        createdBy: null,
        createdDate: null,
        lastModifiedBy: null,
        lastModifiedDate: null,
        deleteState: null,
        tagId: null,
        autoInput: null,
        type: null
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
      this.title = "添加工序管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProdStep(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工序管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProdStep(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProdStep(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除工序管理编号为"' + ids + '"的数据项？').then(function() {
        return delProdStep(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('prod/ProdStep/export', {
        ...this.queryParams
      }, `ProdStep_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
