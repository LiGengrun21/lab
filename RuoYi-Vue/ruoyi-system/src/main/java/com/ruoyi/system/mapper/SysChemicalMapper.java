package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysChemical;

/**
 * 危化品Mapper接口
 * 
 *
 * @date 2021-08-06
 */
public interface SysChemicalMapper 
{
    /**
     * 查询危化品
     * 
     * @param chemicalId 危化品ID
     * @return 危化品
     */
    public SysChemical selectSysChemicalById(Long chemicalId);

    /**
     * 查询危化品列表
     * 
     * @param sysChemical 危化品
     * @return 危化品集合
     */
    public List<SysChemical> selectSysChemicalList(SysChemical sysChemical);

    /**
     * 新增危化品
     * 
     * @param sysChemical 危化品
     * @return 结果
     */
    public int insertSysChemical(SysChemical sysChemical);

    /**
     * 修改危化品
     * 
     * @param sysChemical 危化品
     * @return 结果
     */
    public int updateSysChemical(SysChemical sysChemical);

    /**
     * 删除危化品
     * 
     * @param chemicalId 危化品ID
     * @return 结果
     */
    public int deleteSysChemicalById(Long chemicalId);

    /**
     * 批量删除危化品
     * 
     * @param chemicalIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysChemicalByIds(Long[] chemicalIds);
}
