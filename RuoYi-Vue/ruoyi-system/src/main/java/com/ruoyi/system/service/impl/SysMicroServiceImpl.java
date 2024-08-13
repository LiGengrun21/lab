package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysMicroMapper;
import com.ruoyi.system.domain.SysMicro;
import com.ruoyi.system.service.ISysMicroService;

/**
 * 微生物Service业务层处理
 * 
 *
 * @date 2021-08-06
 */
@Service
public class SysMicroServiceImpl implements ISysMicroService 
{
    @Autowired
    private SysMicroMapper sysMicroMapper;

    /**
     * 查询微生物
     * 
     * @param microId 微生物ID
     * @return 微生物
     */
    @Override
    public SysMicro selectSysMicroById(Long microId)
    {
        return sysMicroMapper.selectSysMicroById(microId);
    }

    /**
     * 查询微生物列表
     * 
     * @param sysMicro 微生物
     * @return 微生物
     */
    @Override
    public List<SysMicro> selectSysMicroList(SysMicro sysMicro)
    {
        return sysMicroMapper.selectSysMicroList(sysMicro);
    }

    /**
     * 新增微生物
     * 
     * @param sysMicro 微生物
     * @return 结果
     */
    @Override
    public int insertSysMicro(SysMicro sysMicro)
    {
        return sysMicroMapper.insertSysMicro(sysMicro);
    }

    /**
     * 修改微生物
     * 
     * @param sysMicro 微生物
     * @return 结果
     */
    @Override
    public int updateSysMicro(SysMicro sysMicro)
    {
        return sysMicroMapper.updateSysMicro(sysMicro);
    }

    /**
     * 批量删除微生物
     * 
     * @param microIds 需要删除的微生物ID
     * @return 结果
     */
    @Override
    public int deleteSysMicroByIds(Long[] microIds)
    {
        return sysMicroMapper.deleteSysMicroByIds(microIds);
    }

    /**
     * 删除微生物信息
     * 
     * @param microId 微生物ID
     * @return 结果
     */
    @Override
    public int deleteSysMicroById(Long microId)
    {
        return sysMicroMapper.deleteSysMicroById(microId);
    }
}
