import request from '@/utils/request'

// 查询批次管理列表
export function listLot(query) {
  return request({
    url: '/prod/lot/list',
    method: 'get',
    params: query
  })
}

// 查询批次管理详细
export function getLot(id) {
  return request({
    url: '/prod/lot/' + id,
    method: 'get'
  })
}

// 新增批次管理
export function addLot(data) {
  return request({
    url: '/prod/lot',
    method: 'post',
    data: data
  })
}

// 修改批次管理
export function updateLot(data) {
  return request({
    url: '/prod/lot',
    method: 'put',
    data: data
  })
}

// 删除批次管理
export function delLot(id) {
  return request({
    url: '/prod/lot/' + id,
    method: 'delete'
  })
}
