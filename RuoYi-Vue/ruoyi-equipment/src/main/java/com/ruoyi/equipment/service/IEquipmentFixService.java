package com.ruoyi.equipment.service;

import java.util.List;
import com.ruoyi.equipment.domain.EquipmentFix;

/**
 * 设备维保Service接口
 *
 */
public interface IEquipmentFixService 
{
    /**
     * 查询设备维保
     * 
     * @param fixId 设备维保ID
     * @return 设备维保
     */
    public EquipmentFix selectEquipmentFixById(Long fixId);

    /**
     * 查询设备维保列表
     * 
     * @param equipmentFix 设备维保
     * @return 设备维保集合
     */
    public List<EquipmentFix> selectEquipmentFixList(EquipmentFix equipmentFix);

    /**
     * 新增设备维保
     * 
     * @param equipmentFix 设备维保
     * @return 结果
     */
    public int insertEquipmentFix(EquipmentFix equipmentFix);

    /**
     * 修改设备维保
     * 
     * @param equipmentFix 设备维保
     * @return 结果
     */
    public int updateEquipmentFix(EquipmentFix equipmentFix);

    /**
     * 批量删除设备维保
     * 
     * @param fixIds 需要删除的设备维保ID
     * @return 结果
     */
    public int deleteEquipmentFixByIds(Long[] fixIds);

    /**
     * 删除设备维保信息
     * 
     * @param fixId 设备维保ID
     * @return 结果
     */
    public int deleteEquipmentFixById(Long fixId);
}
