package com.ruoyi.web.controller.lab;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.equipment.domain.EquipmentSubscribe;
import com.ruoyi.equipment.domain.ZjutEquipment;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.lab.domain.ZjutLab;
import com.ruoyi.lab.service.IZjutLabService;
import com.ruoyi.system.domain.SysLogs;
import com.ruoyi.system.service.ISysLogsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lab.domain.LabSubscribe;
import com.ruoyi.lab.service.ILabSubscribeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 实验室预约Controller
 *
 */
@RestController
@RequestMapping("/lab/subscribe")
public class LabSubscribeController extends BaseController
{
    @Autowired
    private IZjutLabService zjutLabService;

    @Autowired
    private ILabSubscribeService labSubscribeService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysLogsService sysLogsService;

    /**
     * 查询实验室预约列表
     */
    @PreAuthorize("@ss.hasPermi('lab:subscribe:list')")
    @GetMapping("/list")
    public TableDataInfo list(LabSubscribe labSubscribe)
    {
        startPage();
        List<LabSubscribe> list = labSubscribeService.selectLabSubscribeList(labSubscribe);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('lab:subscribe:list')")
    @GetMapping("/auditList")
    public TableDataInfo listAudit(LabSubscribe labSubscribe)
    {
        LoginUser loginUser=tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user=loginUser.getUser();
        List<LabSubscribe> list = labSubscribeService.selectAuditLabSubscribeList(labSubscribe);
        ArrayList<LabSubscribe> auditlist= new ArrayList<>();
        for(LabSubscribe i: list){
            Long id=i.getLabId();
            ZjutLab zl=zjutLabService.selectZjutLabById(id);
            if(zl.getAdminId().equals(user.getUserId())){
                auditlist.add(i);
            }
        }
        return getDataTable(auditlist);
    }

    /**
     * 导出实验室预约列表
     */
    @PreAuthorize("@ss.hasPermi('lab:subscribe:export')")
    @Log(title = "实验室预约", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LabSubscribe labSubscribe)
    {
        List<LabSubscribe> list = labSubscribeService.selectLabSubscribeList(labSubscribe);
        ExcelUtil<LabSubscribe> util = new ExcelUtil<LabSubscribe>(LabSubscribe.class);
        return util.exportExcel(list, "subscribe");
    }

    /**
     * 获取实验室预约详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:subscribe:query')")
    @GetMapping(value = "/{labSubscribeId}")
    public AjaxResult getInfo(@PathVariable("labSubscribeId") Long labSubscribeId)
    {
        return AjaxResult.success(labSubscribeService.selectLabSubscribeById(labSubscribeId));
    }

    /**
     * 新增实验室预约
     */
    //@PreAuthorize("@ss.hasPermi('lab:subscribe:add')")
    @Log(title = "实验室预约", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LabSubscribe labSubscribe)
    {
        labSubscribeService.insertLabSubscribe(labSubscribe);
        //在日志管理里添加一条记录
        SysLogs sysLogs=new SysLogs();
        sysLogs.setUserId(labSubscribe.getUserId());
        sysLogs.setType(1);
        sysLogs.setSubscribeStartTime(labSubscribe.getLabSubscribeStartTime());
        sysLogs.setSubscribeEndTime(labSubscribe.getLabSubscribeEndTime());
        sysLogs.setSubscribeDate(labSubscribe.getLabSubscribeDate());
        sysLogs.setRemark("subscribe a lab");
        sysLogs.setSubscribeId(labSubscribe.getLabSubscribeId());
        sysLogsService.insertLogs(sysLogs);
        return AjaxResult.success("设备预约成功");
    }

    /**
     * 修改实验室预约
     */
    //@PreAuthorize("@ss.hasPermi('lab:subscribe:edit')")
    @Log(title = "实验室预约", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LabSubscribe labSubscribe)
    {
        //根据类型和实验室id查询到对应的日志
        SysLogs sysLogs=sysLogsService.selectLogsBySubscribeId(1,labSubscribe.getLabSubscribeId());
        //只有修改了结束时间才影响到SysLogs表
        sysLogs.setSubscribeEndTime(labSubscribe.getLabSubscribeEndTime());
        sysLogsService.updateLogs(sysLogs);
        return toAjax(labSubscribeService.updateLabSubscribe(labSubscribe));
    }

    /**
     * 删除实验室预约
     */
    //@PreAuthorize("@ss.hasPermi('lab:subscribe:remove')")
    @Log(title = "实验室预约", businessType = BusinessType.DELETE)
	@DeleteMapping("/{labSubscribeIds}")
    public AjaxResult remove(@PathVariable Long[] labSubscribeIds)
    {
        //删除这些实验室在日志管理系统对应的记录
        for (Long labSubscribeId : labSubscribeIds) {
            SysLogs sysLogs=sysLogsService.selectLogsBySubscribeId(1,labSubscribeId);
            sysLogsService.deleteLogs(sysLogs.getLogsId());
        }
        return toAjax(labSubscribeService.deleteLabSubscribeByIds(labSubscribeIds));
    }
}
