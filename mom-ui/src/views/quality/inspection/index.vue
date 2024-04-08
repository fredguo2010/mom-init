<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批次号" prop="batchNo">
        <el-input
          v-model="queryParams.batchNo"
          placeholder="请输入批次号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检验项目" prop="inspectionItem">
        <el-input
          v-model="queryParams.inspectionItem"
          placeholder="请输入检验项目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检验测量值" prop="inspectionValue">
        <el-input
          v-model="queryParams.inspectionValue"
          placeholder="请输入检验测量值"
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
          v-hasPermi="['quality:inspection:add']"
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
          v-hasPermi="['quality:inspection:edit']"
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
          v-hasPermi="['quality:inspection:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['quality:inspection:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="10">
        <el-table v-loading="loading" :data="inspectionList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="id" align="center" prop="id" />
<!--          <el-table-column label="产品名称" align="center" prop="productName" />-->
<!--          <el-table-column label="批次号" align="center" prop="batchNo" />-->
<!--          <el-table-column label="检验项目" align="center" prop="inspectionItem" />-->
<!--          <el-table-column label="检验员" align="center" prop="inspector" />-->
          <el-table-column label="检验测量值" align="center" prop="inspectionValue" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['quality:inspection:edit']"
              >修改</el-button>
              <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['quality:inspection:remove']"
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
      </el-col>
      <el-col :span="14">
        <spcChart :chartData="spcChartData" :height="'600px'"  :width="'100%'"/>
      </el-col>
    </el-row>

    <!-- 添加或修改质量检测对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="批次号" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入批次号" />
        </el-form-item>
        <el-form-item label="检验项目" prop="inspectionItem">
          <el-input v-model="form.inspectionItem" placeholder="请输入检验项目" />
        </el-form-item>
        <el-form-item label="检验员" prop="inspector">
          <el-input v-model="form.inspector" placeholder="请输入检验员" />
        </el-form-item>
        <el-form-item label="检验测量值" prop="inspectionValue">
          <el-input v-model="form.inspectionValue" placeholder="请输入检验测量值" />
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
import {
  listInspection,
  getInspection,
  delInspection,
  addInspection,
  updateInspection,
  listAllInspection, getSpcData
} from "@/api/quality/inspection";
import SpcChart from "@/views/quality/spc/spcChart.vue";

const spcChartData = {
    xAxisData: [],
    inspectionValues: [],
    uclData: [],
    lclData: [],
    avgArray: []
}
export default {
  name: "Inspection",
  components: {
      SpcChart
  },
  data() {
    return {
      spcChartData: spcChartData,
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
      // 质量检测表格数据
      inspectionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productName: null,
        batchNo: null,
        inspectionItem: null,
        inspectionValue: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }

    };
  },
  created() {
    this.getList();
    this.reloadChartData();
  },
  methods: {

    /** 查询质量检测列表 */
    getList() {
      this.loading = true;
      listInspection(this.queryParams).then(response => {
        this.inspectionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 查询所有质量检测列表 */
    reloadChartData() {
      getSpcData().then(response => {
        // console.log("getSpcData...");
        // console.log(response);
        spcChartData.inspectionValues = response?.inspectionValues;
        spcChartData.uclData = response?.uclArray;
        spcChartData.lclData = response?.lclArray;
        spcChartData.xAxisData = response?.xAxisData;
        spcChartData.avgData = response?.avgArray;
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
        productId: null,
        productName: null,
        batchNo: null,
        inspectionItem: null,
        inspector: null,
        inspectionTime: null,
        inspectionValue: null,
        upperLimit: null,
        lowerLimit: null,
        qualityStatus: null
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加质量检测";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInspection(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改质量检测";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInspection(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInspection(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
              this.reloadChartData();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除质量检测编号为"' + ids + '"的数据项？').then(function () {
        return delInspection(ids);
      }).then(() => {
        this.getList();
        this.reloadChartData();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('quality/inspection/export', {
        ...this.queryParams
      }, `inspection_${new Date().getTime()}.xlsx`)
    },
      caculateSpcData() {
        console.log(this.inspectionList);
      }
  }
};
</script>
