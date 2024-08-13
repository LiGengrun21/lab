import request from '@/utils/request'

// 查询值班列表
export function listWork(query) {
  return request({
    url: '/system/work/list',
    method: 'get',
    params: query
  })
}

// 查询值班详细
export function getWork(workId) {
  return request({
    url: '/system/work/' + workId,
    method: 'get'
  })
}

// 新增值班
export function addWork(data) {
  return request({
    url: '/system/work',
    method: 'post',
    data: data
  })
}

// 修改值班
export function updateWork(data) {
  return request({
    url: '/system/work',
    method: 'put',
    data: data
  })
}

// 删除值班
export function delWork(workId) {
  return request({
    url: '/system/work/' + workId,
    method: 'delete'
  })
}

// 导出值班
export function exportWork(query) {
  return request({
    url: '/system/work/export',
    method: 'get',
    params: query
  })
}

// 下载用户导入模板
export function importTemplate() {
  return request({
    url: '/system/work/importTemplate',
    method: 'get'
  })
}
