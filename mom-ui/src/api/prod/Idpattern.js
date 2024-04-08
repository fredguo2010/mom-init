import request from '@/utils/request'

// 查询ID生成器列表
export function listIdpattern(query) {
  return request({
    url: '/prod/Idpattern/list',
    method: 'get',
    params: query
  })
}

// 查询ID生成器详细
export function getIdpattern(id) {
  return request({
    url: '/prod/Idpattern/' + id,
    method: 'get'
  })
}

// 新增ID生成器
export function addIdpattern(data) {
  return request({
    url: '/prod/Idpattern',
    method: 'post',
    data: data
  })
}

// 修改ID生成器
export function updateIdpattern(data) {
  return request({
    url: '/prod/Idpattern',
    method: 'put',
    data: data
  })
}

// 删除ID生成器
export function delIdpattern(id) {
  return request({
    url: '/prod/Idpattern/' + id,
    method: 'delete'
  })
}
