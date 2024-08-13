package com.ruoyi.train.service;

import java.util.List;
import com.ruoyi.train.domain.TrainUser;

/**
 * 培训-用户Service接口
 *
 * 
 * @date 2021-09-18
 */
public interface ITrainUserService
{
    /**
     * 查询培训-用户
     *
     * @param trainId 培训-用户ID
     * @return 培训-用户
     */
    public TrainUser selectTrainUserById(Long trainId , Long userId);

    /**
     * 查询培训-用户列表
     *
     * @param trainUser 培训-用户
     * @return 培训-用户集合
     */
    public List<TrainUser> selectTrainUserList(TrainUser trainUser);

    /**
     * 新增培训-用户
     *
     * @param trainUser 培训-用户
     * @return 结果
     */
    public int insertTrainUser(TrainUser trainUser);

    /**
     * 修改培训-用户
     *
     * @param trainUser 培训-用户
     * @return 结果
     */
    public int updateTrainUser(TrainUser trainUser);

    /**
     * 批量删除培训-用户
     *
     * @param trainIds 需要删除的培训-用户ID
     * @return 结果
     */
    public int deleteTrainUserByIds(Long[] trainIds);

    /**
     * 删除培训-用户信息
     *
     * @param trainId 培训-用户ID
     * @return 结果
     */
    public int deleteTrainUserById(Long trainId);

    public int deleteTrainUser(TrainUser trainUser);
}
