package com.ruoyi.goods.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 耗材列表对象 zjut_goods
 *
 *
 * @date 2021-05-18
 */
public class ZjutGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 耗材id */
    private Long goodsId;

    /** 耗材编号 */
    @Excel(name = "耗材编号")
    private String goodsNum;

    /** 耗材数量 */
    @Excel(name = "耗材数量")
    private Long goodsQuantity;

    /** 耗材预警数量 */
    @Excel(name = "耗材预警数量")
    private String goodsWarning;

    /** 耗材单位 */
    @Excel(name = "耗材单位")
    private String goodsUnit;

    /** 耗材存储位置 */
    @Excel(name = "耗材存储位置")
    private String goodsAd;

    /** 耗材类型 */
    @Excel(name = "耗材类型")
    private String goodsType;

    private String goodsName;

    public void setGoodsId(Long goodsId)
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId()
    {
        return goodsId;
    }
    public void setGoodsNum(String goodsNum)
    {
        this.goodsNum = goodsNum;
    }

    public String getGoodsNum()
    {
        return goodsNum;
    }
    public void setGoodsQuantity(Long goodsQuantity)
    {
        this.goodsQuantity = goodsQuantity;
    }

    public Long getGoodsQuantity()
    {
        return goodsQuantity;
    }
    public void setGoodsWarning(String goodsWarning)
    {
        this.goodsWarning = goodsWarning;
    }

    public String getGoodsWarning()
    {
        return goodsWarning;
    }
    public void setGoodsUnit(String goodsUnit)
    {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsUnit()
    {
        return goodsUnit;
    }
    public void setGoodsAd(String goodsAd)
    {
        this.goodsAd = goodsAd;
    }

    public String getGoodsAd()
    {
        return goodsAd;
    }

    public void setGoodsType(String goodsType)
    {
        this.goodsType = goodsType;
    }
    public String getGoodsType()
    {
        return goodsType;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }
    public String getGoodsName()
    {
        return goodsName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("goodsNum", getGoodsNum())
            .append("goodsQuantity", getGoodsQuantity())
            .append("goodsWarning", getGoodsWarning())
            .append("goodsUnit", getGoodsUnit())
            .append("goodsAd", getGoodsAd())
            .append("goodsType", getGoodsType())
            .append("goodName", getGoodsName())
            .toString();
    }
}
