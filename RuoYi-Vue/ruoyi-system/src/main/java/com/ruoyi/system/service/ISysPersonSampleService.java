package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysPersonSample;

/**
 * 人类样本Service接口
 * 
 * 
 * @date 2021-08-06
 */
public interface ISysPersonSampleService 
{
    /**
     * 查询人类样本
     * 
     * @param sampleId 人类样本ID
     * @return 人类样本
     */
    public SysPersonSample selectSysPersonSampleById(Long sampleId);

    /**
     * 查询人类样本列表
     * 
     * @param sysPersonSample 人类样本
     * @return 人类样本集合
     */
    public List<SysPersonSample> selectSysPersonSampleList(SysPersonSample sysPersonSample);

    /**
     * 新增人类样本
     * 
     * @param sysPersonSample 人类样本
     * @return 结果
     */
    public int insertSysPersonSample(SysPersonSample sysPersonSample);

    /**
     * 修改人类样本
     * 
     * @param sysPersonSample 人类样本
     * @return 结果
     */
    public int updateSysPersonSample(SysPersonSample sysPersonSample);

    /**
     * 批量删除人类样本
     * 
     * @param sampleIds 需要删除的人类样本ID
     * @return 结果
     */
    public int deleteSysPersonSampleByIds(Long[] sampleIds);

    /**
     * 删除人类样本信息
     * 
     * @param sampleId 人类样本ID
     * @return 结果
     */
    public int deleteSysPersonSampleById(Long sampleId);
}
