import request from '@/utils/request'

// 查询一般耗材列表列表
export function listShowGoods(query) {
  return request({
    url: '/goods/showGoods/list',
    method: 'get',
    params: query
  })
}

// 查询设备耗材列表列表
export function listShowEquipmentGoods(query) {
  return request({
    url: '/goods/showGoods/equipmentlist',
    method: 'get',
    params: query
  })
}

// 查询贵重耗材列表列表
export function listShowValuableGoods(query) {
  return request({
    url: '/goods/showGoods/valuablelist',
    method: 'get',
    params: query
  })
}

// 查询耗材列表详细
export function getShowGoods(goodsId) {
  return request({
    url: '/goods/showGoods/' + goodsId,
    method: 'get'
  })
}

// 新增耗材列表
export function addShowGoods(data) {
  return request({
    url: '/goods/showGoods',
    method: 'post',
    data: data
  })
}

// 修改耗材列表
export function updateShowGoods(data) {
  return request({
    url: '/goods/showGoods',
    method: 'put',
    data: data
  })
}

export function addGoods(data) {
  return request({
    url: '/goods/showGoods/addgoods',
    method: 'put',
    data: data
  })
}

// 删除耗材列表
export function delShowGoods(goodsId) {
  return request({
    url: '/goods/showGoods/' + goodsId,
    method: 'delete'
  })
}

// 导出耗材列表
export function exportShowGoods(query) {
  return request({
    url: '/goods/showGoods/export',
    method: 'get',
    params: query
  })
}
