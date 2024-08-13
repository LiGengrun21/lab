import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";

// 查询用户列表
export function listUser(query) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}
/**接受注册
 *@param {string}  userName
 */
export function Acceptrg(query) {
  return request({
    url: '/system/user/registerAccept',
    method: 'post',
    params: query
  })
}
/**拒绝注册
 *@param {string}  userName
 */
 export function rejectrg(query) {
  return request({
    url: '/system/user/registerAccept',
    method: 'post',
    params: query
  })
}
/** 查询化学实验相关权限
 *
 * @param {Long} userId
 * @returns
 */
 export function getChemicals(data) {
  return request({
    url: '/system/user/getChemicalsOfUserById',
    method: 'post',
    data:data
  })
}


/**查询用户详细
 * 接口：
*/
export function getUser(userId) {
  return request({
    url: '/system/user/' + praseStrEmpty(userId),
    method: 'get'
  })
}

/**
 * @param {String} name          名字
 * @param {String} sex           性别
 * @param {String} admin_home    科室
 * @param {String} group         课题组
 * @param {String} phone         联系电话
 * @param {String} email         邮箱账号
 * @returns {bool} data
 */
export function registerUser(data) {
  return request({
    url: '/system/user/normalRegister',
    method: 'post',
    data: data
  })
}

export function piregist(data){
  return request({
    url:"/system/user/piRegister",
    method:"post",
    data:data
  })
}
// 新增用户
export function addUser(data) {
  return request({
    url: '/system/user',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateUser(data) {
  return request({
    url: '/system/user',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delUser(userId) {
  return request({
    url: '/system/user/' + userId,
    method: 'delete'
  })
}

// 导出用户
export function exportUser(query) {
  return request({
    url: '/system/user/export',
    method: 'get',
    params: query
  })
}

// 用户密码重置
export function resetUserPwd(userId, password) {
  const data = {
    userId,
    password
  }
  return request({
    url: '/system/user/resetPwd',
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changeUserStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: '/system/user/changeStatus',
    method: 'put',
    data: data
  })
}

// 查询用户个人信息
export function getUserProfile() {
  return request({
    url: '/system/user/profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updateUserProfile(data) {
  return request({
    url: '/system/user/profile',
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updateUserPwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/system/user/profile/updatePwd',
    method: 'put',
    params: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/system/user/profile/avatar',
    method: 'post',
    data: data
  })
}

// 下载用户导入模板
export function importTemplate() {
  return request({
    url: '/system/user/importTemplate',
    method: 'get'
  })
}
/**添加用户 */
export function addUsers(data){
  return request({
    url:"/system/user",
    method:"post",
    data:data
  })
}
/**查看所有的PI 注册界面使用 */
export function GetDept(){
  return request({
    url:"/system/dept/getAllDepts",
    method:"get",
  })
}

/** 获取所有pi 非鉴权界面使用
 * @param {String}
*/
export function GetAllPis(){
  return request({
    url:"/system/user/getAllPis",
    method:"get",

  })
}
/** 获取所有pi 鉴权界面使用
 * @param {String}
*/
export function GetAAllPis2(){
  return request({
    url:"/system/user/getAllPi",
    method:"get",
  })
}

/** 获取部门下所有pi 非鉴权界面使用
 * @param {String}
*/
export function GetAllPi(deptId){
  return request({
    url:"system/dept/getAllUsersByDeptId",
    method:"get",
    deptId:deptId
  })
}

/**
 * 获取所有的部下
 * @param {String} piId
 * */
export function GetPiuser(query){
  return request({
    url:"/system/user/getUsersOfPiByPiId",
    method:"post",
    params:query
  })
}
/**获取用户值班
 * @param {String}workDate 
 */
export function GerWork(data){
  return request({
    url:"/system/work/getWorkUserByDate",
    method:"get",
    params:data
  })
}
/**获取当前实验室情况
 * @param{String} definiteTime
 */
 export function getEquimnetNow(data){
  return request({
    url:"/lab/showlab/getAmountOfConferenceRoomByDefiniteTime",
    method:"get",
    params:data
  })
}
/**
 * 获取当前房间情况
 */
export function GetLabUn(data){
  return request({
    url:"/lab/showlab/getAmountOfLabByDefiniteTime",
    method:"get",
    params:data
  })
}
