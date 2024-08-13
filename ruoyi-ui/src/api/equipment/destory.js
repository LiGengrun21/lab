import request from '@/utils/request'

// 查询设备报废列表
export function listDestory(query) {
  return request({
    url: '/equipment/destory/list',
    method: 'get',
    params: query
  })
}

// 查询设备报废详细
export function getDestory(destoryId) {
  return request({
    url: '/equipment/destory/' + destoryId,
    method: 'get'
  })
}

// 新增设备报废
export function addDestory(data) {
  return request({
    url: '/equipment/destory',
    method: 'post',
    data: data
  })
}

// 修改设备报废
export function updateDestory(data) {
  return request({
    url: '/equipment/destory',
    method: 'put',
    data: data
  })
}

// 删除设备报废
export function delDestory(destoryId) {
  return request({
    url: '/equipment/destory/' + destoryId,
    method: 'delete'
  })
}

// 导出设备报废
export function exportDestory(query) {
  return request({
    url: '/equipment/destory/export',
    method: 'get',
    params: query
  })
}