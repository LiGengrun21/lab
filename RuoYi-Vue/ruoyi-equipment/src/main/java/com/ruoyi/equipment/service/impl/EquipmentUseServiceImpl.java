package com.ruoyi.equipment.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.equipment.domain.EquipmentSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equipment.mapper.EquipmentUseMapper;
import com.ruoyi.equipment.domain.EquipmentUse;
import com.ruoyi.equipment.service.IEquipmentUseService;

/**
 * 设备实际使用记录Service业务层处理
 *
 */
@Service
public class EquipmentUseServiceImpl implements IEquipmentUseService
{
    @Autowired
    private EquipmentUseMapper equipmentUseMapper;

    /**
     * 查询设备实际使用记录
     *
     * @param equipmentUseId 设备实际使用记录ID
     * @return 设备实际使用记录
     */
    @Override
    public EquipmentUse selectEquipmentUseById(Long equipmentUseId)
    {
        return equipmentUseMapper.selectEquipmentUseById(equipmentUseId);
    }

    /**
     * 查询设备实际使用记录列表
     *
     * @param equipmentUse 设备实际使用记录
     * @return 设备实际使用记录
     */
    @Override
    public List<EquipmentUse> selectEquipmentUseList(EquipmentUse equipmentUse)
    {
        return equipmentUseMapper.selectEquipmentUseList(equipmentUse);
    }

    @Override
    public List<EquipmentUse> selectEquipmentUseListBySubscribeId(Long equipmentSubscribeId) {
        return equipmentUseMapper.selectEquipmentUseListBySubscribeId(equipmentSubscribeId);
    }

    /**
     * 新增设备实际使用记录
     *
     * @param equipmentUse 设备实际使用记录
     * @return 结果
     */
    @Override
    public int insertEquipmentUse(EquipmentUse equipmentUse)
    {
        return equipmentUseMapper.insertEquipmentUse(equipmentUse);
    }

    /**
     * 修改设备实际使用记录
     *
     * @param equipmentUse 设备实际使用记录
     * @return 结果
     */
    @Override
    public int updateEquipmentUse(EquipmentUse equipmentUse)
    {
        return equipmentUseMapper.updateEquipmentUse(equipmentUse);
    }

    /**
     * 批量删除设备实际使用记录
     *
     * @param equipmentUseIds 需要删除的设备实际使用记录ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentUseByIds(Long[] equipmentUseIds)
    {
        return equipmentUseMapper.deleteEquipmentUseByIds(equipmentUseIds);
    }

    /**
     * 删除设备实际使用记录信息
     *
     * @param equipmentUseId 设备实际使用记录ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentUseById(Long equipmentUseId)
    {
        return equipmentUseMapper.deleteEquipmentUseById(equipmentUseId);
    }

    @Override
    public double equipmentUseCost(Long equipmentUseId) throws ParseException {
        return 0;
    }

}
