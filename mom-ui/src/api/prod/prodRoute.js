import request from '@/utils/request'

// 查询工艺路线列表
export function listProdRoute(query) {
  return request({
    url: '/prod/prodRoute/list',
    method: 'get',
    params: query
  })
}

// 查询工艺路线详细
export function getProdRoute(id) {
  return request({
    url: '/prod/prodRoute/' + id,
    method: 'get'
  })
}

// 新增工艺路线
export function addProdRoute(data) {
  return request({
    url: '/prod/prodRoute',
    method: 'post',
    data: data
  })
}

// 修改工艺路线
export function updateProdRoute(data) {
  return request({
    url: '/prod/prodRoute',
    method: 'put',
    data: data
  })
}

// 删除工艺路线
export function delProdRoute(id) {
  return request({
    url: '/prod/prodRoute/' + id,
    method: 'delete'
  })
}
