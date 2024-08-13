import request from '@/utils/request'

// 查询危化品列表
export function listChemical(query) {
  return request({
    url: '/system/chemical/list',
    method: 'get',
    params: query
  })
}

// 查询危化品详细
export function getChemical(chemicalId) {
  return request({
    url: '/system/chemical/' + chemicalId,
    method: 'get'
  })
}

// 新增危化品
export function addChemical(data) {
  return request({
    url: '/system/chemical',
    method: 'post',
    data: data
  })
}

// 修改危化品
export function updateChemical(data) {
  return request({
    url: '/system/chemical',
    method: 'put',
    data: data
  })
}

// 删除危化品
export function delChemical(chemicalId) {
  return request({
    url: '/system/chemical/' + chemicalId,
    method: 'delete'
  })
}

// 导出危化品
export function exportChemical(query) {
  return request({
    url: '/system/chemical/export',
    method: 'get',
    params: query
  })
}