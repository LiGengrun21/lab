 package com.ruoyi.lab.domain;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 实验室预约对象 lab_subscribe
 *
 * 
 * @date 2021-05-06
 */
public class LabSubscribe extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 实验室预约id */
    private Long labSubscribeId;

    /** 实验室id */
    @Excel(name = "实验室id")
    private Long labId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date labSubscribeDate;

    /** 预约开始时间 */
    @JsonFormat(pattern = "HH:mm:ss")
    @Excel(name = "预约开始时间", width = 30, dateFormat = "HH:mm:ss")
    private String labSubscribeStartTime;

    /** 预约结束时间 */
    @JsonFormat(pattern = "HH:mm:ss")
    @Excel(name = "预约结束时间", width = 30, dateFormat = "HH:mm:ss")
    private String labSubscribeEndTime;


    /** 实验室预约人 */
    @Excel(name = "实验室预约人")
    private String labSubscribePerson;

    /** 实验室预约用户id */
    private Long userId;

    private ZjutLab zjutLab;

    private String labSubscribeStatus;
    private String labSubscribePhone;

    public void setLabSubscribeId(Long labSubscribeId)
    {
        this.labSubscribeId = labSubscribeId;
    }

    public Long getLabSubscribeId()
    {
        return labSubscribeId;
    }
    public void setLabId(Long labId)
    {
        this.labId = labId;
    }

    public Long getLabId()
    {
        return labId;
    }

    public void setLabSubscribeDate(Date labSubscribeDate){this.labSubscribeDate = labSubscribeDate; }
    public Date getLabSubscribeDate(){return labSubscribeDate; }
    public void setLabSubscribeStartTime(String labSubscribeStartTime) { this.labSubscribeStartTime = labSubscribeStartTime; }
    public String  getLabSubscribeStartTime()
    {
        return labSubscribeStartTime;
    }
    public void setLabSubscribeEndTime(String  labSubscribeEndTime)
    {
        this.labSubscribeEndTime = labSubscribeEndTime;
    }
    public String  getLabSubscribeEndTime()
    {
        return labSubscribeEndTime;
    }

    public void setLabSubscribePerson(String labSubscribePerson)
    {
        this.labSubscribePerson = labSubscribePerson;
    }
    public String getLabSubscribePerson()
    {
        return labSubscribePerson;
    }

    public void setZjutLab(ZjutLab zjutLab){this.zjutLab = zjutLab;}
    public ZjutLab getZjutLab(){return zjutLab;}

    public void setLabSubscribeStatus(String labSubscribeStatus)
    {
        this.labSubscribeStatus = labSubscribeStatus;
    }
    public String getLabSubscribeStatus()
    {
        return labSubscribeStatus;
    }

    public void setLabSubscribePhone(String labSubscribePhone)
    {
        this.labSubscribePhone = labSubscribePhone;
    }
    public String getLabSubscribePhone()
    {
        return labSubscribePhone;
    }

    public void setUserId(Long userId) {this.userId = userId;}
    public Long getUserId() {return userId;}

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("labSubscribeId", getLabSubscribeId())
            .append("labId", getLabId())
            .append("labSubscribeDate", getLabSubscribeDate())
            .append("labSubscribeStartTime", getLabSubscribeStartTime())
            .append("labSubscribeEndTime", getLabSubscribeEndTime())
            .append("labSubscribePerson", getLabSubscribePerson())
            .append("labSubscribeStatus",getLabSubscribeStatus())
            .append("labSubscribePhone",getLabSubscribePhone())
            .append("zjutLab", getZjutLab())
            .toString();
    }
}
