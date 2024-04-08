<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物料号" prop="partNumber">
        <el-input
          v-model="queryParams.partNumber"
          placeholder="请输入物料号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物料名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入物料名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

<!--      <el-form-item label="起效日期" prop="effectiveStart">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.effectiveStart"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择起效日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="终效日期" prop="effectiveEnd">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.effectiveEnd"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择终效日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->

      <el-form-item label="供应商" prop="supplierName">
        <el-input
          v-model="queryParams.supplierName"
          placeholder="请输入供应商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="规格" prop="specification">
        <el-input
          v-model="queryParams.specification"
          placeholder="请输入规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品牌" prop="brand">
        <el-input
          v-model="queryParams.brand"
          placeholder="请输入品牌"
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
          v-hasPermi="['masterdata:part:add']"
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
          v-hasPermi="['masterdata:part:edit']"
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
          v-hasPermi="['masterdata:part:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['masterdata:part:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="partList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="物料号" align="center" prop="partNumber" />
      <el-table-column label="物料名" align="center" prop="name" />
      <el-table-column label="版本" align="center" prop="revision" />
      <el-table-column label="描述" align="center" prop="description" />
<!--      <el-table-column label="起效日期" align="center" prop="effectiveStart" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.effectiveStart, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="终效日期" align="center" prop="effectiveEnd" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.effectiveEnd, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="类型" align="center" prop="type" />
      <el-table-column label="供应商" align="center" prop="supplierName" />
      <el-table-column label="规格" align="center" prop="specification" />
      <el-table-column label="品牌" align="center" prop="brand" />
      <el-table-column label="状态" align="center" prop="state" />
      <el-table-column label="安全库存" align="center" prop="safetyStock" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="创建者" align="center" prop="createdBy" />
      <el-table-column label="创建日期" align="center" prop="createdDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后修改者" align="center" prop="lastModifiedBy" />
      <el-table-column label="最后修改日期" align="center" prop="lastModifiedDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastModifiedDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="重量" align="center" prop="weight" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['masterdata:part:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['masterdata:part:remove']"
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

    <!-- 添加或修改物料对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物料号" prop="partNumber">
          <el-input v-model="form.partNumber" placeholder="请输入物料号" />
        </el-form-item>
        <el-form-item label="物料名" prop="name">
          <el-input v-model="form.name" placeholder="请输入物料名" />
        </el-form-item>
        <el-form-item label="版本" prop="revision">
          <el-input v-model="form.revision" placeholder="请输入版本" />
        </el-form-item>
        <el-form-item label="bom_id" prop="bomId">
          <el-input v-model="form.bomId" placeholder="请输入bom_id" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="起效日期" prop="effectiveStart">
          <el-date-picker clearable
            v-model="form.effectiveStart"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择起效日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="终效日期" prop="effectiveEnd">
          <el-date-picker clearable
            v-model="form.effectiveEnd"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择终效日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="site_id" prop="siteId">
          <el-input v-model="form.siteId" placeholder="请输入site_id" />
        </el-form-item>
        <el-form-item label="supplier_id" prop="supplierId">
          <el-input v-model="form.supplierId" placeholder="请输入supplier_id" />
        </el-form-item>
        <el-form-item label="供应商" prop="supplierName">
          <el-input v-model="form.supplierName" placeholder="请输入供应商" />
        </el-form-item>
        <el-form-item label="lot_generator_id" prop="lotGeneratorId">
          <el-input v-model="form.lotGeneratorId" placeholder="请输入lot_generator_id" />
        </el-form-item>
        <el-form-item label="unit_generator_id" prop="unitGeneratorId">
          <el-input v-model="form.unitGeneratorId" placeholder="请输入unit_generator_id" />
        </el-form-item>
        <el-form-item label="mf_location" prop="mfLocation">
          <el-input v-model="form.mfLocation" placeholder="请输入mf_location" />
        </el-form-item>
        <el-form-item label="mf_part_number" prop="mfPartNumber">
          <el-input v-model="form.mfPartNumber" placeholder="请输入mf_part_number" />
        </el-form-item>
        <el-form-item label="texture_id" prop="textureId">
          <el-input v-model="form.textureId" placeholder="请输入texture_id" />
        </el-form-item>
        <el-form-item label="规格" prop="specification">
          <el-input v-model="form.specification" placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="品牌" prop="brand">
          <el-input v-model="form.brand" placeholder="请输入品牌" />
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-input v-model="form.state" placeholder="请输入状态" />
        </el-form-item>
        <el-form-item label="uom_id" prop="uomId">
          <el-input v-model="form.uomId" placeholder="请输入uom_id" />
        </el-form-item>
        <el-form-item label="安全库存" prop="safetyStock">
          <el-input v-model="form.safetyStock" placeholder="请输入安全库存" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="删除状态" prop="deleteState">
          <el-input v-model="form.deleteState" placeholder="请输入删除状态" />
        </el-form-item>
        <el-form-item label="创建者" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入创建者" />
        </el-form-item>
        <el-form-item label="创建日期" prop="createdDate">
          <el-date-picker clearable
            v-model="form.createdDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最后修改者" prop="lastModifiedBy">
          <el-input v-model="form.lastModifiedBy" placeholder="请输入最后修改者" />
        </el-form-item>
        <el-form-item label="最后修改日期" prop="lastModifiedDate">
          <el-date-picker clearable
            v-model="form.lastModifiedDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择最后修改日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="inner_code" prop="innerCode">
          <el-input v-model="form.innerCode" placeholder="请输入inner_code" />
        </el-form-item>
        <el-form-item label="calculate_exp" prop="calculateExp">
          <el-input v-model="form.calculateExp" placeholder="请输入calculate_exp" />
        </el-form-item>
        <el-form-item label="exp_rule" prop="expRule">
          <el-input v-model="form.expRule" placeholder="请输入exp_rule" />
        </el-form-item>
        <el-form-item label="exp_rule_time" prop="expRuleTime">
          <el-input v-model="form.expRuleTime" placeholder="请输入exp_rule_time" />
        </el-form-item>
        <el-form-item label="exp_rule_uom" prop="expRuleUom">
          <el-input v-model="form.expRuleUom" placeholder="请输入exp_rule_uom" />
        </el-form-item>
        <el-form-item label="重量" prop="weight">
          <el-input v-model="form.weight" placeholder="请输入重量" />
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
import { listPart, getPart, delPart, addPart, updatePart } from "@/api/masterdata/part";

