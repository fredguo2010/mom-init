<template>
  <div class="app-container">


    <el-input v-model="rootNodeId" placeholder="请输入内容" v-if="false"></el-input>


    <el-row :gutter="20">
      <el-col :span="4" :xs="24">
        <el-tree
          :props="props"
          :load="loadNode"
          @node-click="handleNodeClick"
          lazy>
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <span style="font-size: 20px">
              <i :class="data.icon"></i> {{ data.displayNodeName }}
            </span>
          </span>
        </el-tree>
      </el-col>
      <el-col :span="20" :xs="24">
        <div style="text-align: center;height: 900px">
          <div style="font-size: 40px" v-if="selectedNodeId"><b>Tag:</b> {{selectedNodeDesc}}</div>
          <el-button type="primary" @click="handleBtnClick()"  v-if="false">Read Tag Value</el-button>
          <div  style="font-size: 40px" v-if="selectedNodeId"><b>Tag Value:</b> {{ nodeVal }}</div>
          <img src="@/assets/images/OPC-UA-Logo-Color_Large.png" class="opc_logo"></img>
        </div>

      </el-col>
    </el-row>
  </div>
</template>

<script>
import {readNode, browseNode} from "@/api/system/opcua";
export default {
  name: "index.vue",
  data() {
    return {
      nodeVal: null,
      rootNodeId: 'ns=3;s=85/0:Simulation',

      props: {
        label: 'name',
        children: 'zones',
        isLeaf: 'leaf'
      },
      selectedNodeDesc: null,
      selectedNodeId: null
    };
  },
  methods: {
    async handleNodeClick(data) {
      let nodeId = data == undefined ? this.rootNodeId : data?.nodeId;
      this.selectedNodeDesc = `${data == undefined ? "Root" : data?.displayNodeName} (${nodeId})`
      this.selectedNodeId = nodeId;
      await this.handleBtnClick()

    },

    async handleBtnClick() {
      // const result = await browseNode({"nodeId":"ns=3;s=85/0:Simulation"});
      console.log("selectedNodeId is: "+this.selectedNodeId);
      const result = await readNode({"nodeId":this.selectedNodeId});
      console.log("result is: "+result);

      this.nodeVal = result.data.value.value;
    },

    /**
     * 动态加载 OPC UA 树形结构，懒加载
     * @param node
     * @param resolve
     * @returns {Promise<*>}
     */
    async loadNode(node, resolve) {
      // console.log(node.data?.nodeId);
      // 由于自定义了node节点的数据，node下面多了data对象
      let nodeId = node.data == undefined ? this.rootNodeId : node.data?.nodeId;
      // console.log("node id to browse: "+nodeId);
      // 调用opcua服务端，查询子节点
      const response = await browseNode({"nodeId": nodeId});
      let children = [];
      // 循环处理回调对象中的子节点，组成children数组
      response.data.forEach(uanode => {
        let type;
        if(uanode.nodeId.type=="String"){
          type = "s";
        }else if(uanode.nodeId.type=="Guid"){
          type = "g";
        }else{
          type = "i";
        }
        let nodeId = "ns=" + uanode.nodeId.namespaceIndex + ";"+type+"=" + uanode.nodeId.identifier;
        //console.log(uanode.displayName.text + " : " + nodeId);
        children.push( {displayNodeName : uanode.displayName.text,
                        nodeId : nodeId,
                        icon : 'el-icon-price-tag'});
      });

      // 如果是根节点，为 Root
      if (node.level === 0) {
        return resolve([{ displayNodeName : 'Root', nodeId : this.rootNodeId, icon : 'el-icon-folder' }]);
      } else if (node.level > 1) {
        return resolve(children);
      }

      setTimeout(() => {
        // console.log(children);
        resolve(children);
      }, 500);
    }

  }
}
</script>

<style scoped>

.opc_logo{
  position: absolute;
  bottom: 0px;
  right: 0px;
  height: 60px;
}

</style>
