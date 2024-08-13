import request from '@/utils/request'

// 查询设备报废文件列表
export function listDestoryfile(query) {
  return request({
    url: '/file/destoryfile/list',
    method: 'get',
    params: query
  })
}

// 查询设备报废文件详细
export function getDestoryfile(destoryFileId) {
  return request({
    url: '/file/destoryfile/' + destoryFileId,
    method: 'get'
  })
}

// 新增设备报废文件
export function addDestoryfile(data) {
  return request({
    url: '/file/destoryfile',
    method: 'post',
    data: data
  })
}

// 修改设备报废文件
export function updateDestoryfile(data) {
  return request({
    url: '/file/destoryfile',
    method: 'put',
    data: data
  })
}

// 删除设备报废文件
export function delDestoryfile(destoryFileId) {
  return request({
    url: '/file/destoryfile/' + destoryFileId,
    method: 'delete'
  })
}

// 导出设备报废文件
export function exportDestoryfile(query) {
  return request({
    url: '/file/destoryfile/export',
    method: 'get',
    params: query
  })
}