package com.ruoyi.equipment.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.sql.Date;

/**
 * 设备白名单对象 equipment_whitelist
 *
 */
public class EquipmentWhitelist extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备id */
    private Long equipmentId;

    /** 用户id */
    private Long userId;

    /**
     * 添加日期
     */
    private Date addDate;

    /**
     * 添加此用户的用户的名字
     */
    private String adderNickname;

    public void setEquipmentId(Long equipmentId) 
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId() 
    {
        return equipmentId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public Date getAddDate(){
        return addDate;
    }
    public void setAddDate(Date addDate){
        this.addDate=addDate;
    }
    public String getAdderNickname(){
        return adderNickname;
    }
    public void setAdderNickname(String adderNickname){
        this.adderNickname=adderNickname;
    }
    @Override
    public String toString(){
        return "EquipmentWhitelist{"+"equipmentId="+equipmentId+", userId="+userId+", addDate="+addDate+", adderNickname='"+adderNickname+'\''+'}';
    }
}
