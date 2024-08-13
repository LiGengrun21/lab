import request from '@/utils/request'

// 查询人类样本列表
export function listSample(query) {
  return request({
    url: '/system/sample/list',
    method: 'get',
    params: query
  })
}

// 查询人类样本详细
export function getSample(sampleId) {
  return request({
    url: '/system/sample/' + sampleId,
    method: 'get'
  })
}

// 新增人类样本
export function addSample(data) {
  return request({
    url: '/system/sample',
    method: 'post',
    data: data
  })
}

// 修改人类样本
export function updateSample(data) {
  return request({
    url: '/system/sample',
    method: 'put',
    data: data
  })
}

// 删除人类样本
export function delSample(sampleId) {
  return request({
    url: '/system/sample/' + sampleId,
    method: 'delete'
  })
}

// 导出人类样本
export function exportSample(query) {
  return request({
    url: '/system/sample/export',
    method: 'get',
    params: query
  })
}