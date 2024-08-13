package com.ruoyi.equipment.service;

import java.text.ParseException;
import java.util.List;
import com.ruoyi.equipment.domain.EquipmentUse;

/**
 * 设备实际使用记录Service接口
 */
public interface IEquipmentUseService
{
    /**
     * 查询设备实际使用记录
     *
     * @param equipmentUseId 设备实际使用记录ID
     * @return 设备实际使用记录
     */
    public EquipmentUse selectEquipmentUseById(Long equipmentUseId);

    /**
     * 查询设备实际使用记录列表
     *
     * @param equipmentUse 设备实际使用记录
     * @return 设备实际使用记录集合
     */
    public List<EquipmentUse> selectEquipmentUseList(EquipmentUse equipmentUse);

    public List<EquipmentUse> selectEquipmentUseListBySubscribeId(Long equipmentSubscribeId);

    /**
     * 新增设备实际使用记录
     *
     * @param equipmentUse 设备实际使用记录
     * @return 结果
     */
    public int insertEquipmentUse(EquipmentUse equipmentUse);

    /**
     * 修改设备实际使用记录
     *
     * @param equipmentUse 设备实际使用记录
     * @return 结果
     */
    public int updateEquipmentUse(EquipmentUse equipmentUse);

    /**
     * 批量删除设备实际使用记录
     *
     * @param equipmentUseIds 需要删除的设备实际使用记录ID
     * @return 结果
     */
    public int deleteEquipmentUseByIds(Long[] equipmentUseIds);

    /**
     * 删除设备实际使用记录信息
     *
     * @param equipmentUseId 设备实际使用记录ID
     * @return 结果
     */
    public int deleteEquipmentUseById(Long equipmentUseId);

    /**
     * 根据使用id计算费用
     *
     * @param equipmentUseId 设备实际使用记录ID
     * @return 结果
     */
    public double equipmentUseCost(Long equipmentUseId) throws ParseException;
}
