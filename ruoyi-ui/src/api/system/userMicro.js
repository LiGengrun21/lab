import request from '@/utils/request'

// 查询用户和微生物的中间列表
export function listUserMicro(query) {
  return request({
    url: '/system/userMicro/list',
    method: 'get',
    params: query
  })
}

// 查询用户和微生物的中间详细
export function getUserMicro(userId) {
  return request({
    url: '/system/userMicro/' + userId,
    method: 'get'
  })
}

// 新增用户和微生物的中间
export function addUserMicro(data) {
  return request({
    url: '/system/userMicro',
    method: 'post',
    data: data
  })
}

// 修改用户和微生物的中间
export function updateUserMicro(data) {
  return request({
    url: '/system/userMicro',
    method: 'put',
    data: data
  })
}

// 删除用户和微生物的中间
export function delUserMicro(userId) {
  return request({
    url: '/system/userMicro/' + userId,
    method: 'delete'
  })
}

// 导出用户和微生物的中间
export function exportUserMicro(query) {
  return request({
    url: '/system/userMicro/export',
    method: 'get',
    params: query
  })
}