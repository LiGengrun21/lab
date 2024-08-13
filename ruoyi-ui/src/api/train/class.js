import request from '@/utils/request'

// 查询课程列表
export function listClass(query) {
  return request({
    url: '/train/class/list',
    method: 'get',
    params: query
  })
}

// 查询课程详细
export function getClass(classId) {
  return request({
    url: '/train/class/' + classId,
    method: 'get'
  })
}

// 新增课程
export function addClass(data) {
  return request({
    url: '/train/class',
    method: 'post',
    data: data
  })
}

// 修改课程
export function updateClass(data) {
  return request({
    url: '/train/class',
    method: 'put',
    data: data
  })
}

// 删除课程
export function delClass(classId) {
  return request({
    url: '/train/class/' + classId,
    method: 'delete'
  })
}

// 导出课程
export function exportClass(query) {
  return request({
    url: '/train/class/export',
    method: 'get',
    params: query
  })
}