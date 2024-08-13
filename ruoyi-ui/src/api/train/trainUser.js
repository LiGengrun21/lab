import request from '@/utils/request'

// 查询培训-用户列表
export function listTrainUser(query) {
  return request({
    url: '/train/trainUser/list',
    method: 'get',
    params: query
  })
}

// 查询培训-用户详细
export function getTrainUser(trainId,userId) {
  return request({
    url: '/train/trainUser/' + trainId + "/" + userId,
    method: 'get'
  })
}

// 新增培训-用户
export function addTrainUser(data) {
  return request({
    url: '/train/trainUser/add',
    method: 'post',
    data: data
  })
}

// 修改培训-用户
export function updateTrainUser(data) {
  return request({
    url: '/train/trainUser/edit',
    method: 'put',
    data: data
  })
}

// 删除培训-用户
export function delTrainUser(trainId) {
  return request({
    url: '/train/trainUser/' + trainId,
    method: 'delete'
  })
}

export function deleteTrainUser(data) {
  return request({
    url: '/train/trainUser/delete',
    method: 'delete',
    data: data
  })
}

// 导出培训-用户
export function exportTrainUser(query) {
  return request({
    url: '/train/trainUser/export',
    method: 'get',
    params: query
  })
}
