package com.ruoyi.equipment.mapper;

import java.util.List;

import com.ruoyi.equipment.domain.EquipmentManager;
import com.ruoyi.equipment.domain.ZjutEquipment;
import com.ruoyi.equipment.domain.EquipmentSubscribe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//import com.tuya.connector.api.annotations.GET;
//import com.tuya.connector.api.annotations.Path;

/**
 * 设备Mapper接口
 *
 * 
 * @date 2021-04-25
 */
@Mapper
public interface ZjutEquipmentMapper
{
    /**
     * 查询设备
     *
     * @param equipmentId 设备ID
     * @return 设备
     */
    public ZjutEquipment selectZjutEquipmentById(Long equipmentId);
    public ZjutEquipment selectZjutEquipmentByDeviceId(String equipmentDeviceId);

    /**
     * 查询设备及其白名单
     *
     * @param equipmentId 设备ID
     * @return 设备
     */
    public List<ZjutEquipment> selectEquipmentAndWhitelist(Long equipmentId);

    /**
     * 查询设备列表
     *
     * @param zjutEquipment 设备
     * @return 设备集合
     */
    public List<ZjutEquipment> selectZjutEquipmentList(ZjutEquipment zjutEquipment);

    /**
     * 查询可预约设备
     *
     * @param zjutEquipment 设备
     * @return 设备集合
     */
    public List<ZjutEquipment> selectZjutEquipmentSubscribeList(ZjutEquipment zjutEquipment);

    /**
     * 新增设备
     *
     * @param zjutEquipment 设备
     * @return 结果
     */
    public int insertZjutEquipment(ZjutEquipment zjutEquipment);

    /**
     * 修改设备
     *
     * @param zjutEquipment 设备
     * @return 结果
     */
    public int updateZjutEquipment(ZjutEquipment zjutEquipment);

    /**
     * 修改设备图片
     *
     * @param Long equipmentId, String equipmentPic
     * @return 结果
     */
    public int updateZjutEquipmentPic(@Param("equipmentId")Long equipmentId, @Param("equipmentPic")String equipmentPic);

    /**
     * 删除设备
     *
     * @param equipmentId 设备ID
     * @return 结果
     */
    public int deleteZjutEquipmentById(Long equipmentId);

    /**
     * 批量删除设备
     *
     * @param equipmentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteZjutEquipmentByIds(Long[] equipmentIds);

    /**
     * 批量删除设备预约
     *
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEquipmentSubscribeByEquipmentIds(Long[] equipmentIds);

    /**
     * 批量新增设备预约
     *
     * @param equipmentSubscribeList 设备预约列表
     * @return 结果
     */
    public int batchEquipmentSubscribe(List<EquipmentSubscribe> equipmentSubscribeList);

    /**
     * 批量新增设备-管理员关系
     *
     * @param list 设备管理员列表
     * @return 结果
     */
    public int batchEquipmentManager(List<EquipmentManager> list);

    /**
     * 删除设备-管理员关系
     *
     * @param equipmentId 设备id
     * @return 结果
     */
    int deleteEquipmentManagerByEquipmentId(Long equipmentId);

    /**
     * 批量删除设备-管理员关系
     *
     * @param equipmentId 设备id
     * @return 结果
     */
    int deleteEquipmentManagerByEquipmentIds(Long[] equipmentIds);

    /**
     * 通过设备ID删除设备预约信息
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteEquipmentSubscribeByEquipmentId(Long equipmentId);

    /**
     * 通过条件查询设备数量
     */
    public int selectAmountByCondition(ZjutEquipment equipment);



}

