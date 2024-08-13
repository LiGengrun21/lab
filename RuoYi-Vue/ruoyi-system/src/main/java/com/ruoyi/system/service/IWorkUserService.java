package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WorkUser;

/**
 * 【值班人员列表 Service接口
 *
 * 
 * @date 2022-07-12
 */
public interface IWorkUserService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param workUserId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public WorkUser selectWorkUserById(Long workUserId);

    /**
     * 根据用户id查询值班人员(使用)
     *
     * @param userId
     * @return 值班人员
     */
    public WorkUser selectWorkUserByUserId(Long userId);

    /**
     * 新增
     *
     * @param workUser
     * @return 结果
     */
    public int insertWorkUser(WorkUser workUser);

    /**
     * 批量新增
     *
     * @param workUserList
     * @return
     */
    public int insertWorkUserList(List<WorkUser> workUserList);


    /**
     * 修改【请填写功能名称】
     *
     * @param workUser 【请填写功能名称】
     * @return 结果
     */
    public int updateWorkUser(WorkUser workUser);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param workUserIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteWorkUserByIds(Long[] workUserIds);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param workUserId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteWorkUserById(Long workUserId);

    /**
     * 查询全部
     *
     * @return
     */
    public List<WorkUser> selectAll();
}
