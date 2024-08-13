package com.ruoyi.equipment.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equipment.mapper.DeviceLogMapper;
import com.ruoyi.equipment.domain.DeviceLog;
import com.ruoyi.equipment.service.IDeviceLogService;

/**
 * 设备运行统计Service业务层处理
 *
 */
@Service
public class DeviceLogServiceImpl implements IDeviceLogService
{
    @Autowired
    private DeviceLogMapper deviceLogMapper;

    /**
     * 查询设备运行统计
     *
     * @param deviceLogId 设备运行统计ID
     * @return 设备运行统计
     */
    @Override
    public DeviceLog selectDeviceLogById(Long deviceLogId)
    {
        return deviceLogMapper.selectDeviceLogById(deviceLogId);
    }

    /**
     * 查询设备运行统计列表
     *
     * @param deviceLog 设备运行统计
     * @return 设备运行统计
     */
    @Override
    public List<DeviceLog> selectDeviceLogList(DeviceLog deviceLog)
    {
        return deviceLogMapper.selectDeviceLogList(deviceLog);
    }

    @Override
    public List<DeviceLog> select1HoursDeviceLogList(DeviceLog deviceLog) {
        Date time = new Date();
        Date time1Hours = new Date(time.getTime() - 3600000);
        List<DeviceLog> deviceLogs = deviceLogMapper.selectDeviceLogList(deviceLog);
        ArrayList<DeviceLog> deviceLoglist = new ArrayList<>();
        for(DeviceLog i:deviceLogs){
            if(time1Hours.getTime() < i.getLogTime().getTime()){
                deviceLoglist.add(i);
            }
        }
        return deviceLoglist;
    }

    @Override
    public List<DeviceLog> select3HoursDeviceLogList(DeviceLog deviceLog) {
        Date time = new Date();
        Date time3Hours = new Date(time.getTime() - 10800000);
        List<DeviceLog> deviceLogs = deviceLogMapper.selectDeviceLogList(deviceLog);
        ArrayList<DeviceLog> deviceLoglist = new ArrayList<>();
        for(DeviceLog i:deviceLogs){
            if(time3Hours.getTime() < i.getLogTime().getTime()){
                deviceLoglist.add(i);
            }
        }
        return deviceLoglist;
    }

    @Override
    public List<DeviceLog> select24HoursDeviceLogList(DeviceLog deviceLog) {
        Date time = new Date();
        Date time1Hours = new Date(time.getTime() - 86400000);
        List<DeviceLog> deviceLogs = deviceLogMapper.selectDeviceLogList(deviceLog);
        ArrayList<DeviceLog> deviceLoglist = new ArrayList<>();
        for(DeviceLog i:deviceLogs){
            if(time1Hours.getTime() < i.getLogTime().getTime()){
                deviceLoglist.add(i);
            }
        }
        return deviceLoglist;
    }

    @Override
    public List<DeviceLog> selectOneDayDeviceLogList(DeviceLog deviceLog, String date) {
        List<DeviceLog> deviceLogs = deviceLogMapper.selectDeviceLogList(deviceLog);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<DeviceLog> deviceLoglist = new ArrayList<>();
        for(DeviceLog i:deviceLogs){
            if(date.equals(df.format(i.getLogTime()))){
                deviceLoglist.add(i);
            }
        }
        return deviceLoglist;
    }


    /**
     * 新增设备运行统计
     *
     * @param deviceLog 设备运行统计
     * @return 结果
     */
    @Override
    public int insertDeviceLog(DeviceLog deviceLog)
    {
        return deviceLogMapper.insertDeviceLog(deviceLog);
    }


    /**
     * 新增具体设备运行统计
     *
     * @param deviceLog 设备运行统计
     * @param snNumber 设备SN编号
     * @return 结果
     */
    @Override
    public int insertOneDeviceLog(DeviceLog deviceLog, String snNumber) {
        return deviceLogMapper.insertOneDeviceLog(deviceLog, snNumber);
    }

    /**
     * 修改设备运行统计
     *
     * @param deviceLog 设备运行统计
     * @return 结果
     */
    @Override
    public int updateDeviceLog(DeviceLog deviceLog)
    {
        return deviceLogMapper.updateDeviceLog(deviceLog);
    }

    /**
     * 批量删除设备运行统计
     *
     * @param deviceLogIds 需要删除的设备运行统计ID
     * @return 结果
     */
    @Override
    public int deleteDeviceLogByIds(Long[] deviceLogIds)
    {
        return deviceLogMapper.deleteDeviceLogByIds(deviceLogIds);
    }

    /**
     * 删除设备运行统计信息
     *
     * @param deviceLogId 设备运行统计ID
     * @return 结果
     */
    @Override
    public int deleteDeviceLogById(Long deviceLogId)
    {
        return deviceLogMapper.deleteDeviceLogById(deviceLogId);
    }

    @Override
    public int createNewDeviceLog(String tableName) {
        return deviceLogMapper.createNewDeviceLog(tableName);
    }
}
