package com.ruoyi.web.controller.system;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.equipment.domain.EquipmentSubscribe;
import com.ruoyi.equipment.domain.ZjutEquipment;
import com.ruoyi.equipment.mapper.ZjutEquipmentMapper;
import com.ruoyi.equipment.service.IZjutEquipmentService;
import com.ruoyi.equipment.service.impl.EquipmentSubscribeServiceImpl;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysMenuService;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * 登录验证
 *
 */
@RestController
public class SysLoginController
{
    @Autowired
    private RedisCache redisCache;

    @Autowired
    ISysUserService userService;
    @Autowired
    EquipmentSubscribeServiceImpl equipmentSubscribeService;
    @Autowired
    IZjutEquipmentService equipmentService;
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) throws UnknownHostException {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        //获取ip地址
        InetAddress ip4 = Inet4Address.getLocalHost();
        System.out.println(ip4.getHostAddress());
        //查找设备
        ZjutEquipment equipmentCondition=new ZjutEquipment();
        equipmentCondition.setEquipmentCode(ip4.getHostAddress());
        ArrayList<ZjutEquipment> equipments=(ArrayList<ZjutEquipment>)equipmentService.selectZjutEquipmentList(equipmentCondition);
        //如果查到设备,则说明是设备旁边的电脑访问的.
        if(equipments.size()>0){
            //获取设备id
            long equipmentId=equipments.get(0).getEquipmentId();
            //获取用户id
            long userId=userService.selectUserByUserName(loginBody.getUsername()).getUserId();
            //构造equipmentSubscribeCondition
            EquipmentSubscribe equipmentSubscribeCondition=new EquipmentSubscribe();
            equipmentSubscribeCondition.setEquipmentId(equipmentId);
            equipmentSubscribeCondition.setUserId(userId);
            equipmentSubscribeCondition.setSubscribeDate(new Date(System.currentTimeMillis()));
            String currentTime=new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
            equipmentSubscribeCondition.setSubscribeStartTime(currentTime);
            equipmentSubscribeCondition.setSubscribeEndTime(currentTime);
            //查询预约记录
            List<EquipmentSubscribe> equipmentSubscribes=equipmentSubscribeService.selectEquipmentSubscribeList(equipmentSubscribeCondition);
            //没有查到则说明未预约
            if(equipmentSubscribes.size()<=0){
                return AjaxResult.error("此用户当前未预约本设备,登录失败.");
            }
        }
        redisCache.setCacheObject(ip4.getHostAddress(), "1", 10, TimeUnit.MINUTES);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
