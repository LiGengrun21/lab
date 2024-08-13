package com.ruoyi.train.mapper;

import java.util.List;
import com.ruoyi.train.domain.TrainItem;

/**
 * 培训Mapper接口
 */
public interface TrainItemMapper
{
    /**
     * 查询培训
     *
     * @param trainId 培训ID
     * @return 培训
     */
    public TrainItem selectTrainItemById(Long trainId);
    public TrainItem selectTrainAndUserListById(Long trainId);
    /**
     * 查询培训列表
     *
     * @param trainItem 培训
     * @return 培训集合
     */
    public List<TrainItem> selectTrainItemList(TrainItem trainItem);

    /**
     * 新增培训
     *
     * @param trainItem 培训
     * @return 结果
     */
    public int insertTrainItem(TrainItem trainItem);

    /**
     * 修改培训
     *
     * @param trainItem 培训
     * @return 结果
     */
    public int updateTrainItem(TrainItem trainItem);

    /**
     * 删除培训
     *
     * @param trainId 培训ID
     * @return 结果
     */
    public int deleteTrainItemById(Long trainId);

    /**
     * 批量删除培训
     *
     * @param trainIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTrainItemByIds(Long[] trainIds);
}
