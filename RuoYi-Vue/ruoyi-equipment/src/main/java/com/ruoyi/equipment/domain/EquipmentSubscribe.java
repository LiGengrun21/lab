package com.ruoyi.equipment.domain;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备预约对象 equipment_subscribe
 *
 */
public class EquipmentSubscribe extends BaseEntity{
    private static final long serialVersionUID=1L;
    /** 预定id */
    private Long equipmentSubscribeId;
    /** 设备id */
    @Excel(name="设备id")
    private Long equipmentId;
    /** 设备名称 */
    @Excel(name="设备名称")
    private String equipmentName;
    @Excel(name="预约日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date subscribeDate;
    /** 预约开始时间 */
    @JsonFormat(pattern="HH:mm:ss")
    @Excel(name="预约开始时间", width=30, dateFormat="HH:mm:ss")
    private String subscribeStartTime;
    /** 预约结束时间 */
    @JsonFormat(pattern="HH:mm:ss")
    @Excel(name="预约结束时间", width=30, dateFormat="HH:mm:ss")
    private String subscribeEndTime;
    /** 预约人id */
    private Long userId;
    /** 预约人 */
    @Excel(name="预约人")
    private String subscribeName;
    /** 预约状态（0预约中，1预约结束，2预约取消） */
    @Excel(name="预约状态", readConverterExp="0=预约中，1预约结束，2预约取消")
    private String subscribeStatus;
    /** 预约人手机号 */
    @Excel(name="预约人手机号码")
    private String subscribePhone;
    /**
     * 是否有导师辅导
     */
    private boolean hasHelp;
    /**
     * 此用户PI的id
     */
    private Long piId;
    /**
     * 设备费
     */
    @Excel(name="设备费")
    private BigDecimal equipmentCost;
    /**
     * 人工费
     */
    @Excel(name="人工费")
    private BigDecimal artificialCost;
    /**
     * 耗材费
     */
    @Excel(name="耗材费")
    private BigDecimal materialCost;
    /**
     * 预约总费用
     */
    @Excel(name="预约总费用")
    private BigDecimal totalCost;
    /**
     *真实总费用
     *因为真实上下机时间点和预约的上下机时间点可能有差异,使得真实总费用可能与预约时计算的总费用有差异.
     */
    @Excel(name="真实总费用")
    private BigDecimal actualTotalCost;
    public BigDecimal getTotalCost(){
        return totalCost;
    }
    public void setTotalCost(BigDecimal totalCost){
        this.totalCost=totalCost;
    }
    public void setEquipmentSubscribeId(Long equipmentSubscribeId){
        this.equipmentSubscribeId=equipmentSubscribeId;
    }
    public Long getEquipmentSubscribeId(){
        return equipmentSubscribeId;
    }
    public void setEquipmentId(Long equipmentId){
        this.equipmentId=equipmentId;
    }
    public Long getEquipmentId(){
        return equipmentId;
    }
    public void setEquipmentName(String equipmentName){
        this.equipmentName=equipmentName;
    }
    public String getEquipmentName(){
        return equipmentName;
    }
    public void setSubscribeStartTime(String subscribeStartTime){
        this.subscribeStartTime=subscribeStartTime;
    }
    public String getSubscribeStartTime(){
        return subscribeStartTime;
    }
    public void setSubscribeEndTime(String subscribeEndTime){
        this.subscribeEndTime=subscribeEndTime;
    }
    public String getSubscribeEndTime(){
        return subscribeEndTime;
    }
    public void setSubscribeDate(Date subscribeDate){
        this.subscribeDate=subscribeDate;
    }
    public Date getSubscribeDate(){
        return subscribeDate;
    }
    public void setSubscribeName(String subscribeName){
        this.subscribeName=subscribeName;
    }
    public String getSubscribeName(){
        return subscribeName;
    }
    public void setSubscribeStatus(String subscribeStatus){
        this.subscribeStatus=subscribeStatus;
    }
    public String getSubscribeStatus(){
        return subscribeStatus;
    }
    public void setSubscribePhone(String subscribePhone){
        this.subscribePhone=subscribePhone;
    }
    public String getSubscribePhone(){
        return subscribePhone;
    }
    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId=userId;
    }
    public boolean isHasHelp(){
        return hasHelp;
    }
    public void setHasHelp(boolean hasHelp){
        this.hasHelp=hasHelp;
    }
    public BigDecimal getEquipmentCost(){
        return equipmentCost;
    }
    public void setEquipmentCost(BigDecimal equipmentCost){
        this.equipmentCost=equipmentCost;
    }
    public BigDecimal getArtificialCost(){
        return artificialCost;
    }
    public void setArtificialCost(BigDecimal artificialCost){
        this.artificialCost=artificialCost;
    }
    public BigDecimal getMaterialCost(){
        return materialCost;
    }
    public void setMaterialCost(BigDecimal materialCost){
        this.materialCost=materialCost;
    }
    public Long getPiId(){
        return piId;
    }
    public void setPiId(Long piId){
        this.piId=piId;
    }
    public BigDecimal getActualTotalCost(){
        return actualTotalCost;
    }
    public void setActualTotalCost(BigDecimal actualTotalCost){
        this.actualTotalCost=actualTotalCost;
    }
    @Override
    public String toString(){
        return "EquipmentSubscribe{"+"equipmentSubscribeId="+equipmentSubscribeId+", equipmentId="+equipmentId+", equipmentName='"+equipmentName+'\''+", subscribeDate="+subscribeDate+", subscribeStartTime='"+subscribeStartTime+'\''+", subscribeEndTime='"+subscribeEndTime+'\''+", userId="+userId+", subscribeName='"+subscribeName+'\''+", subscribeStatus='"+subscribeStatus+'\''+", subscribePhone='"+subscribePhone+'\''+", hasHelp="+hasHelp+", piId="+piId+", equipmentCost="+equipmentCost+", artificialCost="+artificialCost+", materialCost="+materialCost+", totalCost="+totalCost+", actualTotalCost="+actualTotalCost+'}';
    }
}
