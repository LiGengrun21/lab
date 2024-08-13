package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserMicroMapper;
import com.ruoyi.system.domain.SysUserMicro;
import com.ruoyi.system.service.ISysUserMicroService;

/**
 * 用户和微生物的中间Service业务层处理
 * 
 *
 * @date 2021-08-06
 */
@Service
public class SysUserMicroServiceImpl implements ISysUserMicroService 
{
    @Autowired
    private SysUserMicroMapper sysUserMicroMapper;

    /**
     * 查询用户和微生物的中间
     * 
     * @param userId 用户和微生物的中间ID
     * @return 用户和微生物的中间
     */
    @Override
    public SysUserMicro selectSysUserMicroById(Long userId)
    {
        return sysUserMicroMapper.selectSysUserMicroById(userId);
    }

    /**
     * 查询用户和微生物的中间列表
     * 
     * @param sysUserMicro 用户和微生物的中间
     * @return 用户和微生物的中间
     */
    @Override
    public List<SysUserMicro> selectSysUserMicroList(SysUserMicro sysUserMicro)
    {
        return sysUserMicroMapper.selectSysUserMicroList(sysUserMicro);
    }

    /**
     * 新增用户和微生物的中间
     * 
     * @param sysUserMicro 用户和微生物的中间
     * @return 结果
     */
    @Override
    public int insertSysUserMicro(SysUserMicro sysUserMicro)
    {
        return sysUserMicroMapper.insertSysUserMicro(sysUserMicro);
    }

    /**
     * 修改用户和微生物的中间
     * 
     * @param sysUserMicro 用户和微生物的中间
     * @return 结果
     */
    @Override
    public int updateSysUserMicro(SysUserMicro sysUserMicro)
    {
        return sysUserMicroMapper.updateSysUserMicro(sysUserMicro);
    }

    /**
     * 批量删除用户和微生物的中间
     * 
     * @param userIds 需要删除的用户和微生物的中间ID
     * @return 结果
     */
    @Override
    public int deleteSysUserMicroByIds(Long[] userIds)
    {
        return sysUserMicroMapper.deleteSysUserMicroByIds(userIds);
    }

    /**
     * 删除用户和微生物的中间信息
     * 
     * @param userId 用户和微生物的中间ID
     * @return 结果
     */
    @Override
    public int deleteSysUserMicroById(Long userId)
    {
        return sysUserMicroMapper.deleteSysUserMicroById(userId);
    }
}
