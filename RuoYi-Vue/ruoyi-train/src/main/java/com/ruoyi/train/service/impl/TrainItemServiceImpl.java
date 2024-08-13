package com.ruoyi.train.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.train.mapper.TrainItemMapper;
import com.ruoyi.train.domain.TrainItem;
import com.ruoyi.train.service.ITrainItemService;

/**
 * 培训Service业务层处理
 *
 *
 * @date 2021-09-18
 */
@Service
public class TrainItemServiceImpl implements ITrainItemService
{
    @Autowired
    private TrainItemMapper trainItemMapper;

    /**
     * 查询培训
     *
     * @param trainId 培训ID
     * @return 培训
     */
    @Override
    public TrainItem selectTrainItemById(Long trainId)
    {
        return trainItemMapper.selectTrainItemById(trainId);
    }

    @Override
    public TrainItem selectTrainAndUserListById(Long trainId) {
        return trainItemMapper.selectTrainAndUserListById(trainId);
    }

    /**
     * 查询培训列表
     *
     * @param trainItem 培训
     * @return 培训
     */
    @Override
    public List<TrainItem> selectTrainItemList(TrainItem trainItem)
    {
        return trainItemMapper.selectTrainItemList(trainItem);
    }

    /**
     * 新增培训
     *
     * @param trainItem 培训
     * @return 结果
     */
    @Override
    public int insertTrainItem(TrainItem trainItem)
    {
        return trainItemMapper.insertTrainItem(trainItem);
    }

    /**
     * 修改培训
     *
     * @param trainItem 培训
     * @return 结果
     */
    @Override
    public int updateTrainItem(TrainItem trainItem)
    {
        return trainItemMapper.updateTrainItem(trainItem);
    }

    /**
     * 批量删除培训
     *
     * @param trainIds 需要删除的培训ID
     * @return 结果
     */
    @Override
    public int deleteTrainItemByIds(Long[] trainIds)
    {
        return trainItemMapper.deleteTrainItemByIds(trainIds);
    }

    /**
     * 删除培训信息
     *
     * @param trainId 培训ID
     * @return 结果
     */
    @Override
    public int deleteTrainItemById(Long trainId)
    {
        return trainItemMapper.deleteTrainItemById(trainId);
    }
}
