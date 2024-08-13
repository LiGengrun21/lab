package com.ruoyi.lab.service;

import java.util.List;
import com.ruoyi.lab.domain.LabSubscribe;

/**
 * 实验室预约Service接口
 *
 *
 * @date 2021-05-06
 */
public interface ILabSubscribeService
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
     * 批量删除实验室预约
     *
     * @param labSubscribeIds 需要删除的实验室预约ID
     * @return 结果
     */
    public int deleteLabSubscribeByIds(Long[] labSubscribeIds);

    /**
     * 删除实验室预约信息
     *
     * @param labSubscribeId 实验室预约ID
     * @return 结果
     */
    public int deleteLabSubscribeById(Long labSubscribeId);
    /**
     *查询某个准确时间被预约的指定房间的个数
     */
    public int selectAmountOfSubscribedRoomByDefiniteTimeAndRoomType(String date,String time,String roomType);

}
