package com.ruoyi.train.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 培训-用户对象 train_user
 *
 */
public class TrainUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 培训id */
    private Long trainId;

    /** 用户id */
    private Long userId;

    /** 用户得分 */
    @Excel(name = "用户得分")
    private Long userScore;

    public void setTrainId(Long trainId) 
    {
        this.trainId = trainId;
    }

    public Long getTrainId() 
    {
        return trainId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserScore(Long userScore) 
    {
        this.userScore = userScore;
    }

    public Long getUserScore() 
    {
        return userScore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("trainId", getTrainId())
            .append("userId", getUserId())
            .append("userScore", getUserScore())
            .toString();
    }
}
