<template>
<div class="app-container home" style="text-align: center">
    <!-- <img src="@/assets/images/Rockwell_Automation_logo.png" height="200px"> -->

    <el-row :gutter="30">
        <el-col :span="6" v-for="(data,idx) in seasonData" :key="idx">
            <div class="grid-content bg-purple">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>{{data.headerTitle}}</span>
                        <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
                    </div>
                    <bar-chart :chartData="{}"></bar-chart>
                </el-card>
            </div>
        </el-col>

    </el-row>
    <el-row :gutter="30" style="margin-top:30px">
        <el-col :span="6" v-for="(data,idx) in seasonElcData" :key="idx">
            <div class="grid-content bg-purple">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>{{data.headerTitle}}</span>
                        <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
                    </div>
                    <line-chart :chartData="LineData"></line-chart>
                </el-card>
            </div>
        </el-col>

    </el-row>

    <el-row :gutter="30" style="margin-top:30px">
        <el-col :span="6" v-for="(data,idx) in seasonCostData" :key="idx">
            <div class="grid-content bg-purple">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>{{data.headerTitle}}</span>
                        <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
                    </div>
                    <raddar-chart :chartData="{}"></raddar-chart>
                </el-card>
            </div>
        </el-col>

    </el-row>
</div>
</template>

<script>
import BarChart from './dashboard/BarChart.vue';
import LineChart from './dashboard/LineChart.vue';
import RaddarChart from './dashboard/RaddarChart.vue'
export default {
    name: "Index",
    components: {
        BarChart,
        LineChart,
        RaddarChart
    },
    data() {
        return {
            // 版本号
            version: "3.8.6",
            seasonData: [{
                    headerTitle: 'Q1 产量'
                },
                {
                    headerTitle: 'Q2 产量'
                },
                {
                    headerTitle: 'Q3 产量'
                },
                {
                    headerTitle: 'Q4 产量'
                }
            ],
            seasonElcData: [{
                    headerTitle: 'Q1 耗电量'
                },
                {
                    headerTitle: 'Q2 耗电量'
                },
                {
                    headerTitle: 'Q3 耗电量'
                },
                {
                    headerTitle: 'Q4 耗电量'
                }
            ],
            seasonCostData: [{
                    headerTitle: 'Q1 成本'
                },
                {
                    headerTitle: 'Q2 成本'
                },
                {
                    headerTitle: 'Q3 成本'
                },
                {
                    headerTitle: 'Q4 成本'
                }
            ],
            LineData: {
                xAxisData :['2023-01','2023-02','2023-03',],
                expectedData:['1000000','122222','130000'], 
                actualData:['122222','130000','1000000',]
            }
        };
    },
    methods: {
        goTarget(href) {
            window.open(href, "_blank");
        }
    }
};
</script>

<style lang="scss" scoped>
.home {
    blockquote {
        padding: 10px 20px;
        margin: 0 0 20px;
        font-size: 17.5px;
        border-left: 5px solid #eee;
    }

    hr {
        margin-top: 20px;
        margin-bottom: 20px;
        border: 0;
        border-top: 1px solid #eee;
    }

    .col-item {
        margin-bottom: 20px;
    }

    ul {
        padding: 0;
        margin: 0;
    }

    font-family: "open sans",
    "Helvetica Neue",
    Helvetica,
    Arial,
    sans-serif;
    font-size: 13px;
    color: #676a6c;
    overflow-x: hidden;

    ul {
        list-style-type: none;
    }

    h4 {
        margin-top: 0px;
    }

    h2 {
        margin-top: 10px;
        font-size: 26px;
        font-weight: 100;
    }

    p {
        margin-top: 10px;

        b {
            font-weight: 700;
        }
    }

    .update-log {
        ol {
            display: block;
            list-style-type: decimal;
            margin-block-start: 1em;
            margin-block-end: 1em;
            margin-inline-start: 0;
            margin-inline-end: 0;
            padding-inline-start: 40px;
        }
    }
}
</style>
