import request from '@/utils/request'

// 查询实验室预约列表
export function listlabSubscribe(query) {
  return request({
    url: '/lab/subscribe/list',
    method: 'get',
    params: query
  })
}

export function listlabAuditSubscribe(query) {
  return request({
    url: '/lab/subscribe/auditList',
    method: 'get',
    params: query
  })
}

// 查询实验室预约详细
export function getSubscribe(labSubscribeId) {
  return request({
    url: '/lab/subscribe/' + labSubscribeId,
    method: 'get'
  })
}

// 新增实验室预约
export function addSubscribe(data) {
  return request({
    url: '/lab/subscribe',
    method: 'post',
    data: data
  })
}

// 修改实验室预约
export function updateSubscribe(data) {
  return request({
    url: '/lab/subscribe',
    method: 'put',
    data: data
  })
}

// 删除实验室预约
export function delSubscribe(labSubscribeId) {
  return request({
    url: '/lab/subscribe/' + labSubscribeId,
    method: 'delete'
  })
}

// 导出实验室预约
export function exportSubscribe(query) {
  return request({
    url: '/lab/subscribe/export',
    method: 'get',
    params: query
  })
}
