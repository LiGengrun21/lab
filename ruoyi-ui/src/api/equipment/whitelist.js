import request from '@/utils/request'

// 查询设备白名单列表
export function listWhitelist(query) {
  return request({
    url: '/equipment/whitelist/list',
    method: 'get',
    params: query
  })
}

// 查询设备白名单详细
export function getWhitelist(equipmentId) {
  return request({
    url: '/equipment/whitelist/' + equipmentId,
    method: 'get'
  })
}

// 新增设备白名单
export function addWhitelist(data) {
  return request({
    url: '/equipment/whitelist',
    method: 'post',
    data: data
  })
}

// 修改设备白名单
export function updateWhitelist(data) {
  return request({
    url: '/equipment/whitelist',
    method: 'put',
    data: data
  })
}

// 删除设备白名单
export function delWhitelist(equipmentId) {
  return request({
    url: '/equipment/whitelist/batchdelete/',
    method: 'delete',
    data:equipmentId
  })
}

// 删除设备白名单
export function deleteWhitelist(data) {
  return request({
    url: '/equipment/whitelist/delete',
    method: 'delete',
    data: data,
  })
}

// 导出设备白名单
export function exportWhitelist(query) {
  return request({
    url: '/equipment/whitelist/export',
    method: 'get',
    params: query
  })
}
/** 批量增加白名单
 * @param {Array}   List
 *    @param {Object}     //子内容  
 *        @param  {String}     equipmentId            
 *        @param  {String}      userId:
 *                             
 */
 export function  AddWhites(data) {
  return request({
    url: '/equipment/whitelist/batchAdd',
    method: 'post',
    data: data
  })
}

/** 批量删除白名单
 * @param {Array}   List
 *    @param {Object}     //子内容  
 *        @param  {String}     equipmentId            
 *        @param  {String}      userId:
 *                             
 */
 export function DeleteWhites(data) {
  return request({
    url: '/equipment/whitelist/batchdelete',
    method: 'post',
    data: data
  })
}