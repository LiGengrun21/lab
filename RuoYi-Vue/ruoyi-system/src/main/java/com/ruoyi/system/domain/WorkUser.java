package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 值班人员列表
 *
 *
 * @date 2022-07-12
 */
public class WorkUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 值班人员id */
    private Long workUserId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /**
     * 值班用户
     */
    private SysUser user;

    /**
     * 值班人员昵称
     */
    @Excel(name = "值班人员昵称")
    private String nickName;

    public void setWorkUserId(Long workUserId)
    {
        this.workUserId = workUserId;
    }

    public Long getWorkUserId()
    {
        return workUserId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
        this.nickName=user.getNickName();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "WorkUser{" +
                "workUserId=" + workUserId +
                ", userId=" + userId +
                ", user=" + user +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
