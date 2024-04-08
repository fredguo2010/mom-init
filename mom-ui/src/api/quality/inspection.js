import request from '@/utils/request'

// 查询质量检测列表
export function listInspection(query) {
  return request({
    url: '/quality/inspection/list',
    method: 'get',
    params: query
  })
}

// 查询质量检测列表
export function listAllInspection() {
  return request({
    url: '/quality/inspection/listAll',
    method: 'get'
  })
}

// 获得SPC数据
export function getSpcData() {
  return request({
    url: '/quality/inspection/spcData',
    method: 'get'
  })
}

// 查询质量检测详细
export function getInspection(id) {
  return request({
    url: '/quality/inspection/' + id,
    method: 'get'
  })
}

// 新增质量检测
export function addInspection(data) {
  return request({
    url: '/quality/inspection',
    method: 'post',
    data: data
  })
}

// 修改质量检测
export function updateInspection(data) {
  return request({
    url: '/quality/inspection',
    method: 'put',
    data: data
  })
}

// 删除质量检测
export function delInspection(id) {
  return request({
    url: '/quality/inspection/' + id,
    method: 'delete'
  })
}
