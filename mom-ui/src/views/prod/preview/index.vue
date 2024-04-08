<template>
<div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="名称" prop="name">
            <el-input v-model="queryParams.name" placeholder="请输入名称" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="创建人" prop="createdBy">
            <el-input v-model="queryParams.createdBy" placeholder="请输入创建人" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdDate">
            <el-date-picker clearable v-model="queryParams.createdDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择创建时间">
            </el-date-picker>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
            <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['prod:preview:add']">新增</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['prod:preview:edit']">修改</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['prod:preview:remove']">删除</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['prod:preview:export']">导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="previewList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="名称" align="center" prop="name" />
        <el-table-column label="文档" align="center" prop="file">
            <template slot-scope="scope">
                <el-link :href="`${baseUrl}${scope.row.file}`" :underline="false" target="_blank">
                    <span class="el-icon-document"> {{ getFileName(scope.row.file) }} </span>
                </el-link>
            </template>
        </el-table-column>
        <el-table-column label="描述" align="center" prop="description" />
        <el-table-column label="创建人" align="center" prop="createdBy" />
        <el-table-column label="创建时间" align="center" prop="createdDate" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>
            </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
                <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['prod:preview:edit']">修改</el-button>
                <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['prod:preview:remove']">删除</el-button>
            </template>
        </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改操作手册对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item label="名称" prop="name">
                <el-input v-model="form.name" placeholder="请输入名称" />
            </el-form-item>
            <el-form-item label="文档" prop="file">
                <file-upload v-model="form.file"  :limit="1" :viewWithDialog="false" />
            </el-form-item>
            <el-form-item label="描述" prop="description">
                <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
            </el-form-item>
            <el-form-item label="创建人" prop="createdBy">
                <el-input v-model="form.createdBy" placeholder="请输入创建人" />
            </el-form-item>
            <el-form-item label="创建时间" prop="createdDate">
                <el-date-picker clearable v-model="form.createdDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择创建时间">
                </el-date-picker>
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
    listPreview,
    getPreview,
    delPreview,
    addPreview,
    updatePreview
} from "@/api/prod/preview";

export default {
    name: "Preview",
    data() {
        return {
            baseUrl: process.env.VUE_APP_BASE_API,
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
            // 操作手册表格数据
            previewList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                name: null,
                createdBy: null,
                createdDate: null
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
                name: [{
                    required: true,
                    message: "名称不能为空",
                    trigger: "blur"
                }],
            },
            // upload: {
            //     // 是否禁用上传
            //     isUploading: false,
            //     // 设置上传的请求头部
            //     headers: {
            //         Authorization: "Bearer " + getToken()
            //     },
            //     // 上传的地址
            //     url: process.env.VUE_APP_BASE_API + "/common/upload",
            //     // 上传的文件列表
            //     fileList: []
            // },
        };
    },
    created() {
        this.getList();
    },
    methods: {
        // handleAdd() {
        //     this.upload.fileList = []
        // },

        // handleUpdate(row) {
        //     this.upload.fileList = [{
        //         name: this.form.fileName,
        //         url: this.form.filePath
        //     }];
        // },
        // submitUpload() {
        //     this.$refs.upload.submit();
        // },
        // // 文件上传中处理
        // handleFileUploadProgress(event, file, fileList) {
        //     this.upload.isUploading = true;
        // },
        // // 文件上传成功处理
        // handleFileSuccess(response, file, fileList) {
        //     this.upload.isUploading = false;
        //     this.form.filePath = response.url;
        //     this.msgSuccess(response.msg);
        // },
        getFileName(name) {
            if (name.lastIndexOf("/") > -1) {
                return name.slice(name.lastIndexOf("/") + 1);
            } else {
                return "";
            }
        },

        /** 查询操作手册列表 */
        getList() {
            this.loading = true;
            listPreview(this.queryParams).then(response => {
                this.previewList = response.rows;
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
                file: null,
                description: null,
                createdBy: null,
                createdDate: null
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
            this.title = "添加操作手册";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const id = row.id || this.ids
            getPreview(id).then(response => {
                this.form = response.data;
                this.open = true;
                this.title = "修改操作手册";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.id != null) {
                        updatePreview(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addPreview(this.form).then(response => {
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
            this.$modal.confirm('是否确认删除操作手册编号为"' + ids + '"的数据项？').then(function () {
                return delPreview(ids);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => {});
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('prod/preview/export', {
                ...this.queryParams
            }, `preview_${new Date().getTime()}.xlsx`)
        }
    }
};
</script>
