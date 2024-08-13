import request from '@/utils/request'

// 查询实验室列表列表
export function showThisWeek(query) {
  return request({
    url: '/lab/showdate/thisweek',
    method: 'get',
    params: query
  })
}
