import request from '@/utils/request'

// 查询出入库日志列表
export function listLog(query) {
  return request({
    url: '/goods/log/list',
    method: 'get',
    params: query
  })
}

// 查询出入库日志详细
export function getLog(goodsLogId) {
  return request({
    url: '/goods/log/' + goodsLogId,
    method: 'get'
  })
}

// 新增出入库日志
export function addLog(data) {
  return request({
    url: '/goods/log',
    method: 'post',
    data: data
  })
}

// 修改出入库日志
export function updateLog(data) {
  return request({
    url: '/goods/log',
    method: 'put',
    data: data
  })
}

// 删除出入库日志
export function delLog(goodsLogId) {
  return request({
    url: '/goods/log/' + goodsLogId,
    method: 'delete'
  })
}

// 导出出入库日志
export function exportLog(query) {
  return request({
    url: '/goods/log/export',
    method: 'get',
    params: query
  })
}