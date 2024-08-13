package com.ruoyi.equipment.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.file.domain.*;
import com.ruoyi.equipment.domain.ZjutEquipment;

/**
 * 设备报废对象 equipment_destory
 *
 */
public class EquipmentDestory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备报废id */
    private Long destoryId;

    /** 设备id */
    @Excel(name = "设备id")
    private Long equipmentId;

    /** 报废原因 */
    @Excel(name = "报废原因")
    private String destoryReason;

    /** 报废申请人 */
    @Excel(name = "报废申请人")
    private String destoryPerson;

    /** 设备 */
    private ZjutEquipment zjutEquipment;

    /** 报废文件 */
    private List<DestoryFile> destoryFileList;

    /** 报废日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报废日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date destoryTime;

    public void setDestoryId(Long destoryId)
    {
        this.destoryId = destoryId;
    }

    public Long getDestoryId()
    {
        return destoryId;
    }
    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }
    public void setDestoryReason(String destoryReason)
    {
        this.destoryReason = destoryReason;
    }

    public String getDestoryReason()
    {
        return destoryReason;
    }
    public void setDestoryPerson(String destoryPerson)
    {
        this.destoryPerson = destoryPerson;
    }

    public String getDestoryPerson()
    {
        return destoryPerson;
    }
    public void setDestoryTime(Date destoryTime){this.destoryTime = destoryTime;}

    public Date getDestoryTime(){return destoryTime;}

    public ZjutEquipment getZjutEquipment(){return zjutEquipment;}
    public void setZjutEquipment(ZjutEquipment zjutEquipment) { this.zjutEquipment = zjutEquipment; }

    public List<DestoryFile> getDestoryFileList()
    {
        return destoryFileList;
    }
    public void setDestoryFileList(List<DestoryFile> destoryFileList)
    {
        this.destoryFileList = destoryFileList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("destoryId", getDestoryId())
            .append("equipmentId", getEquipmentId())
            .append("destoryReason", getDestoryReason())
            .append("destoryPerson", getDestoryPerson())
            .append("destoryTime", getDestoryTime())
            .append("zjutEquipment",getZjutEquipment())
            .append("destoryFileList", getDestoryFileList())
            .toString();
    }
}
