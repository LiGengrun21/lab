import request from '@/utils/request'

// 查询设备报警记录列表
export function listWarning(query) {
  return request({
    url: '/equipment/warning/list',
    method: 'get',
    params: query
  })
}

// 查询设备报警记录详细
export function getWarning(warningId) {
  return request({
    url: '/equipment/warning/' + warningId,
    method: 'get'
  })
}

// 新增设备报警记录
export function addWarning(data) {
  return request({
    url: '/equipment/warning',
    method: 'post',
    data: data
  })
}

// 修改设备报警记录
export function updateWarning(data) {
  return request({
    url: '/equipment/warning',
    method: 'put',
    data: data
  })
}

// 删除设备报警记录
export function delWarning(warningId) {
  return request({
    url: '/equipment/warning/' + warningId,
    method: 'delete'
  })
}

// 导出设备报警记录
export function exportWarning(query) {
  return request({
    url: '/equipment/warning/export',
    method: 'get',
    params: query
  })
}