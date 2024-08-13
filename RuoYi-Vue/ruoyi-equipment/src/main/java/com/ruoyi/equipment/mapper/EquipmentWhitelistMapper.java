package com.ruoyi.equipment.mapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import com.ruoyi.equipment.domain.EquipmentWhitelist;
import com.ruoyi.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;

/**
 * 设备白名单Mapper接口
 */
public interface EquipmentWhitelistMapper
{
    /**
     * 查询设备白名单
     *
     * @param equipmentId 设备白名单ID
     * @return 设备白名单
     */
    public ArrayList<EquipmentWhitelist> selectEquipmentWhitelistById(Long equipmentId);

    /**
     * 根据联合主键 查询设备白名单
     *
     * @param
     * @return 设备白名单
     */
    public EquipmentWhitelist selectOneEquipmentWhitelist(EquipmentWhitelist equipmentWhitelist);

    /**
     * 查询设备白名单列表
     *
     * @param equipmentWhitelist 设备白名单
     * @return 设备白名单集合
     */
    public List<EquipmentWhitelist> selectEquipmentWhitelistList(EquipmentWhitelist equipmentWhitelist);

    /**
     * 新增设备白名单
     *
     * @param equipmentWhitelist 设备白名单
     * @return 结果
     */
    public int insertEquipmentWhitelist(EquipmentWhitelist equipmentWhitelist);

    /**
     * 修改设备白名单
     *
     * @param equipmentWhitelist 设备白名单
     * @return 结果
     */
    public int updateEquipmentWhitelist(EquipmentWhitelist equipmentWhitelist);

    /**
     * 删除设备白名单
     *
     * @param equipmentWhitelist 设备白名单
     * @return 结果
     */
    public int deleteEquipmentWhitelist(EquipmentWhitelist equipmentWhitelist);

    /**
     * 批量删除设备白名单
     *
     * @param userIds 需要删除的用户的ID
     * @return 结果
     */
    public int deleteEquipmentWhitelistByIds(@Param("equipmentId") Long equipmentId, @Param("userIds") Long[] userIds);

    /**
     * 批量添加设备白名单
     *
     * @param  equipmentWhitelistList 设备白名单表
     * @return 结果
     */
    public int batchEquipmentWhitelist(List<EquipmentWhitelist> equipmentWhitelistList);
}
