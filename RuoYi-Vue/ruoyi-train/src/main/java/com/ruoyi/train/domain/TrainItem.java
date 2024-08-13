package com.ruoyi.train.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 培训对象 train_item
 *
 */
public class TrainItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 培训id */
    private Long trainId;

    private Long classId;

    /** 培训发布人 */
    @Excel(name = "培训发布人")
    private String trainAdder;

    /** 培训时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "培训时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date trainTime;

    /** 培训地点 */
    @Excel(name = "培训地点")
    private String trainAd;

    /** 培训备注 */
    @Excel(name = "培训备注")
    private String trainTip;

    /** 培训最大人数 */
    @Excel(name = "培训最大人数")
    private Long trainMaxPerson;

    private List<SysUser> sysUser;
    private List<TrainUser> trainUser;

    public void setTrainId(Long trainId)
    {
        this.trainId = trainId;
    }

    public Long getTrainId()
    {
        return trainId;
    }

    public void setTrainAdder(String trainAdder)
    {
        this.trainAdder = trainAdder;
    }

    public String getTrainAdder()
    {
        return trainAdder;
    }
    public void setTrainTime(Date trainTime)
    {
        this.trainTime = trainTime;
    }

    public Date getTrainTime()
    {
        return trainTime;
    }
    public void setTrainAd(String trainAd)
    {
        this.trainAd = trainAd;
    }

    public String getTrainAd()
    {
        return trainAd;
    }
    public void setTrainTip(String trainTip)
    {
        this.trainTip = trainTip;
    }

    public String getTrainTip()
    {
        return trainTip;
    }
    public void setTrainMaxPerson(Long trainMaxPerson)
    {
        this.trainMaxPerson = trainMaxPerson;
    }

    public Long getTrainMaxPerson()
    {
        return trainMaxPerson;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public void setSysUser(List<SysUser> sysUser) {
        this.sysUser = sysUser;
    }

    public List<SysUser> getSysUser() {
        return sysUser;
    }

    public void setTrainUser(List<TrainUser> trainUser) {
        this.trainUser = trainUser;
    }

    public List<TrainUser> getTrainUser() {
        return trainUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("trainId", getTrainId())
            .append("trainAdder", getTrainAdder())
            .append("trainTime", getTrainTime())
            .append("trainAd", getTrainAd())
            .append("trainTip", getTrainTip())
            .append("trainMaxPerson", getTrainMaxPerson())
            .append("classId",getClassId())
            .append("SysUser",getSysUser())
            .append("TrainUser",getTrainUser())
            .toString();
    }
}
