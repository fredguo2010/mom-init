import request from '@/utils/request'

// 查询车间列表
export function listArea(query) {
  return request({
    url: '/masterdata/area/list',
    method: 'get',
    params: query
  })
}

// 查询车间详细
export function getArea(id) {
  return request({
    url: '/masterdata/area/' + id,
    method: 'get'
  })
}

// 新增车间
export function addArea(data) {
  return request({
    url: '/masterdata/area',
    method: 'post',
    data: data
  })
}

// 修改车间
export function updateArea(data) {
  return request({
    url: '/masterdata/area',
    method: 'put',
    data: data
  })
}

// 删除车间
export function delArea(id) {
  return request({
    url: '/masterdata/area/' + id,
    method: 'delete'
  })
}
