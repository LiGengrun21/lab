package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.Work;
import com.ruoyi.system.domain.WorkUser;

/**
 * 值班人员列表 Mapper接口
 *
 * 
 * @date 2022-07-12
 */
public interface WorkUserMapper
{
    /**
     * 根据主键ID查询值班人员
     *
     * @param workUserId
     * @return
     */
    public WorkUser selectWorkUserById(Long workUserId);

    /**
     * 根据用户id查询值班人员（使用）
     *
     * @param userId
     * @return 值班人员
     */
    public WorkUser selectWorkUserByUserId(Long userId);

    /**
     * 新增值班人员（使用）
     *
     * @param workUser
     * @return 结果
     */
    public int insertWorkUser(WorkUser workUser);

    /**
     * 修改值班人员
     *
     * @param workUser
     * @return 结果
     */
    public int updateWorkUser(WorkUser workUser);

    /**
     * 删除值班人员
     *
     * @param workUserId
     * @return 结果
     */
    public int deleteWorkUserById(Long workUserId);

    /**
     * 批量删除值班人员（使用）
     *
     * @param workUserIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkUserByIds(Long[] workUserIds);

    /**
     * 查询名单全部人员
     * @return
     */
    public List<WorkUser> selectAll();
}
