<template>
<div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts';
require('echarts/theme/macarons') // echarts theme

export default {
    name: "spcChart",
    props: {
        className: {
            type: String,
            default: 'chart'
        },
        width: {
            type: String,
            default: '100%'
        },
        height: {
            type: String,
            default: '150px'
        },
        autoResize: {
            type: Boolean,
            default: true
        },
        chartData: {
            type: Object,
            required: true
        }

    },
    data() {
        return {
            chart: null
        }
    },
    watch: {
        chartData: {
            deep: true,
            handler(val) {
                this.setOptions(val)
            }
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.initChart()
        })
    },
    beforeDestroy() {
        if (!this.chart) {
            return
        }
        this.chart.dispose()
        this.chart = null
    },
    methods: {
        initChart() {
            this.chart = echarts.init(this.$el, 'macarons');
            this.setOptions(this.chartData);
        },
      setOptions({ xAxisData, inspectionValues, uclData, lclData,avgData } = {}) {
        this.chart.setOption({
          color: ['#5470c6', 'green', 'red', 'purple', 'yellow'],
          xAxis: {
            data: xAxisData,
            boundaryGap: false,
            axisTick: {
              show: false
            }
          },
          // grid: {
          //   left: 10,
          //   right: 10,
          //   bottom: 20,
          //   top: 30,
          //   containLabel: true
          // },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross'
            },
            padding: [5, 10]
          },
          yAxis: {
            axisTick: {
              show: false
            }
          },
          legend: {
            data: ['iData', 'UCL', 'LCL','AVG']
          },
          series: [
            {
              name: "Individual Data",
              data: inspectionValues,
              type: 'line',
              markPoint: {
                data: [
                  {type: 'max', name: 'Max'},
                  {type: 'min', name: 'Min'}
                ]
              }
            },
            {
              name: 'iData',
              smooth: true,
              type: 'line',
              itemStyle: {
                normal: {
                  color: '#3888fa',
                  lineStyle: {
                    color: '#3888fa',
                    width: 2
                  },
                  areaStyle: {
                    color: '#f3f8ff'
                  }
                }
              },
              data: inspectionValues,
              animationDuration: 2800,
              animationEasing: 'quadraticOut',
              endLabel: {
                show:true,
                formatter: '{a}: {c}'
              }
            },
            {
              name: "UCL", data: uclData,
              type: 'line', symbol: 'none',
              lineStyle: {
                type: 'dashed'
              },
              endLabel: {
                show:true,
                formatter: '{a}: {c}'
              }
            }
            , {
              name: "LCL", data: lclData,
              type: 'line', symbol: 'none',
              lineStyle: {
                type: 'dashed'
              },
              endLabel: {
                show:true,
                formatter: '{a}: {c}'
              }
            }, {
              name: "AVG", data: avgData,
              type: 'line', symbol: 'none',
              lineStyle: {
                type: 'dashed'
              },
              endLabel: {
                show:true,
                formatter: '{a}: {c}'
              }
            }
            ]
        })
      }
    }
}
</script>
