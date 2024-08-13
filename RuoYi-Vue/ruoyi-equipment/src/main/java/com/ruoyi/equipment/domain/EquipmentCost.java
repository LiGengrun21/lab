package com.ruoyi.equipment.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备收费对象 equipment_cost
 *
 */
public class EquipmentCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收费id */
    private Long costId;

    /** 设备id */
    @Excel(name = "设备id")
    private Long equipmentId;

    /** 每小时的收费价格 */
    @Excel(name = "每最小时间段的设备费,单位(元)")
    private BigDecimal costNumber;
    /** 收费类型 */
    @Excel(name = "收费类型")
    private String costType;
    /**
     * 最小时间段,单位(h).
     */
    @Excel(name= "最小时间段,单位(h)")
    private Double minInterval;
    /**
     * 人工费
     */
    @Excel(name="每最小时间段的人工费,单位(元)")
    private BigDecimal artificialCostNumber;
    /**
     * 操作开始前的这段时间内不能取消预约,单位(h).
     */
    @Excel(name="缓冲时间段,单位(h)")
    private Double lastTimeInterval;
    public void setCostId(Long costId) 
    {
        this.costId = costId;
    }

    public Long getCostId() 
    {
        return costId;
    }
    public void setEquipmentId(Long equipmentId) 
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId() 
    {
        return equipmentId;
    }
    public void setCostNumber(BigDecimal costNumber) 
    {
        this.costNumber = costNumber;
    }

    public BigDecimal getCostNumber() 
    {
        return costNumber;
    }
    public void setCostType(String costType) 
    {
        this.costType = costType;
    }

    public String getCostType() 
    {
        return costType;
    }
    public Double getMinInterval(){
        return minInterval;
    }
    public void setMinInterval(Double minInterval){
        this.minInterval=minInterval;
    }
    public BigDecimal getArtificialCostNumber(){
        return artificialCostNumber;
    }
    public void setArtificialCostNumber(BigDecimal artificialCostNumber){
        this.artificialCostNumber=artificialCostNumber;
    }
    public Double getLastTimeInterval(){
        return lastTimeInterval;
    }
    public void setLastTimeInterval(Double lastTimeInterval){
        this.lastTimeInterval=lastTimeInterval;
    }
    @Override
    public String toString(){
        return "EquipmentCost{"+"costId="+costId+", equipmentId="+equipmentId+", costNumber="+costNumber+", costType='"+costType+'\''+", minInterval="+minInterval+", artificialCostNumber="+artificialCostNumber+", lastTimeInterval="+lastTimeInterval+'}';
    }
}
