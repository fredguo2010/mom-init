<template>
<div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="名称" prop="name">
            <el-input v-model="queryParams.name" placeholder="请输入名称" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="类别" prop="category">
            <el-input v-model="queryParams.category" placeholder="请输入类别" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
            <el-input v-model="queryParams.description" placeholder="请输入描述" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="创建人" prop="createdBy">
            <el-input v-model="queryParams.createdBy" placeholder="请输入创建人" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="创建日期" prop="createdDate">
            <el-date-picker clearable v-model="queryParams.createdDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择创建日期">
            </el-date-picker>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
            <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['prod:Idpattern:add']">新增</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['prod:Idpattern:edit']">修改</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['prod:Idpattern:remove']">删除</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['prod:Idpattern:export']">导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="IdpatternList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <!-- <el-table-column label="" align="center" prop="id" /> -->
        <el-table-column label="名称" align="center" prop="name" />
        <el-table-column label="类别" align="center" prop="category" />
        <el-table-column label="描述" align="center" prop="description" />
        <!-- <el-table-column label="规则" align="center" prop="patterns" /> -->
        <el-table-column label="创建人" align="center" prop="createdBy" />
        <el-table-column label="创建日期" align="center" prop="createdDate" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>
            </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">

                <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['prod:Idpattern:edit']">修改</el-button>
                <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['prod:Idpattern:remove']">删除</el-button>
                <el-button size="mini" type="text" icon="el-icon-coordinate" @click="handleTestOpen(scope.row)" v-hasPermi="['prod:Idpattern:remove']">测试迭代器</el-button>
            </template>
        </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    <el-dialog :before-close="handleClose" :title="'测试ID迭代器：' + testTitle" :visible.sync="testOpen" width="500px" append-to-body>
        <el-button type="primary" icon="el-icon-plus" circle @click="testAdd" style="margin-left:15px"></el-button>
        <ul>
            <li v-for="(item, index) in testList" :key="index">{{item}}</li>
        </ul>
    </el-dialog>

    <!-- 添加或修改ID生成器对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item label="名称" prop="name">
                <el-input v-model="form.name" placeholder="请输入名称" />
            </el-form-item>
            <el-form-item label="类别" prop="category">
                <el-input v-model="form.category" placeholder="请输入类别" />
            </el-form-item>
            <el-form-item label="描述" prop="description">
                <el-input v-model="form.description" placeholder="请输入描述" />
            </el-form-item>
            <el-form-item label="创建人" prop="createdBy">
                <el-input v-model="form.createdBy" placeholder="请输入创建人" />
            </el-form-item>
            <el-form-item label="创建日期" prop="createdDate">
                <el-date-picker clearable v-model="form.createdDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择创建日期">
                </el-date-picker>
            </el-form-item>
            <div class="demo-box" v-if="demoStr">规则样例: {{demoStr}}</div>
            <el-form-item label="规则" prop="patterns">
                <!-- <el-input v-model="form.patterns" type="textarea" placeholder="请输入内容" /> -->
                <el-select v-model="currentSelectPattern" filterable allow-create placeholder="请选择规则">
                    <el-option v-for="item in patternOptions" :key="item.id" :label="item.label" :value="item">
                    </el-option>
                </el-select>
                <!-- <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handlePatternAdd">新增</el-button> -->
                <el-button type="primary" icon="el-icon-plus" circle @click="handlePatternAdd" style="margin-left:15px"></el-button>
                <div style="font-weight:bold;margin-top:10px" v-if="selectedPatterns.length > 0">拖拽排序</div>
                <ul class="drag-box" v-if="selectedPatterns.length > 0">
                    <li style="position:relative" v-for="(item, index) in selectedPatterns" :key="index" :class="item.active && 'active'" :draggable="true" @dragstart="dragstart(index)" @click="openConfig(item)" @dragenter="dragenter(index, $event)" @dragend="dragend(index, $event)" @dragover="dragover($event)">
                        {{ item.label }}
                        <el-button style="margin-left:15px;position:absolute;right:20px" type="primary" icon="el-icon-minus" circle @click="handlePatternDelete(item)"></el-button>
                    </li>
                </ul>

                <div style="font-weight:bold;margin-top:10px">配置规则</div>
                <div v-if="currentPatternConfig.value == 'string'">
                    <el-form-item label="字符串">
                        <el-input v-model="currentPatternConfig.config.patternValue" placeholder="请输入字符串" @change="formatPattern" />
                    </el-form-item>
                </div>
                <div v-else-if="currentPatternConfig.value == 'date'">
                    <el-form-item label="日期格式">
                        <el-input v-model="currentPatternConfig.config.patternValue" placeholder="请输入字符串" @change="formatPattern" />
                    </el-form-item>
                </div>
                <div v-else-if="currentPatternConfig.value == 'sequence'">
                    <el-form-item label="字符长度">
                        <el-input v-model="currentPatternConfig.config.length" placeholder="请输入字符长度" @change="formatPattern" />
                    </el-form-item>
                    <el-form-item label="开始值">
                        <el-input v-model="currentPatternConfig.config.start" placeholder="请输入开始值" @change="formatPattern" />
                    </el-form-item>
                    <el-form-item label="变量标识">
                        <el-input v-model="currentPatternConfig.config.flag" placeholder="请输入变量标识" @change="formatPattern" />
                    </el-form-item>
                </div>
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
.drag-box {
    // text-decoration: none;
    list-style-type: none;
    padding: 0;
    margin: 0;

    li {
        background: #F5F7FA;
        margin-top: 3px;
        padding-left: 10px;
        cursor: move;
    }

    li.active {
        background: #a3d3ff;
        color: white;
    }
}

