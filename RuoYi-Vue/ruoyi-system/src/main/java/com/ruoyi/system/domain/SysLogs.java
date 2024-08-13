package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * @author 李庚润
 * @date 2022/5/2 14:32
 */
public class SysLogs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    //日志ID
    private Long logsId;

    //用户ID
    private Long userId;

    //日期
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date subscribeDate;

    //开始使用的时间
    @JsonFormat(pattern = "HH:mm:ss")
    private String subscribeStartTime;

    //结束使用的时间
    @JsonFormat(pattern = "HH:mm:ss")
    private String subscribeEndTime;

    //评论
    private String remark;

    //预约的类型（0代表设备,1代表房间,3代表值班管理）
    private int type;

    //预约的ID
    private Long subscribeId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getLogsId() {
        return logsId;
    }

    public void setLogsId(Long logsId) {
        this.logsId = logsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(Date subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public String getSubscribeStartTime() {
        return subscribeStartTime;
    }

    public void setSubscribeStartTime(String subscribeStartTime) {
        this.subscribeStartTime = subscribeStartTime;
    }

    public String getSubscribeEndTime() {
        return subscribeEndTime;
    }

    public void setSubscribeEndTime(String subscribeEndTime) {
        this.subscribeEndTime = subscribeEndTime;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(Long subscribeId) {
        this.subscribeId = subscribeId;
    }

    @Override
    public String toString() {
        return "SysLogs{" +
                "logsId=" + logsId +
                ", userId=" + userId +
                ", subscribeDate=" + subscribeDate +
                ", subscribeStartTime='" + subscribeStartTime + '\'' +
                ", subscribeEndTime='" + subscribeEndTime + '\'' +
                ", remark='" + remark + '\'' +
                ", type=" + type +
                ", subscribeId=" + subscribeId +
                '}';
    }
}
