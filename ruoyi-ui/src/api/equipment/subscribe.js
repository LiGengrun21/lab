import request from '@/utils/request'

// 查询设备预约列表
export function listSubscribe(query) {
  return request({
    url: '/equipment/subscribe/list',
    method: 'get',
    params: query
  })
}

// 查询用户设备预约列表
export function listMySubscribe(query) {
  return request({
    url: '/equipment/subscribe/mylist',
    method: 'get',
    params: query
  })
}

// 查询审核设备预约列表
export function listAuditSubscribe(query) {
  return request({
    url: '/equipment/subscribe/audit',
    method: 'get',
    params: query
  })
}

// 查询用户预约中设备预约列表
export function listTBDSubscribe(query) {
  return request({
    url: '/equipment/subscribe/TBDlist',
    method: 'get',
    params: query
  })
}

// 查询设备预约详细
export function getSubscribe(equipmentSubscribeId) {
  return request({
    url: '/equipment/subscribe/' + equipmentSubscribeId,
    method: 'get'
  })
}

// 新增设备预约
export function addSubscribe(data) {
  return request({
    url: '/equipment/subscribe/add',
    method: 'post',
    data: data
  })
}

// 修改设备预约
export function updateSubscribe(data) {
  return request({
    url: '/equipment/subscribe',
    method: 'put',
    data: data
  })
}

// 删除设备预约
export function delSubscribe(equipmentSubscribeId) {
  return request({
    url: '/equipment/subscribe/' + equipmentSubscribeId,
    method: 'delete'
  })
}

// 导出设备预约
export function exportSubscribe(query) {
  return request({
    url: '/equipment/subscribe/export',
    method: 'get',
    params: query
  })
}
/**预约费用查看模块
 * 
 */

/**
 * 1.根据PI的id对下属成员进行查看
 * @parame {String}
 */
/**设备预约
 * @param {String} equipmentId
 * @param {String} subscribeStartTime
 * @param {String} subscribeEndTime
 * @param {String} subscribePhone
 * @param {String} hasHelp
 * @param {String} userId
 */
export function subscribeAdd(data){
  return request({
    url: '/equipment/subscribe/add',
    method:'post',
    data:data
  })
}
/**设备预约获取设备的价钱
 * @param {String} equipmentId
 * @param {String} subscribeStartTime
 * @param {String} subscribeEndTime
 * @param {String} subscribePhone
 * @param {String} hasHelp
 * @param {String} userId
 */
 export function subscribeGetMoney(data){
  return request({
    url: '/equipment/subscribe/getFakeCost',
    method:'get',
    params:data
  })
}

/**取消预约
 * @param {String} 
 */
export function cancelSubscribe(equipmentSubscribeId){
    return request({
      url:"/equipment/subscribe/cancelSubscribe",
      method:'delete',
      params:equipmentSubscribeId
    })

}
/** 
 * @param {String}  equipmentId
 * @param {String}  userId
 */
export function updateEquimnet(data){
    return request({
      url:"/equipment/subscribe/getSubscribableTimeInterval",
      method:'get',
      params:data
    })

}
