<template>
<div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="工艺路线名称" prop="name">
            <el-input v-model="queryParams.name" placeholder="请输入工艺路线名称" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="创建人" prop="createdBy">
            <el-input v-model="queryParams.createdBy" placeholder="请输入创建人" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="更新人" prop="lastModifiedBy">
            <el-input v-model="queryParams.lastModifiedBy" placeholder="请输入更新人" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
            <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['prod:prodRoute:add']">新增</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['prod:prodRoute:edit']">修改</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['prod:prodRoute:remove']">删除</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['prod:prodRoute:export']">导出</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button type="primary" plain icon="el-icon-success" size="mini" :disabled="single" @click="handleApprove" v-hasPermi="['prod:prodRoute:approved']">审核</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table highlight-current-row v-loading="loading" :data="prodRouteList" @selection-change="handleSelectionChange" @current-change="handleCurrentChange" ref="routeTable">
        <el-table-column type="selection" width="55" align="center" />
        <!-- <el-table-column label="" align="center" prop="id" /> -->
        <el-table-column label="工艺路线名称" align="center" prop="name" />
        <!-- <el-table-column label="工艺路线描述" align="center" prop="description" /> -->
        <el-table-column label="创建人" align="center" prop="createdBy" />
        <el-table-column label="已审核" align="center" prop="approved">
            <template slot-scope="scope">
                <span :class="scope.row.approved &&'approved'">{{scope.row.approved?'已审核':'未审核'}}</span>
            </template>
        </el-table-column>
        <el-table-column label="创建日期" align="center" prop="createdDate" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>
            </template>
        </el-table-column>
        <el-table-column label="更新人" align="center" prop="lastModifiedBy" />
        <el-table-column label="更新时间" align="center" prop="lastModifiedDate" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.lastModifiedDate, '{y}-{m}-{d}') }}</span>
            </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
                <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)" v-hasPermi="['prod:prodRoute:edit']">查看</el-button>
                <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['prod:prodRoute:edit']">修改</el-button>
                <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['prod:prodRoute:remove']">删除</el-button>
            </template>
        </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    <div class="bread-box">
        <div class="bread-title">
            工艺路线: {{currentRow.name}}
        </div>
        <el-breadcrumb separator-class="el-icon-right">
            <el-breadcrumb-item v-for="(step, index) in currentSteps" :key="index">{{step}}</el-breadcrumb-item>
        </el-breadcrumb>
    </div>

    <!-- 添加或修改工艺路线对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item label="工艺路线名称" prop="description">
                <el-input v-model="form.name" placeholder="请输入工艺路线名称" />
            </el-form-item>
            <el-form-item label="工艺路线描述" prop="description">
                <el-input v-model="form.description" placeholder="请输入工艺路线描述" />
            </el-form-item>
            <el-form-item label="创建人" prop="createdBy">
                <el-input v-model="form.createdBy" placeholder="请输入创建人" />
            </el-form-item>
            <el-form-item label="创建日期" prop="createdDate">
                <el-date-picker clearable v-model="form.createdDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择创建日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="更新人" prop="lastModifiedBy">
                <el-input v-model="form.lastModifiedBy" placeholder="请输入更新人" />
            </el-form-item>
            <el-form-item label="更新时间" prop="lastModifiedDate">
                <el-date-picker clearable v-model="form.lastModifiedDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择更新时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="工序" prop="steps">
                <el-select v-model="selectedStep" multiple filterable allow-create placeholder="请选择工序">
                    <el-option v-for="item in ProdStepList" :key="item.id" :label="item.displayName" :value="item.displayName">
                    </el-option>
                </el-select>
                <div style="font-weight:bold;margin-top:10px" v-if="selectedStep.length > 0">拖拽排序</div>
                <ul class="drag-box">
                    <li v-for="(item, index) in selectedStep" :key="index" :draggable="true" 
                            @dragstart="dragstart(item)"
                            @dragenter="dragenter(item, $event)"
                            @dragend="dragend(item, $event)"
                            @dragover="dragover($event)"
                            >
                            {{ item }}
                        </li>
                </ul>
            </el-form-item>
            <!-- <el-form-item label="已审核" prop="approved">
                <el-input v-model="form.approved" placeholder="请输入已审核" />
            </el-form-item> -->
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
        </div>
    </el-dialog>
</div>
</template>

<style lang="scss" scoped>
.bread-box {
    padding: 50px 20px;
    border: 2px solid #999;
    // width: fit-content;
    position: relative;
    margin-top: 40px;
    margin-bottom: 40px;

    .bread-title {
        background: white;
        position: absolute;
        padding: 0 10px;
        top: -10px;
        left: 10px;
    }

    .el-breadcrumb__item {
        font-size: 20px;
    }
}

