package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysUserChemical;

/**
 * 用户和危化品中间Mapper接口
 * 
 * 
 * @date 2021-08-06
 */
public interface SysUserChemicalMapper 
{
    /**
     * 查询用户和危化品中间
     * 
     * @param userId 用户和危化品中间ID
     * @return 用户和危化品中间
     */
    public SysUserChemical selectSysUserChemicalById(Long userId);

    /**
     * 查询用户和危化品中间列表
     * 
     * @param sysUserChemical 用户和危化品中间
     * @return 用户和危化品中间集合
     */
    public List<SysUserChemical> selectSysUserChemicalList(SysUserChemical sysUserChemical);

    /**
     * 新增用户和危化品中间
     * 
     * @param sysUserChemical 用户和危化品中间
     * @return 结果
     */
    public int insertSysUserChemical(SysUserChemical sysUserChemical);

    /**
     * 修改用户和危化品中间
     * 
     * @param sysUserChemical 用户和危化品中间
     * @return 结果
     */
    public int updateSysUserChemical(SysUserChemical sysUserChemical);

    /**
     * 删除用户和危化品中间
     * 
     * @param userId 用户和危化品中间ID
     * @return 结果
     */
    public int deleteSysUserChemicalById(Long userId);

    /**
     * 批量删除用户和危化品中间
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserChemicalByIds(Long[] userIds);
}
