package com.ruoyi.equipment.service;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.equipment.domain.EquipmentCost;

/**
 * 设备收费Service接口
 */
public interface IEquipmentCostService 
{
    /**
     * 查询设备收费
     * 
     * @param costId 设备收费ID
     * @return 设备收费
     */
    public EquipmentCost selectEquipmentCostById(Long costId);

    /**
     * 查询设备收费列表
     * 
     * @param equipmentCost 设备收费
     * @return 设备收费集合
     */
    public List<EquipmentCost> selectEquipmentCostList(EquipmentCost equipmentCost);

    /**
     * 新增设备收费
     * 
     * @param equipmentCost 设备收费
     * @return 结果
     */
    public int insertEquipmentCost(EquipmentCost equipmentCost);

    /**
     * 修改设备收费
     * 
     * @param equipmentCost 设备收费
     * @return 结果
     */
    public int updateEquipmentCost(EquipmentCost equipmentCost);

    /**
     * 批量删除设备收费
     * 
     * @param costIds 需要删除的设备收费ID
     * @return 结果
     */
    public int deleteEquipmentCostByIds(Long[] costIds);

    /**
     * 删除设备收费信息
     * 
     * @param costId 设备收费ID
     * @return 结果
     */
    public int deleteEquipmentCostById(Long costId);
    /**
     * 获取指定设备的所有收费类型
     *
     * @param equipmentId 设备id
     * @return 结果
     */
    public ArrayList<String> selectCostTypesByEquipmentId(Long equipmentId);
}
