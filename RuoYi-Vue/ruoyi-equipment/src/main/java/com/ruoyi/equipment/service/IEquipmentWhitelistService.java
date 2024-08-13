package com.ruoyi.equipment.service;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.equipment.domain.EquipmentWhitelist;
import org.apache.ibatis.annotations.Param;

/**
 * 设备白名单Service接口
 *
 */
public interface IEquipmentWhitelistService
{
    /**
     * 查询设备白名单
     *
     * @param equipmentId 设备ID
     * @return 设备白名单
     */
    public ArrayList<EquipmentWhitelist> selectEquipmentWhitelistById(Long equipmentId);

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
     * 批量删除设备白名单
     *
     * @param equipmentIds 需要删除的设备白名单ID
     * @return 结果
     */
    public int deleteEquipmentWhitelistByIds(Long equipmentId, Long[] userIds);

    /**
     * 删除设备白名单信息
     *
     * @param equipmentWhitelist 设备白名单
     * @return 结果
     */
    public int deleteEquipmentWhitelist(EquipmentWhitelist equipmentWhitelist);

    /**
     * 批量添加设备白名单
     *
     * @param  equipmentWhitelistList 设备白名单表
     * @return 结果
     */
    public int batchEquipmentWhitelist(List<EquipmentWhitelist> equipmentWhitelistList);
}
