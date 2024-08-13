import request from '@/utils/request'

// 查询设备实际使用记录列表
export function listUse(query) {
  return request({
    url: '/equipment/use/list',
    method: 'get',
    params: query
  })
}

// 查询设备实际使用记录详细
export function getUse(equipmentUseId) {
  return request({
    url: '/equipment/use/' + equipmentUseId,
    method: 'get'
  })
}

// 新增设备实际使用记录
export function addUse(data) {
  return request({
    url: '/equipment/use',
    method: 'post',
    data: data
  })
}

// 修改设备实际使用记录
export function updateUse(data) {
  return request({
    url: '/equipment/use',
    method: 'put',
    data: data
  })
}

// 删除设备实际使用记录
export function delUse(equipmentUseId) {
  return request({
    url: '/equipment/use/' + equipmentUseId,
    method: 'delete'
  })
}

// 导出设备实际使用记录
export function exportUse(query) {
  return request({
    url: '/equipment/use/export',
    method: 'get',
    params: query
  })
}

// 计算设备使用费用
export function costUse(equipmentUseId) {
  return request({
    url: '/equipment/use/cost/' + equipmentUseId,
    method: 'put'
  })
}