export default {
  name: "Part",
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
      // 物料表格数据
      partList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        partNumber: null,
        name: null,
        revision: null,
        bomId: null,
        description: null,
        effectiveStart: null,
        effectiveEnd: null,
        type: null,
        siteId: null,
        supplierId: null,
        supplierName: null,
        lotGeneratorId: null,
        unitGeneratorId: null,
        mfLocation: null,
        mfPartNumber: null,
        textureId: null,
        specification: null,
        brand: null,
        state: null,
        uomId: null,
        safetyStock: null,
        deleteState: null,
        createdBy: null,
        createdDate: null,
        lastModifiedBy: null,
        lastModifiedDate: null,
        innerCode: null,
        calculateExp: null,
        expRule: null,
        expRuleTime: null,
        expRuleUom: null,
        weight: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        createdBy: [
          { required: true, message: "创建者不能为空", trigger: "blur" }
        ],
        createdDate: [
          { required: true, message: "创建日期不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询物料列表 */
    getList() {
      this.loading = true;
      listPart(this.queryParams).then(response => {
        this.partList = response.rows;
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
        partNumber: null,
        name: null,
        revision: null,
        bomId: null,
        description: null,
        effectiveStart: null,
        effectiveEnd: null,
        type: null,
        siteId: null,
        supplierId: null,
        supplierName: null,
        lotGeneratorId: null,
        unitGeneratorId: null,
        mfLocation: null,
        mfPartNumber: null,
        textureId: null,
        specification: null,
        brand: null,
        state: null,
        uomId: null,
        safetyStock: null,
        remark: null,
        deleteState: null,
        createdBy: null,
        createdDate: null,
        lastModifiedBy: null,
        lastModifiedDate: null,
        innerCode: null,
        calculateExp: null,
        expRule: null,
        expRuleTime: null,
        expRuleUom: null,
        weight: null
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
      this.title = "添加物料";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPart(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物料";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePart(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPart(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除物料编号为"' + ids + '"的数据项？').then(function() {
        return delPart(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('masterdata/part/export', {
        ...this.queryParams
      }, `part_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
