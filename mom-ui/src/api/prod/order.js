import request from '@/utils/request'

// 查询工单管理列表
export function listOrder(query) {
  return request({
    url: '/prod/order/list',
    method: 'get',
    params: query
  })
}

// 查询工单管理详细
export function getOrder(id) {
  return request({
    url: '/prod/order/' + id,
    method: 'get'
  })
}

// 新增工单管理
export function addOrder(data) {
  return request({
    url: '/prod/order',
    method: 'post',
    data: data
  })
}

// 修改工单管理
export function updateOrder(data) {
  return request({
    url: '/prod/order',
    method: 'put',
    data: data
  })
}

// 删除工单管理
export function delOrder(id) {
  return request({
    url: '/prod/order/' + id,
    method: 'delete'
  })
}
