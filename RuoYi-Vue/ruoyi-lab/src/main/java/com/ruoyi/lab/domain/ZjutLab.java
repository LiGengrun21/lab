package com.ruoyi.lab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.equipment.domain.*;

import java.util.List;

/**
 * 实验室列表对象 zjut_lab
 *
 *
 * @date 2021-05-04
 */
public class ZjutLab extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 房间id */
    private Long labId;

    /** 房间编号 */
    @Excel(name = "房间编号")
    private String labNum;

    /** 房间位置 */
    @Excel(name = "房间位置")
    private String labAd;

    private List<ZjutEquipment> zjutEquipmentList;

    private Long adminId;

    private Long equipmentNum;

    /** 房间类型*/
    private String labType;
    /**
     * 实验室
     */
    public static final String LAB="1";
    /**
     * 会议室
     */
    public static final String CONFERENCE_ROOM="2";
    /** 预约表 */
    private List<LabSubscribe> labSubscribeList;

    public void setLabId(Long labId)
    {
        this.labId = labId;
    }
    public Long getLabId()
    {
        return labId;
    }

    public void setLabNum(String labNum)
    {
        this.labNum = labNum;
    }
    public String getLabNum()
    {
        return labNum;
    }

    public void setLabType(String labType)
    {
        this.labType = labType;
    }
    public String getLabType()
    {
        return labType;
    }

    public void setLabAd(String labAd)
    {
        this.labAd = labAd;
    }
    public String getLabAd()
    {
        return labAd;
    }

    public List<ZjutEquipment> getzjutEquipmentList()
    {
        return zjutEquipmentList;
    }
    public void setzjutEquipmentList(List<ZjutEquipment> zjutEquipmentList)
    {
        this.zjutEquipmentList = zjutEquipmentList;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public void setEquipmentNum(Long equipmentNum)
    {
        this.equipmentNum = equipmentNum;
    }
    public Long getEquipmentNum()
    {
        return equipmentNum;
    }

    public void setLabSubscribeList(List<LabSubscribe> labSubscribeList){
        this.labSubscribeList = labSubscribeList;
    }
    public List<LabSubscribe> getLabSubscribeList(){
        return labSubscribeList;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("labId", getLabId())
            .append("labNum", getLabNum())
            .append("labAd", getLabAd())
            .append("zjutEquipmentList", getzjutEquipmentList())
            .append("adminId",getAdminId())
            .append("equipmentNum", getEquipmentNum())
            .append("labType",getLabType())
            .append("labsubscribeList",getLabSubscribeList())
            .toString();
    }
}
