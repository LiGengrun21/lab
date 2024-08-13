package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysUserPersonSample;

/**
 * 用户和人类样本中间Mapper接口
 * 
 *
 * @date 2021-08-06
 */
public interface SysUserPersonSampleMapper 
{
    /**
     * 查询用户和人类样本中间
     * 
     * @param userId 用户和人类样本中间ID
     * @return 用户和人类样本中间
     */
    public SysUserPersonSample selectSysUserPersonSampleById(Long userId);

    /**
     * 查询用户和人类样本中间列表
     * 
     * @param sysUserPersonSample 用户和人类样本中间
     * @return 用户和人类样本中间集合
     */
    public List<SysUserPersonSample> selectSysUserPersonSampleList(SysUserPersonSample sysUserPersonSample);

    /**
     * 新增用户和人类样本中间
     * 
     * @param sysUserPersonSample 用户和人类样本中间
     * @return 结果
     */
    public int insertSysUserPersonSample(SysUserPersonSample sysUserPersonSample);

    /**
     * 修改用户和人类样本中间
     * 
     * @param sysUserPersonSample 用户和人类样本中间
     * @return 结果
     */
    public int updateSysUserPersonSample(SysUserPersonSample sysUserPersonSample);

    /**
     * 删除用户和人类样本中间
     * 
     * @param userId 用户和人类样本中间ID
     * @return 结果
     */
    public int deleteSysUserPersonSampleById(Long userId);

    /**
     * 批量删除用户和人类样本中间
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserPersonSampleByIds(Long[] userIds);
}
