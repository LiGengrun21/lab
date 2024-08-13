package com.ruoyi.equipment.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equipment.mapper.EquipmentFixMapper;
import com.ruoyi.equipment.domain.EquipmentFix;
import com.ruoyi.equipment.service.IEquipmentFixService;

/**
 * 设备维保Service业务层处理
 *
 */
@Service
public class EquipmentFixServiceImpl implements IEquipmentFixService 
{
    @Autowired
    private EquipmentFixMapper equipmentFixMapper;

    /**
     * 查询设备维保
     * 
     * @param fixId 设备维保ID
     * @return 设备维保
     */
    @Override
    public EquipmentFix selectEquipmentFixById(Long fixId)
    {
        return equipmentFixMapper.selectEquipmentFixById(fixId);
    }

    /**
     * 查询设备维保列表
     * 
     * @param equipmentFix 设备维保
     * @return 设备维保
     */
    @Override
    public List<EquipmentFix> selectEquipmentFixList(EquipmentFix equipmentFix)
    {
        return equipmentFixMapper.selectEquipmentFixList(equipmentFix);
    }

    /**
     * 新增设备维保
     * 
     * @param equipmentFix 设备维保
     * @return 结果
     */
    @Override
    public int insertEquipmentFix(EquipmentFix equipmentFix)
    {
        return equipmentFixMapper.insertEquipmentFix(equipmentFix);
    }

    /**
     * 修改设备维保
     * 
     * @param equipmentFix 设备维保
     * @return 结果
     */
    @Override
    public int updateEquipmentFix(EquipmentFix equipmentFix)
    {
        return equipmentFixMapper.updateEquipmentFix(equipmentFix);
    }

    /**
     * 批量删除设备维保
     * 
     * @param fixIds 需要删除的设备维保ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentFixByIds(Long[] fixIds)
    {
        return equipmentFixMapper.deleteEquipmentFixByIds(fixIds);
    }

    /**
     * 删除设备维保信息
     * 
     * @param fixId 设备维保ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentFixById(Long fixId)
    {
        return equipmentFixMapper.deleteEquipmentFixById(fixId);
    }
}
