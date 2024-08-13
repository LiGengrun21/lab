import request from '@/utils/request'

// 查询设备收费列表
export function listCost(query) {
  return request({
    url: '/equipment/cost/list',
    method: 'get',
    params: query
  })
}

// 查询设备收费详细
export function getCost(costId) {
  return request({
    url: '/equipment/cost/' + costId,
    method: 'get'
  })
}

// 新增设备收费
export function addCost(data) {
  return request({
    url: '/equipment/cost',
    method: 'post',
    data: data
  })
}

// 修改设备收费
export function updateCost(data) {
  return request({
    url: '/equipment/cost',
    method: 'put',
    data: data
  })
}

// 删除设备收费
export function delCost(costId) {
  return request({
    url: '/equipment/cost/' + costId,
    method: 'delete'
  })
}

// 导出设备收费
export function exportCost(query) {
  return request({
    url: '/equipment/cost/export',
    method: 'get',
    params: query
  })
}

/**  查询用户收费的接口组
 * 获取所有pi的设备收费
 *  
 */
 export function getSubscribesByPiUni( date) {
  return request({
    url: '/equipment/subscribe/getSubscribesByPiUnit',
    method: 'get',
    date: date
  })
}
/**
 *查询pi下的所有成员的消费
 * @param {Interger}  userId(PI的用户名)
 */
 export function getPiMembers(query) {
  return request({
    url: '/equipment/subscribe/getSubscribesOfPiAndMembers',
    method: 'get',
    params: query
  })
}                                         
/**获取设备的收费类型
 * @param {String }  equmentID
 */
 export function getCostType(query) {
  return request({
    url: '/equipment/cost/getCostTypesByEquipmentId',
    method: 'get',
    params: query
  })
}
/**
 * 
 */

export function getpiTotal(date){
 return request({
  url: '/equipment/subscribe/getSubscribesStatisticByPiId',
  method: 'get',
  params: date
})
}
/**获取指定成员的预约信息  
 * @param {String } userId
 */
export function getUseCost(date){
  return request({
   url: '/equipment/subscribe/getSubscribesByUserId',
   method: 'get',
   params: date
 })
 }

