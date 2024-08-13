package com.ruoyi.goods.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出入库日志对象 goods_log
 *
 * 
 * @date 2021-05-19
 */
public class GoodsLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 耗材日志id */
    private Long goodsLogId;

    /** 耗材编号 */
    @Excel(name = "耗材编号")
    private String goodsNum;

    /** 耗材名称 */
    @Excel(name = "耗材名称")
    private String goodsName;

    /** 耗材位置 */
    @Excel(name = "耗材位置")
    private String goodsAd;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private String actionType;

    /** 操作数量 */
    @Excel(name = "操作数量")
    private Long actionNum;

    /** 操作备注 */
    @Excel(name = "操作备注")
    private String goodsLogMassage;

    private String goodsPerson;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date actionDate;

    public void setGoodsLogId(Long goodsLogId)
    {
        this.goodsLogId = goodsLogId;
    }

    public Long getGoodsLogId()
    {
        return goodsLogId;
    }
    public void setGoodsNum(String goodsNum)
    {
        this.goodsNum = goodsNum;
    }

    public String getGoodsNum()
    {
        return goodsNum;
    }
    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName()
    {
        return goodsName;
    }
    public void setGoodsAd(String goodsAd)
    {
        this.goodsAd = goodsAd;
    }

    public String getGoodsAd()
    {
        return goodsAd;
    }
    public void setActionType(String actionType)
    {
        this.actionType = actionType;
    }

    public String getActionType()
    {
        return actionType;
    }
    public void setActionNum(Long actionNum)
    {
        this.actionNum = actionNum;
    }

    public Long getActionNum()
    {
        return actionNum;
    }
    public void setGoodsLogMassage(String goodsLogMassage)
    {
        this.goodsLogMassage = goodsLogMassage;
    }

    public String getGoodsLogMassage()
    {
        return goodsLogMassage;
    }

    public void setGoodsPerson(String goodsPerson)
    {
        this.goodsPerson= goodsPerson;
    }
    public String getGoodsPerson()
    {
        return goodsPerson;
    }

    public void setActionDate(Date actionDate){this.actionDate = actionDate; }
    public Date getActionDate(){ return  actionDate; }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsLogId", getGoodsLogId())
            .append("goodsNum", getGoodsNum())
            .append("goodsName", getGoodsName())
            .append("goodsAd", getGoodsAd())
            .append("actionType", getActionType())
            .append("actionNum", getActionNum())
            .append("goodsLogMassage", getGoodsLogMassage())
            .append("goodsPerson", getGoodsPerson())
            .append("goodsPerson", getActionDate())
            .toString();
    }
}
