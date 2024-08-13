package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysLogs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李庚润
 * @date 2022/5/2 14:34
 */
public interface ISysLogsService {
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
     * 插入一条日志
     * @param logs
     * @return
     */
    int insertLogs(SysLogs logs);

    SysLogs selectLogsBySubscribeId(int type, Long subscribeId);

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
}
