import request from '@/utils/request'

// 查询用户和人类样本中间列表
export function listUserSample(query) {
  return request({
    url: '/system/userSample/list',
    method: 'get',
    params: query
  })
}

// 查询用户和人类样本中间详细
export function getUserSample(userId) {
  return request({
    url: '/system/userSample/' + userId,
    method: 'get'
  })
}

// 新增用户和人类样本中间
export function addUserSample(data) {
  return request({
    url: '/system/userSample',
    method: 'post',
    data: data
  })
}

// 修改用户和人类样本中间
export function updateUserSample(data) {
  return request({
    url: '/system/userSample',
    method: 'put',
    data: data
  })
}

// 删除用户和人类样本中间
export function delUserSample(userId) {
  return request({
    url: '/system/userSample/' + userId,
    method: 'delete'
  })
}

// 导出用户和人类样本中间
export function exportUserSample(query) {
  return request({
    url: '/system/userSample/export',
    method: 'get',
    params: query
  })
}