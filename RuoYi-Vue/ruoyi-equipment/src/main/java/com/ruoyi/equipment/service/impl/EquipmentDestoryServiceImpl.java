package com.ruoyi.equipment.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equipment.mapper.EquipmentDestoryMapper;
import com.ruoyi.equipment.domain.EquipmentDestory;
import com.ruoyi.equipment.service.IEquipmentDestoryService;

/**
 * 设备报废Service业务层处理
 *
 */
@Service
public class EquipmentDestoryServiceImpl implements IEquipmentDestoryService 
{
    @Autowired
    private EquipmentDestoryMapper equipmentDestoryMapper;

    /**
     * 查询设备报废
     * 
     * @param destoryId 设备报废ID
     * @return 设备报废
     */
    @Override
    public EquipmentDestory selectEquipmentDestoryById(Long destoryId)
    {
        return equipmentDestoryMapper.selectEquipmentDestoryById(destoryId);
    }

    /**
     * 查询设备报废列表
     * 
     * @param equipmentDestory 设备报废
     * @return 设备报废
     */
    @Override
    public List<EquipmentDestory> selectEquipmentDestoryList(EquipmentDestory equipmentDestory)
    {
        return equipmentDestoryMapper.selectEquipmentDestoryList(equipmentDestory);
    }

    /**
     * 新增设备报废
     * 
     * @param equipmentDestory 设备报废
     * @return 结果
     */
    @Override
    public int insertEquipmentDestory(EquipmentDestory equipmentDestory)
    {
        return equipmentDestoryMapper.insertEquipmentDestory(equipmentDestory);
    }

    /**
     * 修改设备报废
     * 
     * @param equipmentDestory 设备报废
     * @return 结果
     */
    @Override
    public int updateEquipmentDestory(EquipmentDestory equipmentDestory)
    {
        return equipmentDestoryMapper.updateEquipmentDestory(equipmentDestory);
    }

    /**
     * 批量删除设备报废
     * 
     * @param destoryIds 需要删除的设备报废ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentDestoryByIds(Long[] destoryIds)
    {
        return equipmentDestoryMapper.deleteEquipmentDestoryByIds(destoryIds);
    }

    /**
     * 删除设备报废信息
     * 
     * @param destoryId 设备报废ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentDestoryById(Long destoryId)
    {
        return equipmentDestoryMapper.deleteEquipmentDestoryById(destoryId);
    }
}
