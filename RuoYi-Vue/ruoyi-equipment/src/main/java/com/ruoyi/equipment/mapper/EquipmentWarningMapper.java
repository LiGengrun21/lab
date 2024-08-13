package com.ruoyi.equipment.mapper;

import java.util.List;
import com.ruoyi.equipment.domain.EquipmentWarning;

/**
 * 设备报警记录Mapper接口
 *
 */
public interface EquipmentWarningMapper 
{
    /**
     * 查询设备报警记录
     * 
     * @param warningId 设备报警记录ID
     * @return 设备报警记录
     */
    public EquipmentWarning selectEquipmentWarningById(Long warningId);

    /**
     * 查询设备报警记录列表
     * 
     * @param equipmentWarning 设备报警记录
     * @return 设备报警记录集合
     */
    public List<EquipmentWarning> selectEquipmentWarningList(EquipmentWarning equipmentWarning);

    /**
     * 新增设备报警记录
     * 
     * @param equipmentWarning 设备报警记录
     * @return 结果
     */
    public int insertEquipmentWarning(EquipmentWarning equipmentWarning);

    /**
     * 修改设备报警记录
     * 
     * @param equipmentWarning 设备报警记录
     * @return 结果
     */
    public int updateEquipmentWarning(EquipmentWarning equipmentWarning);

    /**
     * 删除设备报警记录
     * 
     * @param warningId 设备报警记录ID
     * @return 结果
     */
    public int deleteEquipmentWarningById(Long warningId);

    /**
     * 批量删除设备报警记录
     * 
     * @param warningIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEquipmentWarningByIds(Long[] warningIds);
}
