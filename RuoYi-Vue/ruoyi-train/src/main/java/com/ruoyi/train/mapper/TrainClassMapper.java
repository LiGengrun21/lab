package com.ruoyi.train.mapper;

import java.util.List;
import com.ruoyi.train.domain.TrainClass;

/**
 * 课程Mapper接口
 *
 */
public interface TrainClassMapper 
{
    /**
     * 查询课程
     * 
     * @param classId 课程ID
     * @return 课程
     */
    public TrainClass selectTrainClassById(Long classId);

    /**
     * 查询课程列表
     * 
     * @param trainClass 课程
     * @return 课程集合
     */
    public List<TrainClass> selectTrainClassList(TrainClass trainClass);

    /**
     * 新增课程
     * 
     * @param trainClass 课程
     * @return 结果
     */
    public int insertTrainClass(TrainClass trainClass);

    /**
     * 修改课程
     * 
     * @param trainClass 课程
     * @return 结果
     */
    public int updateTrainClass(TrainClass trainClass);

    /**
     * 删除课程
     * 
     * @param classId 课程ID
     * @return 结果
     */
    public int deleteTrainClassById(Long classId);

    /**
     * 批量删除课程
     * 
     * @param classIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTrainClassByIds(Long[] classIds);
}
