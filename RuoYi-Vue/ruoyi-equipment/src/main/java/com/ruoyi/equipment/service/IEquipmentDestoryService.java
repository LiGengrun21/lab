package com.ruoyi.equipment.service;

import java.util.List;
import com.ruoyi.equipment.domain.EquipmentDestory;

/**
 * 设备报废Service接口
 *
 */
public interface IEquipmentDestoryService 
{
    /**
     * 查询设备报废
     * 
     * @param destoryId 设备报废ID
     * @return 设备报废
     */
    public EquipmentDestory selectEquipmentDestoryById(Long destoryId);

    /**
     * 查询设备报废列表
     * 
     * @param equipmentDestory 设备报废
     * @return 设备报废集合
     */
    public List<EquipmentDestory> selectEquipmentDestoryList(EquipmentDestory equipmentDestory);

    /**
     * 新增设备报废
     * 
     * @param equipmentDestory 设备报废
     * @return 结果
     */
    public int insertEquipmentDestory(EquipmentDestory equipmentDestory);

    /**
     * 修改设备报废
     * 
     * @param equipmentDestory 设备报废
     * @return 结果
     */
    public int updateEquipmentDestory(EquipmentDestory equipmentDestory);

    /**
     * 批量删除设备报废
     * 
     * @param destoryIds 需要删除的设备报废ID
     * @return 结果
     */
    public int deleteEquipmentDestoryByIds(Long[] destoryIds);

    /**
     * 删除设备报废信息
     * 
     * @param destoryId 设备报废ID
     * @return 结果
     */
    public int deleteEquipmentDestoryById(Long destoryId);
}
