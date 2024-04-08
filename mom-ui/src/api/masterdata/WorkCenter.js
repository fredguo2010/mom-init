import request from '@/utils/request'

// 查询工作中心列表
export function listWorkCenter(query) {
  return request({
    url: '/masterdata/WorkCenter/list',
    method: 'get',
    params: query
  })
}

// 查询工作中心详细
export function getWorkCenter(id) {
  return request({
    url: '/masterdata/WorkCenter/' + id,
    method: 'get'
  })
}

// 新增工作中心
export function addWorkCenter(data) {
  return request({
    url: '/masterdata/WorkCenter',
    method: 'post',
    data: data
  })
}

// 修改工作中心
export function updateWorkCenter(data) {
  return request({
    url: '/masterdata/WorkCenter',
    method: 'put',
    data: data
  })
}

// 删除工作中心
export function delWorkCenter(id) {
  return request({
    url: '/masterdata/WorkCenter/' + id,
    method: 'delete'
  })
}
