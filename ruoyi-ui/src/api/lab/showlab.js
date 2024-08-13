import request from '@/utils/request'

// 查询实验室列表列表
export function listShowlab(query) {
  return request({
    url: '/lab/showlab/list',
    method: 'get',
    params: query
  })
}

export function listShowlabEquipment(query) {
  return request({
    url: '/lab/showlab/listEquip',
    method: 'get',
    params: query
  })
}

// 查询实验室列表详细
export function getShowlab(labId) {
  return request({
    url: '/lab/showlab/' + labId,
    method: 'get'
  })
}

// 新增实验室列表
export function addShowlab(data) {
  return request({
    url: '/lab/showlab',
    method: 'post',
    data: data
  })
}

// 修改实验室列表
export function updateShowlab(data) {
  return request({
    url: '/lab/showlab',
    method: 'put',
    data: data
  })
}

// 删除实验室列表
export function delShowlab(labId) {
  return request({
    url: '/lab/showlab/' + labId,
    method: 'delete'
  })
}

// 导出实验室列表
export function exportShowlab(query) {
  return request({
    url: '/lab/showlab/export',
    method: 'get',
    params: query
  })
}