.approved {
    font-weight: bold;
    background: green;
    color: white;
    padding: 8px;
    border-radius: 9px;
}
.drag-box{
    // text-decoration: none;
    list-style-type:none;
    padding:0;
    margin:0;
    li{
        background: #e8f4ff;
        margin-top: 3px;
        padding-left: 10px;
        cursor: move;
    }
}
</style>

<script>
import {
    listProdRoute,
    getProdRoute,
    delProdRoute,
    addProdRoute,
    updateProdRoute,

} from "@/api/prod/prodRoute";
import {
    listProdStep
} from "@/api/prod/ProdStep";
export default {
    name: "ProdRoute",
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
            // 工艺路线表格数据
            prodRouteList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            currentRow: {},
            canApprove: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                name: null,
                createdBy: null,
                lastModifiedBy: null,
            },
            currentSteps: [],
            // 表单参数
            form: {},
            // 表单校验
            rules: {
                name: [{
                    required: true,
                    message: "工艺路线名称不能为空",
                    trigger: "blur"
                }],
                createdBy: [{
                    required: true,
                    message: "创建人不能为空",
                    trigger: "blur"
                }],
            },
            stepOption: [], // step 选项
            selectedStep: [],
            dragIndex:null,
            ProdStepList:[],
            newData:[],
            oldData:[],
            oldIndex:null,
            newIndex:null
        };
    },
    created() {
        this.getList();
        this.getStepOptions();
    },

    methods: {
        dragstart(value) {
            this.oldData = value;
        },

        dragenter(value, e) {
            this.newData = value;
            e.preventDefault()
        },
        dragend(value, e) {
            if (this.oldData !== this.newData) {
                let oldIndex = this.selectedStep.indexOf(this.oldData);
                let newIndex = this.selectedStep.indexOf(this.newData);
                let newItems = [...this.selectedStep];
                // 删除老的节点
                newItems.splice(oldIndex, 1)
                // 在列表中目标位置增加新的节点
                newItems.splice(newIndex, 0, this.oldData)
                this.selectedStep = [...newItems]
            }
        },
        dragover(e) {
            e.preventDefault()
        },
        /** 查询工艺路线列表 */
        getList() {
            this.loading = true;
            this.canApprove = false;
            listProdRoute(this.queryParams).then(response => {
                this.prodRouteList = response.rows;
                this.total = response.total;
                this.loading = false;
                this.currentSteps = [];
                this.currentRow = {};
            });
        },
        getStepOptions() {
            this.loading = true;
            listProdStep({
                pageNum: 1,
                pageSize: 1000,
                name: null,
                createdBy: null,
                lastModifiedBy: null,
            }).then(response => {
                this.ProdStepList = response.rows.map(item => {
                    let obj = {};
                    obj.displayName = item.displayName;
                    obj.id = item.id;
                    return obj;
                });
                this.total = response.total;
                this.loading = false;
            })
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
                createdBy: null,
                createdDate: null,
                lastModifiedBy: null,
                lastModifiedDate: null,
                deleteState: null,
                steps: null,
                approved: false
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
            this.multiple = !selection.length;
        },
        /** 新增按钮操作 */
        handleAdd() {
            this.reset();
            this.open = true;
            this.title = "添加工艺路线";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const id = row.id || this.ids
            getProdRoute(id).then(response => {
                this.form = response.data;
                if(this.form.steps){
                    this.selectedStep = JSON.parse(this.form.steps);
                }else{
                    this.selectedStep = [];
                }
                this.open = true;
                this.title = "修改工艺路线";
            });
        },
        handleView(row) {
        },
        handleApprove(row) {
            this.reset();
            // this.currentRow.approved = true;
            console.log(row);
            const id = row.id || this.ids
            getProdRoute(id).then(response => {
                this.form = response.data;
                this.form.approved = true;
                updateProdRoute(this.form).then(response => {
                    this.$modal.msgSuccess("修改成功");
                    this.open = false;
                    this.getList();
                });
            });
        },

        setCurrent(row) {
            this.$refs.routeTable.setCurrentRow(row);
        },
        handleCurrentChange(val) {
            if (JSON.stringify(val) === "") {
                this.canApprove = false;
            } else {
                this.canApprove = true;
            };
            this.currentRow = val;
            if (this.currentRow.steps) {
                this.currentSteps = JSON.parse(this.currentRow.steps)
            }else{
                this.currentSteps = [];
            }
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    this.form.steps = JSON.stringify(this.selectedStep);
                    if (this.form.id != null) {
                        updateProdRoute(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();

                        });
                    } else {
                        addProdRoute(this.form).then(response => {
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
            this.$modal.confirm('是否确认删除工艺路线编号为"' + ids + '"的数据项？').then(function () {
                return delProdRoute(ids);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => {});
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('prod/prodRoute/export', {
                ...this.queryParams
            }, `prodRoute_${new Date().getTime()}.xlsx`)
        }
    },
};
</script>
