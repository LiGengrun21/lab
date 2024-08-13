package com.ruoyi.equipment.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.file.domain.DestoryFile;
import com.ruoyi.file.domain.FixFile;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备维保对象 equipment_fix
 *
 */
public class EquipmentFix extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备维保id */
    private Long fixId;

    /** 设备id */
    @Excel(name = "设备id")
    private Long equipmentId;

    /** 维保开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "维保开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fixStartTime;

    /** 维保结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "维保结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fixEndTime;
    /** 维保原因 */
    @Excel(name = "维保原因")
    private String fixReason;
    /**
     * 维修状态
     */
    @Excel(name= "维修状态")
    private String fixStatus;
    /** 设备 */
    private ZjutEquipment zjutEquipment;
    /** 报废文件 */
    private List<FixFile> fixFileList;
    /**
     * 预报修
     */
    public static final String PRE_FIX="0";
    /**
     * 确认报修
     */
    public static final String CONFIRM_FIX="1";
    /**
     * 报修结束
     */
    public static final String FINISH_FIX="2";
    public void setFixId(Long fixId)
    {
        this.fixId = fixId;
    }

    public Long getFixId()
    {
        return fixId;
    }
    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }
    public void setFixStartTime(Date fixStartTime)
    {
        this.fixStartTime = fixStartTime;
    }

    public Date getFixStartTime()
    {
        return fixStartTime;
    }
    public void setFixEndTime(Date fixEndTime)
    {
        this.fixEndTime = fixEndTime;
    }

    public Date getFixEndTime()
    {
        return fixEndTime;
    }
    public void setFixReason(String fixReason)
    {
        this.fixReason = fixReason;
    }

    public String getFixReason()
    {
        return fixReason;
    }

    public ZjutEquipment getZjutEquipment(){return zjutEquipment;}
    public void setZjutEquipment(ZjutEquipment zjutEquipment) { this.zjutEquipment = zjutEquipment; }

    public List<FixFile> getFixFileList()
    {
        return fixFileList;
    }
    public void setFixFileList(List<FixFile> fixFileList) { this.fixFileList = fixFileList; }
    public String getFixStatus(){
        return fixStatus;
    }
    public void setFixStatus(String fixStatus){
        this.fixStatus=fixStatus;
    }
    @Override
    public String toString(){
        return "EquipmentFix{"+"fixId="+fixId+", equipmentId="+equipmentId+", fixStartTime="+fixStartTime+", fixEndTime="+fixEndTime+", fixReason='"+fixReason+'\''+", fixStatus='"+fixStatus+'\''+", zjutEquipment="+zjutEquipment+", fixFileList="+fixFileList+'}';
    }
}
