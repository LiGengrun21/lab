/**规则校验 */
/**普通成员注册校验 */
export const RULE_REGISTER = {
    phonenumber: {
      pattern: /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/,
      message: '手机号不正确',
    },
    sex: [{
      required: true,
      message: '性别不能为空',
    }],
    nickName: {
      required: true,
      message: '昵称不能为空',
    },
    userName: [{
      required: true,
      message: '用户名不能为空',
    }],
    email: [{
        required: true,
        message: '邮箱不能为空',
        type:"email"
    }],
    piUsername: [{
    required: true,
    message: '课题组长不能为空',
    }],
    password: [{
        required: true,
        message: '密码不能为空',
        }],
  }
  /* 完善个人信息 */
  export const RULE_PERFECT_SELF_INFO = {
    realName: {
      required: true,
      message: '姓名不能为空'
    },
    studentNumber: {
      required: true,
      message: '学号不能为空'
    },
    grade: {
      required: true,
      message: '年级不能为空'
    },
    academyId: {
      required: true,
      message: '学院不能为空'
    },
    majorId: {
      required: true,
      message: '专业不能为空',
      trigger: 'blur'
    },
  }

  