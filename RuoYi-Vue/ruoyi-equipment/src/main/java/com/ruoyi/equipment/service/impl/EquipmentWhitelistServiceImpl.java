package com.ruoyi.equipment.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equipment.mapper.EquipmentWhitelistMapper;
import com.ruoyi.equipment.domain.EquipmentWhitelist;
import com.ruoyi.equipment.service.IEquipmentWhitelistService;

/**
 * 设备白名单Service业务层处理
 *
 */
@Service
public class EquipmentWhitelistServiceImpl implements IEquipmentWhitelistService
{
    @Autowired
    private EquipmentWhitelistMapper equipmentWhitelistMapper;

    /**
     * 查询设备白名单
     *
     * @param equipmentId 设备白名单ID
     * @return 设备白名单
     */
    @Override
    public ArrayList<EquipmentWhitelist> selectEquipmentWhitelistById(Long equipmentId)
    {
        return equipmentWhitelistMapper.selectEquipmentWhitelistById(equipmentId);
    }

    /**
     * 查询设备白名单列表
     *
     * @param equipmentWhitelist 设备白名单
     * @return 设备白名单
     */
    @Override
    public List<EquipmentWhitelist> selectEquipmentWhitelistList(EquipmentWhitelist equipmentWhitelist)
    {
        return equipmentWhitelistMapper.selectEquipmentWhitelistList(equipmentWhitelist);
    }

    /**
     * 新增设备白名单
     *
     * @param equipmentWhitelist 设备白名单
     * @return 结果
     */
    @Override
    public int insertEquipmentWhitelist(EquipmentWhitelist equipmentWhitelist)
    {
        //若已经存在白名单，重复添加则覆盖
        EquipmentWhitelist ewl = equipmentWhitelistMapper.selectOneEquipmentWhitelist(equipmentWhitelist);
        if(ewl != null) equipmentWhitelistMapper.deleteEquipmentWhitelist(ewl);
        return equipmentWhitelistMapper.insertEquipmentWhitelist(equipmentWhitelist);
    }

    /**
     * 修改设备白名单
     *
     * @param equipmentWhitelist 设备白名单
     * @return 结果
     */
    @Override
    public int updateEquipmentWhitelist(EquipmentWhitelist equipmentWhitelist)
    {
        return equipmentWhitelistMapper.updateEquipmentWhitelist(equipmentWhitelist);
    }

    /**
     * 批量删除设备白名单
     *
     * @param equipmentIds 需要删除的设备白名单ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentWhitelistByIds(Long equipmentId, Long[] userIds)
    {
        return equipmentWhitelistMapper.deleteEquipmentWhitelistByIds(equipmentId,userIds);
    }

    /**
     * 删除设备白名单信息
     *
     * @param equipmentWhitelist 设备白名单
     * @return 结果
     */
    @Override
    public int deleteEquipmentWhitelist(EquipmentWhitelist equipmentWhitelist)
    {
        return equipmentWhitelistMapper.deleteEquipmentWhitelist(equipmentWhitelist);
    }

    /**
     * 批量添加设备白名单
     *
     * @return 结果
     */
    @Override
    public int batchEquipmentWhitelist(@Param("equipmentWhitelistList") List<EquipmentWhitelist> equipmentWhitelistList) {
        //若有重复添加则覆盖
        for(EquipmentWhitelist ewl : equipmentWhitelistList){
            if(equipmentWhitelistMapper.selectOneEquipmentWhitelist(ewl) != null)
                deleteEquipmentWhitelist(ewl);
        }
        return equipmentWhitelistMapper.batchEquipmentWhitelist(equipmentWhitelistList);
    }
}
