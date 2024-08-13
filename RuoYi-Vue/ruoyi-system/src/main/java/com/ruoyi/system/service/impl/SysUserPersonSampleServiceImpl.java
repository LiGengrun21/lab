package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserPersonSampleMapper;
import com.ruoyi.system.domain.SysUserPersonSample;
import com.ruoyi.system.service.ISysUserPersonSampleService;

/**
 * 用户和人类样本中间Service业务层处理
 * 
 * 
 * @date 2021-08-06
 */
@Service
public class SysUserPersonSampleServiceImpl implements ISysUserPersonSampleService 
{
    @Autowired
    private SysUserPersonSampleMapper sysUserPersonSampleMapper;

    /**
     * 查询用户和人类样本中间
     * 
     * @param userId 用户和人类样本中间ID
     * @return 用户和人类样本中间
     */
    @Override
    public SysUserPersonSample selectSysUserPersonSampleById(Long userId)
    {
        return sysUserPersonSampleMapper.selectSysUserPersonSampleById(userId);
    }

    /**
     * 查询用户和人类样本中间列表
     * 
     * @param sysUserPersonSample 用户和人类样本中间
     * @return 用户和人类样本中间
     */
    @Override
    public List<SysUserPersonSample> selectSysUserPersonSampleList(SysUserPersonSample sysUserPersonSample)
    {
        return sysUserPersonSampleMapper.selectSysUserPersonSampleList(sysUserPersonSample);
    }

    /**
     * 新增用户和人类样本中间
     * 
     * @param sysUserPersonSample 用户和人类样本中间
     * @return 结果
     */
    @Override
    public int insertSysUserPersonSample(SysUserPersonSample sysUserPersonSample)
    {
        return sysUserPersonSampleMapper.insertSysUserPersonSample(sysUserPersonSample);
    }

    /**
     * 修改用户和人类样本中间
     * 
     * @param sysUserPersonSample 用户和人类样本中间
     * @return 结果
     */
    @Override
    public int updateSysUserPersonSample(SysUserPersonSample sysUserPersonSample)
    {
        return sysUserPersonSampleMapper.updateSysUserPersonSample(sysUserPersonSample);
    }

    /**
     * 批量删除用户和人类样本中间
     * 
     * @param userIds 需要删除的用户和人类样本中间ID
     * @return 结果
     */
    @Override
    public int deleteSysUserPersonSampleByIds(Long[] userIds)
    {
        return sysUserPersonSampleMapper.deleteSysUserPersonSampleByIds(userIds);
    }

    /**
     * 删除用户和人类样本中间信息
     * 
     * @param userId 用户和人类样本中间ID
     * @return 结果
     */
    @Override
    public int deleteSysUserPersonSampleById(Long userId)
    {
        return sysUserPersonSampleMapper.deleteSysUserPersonSampleById(userId);
    }
}
