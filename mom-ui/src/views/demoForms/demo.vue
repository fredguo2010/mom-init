<template>
<div>
    <el-row :gutter="15">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
            <el-col :span="19">
                <el-form-item label="工单号" prop="mobile">
                    <el-input v-model="formData.field109" placeholder="请输入工单号" :style="{width: '100%'}"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="13">
                <el-form-item label="产线" prop="field106">
                    <el-select v-model="formData.field106" placeholder="请选择产线" clearable :style="{width: '100%'}">
                        <el-option v-for="(item, index) in field106Options" :key="index" :label="item.label" :value="item.value" :disabled="item.disabled"></el-option>
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="6">
                <el-form-item label="生产日期" prop="field105">
                    <el-date-picker v-model="formData.field105" format="yyyy-MM-dd" value-format="yyyy-MM-dd" :style="{width: '100%'}" placeholder="请选择生产日期" clearable></el-date-picker>
                </el-form-item>
            </el-col>
            <el-col :span="6">
                <el-form-item label="物料号" prop="field101">
                    <el-input v-model="formData.field101" placeholder="请输入物料号" clearable :style="{width: '100%'}">
                    </el-input>
                </el-form-item>
            </el-col>
            <el-col :span="9">
                <el-form-item label-width="140px" label="生产数量（吨）" prop="field102">
                    <el-input-number v-model="formData.field102" placeholder="请输入生产数量（吨）" :precision='2'>
                    </el-input-number>
                </el-form-item>
            </el-col>

            <el-table :data="tableData" style="width: 100%" :key="rederData">
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="商品名称">
                                <span>{{ props.row.name }}</span>
                            </el-form-item>
                            <el-form-item label="所属店铺">
                                <span>{{ props.row.shop }}</span>
                            </el-form-item>
                            <el-form-item label="商品 ID">
                                <span>{{ props.row.id }}</span>
                            </el-form-item>
                            <el-form-item label="店铺 ID">
                                <span>{{ props.row.shopId }}</span>
                            </el-form-item>
                            <el-form-item label="商品分类">
                                <span>{{ props.row.category }}</span>
                            </el-form-item>
                            <el-form-item label="店铺地址">
                                <span>{{ props.row.address }}</span>
                            </el-form-item>
                            <el-form-item label="商品描述">
                                <span>{{ props.row.desc }}</span>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-table-column>
                <el-table-column label="商品 ID" prop="id">
                </el-table-column>
                <el-table-column label="商品名称" prop="name">
                  <template slot-scope="props">
                        <span v-if="!props.row.isEdit">{{props.row.name}}</span>
                        <el-input v-if="props.row.isEdit" v-model="props.row.name"></el-input>
                    </template>
                </el-table-column>
                <el-table-column label="描述" prop="desc">
                  <template slot-scope="props">
                        <span v-if="!props.row.isEdit">{{props.row.desc}}</span>
                        <el-input v-if="props.row.isEdit" v-model="props.row.desc"></el-input>
                    </template>
                </el-table-column>
                <el-table-column fixed="right" label="操作" width="100">
                    <template slot-scope="scope">
                        <el-button v-if="!scope.row.isEdit" @click="handleEditClick(scope.row)" type="text" size="small">编辑</el-button>
                        <span v-if="scope.row.isEdit">
                            <el-button @click="handleOKClick(scope.row,scope.$index, tableData)" type="text" size="small">确认</el-button>
                            <el-button @click="handleCancelClick(scope.$index, tableData)" type="text" size="small">取消</el-button>
                        </span>
                    </template>
                </el-table-column>
            </el-table>

            <el-col :span="24">
                <el-form-item size="large">
                    <el-button type="primary" @click="submitForm">提交</el-button>
                    <el-button @click="resetForm">重置</el-button>
                    <el-button @click="dialogFormVisible = !dialogFormVisible">打开Dashboard</el-button>
                    
                </el-form-item>
            </el-col>
        </el-form>
    </el-row>

    <el-dialog  :visible.sync="dialogFormVisible" :fullscreen="true">
        <index></index>
    </el-dialog>
</div>
</template>

<script>
import index from '../index.vue'
export default {
    components: { index },
    props: [],
    data() {
        return {
            dialogFormVisible:false,
            editRowOrginData: {},
            rederData: false,
            formData: {
                field109: '',
                field106: undefined,
                field105: null,
                field101: undefined,
                field102: 0,
                field107: undefined,
                field108: undefined,
            },
            rules: {
                field109: [{
                    required: true,
                    message: '请输入工单号',
                    trigger: 'blur'
                }],
                field106: [{
                    required: true,
                    message: '请选择产线',
                    trigger: 'change'
                }],
                field105: [{
                    required: true,
                    message: '请选择生产日期',
                    trigger: 'change'
                }],
                field101: [{
                    required: true,
                    message: '请输入物料号',
                    trigger: 'blur'
                }],
                field102: [{
                    required: true,
                    message: '请输入生产数量（吨）',
                    trigger: 'blur'
                }],
            },
            field106Options: [{
                "label": "选项一",
                "value": 1
            }, {
                "label": "选项二",
                "value": 2
            }],
            tableData: [{
                id: '12987122',
                name: '好滋好味鸡蛋仔',
                category: '江浙小吃、小吃零食',
                desc: '荷兰优质淡奶，奶香浓而不腻',
                address: '上海市普陀区真北路',
                shop: '王小虎夫妻店',
                shopId: '10333',
                isEdit: false
            }, {
                id: '12987123',
                name: '好滋好味鸡蛋仔',
                category: '江浙小吃、小吃零食',
                desc: '荷兰优质淡奶，奶香浓而不腻',
                address: '上海市普陀区真北路',
                shop: '王小虎夫妻店',
                shopId: '10333',
                isEdit: false
            }, {
                id: '12987125',
                name: '好滋好味鸡蛋仔',
                category: '江浙小吃、小吃零食',
                desc: '荷兰优质淡奶，奶香浓而不腻',
                address: '上海市普陀区真北路',
                shop: '王小虎夫妻店',
                shopId: '10333',
                isEdit: false
            }, {
                id: '12987126',
                name: '好滋好味鸡蛋仔',
                category: '江浙小吃、小吃零食',
                desc: '荷兰优质淡奶，奶香浓而不腻',
                address: '上海市普陀区真北路',
                shop: '王小虎夫妻店',
                shopId: '10333',
                isEdit: false
            }]
        }
    },
    computed: {},
    watch: {

    },
    created() {},
    mounted() {},
    methods: {
        submitForm() {
            this.$refs['elForm'].validate(valid => {
                if (!valid) return
                // TODO 提交表单
            })
        },
        resetForm() {
            this.$refs['elForm'].resetFields()
        },
        handleClick(row) {
        },
        handleEditClick(row) {
            this.editRowOrginData = JSON.parse(JSON.stringify(row)); // 记录原始数据
            row.isEdit = true;
        },
        handleOKClick(row, index, rows) {
            rows[index] = row;
            rows[index].isEdit = false;
            this.rederData = !this.rederData;

        },
        handleCancelClick(index, rows) {
            rows[index] = this.editRowOrginData;
            rows[index].isEdit = false;
            this.rederData = !this.rederData;
        },
    }
}
</script>

<style>
</style>
