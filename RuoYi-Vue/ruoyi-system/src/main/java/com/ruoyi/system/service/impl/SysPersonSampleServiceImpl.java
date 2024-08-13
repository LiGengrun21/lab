package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysPersonSampleMapper;
import com.ruoyi.system.domain.SysPersonSample;
import com.ruoyi.system.service.ISysPersonSampleService;

/**
 * 人类样本Service业务层处理
 * 
 * 
 * @date 2021-08-06
 */
@Service
public class SysPersonSampleServiceImpl implements ISysPersonSampleService 
{
    @Autowired
    private SysPersonSampleMapper sysPersonSampleMapper;

    /**
     * 查询人类样本
     * 
     * @param sampleId 人类样本ID
     * @return 人类样本
     */
    @Override
    public SysPersonSample selectSysPersonSampleById(Long sampleId)
    {
        return sysPersonSampleMapper.selectSysPersonSampleById(sampleId);
    }

    /**
     * 查询人类样本列表
     * 
     * @param sysPersonSample 人类样本
     * @return 人类样本
     */
    @Override
    public List<SysPersonSample> selectSysPersonSampleList(SysPersonSample sysPersonSample)
    {
        return sysPersonSampleMapper.selectSysPersonSampleList(sysPersonSample);
    }

    /**
     * 新增人类样本
     * 
     * @param sysPersonSample 人类样本
     * @return 结果
     */
    @Override
    public int insertSysPersonSample(SysPersonSample sysPersonSample)
    {
        return sysPersonSampleMapper.insertSysPersonSample(sysPersonSample);
    }

    /**
     * 修改人类样本
     * 
     * @param sysPersonSample 人类样本
     * @return 结果
     */
    @Override
    public int updateSysPersonSample(SysPersonSample sysPersonSample)
    {
        return sysPersonSampleMapper.updateSysPersonSample(sysPersonSample);
    }

    /**
     * 批量删除人类样本
     * 
     * @param sampleIds 需要删除的人类样本ID
     * @return 结果
     */
    @Override
    public int deleteSysPersonSampleByIds(Long[] sampleIds)
    {
        return sysPersonSampleMapper.deleteSysPersonSampleByIds(sampleIds);
    }

    /**
     * 删除人类样本信息
     * 
     * @param sampleId 人类样本ID
     * @return 结果
     */
    @Override
    public int deleteSysPersonSampleById(Long sampleId)
    {
        return sysPersonSampleMapper.deleteSysPersonSampleById(sampleId);
    }
}
