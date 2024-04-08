import request from '@/utils/request'

// 查询物料列表
export function listPart(query) {
  return request({
    url: '/masterdata/part/list',
    method: 'get',
    params: query
  })
}

// 查询物料详细
export function getPart(id) {
  return request({
    url: '/masterdata/part/' + id,
    method: 'get'
  })
}

// 新增物料
export function addPart(data) {
  return request({
    url: '/masterdata/part',
    method: 'post',
    data: data
  })
}

// 修改物料
export function updatePart(data) {
  return request({
    url: '/masterdata/part',
    method: 'put',
    data: data
  })
}

// 删除物料
export function delPart(id) {
  return request({
    url: '/masterdata/part/' + id,
    method: 'delete'
  })
}
