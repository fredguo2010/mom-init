<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="生产计划号" prop="planNo">
                <el-input v-model="queryParams.planNo" placeholder="请输入生产计划号" clearable @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="产品名" prop="productName">
                <el-input v-model="queryParams.productName" placeholder="请输入产品名" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="计划数量" prop="planQuantity">
                <el-input v-model="queryParams.planQuantity" placeholder="请输入计划数量" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="计划开始日期">
                <el-date-picker v-model="daterangePlanStartDate" style="width: 240px" value-format="yyyy-MM-dd"
                    type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
            </el-form-item>
            <el-form-item label="计划结束日期">
                <el-date-picker v-model="daterangePlanEndDate" style="width: 240px" value-format="yyyy-MM-dd"
                    type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
            </el-form-item>
            <el-form-item label="物料号" prop="materialId">
                <el-input v-model="queryParams.materialId" placeholder="请输入物料号" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="物料数量" prop="materialQuantity">
                <el-input v-model="queryParams.materialQuantity" placeholder="请输入物料数量" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                    v-hasPermi="['prod:plan:add']">新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
                    v-hasPermi="['prod:plan:edit']">修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
                    v-hasPermi="['prod:plan:remove']">删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                    v-hasPermi="['prod:plan:export']">导出</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="id" align="center" prop="id" />
            <el-table-column label="生产计划号" align="center" prop="planNo" />
            <el-table-column label="产品名" align="center" prop="productName" />
            <el-table-column label="状态" align="center" prop="status">
                <template slot-scope="scope">
                    <span class="status" :class="'status_' + scope.row.status">{{ statusArr[scope.row.status] }}</span>
                </template>
            </el-table-column>
            <el-table-column label="计划数量" align="center" prop="planQuantity" />
            <el-table-column label="计划开始日期" align="center" prop="planStartDate" width="180">
                <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.planStartDate, '{y}-{m}-{d}') }}</span>
                </template>
            </el-table-column>
            <el-table-column label="计划结束日期" align="center" prop="planEndDate" width="180">
                <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.planEndDate, '{y}-{m}-{d}') }}</span>
                </template>
            </el-table-column>
            <el-table-column label="物料号" align="center" prop="materialId" />
            <el-table-column label="物料数量" align="center" prop="materialQuantity" />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                        v-hasPermi="['prod:plan:edit']">修改</el-button>
                    <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                        v-hasPermi="['prod:plan:remove']">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
            @pagination="getList" />

        <!-- 添加或修改生产计划对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="生产计划号" prop="planNo">
                    <el-input v-model="form.planNo" placeholder="请输入生产计划号" />
                </el-form-item>
                <el-form-item label="产品名" prop="productName">
                    <el-input v-model="form.productName" placeholder="请输入产品名" />
                </el-form-item>
                <el-form-item label="计划数量" prop="planQuantity">
                    <el-input v-model="form.planQuantity" placeholder="请输入计划数量" />
                </el-form-item>
                <el-form-item label="状态" prop="stauts">
                    <el-select v-model="form.status" placeholder="请选择">
                        <el-option v-for="item in statusOptions" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="计划开始日期" prop="planStartDate">
                    <el-date-picker clearable v-model="form.planStartDate" type="date" value-format="yyyy-MM-dd"
                        placeholder="请选择计划开始日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="计划结束日期" prop="planEndDate">
                    <el-date-picker clearable v-model="form.planEndDate" type="date" value-format="yyyy-MM-dd"
                        placeholder="请选择计划结束日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="物料号" prop="materialId">
                    <el-input v-model="form.materialId" placeholder="请输入物料号" />
                </el-form-item>
                <el-form-item label="物料数量" prop="materialQuantity">
                    <el-input v-model="form.materialQuantity" placeholder="请输入物料数量" />
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<style lang="scss" scoped>
.status {
    font-weight: bold;
    color: white;
    padding: 8px;
    border-radius: 9px;
}

.status_0 {
    background: #ff4949;
}

.status_1 {
    background: #ffba00;
}

.status_2 {
    background: #1890ff;
}

.status_3 {
    background: #13ce66;
}
</style>

<script>
import {
    listPlan,
    getPlan,
    delPlan,
    addPlan,
    updatePlan
} from "@/api/prod/plan";

export default {
    name: "Plan",
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
            // 生产计划表格数据
            planList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 物料数量时间范围
            daterangePlanStartDate: [],
            // 物料数量时间范围
            daterangePlanEndDate: [],
            statusArr: ['新建', '进行中', '已解决', '完成'],
            statusOptions: [{
                label: '新建',
                value: 0
            },
            {
                label: '进行中',
                value: 1
            },
            {
                label: '已解决',
                value: 2
            },
            {
                label: '完成',
                value: 3
            },
            ],
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                planNo: null,
                productName: null,
                planQuantity: null,
                planStartDate: null,
                planEndDate: null,
                materialId: null,
                materialQuantity: null,
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
                planNo: [{
                    required: true,
                    message: "生产计划号不能为空",
                    trigger: "blur"
                }],
                productName: [{
                    required: true,
                    message: "产品名不能为空",
                    trigger: "blur"
                }],
                planQuantity: [{
                    required: true,
                    message: "计划数量不能为空",
                    trigger: "blur"
                }],
                materialId: [{
                    required: true,
                    message: "物料号不能为空",
                    trigger: "blur"
                }],
            }
        };
    },
    created() {
        this.getList();
    },
    methods: {
        /** 查询生产计划列表 */
        getList() {
            this.loading = true;
            this.queryParams.params = {};
            if (null != this.daterangePlanStartDate && '' != this.daterangePlanStartDate) {
                this.queryParams.params["beginPlanStartDate"] = this.daterangePlanStartDate[0];
                this.queryParams.params["endPlanStartDate"] = this.daterangePlanStartDate[1];
            }
            if (null != this.daterangePlanEndDate && '' != this.daterangePlanEndDate) {
                this.queryParams.params["beginPlanEndDate"] = this.daterangePlanEndDate[0];
                this.queryParams.params["endPlanEndDate"] = this.daterangePlanEndDate[1];
            }
            listPlan(this.queryParams).then(response => {
                this.planList = response.rows;
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
                planNo: null,
                productId: null,
                productName: null,
                planQuantity: null,
                planStartDate: null,
                planEndDate: null,
                lineId: null,
                workstationId: null,
                materialId: null,
                materialQuantity: null,
                staffId: null,
                staffHours: null,
                status: null,
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
            this.daterangePlanStartDate = [];
            this.daterangePlanEndDate = [];
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
            this.title = "添加生产计划";

            if (this.form.id == null) { // 新建
                this.form.status = 0; // 新建默认填写为0
            }
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const id = row.id || this.ids
            getPlan(id).then(response => {
                this.form = response.data;
                this.open = true;
                this.title = "修改生产计划";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.id != null) {
                        updatePlan(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        this.form.status = 0;
                        addPlan(this.form).then(response => {
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
            this.$modal.confirm('是否确认删除生产计划编号为"' + ids + '"的数据项？').then(function () {
                return delPlan(ids);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => { });
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('prod/plan/export', {
                ...this.queryParams
            }, `plan_${new Date().getTime()}.xlsx`)
        }
    }
};
</script>
