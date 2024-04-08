import request from '@/utils/request'

// 查询工厂列表
export function listSite(query) {
  return request({
    url: '/masterdata/site/list',
    method: 'get',
    params: query
  })
}

// 查询工厂详细
export function getSite(id) {
  return request({
    url: '/masterdata/site/' + id,
    method: 'get'
  })
}

// 新增工厂
export function addSite(data) {
  return request({
    url: '/masterdata/site',
    method: 'post',
    data: data
  })
}

// 修改工厂
export function updateSite(data) {
  return request({
    url: '/masterdata/site',
    method: 'put',
    data: data
  })
}

// 删除工厂
export function delSite(id) {
  return request({
    url: '/masterdata/site/' + id,
    method: 'delete'
  })
}
