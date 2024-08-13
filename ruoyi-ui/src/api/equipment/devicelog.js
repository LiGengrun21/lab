import request from '@/utils/request'

// 查询设备运行统计列表
export function listDevicelog(query) {
  return request({
    url: '/equipment/devicelog/list',
    method: 'get',
    params: query
  })
}

export function list3HoursDevicelog(query) {
  return request({
    url: '/equipment/devicelog/3Hourlist',
    method: 'get',
    params: query
  })
}

export function list1HoursDevicelog(query) {
  return request({
    url: '/equipment/devicelog/1Hourlist',
    method: 'get',
    params: query
  })
}

export function list24HoursDevicelog(query) {
  return request({
    url: '/equipment/devicelog/24Hourlist',
    method: 'get',
    params: query
  })
}

export function listOneDayDevicelog(query,date) {
  return request({
    url: '/equipment/devicelog/oneDaylist',
    method: 'post',
    params: query,
    data:date
  })
}

// 查询设备运行统计详细
export function getDevicelog(deviceLogId) {
  return request({
    url: '/equipment/devicelog/' + deviceLogId,
    method: 'get'
  })
}

// 新增设备运行统计
export function addDevicelog(data) {
  return request({
    url: '/equipment/devicelog',
    method: 'post',
    data: data
  })
}

// 修改设备运行统计
export function updateDevicelog(data) {
  return request({
    url: '/equipment/devicelog',
    method: 'put',
    data: data
  })
}

// 删除设备运行统计
export function delDevicelog(deviceLogId) {
  return request({
    url: '/equipment/devicelog/' + deviceLogId,
    method: 'delete'
  })
}

// 导出设备运行统计
export function exportDevicelog(query) {
  return request({
    url: '/equipment/devicelog/export',
    method: 'get',
    params: query
  })
}
export function createDevicelog(data) {
  return request({
    url: '/equipment/devicelog/createNewDeviceLog',
    method: 'put',
    data: data
  })
}
