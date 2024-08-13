package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysChemicalMapper;
import com.ruoyi.system.domain.SysChemical;
import com.ruoyi.system.service.ISysChemicalService;

/**
 * 危化品Service业务层处理
 * 
 * 
 * @date 2021-08-06
 */
@Service
public class SysChemicalServiceImpl implements ISysChemicalService 
{
    @Autowired
    private SysChemicalMapper sysChemicalMapper;

    /**
     * 查询危化品
     * 
     * @param chemicalId 危化品ID
     * @return 危化品
     */
    @Override
    public SysChemical selectSysChemicalById(Long chemicalId)
    {
        return sysChemicalMapper.selectSysChemicalById(chemicalId);
    }

    /**
     * 查询危化品列表
     * 
     * @param sysChemical 危化品
     * @return 危化品
     */
    @Override
    public List<SysChemical> selectSysChemicalList(SysChemical sysChemical)
    {
        return sysChemicalMapper.selectSysChemicalList(sysChemical);
    }

    /**
     * 新增危化品
     * 
     * @param sysChemical 危化品
     * @return 结果
     */
    @Override
    public int insertSysChemical(SysChemical sysChemical)
    {
        return sysChemicalMapper.insertSysChemical(sysChemical);
    }

    /**
     * 修改危化品
     * 
     * @param sysChemical 危化品
     * @return 结果
     */
    @Override
    public int updateSysChemical(SysChemical sysChemical)
    {
        return sysChemicalMapper.updateSysChemical(sysChemical);
    }

    /**
     * 批量删除危化品
     * 
     * @param chemicalIds 需要删除的危化品ID
     * @return 结果
     */
    @Override
    public int deleteSysChemicalByIds(Long[] chemicalIds)
    {
        return sysChemicalMapper.deleteSysChemicalByIds(chemicalIds);
    }

    /**
     * 删除危化品信息
     * 
     * @param chemicalId 危化品ID
     * @return 结果
     */
    @Override
    public int deleteSysChemicalById(Long chemicalId)
    {
        return sysChemicalMapper.deleteSysChemicalById(chemicalId);
    }
}
