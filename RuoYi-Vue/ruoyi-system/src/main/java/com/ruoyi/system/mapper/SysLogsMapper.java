package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysLogs;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysLogsMapper {

    /**
     * 根据id查询日志
     * @param logsId
     * @return
     */
    SysLogs selectLogsById(Long logsId);

    /**
     * 查询一个用户的所有日志
     * @param userId
     * @return
     */
    List<SysLogs> selectLogsByUserId(Long userId);

    /**
     * 根据类型和预约号查询
     * @param type
     * @param subscribeId
     * @return
     */
    SysLogs selectLogsBySubscribeIdAndType(@Param("type") int type, @Param("subscribeId") Long subscribeId);

    /**
     * 插入一条日志
     * @param logs
     * @return
     */
    int insertLogs(SysLogs logs);

    /**
     * 删除一条日志
     * @param logsId
     * @return
     */
    int deleteLogs(Long logsId);

    /**
     * 更新一条日志
     * @param sysLogs
     * @return
     */
    int updateLogs(SysLogs sysLogs);

    /**
     * 用于值班管理删除某天的全部值班
     *
     * @param type
     * @param subscribeDate
     * @return
     */
    int deleteLogByTypeAndSubscribeDate(@Param("type") int type, @Param("subscribeDate") Date subscribeDate );

    /**
     * 用于值班管理删除某年某月的全部值班
     *
     * @param type
     * @param year
     * @param month
     * @return
     */
    int deleteLogByTypeAndSubscribeMonth(@Param("type") int type, @Param("year") int year, @Param("month") int month);
}
