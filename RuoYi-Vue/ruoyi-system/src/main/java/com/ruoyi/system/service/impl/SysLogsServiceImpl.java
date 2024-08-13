package com.ruoyi.system.service.impl;


import com.ruoyi.system.domain.SysLogs;
import com.ruoyi.system.mapper.SysLogsMapper;
import com.ruoyi.system.service.ISysLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李庚润
 * @date 2022/5/2 14:35
 */
@Service
public class SysLogsServiceImpl implements ISysLogsService {

    @Autowired
    SysLogsMapper sysLogsMapper;

    @Override
    public SysLogs selectLogsById(Long logsId) {
        return sysLogsMapper.selectLogsById(logsId);
    }

    @Override
    public List<SysLogs> selectLogsByUserId(Long userId) {
        return sysLogsMapper.selectLogsByUserId(userId);
    }

    @Override
    public int insertLogs(SysLogs logs) {
        return sysLogsMapper.insertLogs(logs);
    }

    @Override
    public int deleteLogs(Long logsId) {
        return sysLogsMapper.deleteLogs(logsId);
    }

    @Override
    public int updateLogs(SysLogs sysLogs) {
        return sysLogsMapper.updateLogs(sysLogs);
    }

    @Override
    public SysLogs selectLogsBySubscribeId(int type, Long subscribeId) {
        return sysLogsMapper.selectLogsBySubscribeIdAndType(type,subscribeId);
    }
}
