import request from '@/utils/request'

// 查询微生物列表
export function listMicro(query) {
  return request({
    url: '/system/micro/list',
    method: 'get',
    params: query
  })
}

// 查询微生物详细
export function getMicro(microId) {
  return request({
    url: '/system/micro/' + microId,
    method: 'get'
  })
}

// 新增微生物
export function addMicro(data) {
  return request({
    url: '/system/micro',
    method: 'post',
    data: data
  })
}

// 修改微生物
export function updateMicro(data) {
  return request({
    url: '/system/micro',
    method: 'put',
    data: data
  })
}

// 删除微生物
export function delMicro(microId) {
  return request({
    url: '/system/micro/' + microId,
    method: 'delete'
  })
}

// 导出微生物
export function exportMicro(query) {
  return request({
    url: '/system/micro/export',
    method: 'get',
    params: query
  })
}