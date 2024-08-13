package com.ruoyi.train.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.train.mapper.TrainClassMapper;
import com.ruoyi.train.domain.TrainClass;
import com.ruoyi.train.service.ITrainClassService;

/**
 * 课程Service业务层处理
 * 
 *
 * @date 2021-09-18
 */
@Service
public class TrainClassServiceImpl implements ITrainClassService 
{
    @Autowired
    private TrainClassMapper trainClassMapper;

    /**
     * 查询课程
     * 
     * @param classId 课程ID
     * @return 课程
     */
    @Override
    public TrainClass selectTrainClassById(Long classId)
    {
        return trainClassMapper.selectTrainClassById(classId);
    }

    /**
     * 查询课程列表
     * 
     * @param trainClass 课程
     * @return 课程
     */
    @Override
    public List<TrainClass> selectTrainClassList(TrainClass trainClass)
    {
        return trainClassMapper.selectTrainClassList(trainClass);
    }

    /**
     * 新增课程
     * 
     * @param trainClass 课程
     * @return 结果
     */
    @Override
    public int insertTrainClass(TrainClass trainClass)
    {
        return trainClassMapper.insertTrainClass(trainClass);
    }

    /**
     * 修改课程
     * 
     * @param trainClass 课程
     * @return 结果
     */
    @Override
    public int updateTrainClass(TrainClass trainClass)
    {
        return trainClassMapper.updateTrainClass(trainClass);
    }

    /**
     * 批量删除课程
     * 
     * @param classIds 需要删除的课程ID
     * @return 结果
     */
    @Override
    public int deleteTrainClassByIds(Long[] classIds)
    {
        return trainClassMapper.deleteTrainClassByIds(classIds);
    }

    /**
     * 删除课程信息
     * 
     * @param classId 课程ID
     * @return 结果
     */
    @Override
    public int deleteTrainClassById(Long classId)
    {
        return trainClassMapper.deleteTrainClassById(classId);
    }
}
