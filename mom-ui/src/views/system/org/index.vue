<template>
<div class="app-container">
    <!-- <button @click="addNode">添加子</button> -->
    <el-button type="warning" icon="el-icon-circle-check" @click="isDisabled = !isDisabled" v-if="!isDisabled">保存</el-button>
    <el-button type="primary" icon="el-icon-edit-outline" @click="isDisabled = !isDisabled" v-if="isDisabled">编辑</el-button>
    <!-- <el-button type="primary" icon="el-icon-circle-plus-outline" @click="addRootNode" :disabled="isDisabled">添加工厂</el-button> -->
    <el-button type="primary" @click="dialogFormVisible = true" :disabled="isDisabled" v-if="buttonLabel!= ''">添加{{buttonLabel}}</el-button>
    <!-- <i class="fa fa-book"></i> -->
    <d3-org-chart :data="data1" @getChildId="getChildId" ref="orgChart"></d3-org-chart>

    <!-- Form -->

    <el-dialog :title="'添加'+this.buttonLabel" :visible.sync="dialogFormVisible">
        <el-form :model="form">
            <el-form-item :label="this.buttonLabel+'名称'" :label-width="formLabelWidth">
                <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="描述" :label-width="formLabelWidth">
                <el-input v-model="form.desc" autocomplete="off"></el-input>
            </el-form-item>
            <!-- <el-form-item label="活动区域" :label-width="formLabelWidth">
                <el-select v-model="form.region" placeholder="请选择活动区域">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
            </el-form-item> -->
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false; form = {}">取 消</el-button>
            <el-button type="primary" @click="submit">确 定</el-button>
        </div>
    </el-dialog>
</div>
</template>

<script>
import D3OrgChart from '@/components/D3OrgChart';
import * as d3 from 'd3';

export default {
    name: "org",
    data() {
        return {
            data1: null,
            selectedNodeId: '',
            selectedNode: null,
            selectedNodeTypeId:0,
            isDisabled: true,
            dialogFormVisible: false,
            nodeTypeArr: [{
                    type: 'root',
                    typeId: 0,
                    name: '节点',
                    icon: 'fa fa-globe'
                },
                {
                    type: 'site',
                    typeId: 1,
                    name: '工厂',
                    icon: 'fa fa-industry'
                },
                {
                    type: 'area',
                    typeId: 2,
                    name: '区域',
                    icon: 'fa fa-sitemap'
                },
                {
                    type: 'productLine',
                    typeId: 3,
                    name: '产线',
                    icon: 'fa fa-cogs'
                },
                {
                    type: 'workCenter',
                    typeId: 4,
                    name: '工作中心',
                    icon: 'fa fa-cubes'
                },
                {
                    type: 'equipment',
                    typeId: 5,
                    name: '设备',
                    icon: 'fa fa-cube'
                },
                {
                    type: 'equipment',
                    typeId: 6,
                    name: '设备',
                    icon: 'fa fa-cube'
                },
            ],
            buttonLabel: '工厂',
            form: {
                name: '',
                parentId: '',
                type: '',
                typeId: 0,
                desc: ''
            },
            formLabelWidth: '120px'
        };
    },
    components: {
        D3OrgChart
    },
    created() {

        // d3.csv(
        //     '@/assets/images/demo/data-org-demo.csv'
        // ).then(d => {
        //     console.log('fetched data');
        //     this.data = d;
        //     console.log(this.data);
        // });

    },
    mounted() {
        this.data1 = [{
                id: "root",
                typeId: 0,
                type: "root",
                parentId: "",
                name: "根节点",
                desc: "CEO",
                location: "LA Branch",
                icon: "fa fa-globe",
            },
            {
                id: 'site1',
                typeId: 1,
                type: "site",
                parentId: "root",
                name: "上海",
                desc: "COO",
                location: "LA Branch",

                icon: "fa fa-industry",
            },

            {
                id: 'site2',
                typeId: 1,
                type: "site",
                parentId: "root",
                name: "广州",
                desc: "CTO",
                location: "LA Branch",
                icon: "fa fa-industry",
            }
        ];
        console.log(this.data1);
    },
    methods: {
        getChildId(node) {
            if(node.data.id === this.selectedNodeId){
                this.$refs.orgChart.chartReference.setUpToTheRootHighlighted(node.data.id).render();
            }

            this.selectedNode = node.data;
            this.selectedNodeId = node.data.id;
            this.selectedNodeTypeId = node.data.typeId;
            console.log(node);
            if (node.data.typeId < 5) {
                this.buttonLabel = this.nodeTypeArr[node.data.typeId + 1].name;
            }else{
                this.buttonLabel = '';
            }
            console.log(node.id);

        },
        addNode() {
            console.log(this.$refs.orgChart);
            // this.data1.push({id:'child',parentId:this.id,name:'test',_centered:true})
            // this.$refs.orgChart.chartReference.render();
            let id = Math.floor(Math.random() * 1000);
            this.$refs.orgChart.chartReference.addNode({
                id: `child${id}`,
                parentId: this.selectedNodeId,
                name: 'test',
                _centered: true
            }).render();
            console.log(this.data1);
        },
        addRootNode() {
            this.$refs.orgChart.chartReference.clearHighlighting();
            this.selectedNodeId = "";
            let id = Math.floor(Math.random() * 1000);
            this.$refs.orgChart.chartReference.addNode({
                id: `site_${id}`,
                parentId: "root",
                desc: '上海工厂，坐落在',
                name: '上海工厂',
                _centered: true
            }).render();
        },

        // addNodeByType() {
        //     // this.form = null;
        //     console.log(this.form.name);
        //     this.selectedNodeId = "";
        //     let id = Math.floor(Math.random() * 1000);
        //     let nodeType = this.nodeTypeArr[this.selectedNode.typeId + 1].type; // 获取 node type, 来拼写id
        //     let childNode = {
        //         id: `${nodeType}_${id}`,
        //         parentId: this.selectedNodeId,
        //         name: this.form.name,
        //         desc: this.form.desc,
        //         _centered: true
        //     }
        //     console.log(childNode);
        //     this.$refs.orgChart.chartReference.addNode(childNode).render();
        // },

        submit() {
            console.log(this.form.name);
            let id = Math.floor(Math.random() * 1000);
            let nodeTypeId = this.selectedNode.typeId + 1;
            let nodeIcon = this.nodeTypeArr[nodeTypeId].icon
            let nodeType = this.nodeTypeArr[nodeTypeId].type; // 获取 node type, 来拼写id
            let childNode = {
                id: `${nodeType}_${id}`,
                type: nodeType,
                typeId: nodeTypeId,
                parentId: this.selectedNodeId,
                name: this.form.name,
                desc: this.form.desc,
                icon: nodeIcon,
                _centered: true
            }
            console.log(childNode);
            this.$refs.orgChart.chartReference.addNode(childNode).render();
            this.form = {};
            this.dialogFormVisible = false;

        }
    }

};
</script>
