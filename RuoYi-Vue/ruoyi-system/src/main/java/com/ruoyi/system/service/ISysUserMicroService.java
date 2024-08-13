package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysUserMicro;

/**
 * 用户和微生物的中间Service接口
 * 
 *
 * @date 2021-08-06
 */
public interface ISysUserMicroService 
{
    /**
     * 查询用户和微生物的中间
     * 
     * @param userId 用户和微生物的中间ID
     * @return 用户和微生物的中间
     */
    public SysUserMicro selectSysUserMicroById(Long userId);

    /**
     * 查询用户和微生物的中间列表
     * 
     * @param sysUserMicro 用户和微生物的中间
     * @return 用户和微生物的中间集合
     */
    public List<SysUserMicro> selectSysUserMicroList(SysUserMicro sysUserMicro);

    /**
     * 新增用户和微生物的中间
     * 
     * @param sysUserMicro 用户和微生物的中间
     * @return 结果
     */
    public int insertSysUserMicro(SysUserMicro sysUserMicro);

    /**
     * 修改用户和微生物的中间
     * 
     * @param sysUserMicro 用户和微生物的中间
     * @return 结果
     */
    public int updateSysUserMicro(SysUserMicro sysUserMicro);

    /**
     * 批量删除用户和微生物的中间
     * 
     * @param userIds 需要删除的用户和微生物的中间ID
     * @return 结果
     */
    public int deleteSysUserMicroByIds(Long[] userIds);

    /**
     * 删除用户和微生物的中间信息
     * 
     * @param userId 用户和微生物的中间ID
     * @return 结果
     */
    public int deleteSysUserMicroById(Long userId);
}
