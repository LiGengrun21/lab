package com.ruoyi.equipment.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
* @Description: 设备-管理员关系对象
*/

public class EquipmentManager extends BaseEntity {

    private static final long serialVersionUID=1L;

    /** 设备id */
    private Long equipmentId;

    /** 管理员用户id */
    private Long userId;

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
