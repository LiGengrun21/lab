package com.ruoyi.web.controller.system;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysLogs;
import com.ruoyi.system.service.ISysLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/system/sysLogs")
public class SysLogsController extends BaseController {

    @Autowired
    ISysLogsService sysLogsService;

    /**
     * 获取一个用户的日志列表
     * @param userId
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:sysLogs:getUserLogs')")
    @GetMapping("/user")
    public TableDataInfo getUserLogs(Long userId)
    {
        startPage();
        return getDataTable(sysLogsService.selectLogsByUserId(userId));
    }

    /**
     * 获取一条日志的详细内容
     * @param logsId
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:sysLogs:getInfo')")
    @GetMapping("/getLog")
    public AjaxResult getInfo(Long logsId)
    {
        return AjaxResult.success(sysLogsService.selectLogsById(logsId));
    }

    /**
     * 编辑一条日志
     * @param sysLogs
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:sysLogs:update')")
    @PutMapping
    public AjaxResult update(@RequestBody SysLogs sysLogs)
    {
        return toAjax(sysLogsService.updateLogs(sysLogs));
    }

    /**
     * 删除一条日志
     * @param logsId
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:sysLogs:delete')")
    @DeleteMapping
    public AjaxResult delete(Long logsId)
    {
        return toAjax(sysLogsService.deleteLogs(logsId));
    }

    /**
     * 添加一条日志
     * @param sysLogs
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:sysLogs:insert')")
    @PostMapping
    public AjaxResult insert(@RequestBody SysLogs sysLogs)
    {
        sysLogsService.insertLogs(sysLogs);
        return AjaxResult.success(sysLogs.getLogsId());
    }

}
