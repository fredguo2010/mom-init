import request from '@/utils/request'

// 查询产线列表
export function listProductionline(query) {
  return request({
    url: '/masterdata/productionline/list',
    method: 'get',
    params: query
  })
}

// 查询产线详细
export function getProductionline(id) {
  return request({
    url: '/masterdata/productionline/' + id,
    method: 'get'
  })
}

// 新增产线
export function addProductionline(data) {
  return request({
    url: '/masterdata/productionline',
    method: 'post',
    data: data
  })
}

// 修改产线
export function updateProductionline(data) {
  return request({
    url: '/masterdata/productionline',
    method: 'put',
    data: data
  })
}

// 删除产线
export function delProductionline(id) {
  return request({
    url: '/masterdata/productionline/' + id,
    method: 'delete'
  })
}
