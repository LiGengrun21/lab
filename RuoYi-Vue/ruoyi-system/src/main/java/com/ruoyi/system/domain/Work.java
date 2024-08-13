package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 值班对象 work
 *
 *
 * @date 2021-09-19
 */
public class Work extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 值班id */
    private Long workId;

    /** 值班时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "值班时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date workDate;
    /**
     * 值班用户id
     */
    @Excel(name = "值班人员ID")
    private Long userId;
    /**
     * 值班用户
     */
    private SysUser user;

    @Excel(name = "值班人员")
    private String nickName;


    public void setWorkId(Long workId)
    {
        this.workId = workId;
    }

    public Long getWorkId()
    {
        return workId;
    }
    public void setWorkDate(Date workDate)
    {
        this.workDate = workDate;
    }

    public Date getWorkDate()
    {
        return workDate;
    }
    public SysUser getUser(){
        return user;
    }
    public void setUser(SysUser user){
        this.user=user;
        this.nickName=user.getNickName();
    }
    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId=userId;
    }

    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString(){
        return "Work{"+"workId="+workId+", workDate="+workDate+", userId="+userId+", user="+user+'}';
    }
}
