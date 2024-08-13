package com.ruoyi.equipment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.equipment.domain.AggregateResult;
import com.ruoyi.equipment.domain.EquipmentSubscribe;

/**
 * 设备预约Service接口
 *
 */
public interface IEquipmentSubscribeService
{
    /**
     * 查询设备预约
     *
     * @param equipmentSubscribeId 设备预约ID
     * @return 设备预约
     */
    public EquipmentSubscribe selectEquipmentSubscribeById(Long equipmentSubscribeId);

    /**
     * 查询设备预约列表
     *
     * @param equipmentSubscribe 设备预约
     * @return 设备预约集合
     */
    public List<EquipmentSubscribe> selectEquipmentSubscribeList(EquipmentSubscribe equipmentSubscribe);

    public List<EquipmentSubscribe> selectmyEquipmentSubscribeList(EquipmentSubscribe equipmentSubscribe);

    public List<EquipmentSubscribe> selectAuditSubscribeList(EquipmentSubscribe equipmentSubscribe);

    public List<EquipmentSubscribe> selectTodaySubscribe();

    public List<EquipmentSubscribe> selectTBDSubscribeList(EquipmentSubscribe equipmentSubscribe);
    /**
     * 新增设备预约
     *
     * @param equipmentSubscribe 设备预约
     * @return 结果
     */
    public int insertEquipmentSubscribe(EquipmentSubscribe equipmentSubscribe);

    /**
     * 修改设备预约
     *
     * @param equipmentSubscribe 设备预约
     * @return 结果
     */
    public int updateEquipmentSubscribe(EquipmentSubscribe equipmentSubscribe);

    /**
     * 批量删除设备预约
     *
     * @param equipmentSubscribeIds 需要删除的设备预约ID
     * @return 结果
     */
    public int deleteEquipmentSubscribeByIds(Long[] equipmentSubscribeIds);

    /**
     * 删除设备预约信息
     *
     * @param equipmentSubscribeId 设备预约ID
     * @return 结果
     */
    public int deleteEquipmentSubscribeById(Long equipmentSubscribeId);
    /**
     * 统计pi以及pi的组员的预约记录
     *
     * @param piId
     * @param beginDate
     * @param endDate
     * @return
     */
    public ArrayList<AggregateResult> selectSubscribesStatisticOfPiAndMembers(Long piId,String beginDate,String endDate);
    /**
     * 以PI为单位进行预约统计
     * @return
     * @param piId
     * @param beginDate
     * @param endDate
     */
    AggregateResult selectSubscribesStatisticByPiUnit(Long piId,String beginDate,String endDate);
    /**
     * 通过用户id查询用户的预约记录
     * @param userId
     * @param beginDate
     * @param endDate
     * @param equipmentName
     * @return
     */
    ArrayList<EquipmentSubscribe> selectSubscribesByUserId(Long userId,String beginDate,String endDate,String equipmentName);
    /**
     * 通过用户id统计用户的预约记录
     *
     * @return
     */
    double selectSubscribesStatisticByUserId(Long userId,String beginDate,String endDate);
    /**
     * 查询PI及其组员的预约记录在通过设备id分组后的设备费
     */
    ArrayList<AggregateResult> selectSubscribesCostOfPiAndMembersByEquipmentUnit(Long piId,String beginDate,String endDate);
    /**
     * 查询某个准确时间被预约的设备的个数
     */
    public int selectAmountOfSubscribedEquipmentByDefiniteTime(String date,String time);
    /**
     * 查询预约结束时间在指定时间之后的预约信息列表
     */
    public ArrayList<EquipmentSubscribe> selectInfoThatEndTimeAfterIndicatedDateTime(String date,String time);
    /**
     * 查询冲突的预约请求
     */
    public int checkConflictSubscribe(EquipmentSubscribe equipmentSubscribe);
    /**
     * 查询用户预约的各种费用统计
     */
    public Map<String,Double> selectEveryCostAggregationByUserId(Long userId);
}

