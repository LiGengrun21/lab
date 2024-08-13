import request from '@/utils/request'

// 查询设备列表
export function listShow(query) {
  return request({
    url: '/equipment/show/list',
    method: 'get',
    params: query
  })
}
// 查询可预约设备列表
export function listShowSubscribe(query) {
  return request({
    url: '/equipment/show/listSubscribe',
    method: 'get',
    params: query
  })
}

// 查询设备详细
export function getShow(equipmentId) {
  return request({
    url: '/equipment/show/' + equipmentId,
    method: 'get'
  })
}

// 查询设备白名单
export function getWhiteShow(equipmentId) {
  return request({
    url: '/equipment/show/whitelist/'+ equipmentId,
    method: 'get'
  })
}

// 新增设备
export function addShow(data) {
  return request({
    url: '/equipment/show',
    method: 'post',
    data: data
  })
}

// 修改设备
export function updateShow(data) {
  return request({
    url: '/equipment/show',
    method: 'put',
    data: data
  })
}

// 删除设备
export function delShow(equipmentId) {
  return request({
    url: '/equipment/show/' + equipmentId,
    method: 'delete'
  })
}

// 导出设备
export function exportShow(query) {
  return request({
    url: '/equipment/show/export',
    method: 'get',
    params: query
  })
}

//查询插座状态
export function getDeviceStatus(deviceId) {
  return request({
    url: '/equipment/show/getDeviceStatus/' + deviceId,
    method: 'get',
  })
}
//查询插座信息
export function getByDeviceId(deviceId) {
  return request({
    url: '/equipment/show/getById/' + deviceId,
    method: 'get',
  })
}
//打开插座
export function switchOn(deviceId) {
  return request({
    url: '/equipment/show/switchon/' + deviceId,
    method: 'post',
  })
}
//关闭插座
export function switchOff(deviceId) {
  return request({
    url: '/equipment/show/switchoff/' + deviceId,
    method: 'post',
  })
}
