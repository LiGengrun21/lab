import request from '@/utils/request'

// 查询设备维保列表
export function listFix(query) {
  return request({
    url: '/equipment/fix/list',
    method: 'get',
    params: query
  })
}

// 查询设备维保详细
export function getFix(fixId) {
  return request({
    url: '/equipment/fix/' + fixId,
    method: 'get'
  })
}

// 新增设备维保
export function addFix(data) {
  return request({
    url: '/equipment/fix',
    method: 'post',
    data: data
  })
}

// 修改设备维保
export function updateFix(data) {
  return request({
    url: '/equipment/fix',
    method: 'put',
    data: data
  })
}

// 删除设备维保
export function delFix(fixId) {
  return request({
    url: '/equipment/fix/' + fixId,
    method: 'delete'
  })
}

// 导出设备维保
export function exportFix(query) {
  return request({
    url: '/equipment/fix/export',
    method: 'get',
    params: query
  })
}
/**保修审核通过
 * @param {string} piId PI的ID
 * 
 */
 export function confirmFix(data) {
  return request({
    url: '/equipment/fix/confirmFix',
    method: 'put',
    params: data
  })
}
/**保修审核未通过
 * @param {string} piId PI的ID
 * 
 */
 export function refuseFix(data) {
  return request({
    url: '/equipment/fix/refuseFix',
    method: 'delete',
    params: data
  })
}
/**修复保修
 * @param {string} piId PI的ID
 * 
 */
 export function finishFix(data) {
  return request({
    url: '/equipment/fix/finishFix',
    method: 'put',
    params: data
  })
}