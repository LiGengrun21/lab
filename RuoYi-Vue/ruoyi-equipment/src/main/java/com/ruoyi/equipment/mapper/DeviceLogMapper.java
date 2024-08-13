package com.ruoyi.equipment.mapper;

import java.util.List;
import com.ruoyi.equipment.domain.DeviceLog;
import org.apache.ibatis.annotations.Param;

/**
 * 设备运行统计Mapper接口
 *
 */
public interface DeviceLogMapper
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
     * @param snNumber 设备SN编号
     * @param deviceLog 设备运行统计
     * @return 结果
     */
    public int insertOneDeviceLog(@Param("deviceLog")DeviceLog deviceLog, @Param("snNumber")String snNumber);

    /**
     * 修改设备运行统计
     *
     * @param deviceLog 设备运行统计
     * @return 结果
     */
    public int updateDeviceLog(DeviceLog deviceLog);

    /**
     * 删除设备运行统计
     *
     * @param deviceLogId 设备运行统计ID
     * @return 结果
     */
    public int deleteDeviceLogById(Long deviceLogId);

    /**
     * 批量删除设备运行统计
     *
     * @param deviceLogIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDeviceLogByIds(Long[] deviceLogIds);


    /**
     * 增加新的日志表
     *
     * @param tableName 日志表的名称
     * @return 结果
     */
    public int createNewDeviceLog(String tableName);

}
