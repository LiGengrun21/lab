package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserChemicalMapper;
import com.ruoyi.system.domain.SysUserChemical;
import com.ruoyi.system.service.ISysUserChemicalService;

/**
 * 用户和危化品中间Service业务层处理
 * 
 * 
 * @date 2021-08-06
 */
@Service
public class SysUserChemicalServiceImpl implements ISysUserChemicalService 
{
    @Autowired
    private SysUserChemicalMapper sysUserChemicalMapper;

    /**
     * 查询用户和危化品中间
     * 
     * @param userId 用户和危化品中间ID
     * @return 用户和危化品中间
     */
    @Override
    public SysUserChemical selectSysUserChemicalById(Long userId)
    {
        return sysUserChemicalMapper.selectSysUserChemicalById(userId);
    }

    /**
     * 查询用户和危化品中间列表
     * 
     * @param sysUserChemical 用户和危化品中间
     * @return 用户和危化品中间
     */
    @Override
    public List<SysUserChemical> selectSysUserChemicalList(SysUserChemical sysUserChemical)
    {
        return sysUserChemicalMapper.selectSysUserChemicalList(sysUserChemical);
    }

    /**
     * 新增用户和危化品中间
     * 
     * @param sysUserChemical 用户和危化品中间
     * @return 结果
     */
    @Override
    public int insertSysUserChemical(SysUserChemical sysUserChemical)
    {
        return sysUserChemicalMapper.insertSysUserChemical(sysUserChemical);
    }

    /**
     * 修改用户和危化品中间
     * 
     * @param sysUserChemical 用户和危化品中间
     * @return 结果
     */
    @Override
    public int updateSysUserChemical(SysUserChemical sysUserChemical)
    {
        return sysUserChemicalMapper.updateSysUserChemical(sysUserChemical);
    }

    /**
     * 批量删除用户和危化品中间
     * 
     * @param userIds 需要删除的用户和危化品中间ID
     * @return 结果
     */
    @Override
    public int deleteSysUserChemicalByIds(Long[] userIds)
    {
        return sysUserChemicalMapper.deleteSysUserChemicalByIds(userIds);
    }

    /**
     * 删除用户和危化品中间信息
     * 
     * @param userId 用户和危化品中间ID
     * @return 结果
     */
    @Override
    public int deleteSysUserChemicalById(Long userId)
    {
        return sysUserChemicalMapper.deleteSysUserChemicalById(userId);
    }
}
