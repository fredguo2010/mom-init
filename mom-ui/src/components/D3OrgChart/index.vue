<template>
<div class="app-container">
    <div ref="svgElementContainer"></div>
</div>
</template>
<script>
import {
    OrgChart
} from 'd3-org-chart';

export default {
    name: 'D3OrgChart',
    props: ['data'],
    data() {
        return {
            chartReference: null
        };
    },
    watch: {
        data(value) {
            setTimeout(() => {
                this.renderChart(value);
            }, 1000);
        }
    },
    created() {},
    methods: {
        renderChart(data) {
            if (!this.chartReference) {
                this.chartReference = new OrgChart();
            }
            this.chartReference
                .container(this.$refs.svgElementContainer) // node or css selector
                .data(data)
                .nodeWidth(d => 200)
                .nodeHeight(d => 120)
                .onNodeClick(d => {
                    // console.dir(d);
                    this.chartReference.clearHighlighting();
                    this.chartReference.setHighlighted(d.id).render();
                    this.changeChild(d);
                    // 可以增加双击 激活整条线
                })

                .nodeContent(function (d, i, arr, state) {
                    // console.log(require(d.data.imageUrl))
                    const color = '#FFFFFF';
                    return `
            <div style="font-family: 'Inter', sans-serif;background-color:${color}; position:absolute;margin-top:-1px; margin-left:-1px;width:${d.width}px;height:${d.height}px;border-radius:10px;border: 1px solid #E4E2E9">
               <div style="background-color:${color};position:absolute;margin-top:-25px;margin-left:${15}px;border-radius:100px;width:50px;height:50px;" ></div>
               <span class = "${d.data.icon? d.data.icon:''}"
               style="font-size:20px;position:absolute;margin-top:-20px;margin-left:${20}px;border-radius:100px;width:40px;height:40px;" ></span>

              <div style="color:#08011E;position:absolute;right:20px;top:17px;font-size:10px;"></div>

              <div style="font-size:15px;color:#08011E;margin-left:20px;margin-top:32px"> ${
                d.data.name
              } </div>
              <div style="color:#716E7B;margin-left:20px;margin-top:3px;font-size:10px;"> ${
                d.data.desc
              } </div>
           </div>
  `;
                })
                .render();
        },
        changeChild(node) {
            this.$emit('getChildId',node)
        }
    }
};
</script>
