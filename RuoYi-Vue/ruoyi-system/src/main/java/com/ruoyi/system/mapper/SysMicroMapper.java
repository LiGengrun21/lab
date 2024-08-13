package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysMicro;

/**
 * 微生物Mapper接口
 * 
 *
 * @date 2021-08-06
 */
public interface SysMicroMapper 
{
    /**
     * 查询微生物
     * 
     * @param microId 微生物ID
     * @return 微生物
     */
    public SysMicro selectSysMicroById(Long microId);

    /**
     * 查询微生物列表
     * 
     * @param sysMicro 微生物
     * @return 微生物集合
     */
    public List<SysMicro> selectSysMicroList(SysMicro sysMicro);

    /**
     * 新增微生物
     * 
     * @param sysMicro 微生物
     * @return 结果
     */
    public int insertSysMicro(SysMicro sysMicro);

    /**
     * 修改微生物
     * 
     * @param sysMicro 微生物
     * @return 结果
     */
    public int updateSysMicro(SysMicro sysMicro);

    /**
     * 删除微生物
     * 
     * @param microId 微生物ID
     * @return 结果
     */
    public int deleteSysMicroById(Long microId);

    /**
     * 批量删除微生物
     * 
     * @param microIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysMicroByIds(Long[] microIds);
}
