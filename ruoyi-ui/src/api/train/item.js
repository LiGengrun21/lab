import request from '@/utils/request'

// 查询培训列表
export function listItem(query) {
  return request({
    url: '/train/item/list',
    method: 'get',
    params: query
  })
}

// 查询培训详细
export function getItem(trainId) {
  return request({
    url: '/train/item/' + trainId,
    method: 'get'
  })
}

export function getTrainAndUser(trainId) {
  return request({
    url: '/train/item/trainAndUser/' + trainId,
    method: 'get'
  })
}

// 新增培训
export function addItem(data) {
  return request({
    url: '/train/item',
    method: 'post',
    data: data
  })
}

// 修改培训
export function updateItem(data) {
  return request({
    url: '/train/item',
    method: 'put',
    data: data
  })
}

// 删除培训
export function delItem(trainId) {
  return request({
    url: '/train/item/' + trainId,
    method: 'delete'
  })
}

// 导出培训
export function exportItem(query) {
  return request({
    url: '/train/item/export',
    method: 'get',
    params: query
  })
}
