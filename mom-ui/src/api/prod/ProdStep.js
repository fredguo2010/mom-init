import request from '@/utils/request'

// 查询工序管理列表
export function listProdStep(query) {
  return request({
    url: '/prod/ProdStep/list',
    method: 'get',
    params: query
  })
}

// 查询工序管理详细
export function getProdStep(id) {
  return request({
    url: '/prod/ProdStep/' + id,
    method: 'get'
  })
}

// 新增工序管理
export function addProdStep(data) {
  return request({
    url: '/prod/ProdStep',
    method: 'post',
    data: data
  })
}

// 修改工序管理
export function updateProdStep(data) {
  return request({
    url: '/prod/ProdStep',
    method: 'put',
    data: data
  })
}

// 删除工序管理
export function delProdStep(id) {
  return request({
    url: '/prod/ProdStep/' + id,
    method: 'delete'
  })
}
