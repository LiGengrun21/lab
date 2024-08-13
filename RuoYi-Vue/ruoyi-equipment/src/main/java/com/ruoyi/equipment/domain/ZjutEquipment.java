package com.ruoyi.equipment.domain;

import java.sql.Time;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 设备对象 zjut_equipment
 *
 */
public class ZjutEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备id */
    private Long equipmentId;

    /** 设备图片url */
    private String equipmentPic;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String equipmentName;
    public String getEquipmentCode(){
        return equipmentCode;
    }
    public void setEquipmentCode(String equipmentCode){
        this.equipmentCode=equipmentCode;
    }
    /**
     * 设备码,唯一标识实体设备.
     */
    private String equipmentCode;

    /** 设备管理员 */
    @Excel(name = "设备管理员")
    private Long userId;

    /** 设备管理员列表 */
    @Excel(name = "设备管理员")
    private List<SysUser> manager;

    /** 设备管理员id列表 */
    @Excel(name = "设备管理员id")
    private List<Long> managerIds;

    /** 设备sn编号 */
    @Excel(name = "设备sn编号")
    private String equipmentSnNumber;

    /** 设备状态（0可用，1报修，2报废） */
    @Excel(name = "设备状态", readConverterExp = "0=可用，1报修，2报废")
    private String equipmentStatus;

    /** 设备型号 */
    @Excel(name = "设备型号")
    private String equipmentModel;

    /** 房间号 */
    private Long labId;

    /** 房间名称 */
    private String labName;

    /** 设备预约信息 */
    private List<EquipmentSubscribe> equipmentSubscribeList;

    /** 设备预约类别 (0为可预约、1为不可预约)*/
    private String equipmentSubscribeType;
    public static final String CAN_SUBSCRIBE="0";
    public static final String CAN_NOT_SUBSCRIBE="1";
    private List<SysUser> sysUser;

    /** 设备允许被取消预约的时间 （毫秒数） */
    private Double allowCancel;

    private String deviceId;

    private String autoOff;
    /**
     * 院内人员可预约开始时间
     */
    @DateTimeFormat(pattern="HH:mm:ss")
    @Excel(name="院内人员可预约开始时间")
    private Time innerBeginTime;
    /**
     * 院内人员可预约结束时间
     */
    @DateTimeFormat(pattern="HH:mm:ss")
    @Excel(name="院内人员可预约结束时间")
    private Time innerEndTime;
    /**
     * 院外人员可预约开始时间
     */
    @DateTimeFormat(pattern="HH:mm:ss")
    @Excel(name="院外人员可预约开始时间")
    private Time outerBeginTime;
    /**
     * 院外人员可预约结束时间
     */
    @DateTimeFormat(pattern="HH:mm:ss")
    @Excel(name="院外人员可预约结束时间")
    private Time outerEndTime;
    /**
     * 院内
     */
    public static final String INNER="inner";
    /**
     * 院外
     */
    public static final String OUTER="outer";

    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }
    public Long getEquipmentId()
    {
        return equipmentId;
    }

    public String getEquipmentPic() {
        return equipmentPic;
    }
    public void setEquipmentPic(String equipmentPic) {
        this.equipmentPic = equipmentPic;
    }

    public void setSysUser(List<SysUser> sysUser)
    {
        this.sysUser = sysUser;
    }
    public List<SysUser> getSysUser()
    {
        return sysUser;
    }

    public void setLabId(Long labId)
    {
        this.labId = labId;
    }
    public Long getLabId()
    {
        return labId;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public void setEquipmentName(String equipmentName)
    {
        this.equipmentName = equipmentName;
    }
    public String getEquipmentName()
    {
        return equipmentName;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    public Long getUserId()
    {
        return userId;
    }

    public List<SysUser> getManager() {
        return manager;
    }
    public void setManager(List<SysUser> manager) {
        this.manager = manager;
    }

    public List<Long> getManagerIds() {
        return managerIds;
    }
    public void setManagerIds(List<Long> managerIds) {
        this.managerIds = managerIds;
    }

    public void setEquipmentSnNumber(String equipmentSnNumber)
    {
        this.equipmentSnNumber = equipmentSnNumber;
    }
    public String getEquipmentSnNumber()
    {
        return equipmentSnNumber;
    }

    public void setEquipmentStatus(String equipmentStatus)
    {
        this.equipmentStatus = equipmentStatus;
    }
    public String getEquipmentStatus()
    {
        return equipmentStatus;
    }

    public void setEquipmentSubscribeType(String equipmentSubscribeType)
    {
        this.equipmentSubscribeType = equipmentSubscribeType;
    }

    public Double getAllowCancel() {
        return allowCancel;
    }
    public void setAllowCancel(Double allowCancel) {
        this.allowCancel = allowCancel;
    }

    public String getEquipmentSubscribeType()
    {
        return equipmentSubscribeType;
    }

    public void setEquipmentModel(String equipmentModel)
    {
        this.equipmentModel = equipmentModel;
    }
    public String getEquipmentModel()
    {
        return equipmentModel;
    }

    public void setAutoOff(String autoOff)
    {
        this.autoOff = autoOff;
    }
    public String getAutoOff()
    {
        return autoOff;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }
    public String getDeviceId()
    {
        return deviceId;
    }

    public List<EquipmentSubscribe> getEquipmentSubscribeList()
    {
        return equipmentSubscribeList;
    }

    public void setEquipmentSubscribeList(List<EquipmentSubscribe> equipmentSubscribeList)
    {
        this.equipmentSubscribeList = equipmentSubscribeList;
    }
    public Time getInnerBeginTime(){
        return innerBeginTime;
    }
    public void setInnerBeginTime(Time innerBeginTime){
        this.innerBeginTime=innerBeginTime;
    }
    public Time getInnerEndTime(){
        return innerEndTime;
    }
    public void setInnerEndTime(Time innerEndTime){
        this.innerEndTime=innerEndTime;
    }
    public Time getOuterBeginTime(){
        return outerBeginTime;
    }
    public void setOuterBeginTime(Time outerBeginTime){
        this.outerBeginTime=outerBeginTime;
    }
    public Time getOuterEndTime(){
        return outerEndTime;
    }
    public void setOuterEndTime(Time outerEndTime){
        this.outerEndTime=outerEndTime;
    }
    @Override
    public String toString(){
        return "ZjutEquipment{"+"equipmentId="+equipmentId+", equipmentName='"+equipmentName+'\''+", equipmentCode='"+equipmentCode+'\''+", userId="+userId+", equipmentSnNumber='"+equipmentSnNumber+'\''+", equipmentStatus='"+equipmentStatus+'\''+", equipmentModel='"+equipmentModel+'\''+", labId="+labId+", equipmentSubscribeList="+equipmentSubscribeList+", equipmentSubscribeType='"+equipmentSubscribeType+'\''+", sysUser="+sysUser+", deviceId='"+deviceId+'\''+", autoOff='"+autoOff+'\''+", innerBeginTime="+innerBeginTime+", innerEndTime="+innerEndTime+", outerBeginTime="+outerBeginTime+", outerEndTime="+outerEndTime+'}';
    }
}
