package com.ruoyi.equipment.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备实际使用记录对象 equipment_use
 *
 */
public class EquipmentUse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备使用id */
    private Long equipmentUseId;

    private Long equipmentSubscribeId;

    /** 设备id */
    @Excel(name = "设备id")
    private Long equipmentId;

    /** 使用日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String useDate;

    /** 使用开始时间 */
    @JsonFormat(pattern = "HH:mm:ss")
    @Excel(name = "使用开始时间", width = 30, dateFormat = "HH:mm:ss")
    private String useStartTime;

    /** 使用结束时间 */
    @JsonFormat(pattern = "HH:mm:ss")
    @Excel(name = "使用结束时间", width = 30, dateFormat = "HH:mm:ss")
    private String useEndTime;

    /** 使用者 */
    @Excel(name = "使用者")
    private String useName;

    /** 使用花费 */
    @Excel(name = "使用花费")
    private BigDecimal useCost;

    public void setEquipmentSubscribeId(Long equipmentSubscribeId)
    {
        this.equipmentSubscribeId = equipmentSubscribeId;
    }
    public Long getEquipmentSubscribeId()
    {
        return equipmentSubscribeId;
    }

    public void setEquipmentUseId(Long equipmentUseId)
    {
        this.equipmentUseId = equipmentUseId;
    }

    public Long getEquipmentUseId()
    {
        return equipmentUseId;
    }
    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }
    public void setUseDate(String useDate)
    {
        this.useDate = useDate;
    }

    public String getUseDate()
    {
        return useDate;
    }
    public void setUseStartTime(String useStartTime)
    {
        this.useStartTime = useStartTime;
    }

    public String getUseStartTime()
    {
        return useStartTime;
    }
    public void setUseEndTime(String useEndTime)
    {
        this.useEndTime = useEndTime;
    }

    public String getUseEndTime()
    {
        return useEndTime;
    }
    public void setUseName(String useName)
    {
        this.useName = useName;
    }

    public String getUseName()
    {
        return useName;
    }
    public void setUseCost(BigDecimal useCost)
    {
        this.useCost = useCost;
    }

    public BigDecimal getUseCost()
    {
        return useCost;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("equipmentUseId", getEquipmentUseId())
            .append("equipmentSubscribeId",getEquipmentSubscribeId())
            .append("equipmentId", getEquipmentId())
            .append("useDate", getUseDate())
            .append("useStartTime", getUseStartTime())
            .append("useEndTime", getUseEndTime())
            .append("useName", getUseName())
            .append("useCost", getUseCost())
            .toString();
    }
}
