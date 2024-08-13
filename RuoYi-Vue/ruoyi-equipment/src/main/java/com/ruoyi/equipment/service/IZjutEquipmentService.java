package com.ruoyi.equipment.service;

import java.util.List;
import com.ruoyi.equipment.domain.ZjutEquipment;
//import com.tuya.connector.api.annotations.Body;
//import com.tuya.connector.api.annotations.GET;
//import com.tuya.connector.api.annotations.POST;
//import com.tuya.connector.api.annotations.Path;
import org.apache.ibatis.annotations.Param;

/**
 * 设备Service接口
 */
public interface IZjutEquipmentService
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
     * 查询可预约的设备列表
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
    public boolean updateZjutEquipmentPic(Long equipmentId, String equipmentPic);

    /**
     * 批量删除设备
     *
     * @param equipmentIds 需要删除的设备ID
     * @return 结果
     */
    public int deleteZjutEquipmentByIds(Long[] equipmentIds);

    /**
     * 删除设备信息
     *
     * @param equipmentId 设备ID
     * @return 结果
     */
    public int deleteZjutEquipmentById(Long equipmentId);

    /**
     * 通过条件查询设备数量
     */
    public int selectAmountByCondition(ZjutEquipment equipment);
}
