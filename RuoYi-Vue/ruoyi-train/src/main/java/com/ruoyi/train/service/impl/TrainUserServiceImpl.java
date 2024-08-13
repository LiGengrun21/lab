package com.ruoyi.train.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.train.mapper.TrainUserMapper;
import com.ruoyi.train.domain.TrainUser;
import com.ruoyi.train.service.ITrainUserService;

/**
 * 培训-用户Service业务层处理
 *
 * 
 * @date 2021-09-18
 */
@Service
public class TrainUserServiceImpl implements ITrainUserService
{
    @Autowired
    private TrainUserMapper trainUserMapper;

    /**
     * 查询培训-用户
     *
     * @param trainId 培训-用户ID
     * @return 培训-用户
     */
    @Override
    public TrainUser selectTrainUserById(Long trainId,Long userId)
    {
        return trainUserMapper.selectTrainUserById(trainId,userId);
    }

    /**
     * 查询培训-用户列表
     *
     * @param trainUser 培训-用户
     * @return 培训-用户
     */
    @Override
    public List<TrainUser> selectTrainUserList(TrainUser trainUser)
    {
        return trainUserMapper.selectTrainUserList(trainUser);
    }

    /**
     * 新增培训-用户
     *
     * @param trainUser 培训-用户
     * @return 结果
     */
    @Override
    public int insertTrainUser(TrainUser trainUser)
    {
        return trainUserMapper.insertTrainUser(trainUser);
    }

    /**
     * 修改培训-用户
     *
     * @param trainUser 培训-用户
     * @return 结果
     */
    @Override
    public int updateTrainUser(TrainUser trainUser)
    {
        return trainUserMapper.updateTrainUser(trainUser);
    }

    /**
     * 批量删除培训-用户
     *
     * @param trainIds 需要删除的培训-用户ID
     * @return 结果
     */
    @Override
    public int deleteTrainUserByIds(Long[] trainIds)
    {
        return trainUserMapper.deleteTrainUserByIds(trainIds);
    }

    /**
     * 删除培训-用户信息
     *
     * @param trainId 培训-用户ID
     * @return 结果
     */
    @Override
    public int deleteTrainUserById(Long trainId)
    {
        return trainUserMapper.deleteTrainUserById(trainId);
    }

    @Override
    public int deleteTrainUser(TrainUser trainUser) {
        return trainUserMapper.deleteTrainUser(trainUser);
    }
}
