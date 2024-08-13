package com.ruoyi.equipment.service;

import java.util.Date;
import java.util.List;
import com.ruoyi.equipment.domain.DeviceLog;

/**
 * 设备运行统计Service接口
 *
 */
public interface IDeviceLogService
{
    /**
     * 查询设备运行统计
     *
     * @param deviceLogId 设备运行统计ID
     * @return 设备运行统计
     */
    public DeviceLog selectDeviceLogById(Long deviceLogId);

    /**
     * 查询设备运行统计列表
     *
     * @param deviceLog 设备运行统计
     * @return 设备运行统计集合
     */
    public List<DeviceLog> selectDeviceLogList(DeviceLog deviceLog);
    public List<DeviceLog> select1HoursDeviceLogList(DeviceLog deviceLog);
    public List<DeviceLog> select3HoursDeviceLogList(DeviceLog deviceLog);
    public List<DeviceLog> select24HoursDeviceLogList(DeviceLog deviceLog);
    public List<DeviceLog> selectOneDayDeviceLogList(DeviceLog deviceLog, String date);


    /**
     * 新增设备运行统计
     *
     * @param deviceLog 设备运行统计
     * @return 结果
     */
    public int insertDeviceLog(DeviceLog deviceLog);

    /**
     * 新增具体设备运行统计
     *
     * @param deviceLog 设备运行统计
     * @param snNumber 设备SN编号
     * @return 结果
     */
    public int insertOneDeviceLog(DeviceLog deviceLog,String snNumber);

    /**
     * 修改设备运行统计
     *
     * @param deviceLog 设备运行统计
     * @return 结果
     */
    public int updateDeviceLog(DeviceLog deviceLog);

    /**
     * 批量删除设备运行统计
     *
     * @param deviceLogIds 需要删除的设备运行统计ID
     * @return 结果
     */
    public int deleteDeviceLogByIds(Long[] deviceLogIds);

    /**
     * 删除设备运行统计信息
     *
     * @param deviceLogId 设备运行统计ID
     * @return 结果
     */
    public int deleteDeviceLogById(Long deviceLogId);

    /**
     * 增加新的日志表
     *
     * @param tableName 日志表的名称
     * @return 结果
     */
    public int createNewDeviceLog(String tableName);
}
