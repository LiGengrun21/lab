package com.ruoyi.equipment.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.equipment.domain.AggregateResult;
import com.ruoyi.equipment.domain.EquipmentSubscribe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 设备预约Mapper接口
 */
@Mapper
public interface EquipmentSubscribeMapper{
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
    public List<EquipmentSubscribe> selectAuditSubscribeList(EquipmentSubscribe equipmentSubscribe);
    public List<EquipmentSubscribe> selectTodaySubscribe(String subscribeDate);
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
     * 删除设备预约
     *
     * @param equipmentSubscribeId 设备预约ID
     * @return 结果
     */
    public int deleteEquipmentSubscribeById(Long equipmentSubscribeId);
    /**
     * 批量删除设备预约
     *
     * @param equipmentSubscribeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEquipmentSubscribeByIds(Long[] equipmentSubscribeIds);
    /**
     * 查询pi以及pi的组员的预约记录
     *
     * @param piId
     * @param beginDate
     * @param endDate
     * @return
     */
    ArrayList<AggregateResult> selectSubscribesStatisticOfPiAndMembers(@Param("piId") Long piId,@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    /**
     * 以PI为单位进行预约统计
     *
     * @return
     */
    AggregateResult selectSubscribesStatisticByPiUnit(@Param("piId") Long piId,@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    /**
     * 通过用户id查询用户的预约记录
     *
     * @param userId
     * @param beginDate
     * @param endDate
     * @param equipmentName
     * @return
     */
    ArrayList<EquipmentSubscribe> selectSubscribesByUserId(@Param("userId") Long userId,
            @Param("beginDate") String beginDate,@Param("endDate") String endDate,@Param("equipmentName") String equipmentName);
    /**
     * 通过用户id统计用户的预约记录
     *
     * @return
     */
    double selectSubscribesStatisticByUserId(@Param("userId") Long userId,@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    /**
     * 查询PI及其组员的预约记录在通过设备id分组后的设备费
     */
    ArrayList<AggregateResult> selectSubscribesCostOfPiAndMembersByEquipmentUnit(@Param("piId") Long piId,@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    /**
     * 查询某个准确时间被预约的设备的个数
     */
    public int selectAmountOfSubscribedEquipmentByDefiniteTime(@Param("date") String date,@Param("time") String time);
    /**
     * 查询预约结束时间在指定时间之后的预约信息列表
     */
    public ArrayList<EquipmentSubscribe> selectInfoThatEndTimeAfterIndicatedDateTime(@Param("date") String date,@Param("time") String time);
    /**
     * 查询与新增预约存在冲突的预约记录条数
     */
    public int selectConflictSubscribe(EquipmentSubscribe equipmentSubscribe);
    /**
     * 查询用户预约的各种费用统计
     */
    public Map<String,Double> selectEveryCostAggregationByUserId(Long userId);
}