.demo-box {
    width: 100%;
    background: green;
    height: 32px;
    line-height: 32px;
    color: white;
    padding-left: 10px;
    margin-bottom: 20px;
    font-size: 20px;
}
</style>

<script>
import {
    listIdpattern,
    getIdpattern,
    delIdpattern,
    addIdpattern,
    updateIdpattern
} from "@/api/prod/Idpattern";
import moment from 'moment';
export default {
    name: "Idpattern",
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
            // ID生成器表格数据
            IdpatternList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,

            testOpen: false, // 计数测试弹窗
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                name: null,
                category: null,
                description: null,
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
                createdBy: [{
                    required: true,
                    message: "创建人不能为空",
                    trigger: "blur"
                }],
            },
            currentSelectPattern: {},
            selectedPatterns: [],
            patternOptions: [{
                    label: '字符串类型',
                    value: 'string',
                },
                {
                    label: '日期类型',
                    value: 'date'
                },
                {
                    label: '序列类型',
                    value: 'sequence'
                },
            ],
            patternConfigArr: [{
                    label: '字符串',
                    patternType: 'string',
                    patternValue: '',
                },
                {
                    label: '日期格式',
                    patternType: 'date',
                    patternValue: 'yyyyMMDD',
                },
                {
                    label: '序列',
                    patternType: 'sequence',
                    length: 2,
                    start: "01",
                    flag: 'sequence1',
                }
            ],
            currentPatternConfig: {},
            demoStr: "",
            currentTestPatternConfig: [], // 规则 config
            NO: null,
            testTitle: '',
            testList: []
        };
    },
    created() {
        this.getList();
    },
    methods: {
        handleClose() {
            this.reset();
            this.testOpen = false;
        },
        dragstart(index) {
            this.oldData = this.selectedPatterns[index];
            this.oldIndex = index;
            console.log("old:");
            console.dir(this.oldData);
        },

        dragenter(index, e) {
            this.newData = this.selectedPatterns[index];
            this.newIndex = index;
            console.log("new:");
            console.dir(this.newData);
            e.preventDefault();
        },
        dragend(index, e) {
            if (this.oldData !== this.newData) {
                let newItems = [...this.selectedPatterns];
                // 删除老的节点
                newItems.splice(this.oldIndex, 1)
                // 在列表中目标位置增加新的节点
                newItems.splice(this.newIndex, 0, this.oldData)
                this.selectedPatterns = [...newItems];
                this.formatPattern();
            }
        },
        dragover(e) {
            e.preventDefault()
        },

        formatPattern() { // 拼接demo
            let str = '';
            this.selectedPatterns.forEach(item => {
                if (item.value == 'string') {
                    str += item.config.patternValue;
                } else if (item.value == 'date') {
                    str += moment(new Date()).format(item.config.patternValue);
                } else if (item.value == "sequence") {
                    str += item.config.flag ? item.config.flag : '';
                }
            })
            this.demoStr = str;
            console.log(str);
        },

        testFormat() {
            let str = '';
            this.currentTestPatternConfig.forEach(item => {
                if (item.value == 'string') {
                    str += item.config.patternValue;
                } else if (item.value == 'date') {
                    str += moment(new Date()).format(item.config.patternValue);
                } else if (item.value == "sequence") {
                    if (!this.NO) {
                        let min = item.config.start;
                        this.NO = Number(min);
                        str += this.paddingZero(this.NO, item.config.length);
                    } else {
                        let max = '';
                        for (let i = 0; i < item.config.length; i++) {
                            max += '9';
                        }
                        if (this.NO >= Number(max)) {
                            return;
                        } else {
                            this.NO++;
                        }
                        str += this.paddingZero(this.NO, item.config.length);
                    }
                }
            })
            return str;
        },

        paddingZero(num, length) {
            if ((num + "").length >= length) {
                return num;
            }
            return this.paddingZero("0" + num, length)
        },

        openConfig(item) { // 打开 规则配置项
            this.selectedPatterns.forEach(element => {
                element.active = false;
            });
            item.active = true;
            // if(element)
            this.currentPatternConfig = item;
            console.log(item);
        },
        /** 查询ID生成器列表 */
        getList() {
            this.loading = true;
            listIdpattern(this.queryParams).then(response => {
                this.IdpatternList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        },
        // 取消按钮
        cancel() {
            this.open = false;
            this.reset();
            console.log(this.selectedPatterns);
        },
        // 表单重置
        reset() {
            this.form = {
                id: null,
                name: null,
                category: null,
                description: null,
                patterns: null,
                createdBy: null,
                createdDate: null
            };
            this.selectedPatterns = [];
            this.currentPatternConfig = {};
            this.demoStr = '';
            this.NO = null;
            this.testList = [];
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
            this.title = "添加ID生成器";
        },
        testAdd() {
            let row = this.testFormat();
            this.testList.push(row);
            console.log(row);
        },
        handleTestOpen(row) {
            this.testOpen = true;
            this.testTitle = row.name;
            this.currentTestPatternConfig = JSON.parse(row.patterns);
            console.log(this.currentTestPatternConfig);
        },

        handlePatternAdd() { // 添加pattern
            if (this.currentSelectPattern) {
                this.currentSelectPattern.id = Math.floor(100000 + Math.random() * 900000).toString(); // 生成hash值
                this.currentSelectPattern.active = false;
                let patternConfg = this.patternConfigArr.filter(item => {
                    return item.patternType == this.currentSelectPattern.value
                })[0]; // 匹配patternConfigArr 设置默认值
                let data = JSON.parse(JSON.stringify(this.currentSelectPattern));
                data.config = patternConfg;
                console.log(this.selectedPatterns);
                this.selectedPatterns.push(data);

            }
        },

        handlePatternDelete(item) { // 删除pattern
            this.selectedPatterns.splice(item, 1);
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const id = row.id || this.ids;
            getIdpattern(id).then(response => {
                this.form = response.data;
                if (!!this.form.patterns && Array.isArray(JSON.parse(this.form.patterns))) {
                    this.selectedPatterns = JSON.parse(this.form.patterns);
                    console.log(this.selectedPatterns);
                    this.formatPattern(); // 编辑弹窗出来后，立刻显示 Demo
                } else {
                    this.selectedPatterns = [];
                }

                this.open = true;
                this.title = "修改ID生成器";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.id != null) {
                        this.selectedPatterns.forEach(item => {
                            item.active = false;
                        })
                        this.form.patterns = JSON.stringify(this.selectedPatterns);
                        updateIdpattern(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        this.form.patterns = JSON.stringify(this.selectedPatterns);
                        addIdpattern(this.form).then(response => {
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
            this.$modal.confirm('是否确认删除ID生成器编号为"' + ids + '"的数据项？').then(function () {
                return delIdpattern(ids);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => {});
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('prod/Idpattern/export', {
                ...this.queryParams
            }, `Idpattern_${new Date().getTime()}.xlsx`)
        }
    }
};
</script>
