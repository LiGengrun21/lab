package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.LabSubscribe;
import org.apache.ibatis.annotations.Param;

/**
 * 实验室预约Mapper接口
 *
 * 
 * @date 2021-05-06
 */
public interface LabSubscribeMapper
{
    /**
     * 查询实验室预约
     *
     * @param labSubscribeId 实验室预约ID
     * @return 实验室预约
     */
    public LabSubscribe selectLabSubscribeById(Long labSubscribeId);

    /**
     * 查询实验室预约列表
     *
     * @param labSubscribe 实验室预约
     * @return 实验室预约集合
     */
    public List<LabSubscribe> selectLabSubscribeList(LabSubscribe labSubscribe);
    public List<LabSubscribe> selectAuditLabSubscribeList(LabSubscribe labSubscribe);
    /**
     * 新增实验室预约
     *
     * @param labSubscribe 实验室预约
     * @return 结果
     */
    public int insertLabSubscribe(LabSubscribe labSubscribe);

    /**
     * 修改实验室预约
     *
     * @param labSubscribe 实验室预约
     * @return 结果
     */
    public int updateLabSubscribe(LabSubscribe labSubscribe);

    /**
     * 删除实验室预约
     *
     * @param labSubscribeId 实验室预约ID
     * @return 结果
     */
    public int deleteLabSubscribeById(Long labSubscribeId);

    /**
     * 批量删除实验室预约
     *
     * @param labSubscribeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteLabSubscribeByIds(Long[] labSubscribeIds);
    /**
     * 查询某个准确时间被预约的指定房间的个数
     */
    public int selectAmountOfSubscribedRoomByDefiniteTimeAndRoomType(@Param("date") String date,@Param("time") String time,@Param("roomType") String roomType);
}
