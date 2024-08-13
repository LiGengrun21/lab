package com.ruoyi.equipment.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equipment.mapper.EquipmentCostMapper;
import com.ruoyi.equipment.domain.EquipmentCost;
import com.ruoyi.equipment.service.IEquipmentCostService;

/**
 * 设备收费Service业务层处理
 *
 */
@Service
public class EquipmentCostServiceImpl implements IEquipmentCostService 
{
    @Autowired
    private EquipmentCostMapper equipmentCostMapper;

    /**
     * 查询设备收费
     * 
     * @param costId 设备收费ID
     * @return 设备收费
     */
    @Override
    public EquipmentCost selectEquipmentCostById(Long costId)
    {
        return equipmentCostMapper.selectEquipmentCostById(costId);
    }

    /**
     * 查询设备收费列表
     * 
     * @param equipmentCost 设备收费
     * @return 设备收费
     */
    @Override
    public List<EquipmentCost> selectEquipmentCostList(EquipmentCost equipmentCost)
    {
        return equipmentCostMapper.selectEquipmentCostList(equipmentCost);
    }

    /**
     * 新增设备收费
     * 
     * @param equipmentCost 设备收费
     * @return 结果
     */
    @Override
    public int insertEquipmentCost(EquipmentCost equipmentCost)
    {
        return equipmentCostMapper.insertEquipmentCost(equipmentCost);
    }

    /**
     * 修改设备收费
     * 
     * @param equipmentCost 设备收费
     * @return 结果
     */
    @Override
    public int updateEquipmentCost(EquipmentCost equipmentCost)
    {
        return equipmentCostMapper.updateEquipmentCost(equipmentCost);
    }

    /**
     * 批量删除设备收费
     * 
     * @param costIds 需要删除的设备收费ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentCostByIds(Long[] costIds)
    {
        return equipmentCostMapper.deleteEquipmentCostByIds(costIds);
    }

    /**
     * 删除设备收费信息
     * 
     * @param costId 设备收费ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentCostById(Long costId)
    {
        return equipmentCostMapper.deleteEquipmentCostById(costId);
    }
    @Override
    public ArrayList<String> selectCostTypesByEquipmentId(Long equipmentId){
        return equipmentCostMapper.selectCostTypesByEquipmentId(equipmentId);
    }
}
