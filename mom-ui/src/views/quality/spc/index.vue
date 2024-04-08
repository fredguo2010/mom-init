<script>


import spcChart from "@/views/quality/spc/spcChart.vue";

export default {
  name: 'hello',
  data () {
    return {
      newVisitis: {
        xAxisData: ['1', '2', '3', '4', '5', '6', '7'],
        actualData: [1280, 82, 91, 154, 1625, 140, 1450]
      }
    }
  },
  components: {
    spcChart
  },
  mounted(){
  },
  methods: {
    test(){
      this.newVisitis.xAxisData.push(8);
      this.newVisitis.actualData.push(Math.random()*1000);
    }
}}
</script>

<template>
  <div class="app-container">
    <spcChart  :chartData="newVisitis" :height="'500px'" />
    <el-button @click="test" v-text="'add data'"></el-button>

  </div>


</template>

<style scoped lang="scss">
</style>
