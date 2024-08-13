import request from '@/utils/request'

// 查询设备维保附件列表
export function listFixfile(query) {
  return request({
    url: '/file/fixfile/list',
    method: 'get',
    params: query
  })
}

// 查询设备维保附件详细
export function getFixfile(fixFileId) {
  return request({
    url: '/file/fixfile/' + fixFileId,
    method: 'get'
  })
}

// 新增设备维保附件
export function addFixfile(data) {
  return request({
    url: '/file/fixfile',
    method: 'post',
    data: data
  })
}

// 修改设备维保附件
export function updateFixfile(data) {
  return request({
    url: '/file/fixfile',
    method: 'put',
    data: data
  })
}

// 删除设备维保附件
export function delFixfile(fixFileId) {
  return request({
    url: '/file/fixfile/' + fixFileId,
    method: 'delete'
  })
}

// 导出设备维保附件
export function exportFixfile(query) {
  return request({
    url: '/file/fixfile/export',
    method: 'get',
    params: query
  })
}