import request from '@/utils/request'

// 查询危险事故文件列表
export function listDangerConditionFile(query) {
  return request({
    url: '/file/dangerConditionFile/list',
    method: 'get',
    params: query
  })
}

// 查询危险事故文件详细
export function getDangerConditionFile(dangerConditionFileId) {
  return request({
    url: '/file/dangerConditionFile/' + dangerConditionFileId,
    method: 'get'
  })
}

// 新增危险事故文件
export function addDangerConditionFile(data) {
  return request({
    url: '/file/dangerConditionFile',
    method: 'post',
    data: data
  })
}

// 修改危险事故文件
export function updateDangerConditionFile(data) {
  return request({
    url: '/file/dangerConditionFile',
    method: 'put',
    data: data
  })
}

// 删除危险事故文件
export function delDangerConditionFile(dangerConditionFileId) {
  return request({
    url: '/file/dangerConditionFile/' + dangerConditionFileId,
    method: 'delete'
  })
}

// 导出危险事故文件
export function exportDangerConditionFile(query) {
  return request({
    url: '/file/dangerConditionFile/export',
    method: 'get',
    params: query
  })
}