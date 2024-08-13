import request from '@/utils/request'

// 查询用户和危化品中间列表
export function listUserChemical(query) {
  return request({
    url: '/system/userChemical/list',
    method: 'get',
    params: query
  })
}

// 查询用户和危化品中间详细
export function getUserChemical(userId) {
  return request({
    url: '/system/userChemical/' + userId,
    method: 'get'
  })
}

// 新增用户和危化品中间
export function addUserChemical(data) {
  return request({
    url: '/system/userChemical',
    method: 'post',
    data: data
  })
}

// 修改用户和危化品中间
export function updateUserChemical(data) {
  return request({
    url: '/system/userChemical',
    method: 'put',
    data: data
  })
}

// 删除用户和危化品中间
export function delUserChemical(userId) {
  return request({
    url: '/system/userChemical/' + userId,
    method: 'delete'
  })
}

// 导出用户和危化品中间
export function exportUserChemical(query) {
  return request({
    url: '/system/userChemical/export',
    method: 'get',
    params: query
  })
}