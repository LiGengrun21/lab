package com.ruoyi.equipment.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equipment.mapper.EquipmentWarningMapper;
import com.ruoyi.equipment.domain.EquipmentWarning;
import com.ruoyi.equipment.service.IEquipmentWarningService;

/**
 * 设备报警记录Service业务层处理
 *
 */
@Service
public class EquipmentWarningServiceImpl implements IEquipmentWarningService 
{
    @Autowired
    private EquipmentWarningMapper equipmentWarningMapper;

    /**
     * 查询设备报警记录
     * 
     * @param warningId 设备报警记录ID
     * @return 设备报警记录
     */
    @Override
    public EquipmentWarning selectEquipmentWarningById(Long warningId)
    {
        return equipmentWarningMapper.selectEquipmentWarningById(warningId);
    }

    /**
     * 查询设备报警记录列表
     * 
     * @param equipmentWarning 设备报警记录
     * @return 设备报警记录
     */
    @Override
    public List<EquipmentWarning> selectEquipmentWarningList(EquipmentWarning equipmentWarning)
    {
        return equipmentWarningMapper.selectEquipmentWarningList(equipmentWarning);
    }

    /**
     * 新增设备报警记录
     * 
     * @param equipmentWarning 设备报警记录
     * @return 结果
     */
    @Override
    public int insertEquipmentWarning(EquipmentWarning equipmentWarning)
    {
        return equipmentWarningMapper.insertEquipmentWarning(equipmentWarning);
    }

    /**
     * 修改设备报警记录
     * 
     * @param equipmentWarning 设备报警记录
     * @return 结果
     */
    @Override
    public int updateEquipmentWarning(EquipmentWarning equipmentWarning)
    {
        return equipmentWarningMapper.updateEquipmentWarning(equipmentWarning);
    }

    /**
     * 批量删除设备报警记录
     * 
     * @param warningIds 需要删除的设备报警记录ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentWarningByIds(Long[] warningIds)
    {
        return equipmentWarningMapper.deleteEquipmentWarningByIds(warningIds);
    }

    /**
     * 删除设备报警记录信息
     * 
     * @param warningId 设备报警记录ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentWarningById(Long warningId)
    {
        return equipmentWarningMapper.deleteEquipmentWarningById(warningId);
    }
}
