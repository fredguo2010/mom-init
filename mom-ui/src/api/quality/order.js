import request from '@/utils/request'

// 查询质量工单列表
export function listOrder(query) {
  return request({
    url: '/quality/order/list',
    method: 'get',
    params: query
  })
}

// 查询质量工单详细
export function getOrder(id) {
  return request({
    url: '/quality/order/' + id,
    method: 'get'
  })
}

// 新增质量工单
export function addOrder(data) {
  return request({
    url: '/quality/order',
    method: 'post',
    data: data
  })
}

// 修改质量工单
export function updateOrder(data) {
  return request({
    url: '/quality/order',
    method: 'put',
    data: data
  })
}

// 删除质量工单
export function delOrder(id) {
  return request({
    url: '/quality/order/' + id,
    method: 'delete'
  })
}
