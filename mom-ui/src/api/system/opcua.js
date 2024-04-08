import request from '@/utils/request'
import axios from "axios";

// 查询车间列表
export async function browseNode(data) {
  const rival = await axios.post('/opcua-api/opcua/browse',data);
  return rival;
}


export async function readNode(data) {
  const rival = await axios.post('/opcua-api/opcua/read',data);
  return rival;
}
