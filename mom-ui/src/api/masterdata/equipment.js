import request from '@/utils/request'

// 查询设备列表
export function listEquipment(query) {
  return request({
    url: '/masterdata/equipment/list',
    method: 'get',
    params: query
  })
}

// 查询设备详细
export function getEquipment(id) {
  return request({
    url: '/masterdata/equipment/' + id,
    method: 'get'
  })
}

// 新增设备
export function addEquipment(data) {
  return request({
    url: '/masterdata/equipment',
    method: 'post',
    data: data
  })
}

// 修改设备
export function updateEquipment(data) {
  return request({
    url: '/masterdata/equipment',
    method: 'put',
    data: data
  })
}

// 删除设备
export function delEquipment(id) {
  return request({
    url: '/masterdata/equipment/' + id,
    method: 'delete'
  })
}
