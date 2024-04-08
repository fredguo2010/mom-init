import request from '@/utils/request'

// 查询操作手册列表
export function listPreview(query) {
  return request({
    url: '/prod/preview/list',
    method: 'get',
    params: query
  })
}

// 查询操作手册详细
export function getPreview(id) {
  return request({
    url: '/prod/preview/' + id,
    method: 'get'
  })
}

// 新增操作手册
export function addPreview(data) {
  return request({
    url: '/prod/preview',
    method: 'post',
    data: data
  })
}

// 修改操作手册
export function updatePreview(data) {
  return request({
    url: '/prod/preview',
    method: 'put',
    data: data
  })
}

// 删除操作手册
export function delPreview(id) {
  return request({
    url: '/prod/preview/' + id,
    method: 'delete'
  })
}
