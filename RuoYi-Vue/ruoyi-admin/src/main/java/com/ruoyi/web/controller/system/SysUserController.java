package com.ruoyi.web.controller.system;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.enums.HttpMethod;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import org.apache.catalina.User;
import org.aspectj.weaver.loadtime.Aj;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;

import javax.servlet.http.HttpSession;

/**
 * 用户信息
 *
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController{
    /**
     * 发送者
     */
    @Value("${spring.mail.username}")
    private String from;
    private SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
    {
        simpleMailMessage.setSubject("智慧实验室系统向您发送验证码");
    }
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysPostService postService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 获取用户列表
     */
    //@PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user){
        startPage();
        List<SysUser> list=userService.selectUserList(user);
        System.out.println(list);
        return getDataTable(list);
    }
    @Log(title="用户管理", businessType=BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @GetMapping("/export")
    public AjaxResult export(SysUser user){
        List<SysUser> list=userService.selectUserList(user);
        ExcelUtil<SysUser> util=new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list,"用户数据");
    }
    @Log(title="用户管理", businessType=BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file,boolean updateSupport) throws Exception{
        ExcelUtil<SysUser> util=new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList=util.importExcel(file.getInputStream());
        LoginUser loginUser=tokenService.getLoginUser(ServletUtils.getRequest());
        String operName=loginUser.getUsername();
        String message=userService.importUser(userList,updateSupport,operName);
        return AjaxResult.success(message);
    }
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate(){
        ExcelUtil<SysUser> util=new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }
    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = { "/", "/{userId}" })
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId)
    {
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId))
        {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }
    /**
     * 普通用户注册
     */
    @Log(title="用户管理", businessType=BusinessType.INSERT)
    @PostMapping("/normalRegister")
    public AjaxResult normalRegister(@Validated @RequestBody SysUser user){
        if(UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))){
            return AjaxResult.error("注册用户'"+user.getUserName()+"'失败，登录账号已存在");
        }
        else if(StringUtils.isNotEmpty(user.getPhonenumber())&&UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))){
            return AjaxResult.error("注册用户'"+user.getUserName()+"'失败，手机号码已存在");
        }
        else if(StringUtils.isNotEmpty(user.getEmail())&&UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))){
            return AjaxResult.error("注册用户'"+user.getUserName()+"'失败，邮箱账号已存在");
        }
        else{
            //查到所有PI的用户名
            ArrayList<SysUser> sysUsers=userService.selectUsersOfPiByPiId((long)-1);
            boolean isFind=false;
            for(SysUser sysUser: sysUsers){
                //查到此PI的用户名
                if(sysUser.getUserId().equals(user.getPiId())){
                    isFind=true;
                    break;
                }
            }
            if(!isFind){
                return AjaxResult.error("此PiId'"+user.getPiId()+"'不存在");
            }
        }
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setStatus("1");
        //设置为普通的角色,对应id为2.
        Long[] roleIds={2L};
        user.setRoleIds(roleIds);
        userService.insertUser(user);
        return AjaxResult.success();
    }
    /**
     * PI注册
     */
    @Log(title="用户管理", businessType=BusinessType.INSERT)
    @PostMapping("/piRegister")
    public AjaxResult piRegister(@Validated @RequestBody SysUser user){
        if(UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))){
            return AjaxResult.error("注册用户'"+user.getUserName()+"'失败，登录账号已存在");
        }
        else if(StringUtils.isNotEmpty(user.getPhonenumber())&&UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))){
            return AjaxResult.error("注册用户'"+user.getUserName()+"'失败，手机号码已存在");
        }
        else if(StringUtils.isNotEmpty(user.getEmail())&&UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))){
            return AjaxResult.error("注册用户'"+user.getUserName()+"'失败，邮箱账号已存在");
        }
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setStatus("1");
        //设置为PI,对应id为102.
        Long[] roleIds={102L};
        user.setRoleIds(roleIds);
        user.setPiId(-1L);
        userService.insertUser(user);
        return AjaxResult.success();
    }
    /**
     * 接受注册
     */
    @Log(title="用户管理", businessType=BusinessType.INSERT)
    @PostMapping("/registerAccept")
    public AjaxResult registerAccept(@RequestBody SysUser user){
        //查询到用户
        //SysUser user=userService.selectUserByUserName(username);
        //把用户状态设为"0"
        user.setStatus("0");
        userService.updateUser(user);
        return AjaxResult.success("启用成功");
    }
    /**
     * 拒绝注册
     */
    @Log(title="用户管理", businessType=BusinessType.INSERT)
    @PostMapping("/registerRefuse")
    public AjaxResult registerRefuse(String username){
        //查询到用户
        SysUser user=userService.selectUserByUserName(username);
        Long useId=user.getUserId();
        userService.deleteUserById(useId);
        return AjaxResult.success("删除成功");
    }
    /**
     * 新增用户
     */
    @Log(title="用户管理", businessType=BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user){
        if(UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))){
            return AjaxResult.error("新增用户'"+user.getUserName()+"'失败，登录账号已存在");
        }
        else if(StringUtils.isNotEmpty(user.getPhonenumber())&&UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))){
            return AjaxResult.error("新增用户'"+user.getUserName()+"'失败，手机号码已存在");
        }
        else if(StringUtils.isNotEmpty(user.getEmail())&&UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))){
            return AjaxResult.error("新增用户'"+user.getUserName()+"'失败，邮箱账号已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }
    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title="用户管理", businessType=BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user){
        userService.checkUserAllowed(user);
        if(StringUtils.isNotEmpty(user.getPhonenumber())&&UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))){
            return AjaxResult.error("修改用户'"+user.getUserName()+"'失败，手机号码已存在");
        }
        else if(StringUtils.isNotEmpty(user.getEmail())&&UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))){
            return AjaxResult.error("修改用户'"+user.getUserName()+"'失败，邮箱账号已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }
    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title="用户管理", businessType=BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds){
        return toAjax(userService.deleteUserByIds(userIds));
    }

    @PostMapping("/test/{userId}")
    public AjaxResult select(@PathVariable Long userId){
        String s = sysUserMapper.selectUserById(userId).toString();
        System.out.println(s);
        return AjaxResult.success();
    }
    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('system:user:resetPwd')")
    @Log(title="用户管理", businessType=BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user){
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.resetPwd(user));
    }
    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title="用户管理", businessType=BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user){
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }
    /**
     * 获取用户涉及的病原微生物、人类样本、危化品
     *
     * @param userId 用户id
     * @return 以Map的形式返回结果,其中key是化学品名称(eg.人类样本),value是多个此种化学品组成的列表.
     */
    @PostMapping("/getChemicalsOfUserById")
    public AjaxResult getChemicalsOfUserById(Long userId){
        ArrayList<SysChemical> sysChemicals=userService.selectChemicalsOfUserById(userId);
        ArrayList<SysPersonSample> sysPersonSamples=userService.selectPersonSamplesOfUserById(userId);
        ArrayList<SysMicro> sysMicros=userService.selectMicrosOfUserById(userId);
        AjaxResult res=AjaxResult.success();
        res.put("chemicals",sysChemicals);
        res.put("personSamples",sysPersonSamples);
        res.put("micros",sysMicros);
        return res;
    }
    @PostMapping("/batchAdd")
    public AjaxResult batchAdd(@RequestBody List<SysUserRole> userRole){
        userRoleMapper.batchUserRole(userRole);
        return AjaxResult.success();
    }
    /**
     * 获取一个PI下的成员名单
     *
     * @param piId PI的id
     * @return
     */
    @GetMapping("/getUsersOfPiByPiId")
    public AjaxResult getUsersOfPiByPiId(Long piId){
        System.out.print(piId);
        ArrayList<SysUser> sysUsers=userService.selectUsersOfPiByPiId(piId);
        return AjaxResult.success(sysUsers);
    }
    /**
     * 获取一个PI需要审核的用户
     *
     * @param id PI的id
     * @return
     */
    @GetMapping("/getAuditsOfPiByPiId")
    public AjaxResult getAuditOfPiByPiId(Long piId){
        System.out.print(piId);
        ArrayList<SysUser> sysUsers=userService.selectAuditsOfPiByPiId(piId);
        return AjaxResult.success(sysUsers);
    }
    /**
     * 获取所有的Pi内容
     *
     * @param
     * @return
     */
    @GetMapping("/getAllPis")
    public AjaxResult getAllPis(){
        ArrayList<SysUser> sysUsers=userService.selectAllPis();
        return AjaxResult.success(sysUsers);
    }
    /**获取所有的pi
     * 非鉴权界面使用
     */
    @GetMapping("/getAllPi")
    public AjaxResult getAllPi(){
        ArrayList<SysUser> sysUsers=userService.selectAllPis();
        return AjaxResult.success(sysUsers);
    }
    /**
     *通过邮箱发送验证码
     */
    @GetMapping("/sendVerifyCodeByEmail")
    public AjaxResult sendVerifyCodeByEmail(String email,HttpSession session){
        SysUser tempUser=new SysUser();
        tempUser.setEmail(email);
        List<SysUser> users=userService.selectUserList(tempUser);
        if(users.size()!=1){
            return AjaxResult.error("邮箱不存在");
        }
        else{
            synchronized(simpleMailMessage){
                //生成6位验证码
                String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
                StringBuilder verifyCode=new StringBuilder(6);
                for(int i=0;i<6;i++){
                    char ch=str.charAt(new Random().nextInt(str.length()));
                    verifyCode.append(ch);
                }
                //发送方
                simpleMailMessage.setFrom(from);
                //接收方
                simpleMailMessage.setTo(email);
                //文本内容
                simpleMailMessage.setText("验证码为:"+verifyCode+",5分钟内有效。");
                //发送邮件
                mailSender.send(simpleMailMessage);
                //保存验证码和失效时间
                session.setAttribute("verifyCode",verifyCode.toString());
                Calendar invalidTime=Calendar.getInstance();
                invalidTime.add(Calendar.MINUTE,5);
                session.setAttribute("invalidTime",invalidTime);
                //保存用户名,验证成功后要传回前端.
                session.setAttribute("username",users.get(0).getUserName());
                //保存用户id,后面改密码要用.
                session.setAttribute("userId",users.get(0).getUserId());
                return AjaxResult.success("验证码已发送");
            }
        }
    }
    /**
     *验证验证码
     */
    @PostMapping("/verify")
    public AjaxResult verify(@RequestParam("verifyCode") String userVerifyCode,HttpSession session){
        Calendar invalidTime=(Calendar)session.getAttribute("invalidTime");
        String verifyCode=(String)session.getAttribute("verifyCode");
        if(invalidTime==null||verifyCode==null){
            return AjaxResult.error("验证码错误或过期");
        }
        else {
            if(!Calendar.getInstance().after(invalidTime)&&verifyCode.equalsIgnoreCase(userVerifyCode)){
                //移除这些属性
                session.removeAttribute("invalidTime");
                session.removeAttribute("verifyCode");
                String username=(String)session.getAttribute("username");
                session.removeAttribute("username");
                return AjaxResult.success("验证成功",username);
            }
            else{
                return AjaxResult.error("验证码错误或过期");
            }
        }
    }
    /**
     * 修改密码
     */
    @PutMapping("/modifyPassword")
    public AjaxResult modifyPassword(String userName,String oldPswd,String newPswd){
        SysUser tempUser=new SysUser();
        tempUser.setUserName(userName);
        tempUser.setPassword(oldPswd);
        List<SysUser> sysUsers=userService.selectUserList(tempUser);
        //如果个数不为1
        if(sysUsers.size()!=1){
            return AjaxResult.error("账号或密码错误");
        }
        else {
            //把用户名去除
            tempUser.setUserName(null);
            //把密码设为新密码
            tempUser.setPassword(SecurityUtils.encryptPassword(newPswd));
            //设置用户id
            tempUser.setUserId(sysUsers.get(0).getUserId());
            if(userService.updateUser(tempUser)==1){
               return AjaxResult.success("密码修改成功");
            }
            else {
                return AjaxResult.error("密码修改失败");
            }
        }
    }
    /**
     * 通过找回密码来修改密码
     */
    @PutMapping("/modifyPasswordByFind")
    public AjaxResult modifyPasswordByFind(String newPswd,HttpSession session){
        Long userId=(Long)session.getAttribute("userId");
        if(userId==null){
            return AjaxResult.error("密码修改失败");
        }
        else{
            SysUser tempUser=new SysUser();
            //把密码设为新密码
            tempUser.setPassword(SecurityUtils.encryptPassword(newPswd));
            //设置用户id
            tempUser.setUserId(userId);
            if(userService.updateUser(tempUser)==1){
                session.removeAttribute("userId");
                return AjaxResult.success("密码修改成功");
            }
            else{
                return AjaxResult.error("密码修改失败");
            }
        }
    }
}
