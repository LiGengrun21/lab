package com.ruoyi.lab.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.LabSubscribeMapper;
import com.ruoyi.lab.domain.LabSubscribe;
import com.ruoyi.lab.service.ILabSubscribeService;

/**
 * 实验室预约Service业务层处理
 *
 * 
 * @date 2021-05-06
 */
@Service
public class LabSubscribeServiceImpl implements ILabSubscribeService
{
    @Autowired
    private LabSubscribeMapper labSubscribeMapper;

    /**
     * 查询实验室预约
     *
     * @param labSubscribeId 实验室预约ID
     * @return 实验室预约
     */
    @Override
    public LabSubscribe selectLabSubscribeById(Long labSubscribeId)
    {
        return labSubscribeMapper.selectLabSubscribeById(labSubscribeId);
    }

    /**
     * 查询实验室预约列表
     *
     * @param labSubscribe 实验室预约
     * @return 实验室预约
     */
    @Override
    public List<LabSubscribe> selectLabSubscribeList(LabSubscribe labSubscribe)
    {
        return labSubscribeMapper.selectLabSubscribeList(labSubscribe);
    }

    @Override
    public List<LabSubscribe> selectAuditLabSubscribeList(LabSubscribe labSubscribe) {
        return labSubscribeMapper.selectAuditLabSubscribeList(labSubscribe);
    }

    /**
     * 新增实验室预约
     *
     * @param labSubscribe 实验室预约
     * @return 结果
     */
    @Override
    public int insertLabSubscribe(LabSubscribe labSubscribe)
    {
        return labSubscribeMapper.insertLabSubscribe(labSubscribe);
    }

    /**
     * 修改实验室预约
     *
     * @param labSubscribe 实验室预约
     * @return 结果
     */
    @Override
    public int updateLabSubscribe(LabSubscribe labSubscribe)
    {
        return labSubscribeMapper.updateLabSubscribe(labSubscribe);
    }

    /**
     * 批量删除实验室预约
     *
     * @param labSubscribeIds 需要删除的实验室预约ID
     * @return 结果
     */
    @Override
    public int deleteLabSubscribeByIds(Long[] labSubscribeIds)
    {
        return labSubscribeMapper.deleteLabSubscribeByIds(labSubscribeIds);
    }

    /**
     * 删除实验室预约信息
     *
     * @param labSubscribeId 实验室预约ID
     * @return 结果
     */
    @Override
    public int deleteLabSubscribeById(Long labSubscribeId)
    {
        return labSubscribeMapper.deleteLabSubscribeById(labSubscribeId);
    }
    @Override
    public int selectAmountOfSubscribedRoomByDefiniteTimeAndRoomType(String date,String time,String roomType){
        return labSubscribeMapper.selectAmountOfSubscribedRoomByDefiniteTimeAndRoomType(date,time,roomType);
    }

}
