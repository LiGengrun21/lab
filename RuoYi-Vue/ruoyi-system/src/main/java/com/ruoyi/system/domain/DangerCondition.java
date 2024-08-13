package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 危险事故对象 danger_condition
 * 
 * 
 * @date 2021-09-19
 */
public class DangerCondition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 危险事故id */
    private Long id;

    /** 发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发生时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date happenTime;

    /** 发生地点 */
    @Excel(name = "发生地点")
    private String happenAddress;

    /** 具体情况 */
    @Excel(name = "具体情况")
    private String detail;

    /** 上报时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上报时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;



    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setHappenTime(Date happenTime) 
    {
        this.happenTime = happenTime;
    }

    public Date getHappenTime() 
    {
        return happenTime;
    }
    public void setHappenAddress(String happenAddress) 
    {
        this.happenAddress = happenAddress;
    }

    public String getHappenAddress() 
    {
        return happenAddress;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }
    @Override
    public String toString(){
        return "DangerCondition{"+"id="+id+", happenTime="+happenTime+", happenAddress='"+happenAddress+'\''+", detail='"+detail+'\''+", submitTime="+submitTime+'}';
    }
}
