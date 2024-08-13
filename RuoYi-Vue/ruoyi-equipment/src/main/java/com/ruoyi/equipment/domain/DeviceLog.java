package com.ruoyi.equipment.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备运行统计对象 device_log
 *
 */
public class DeviceLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 插座日志id */
    private Long deviceLogId;

    /** 插座id */
    @Excel(name = "插座id")
    private String deviceId;

    /** 插座电流 */
    @Excel(name = "插座电流")
    private String deviceCurrent;

    /** 插座功率 */
    @Excel(name = "插座功率")
    private String devicePower;

    /** 插座电压 */
    @Excel(name = "插座电压")
    private String deviceVoltage;

    /** 插座开关 */
    @Excel(name = "插座开关")
    private String deviceStatus;

    /** 统计时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "统计时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date logTime;

    public void setDeviceLogId(Long deviceLogId)
    {
        this.deviceLogId = deviceLogId;
    }

    public Long getDeviceLogId()
    {
        return deviceLogId;
    }
    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId()
    {
        return deviceId;
    }
    public void setDeviceCurrent(String deviceCurrent)
    {
        this.deviceCurrent = deviceCurrent;
    }

    public String getDeviceCurrent()
    {
        return deviceCurrent;
    }
    public void setDevicePower(String devicePower)
    {
        this.devicePower = devicePower;
    }

    public String getDevicePower()
    {
        return devicePower;
    }
    public void setDeviceVoltage(String deviceVoltage)
    {
        this.deviceVoltage = deviceVoltage;
    }

    public String getDeviceVoltage()
    {
        return deviceVoltage;
    }
    public void setDeviceStatus(String deviceStatus)
    {
        this.deviceStatus = deviceStatus;
    }

    public String getDeviceStatus()
    {
        return deviceStatus;
    }
    public void setLogTime(Date logTime)
    {
        this.logTime = logTime;
    }

    public Date getLogTime()
    {
        return logTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deviceLogId", getDeviceLogId())
            .append("deviceId", getDeviceId())
            .append("deviceCurrent", getDeviceCurrent())
            .append("devicePower", getDevicePower())
            .append("deviceVoltage", getDeviceVoltage())
            .append("deviceStatus", getDeviceStatus())
            .append("logTime", getLogTime())
            .toString();
    }
}
