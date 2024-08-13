import request from '@/utils/request'

// 查询危险事故列表
export function listDanger(query) {
  return request({
    url: '/system/danger/list',
    method: 'get',
    params: query
  })
}

// 查询危险事故详细
export function getDanger(id) {
  return request({
    url: '/system/danger/' + id,
    method: 'get'
  })
}

// 新增危险事故
export function addDanger(data) {
  return request({
    url: '/system/danger',
    method: 'post',
    data: data
  })
}

// 修改危险事故
export function updateDanger(data) {
  return request({
    url: '/system/danger',
    method: 'put',
    data: data
  })
}

// 删除危险事故
export function delDanger(id) {
  return request({
    url: '/system/danger/' + id,
    method: 'delete'
  })
}

// 导出危险事故
export function exportDanger(query) {
  return request({
    url: '/system/danger/export',
    method: 'get',
    params: query
  })
}