package com.ruoyi.web.controller.system;

import java.util.List;
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
import com.ruoyi.system.domain.SysUserMicro;
import com.ruoyi.system.service.ISysUserMicroService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户和微生物的中间Controller
 *
 */
@RestController
@RequestMapping("/system/userMicro")
public class SysUserMicroController extends BaseController
{
    @Autowired
    private ISysUserMicroService sysUserMicroService;

    /**
     * 查询用户和微生物的中间列表
     */
    @PreAuthorize("@ss.hasPermi('system:userMicro:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserMicro sysUserMicro)
    {
        startPage();
        List<SysUserMicro> list = sysUserMicroService.selectSysUserMicroList(sysUserMicro);
        return getDataTable(list);
    }

    /**
     * 导出用户和微生物的中间列表
     */
    @PreAuthorize("@ss.hasPermi('system:userMicro:export')")
    @Log(title = "用户和微生物的中间", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserMicro sysUserMicro)
    {
        List<SysUserMicro> list = sysUserMicroService.selectSysUserMicroList(sysUserMicro);
        ExcelUtil<SysUserMicro> util = new ExcelUtil<SysUserMicro>(SysUserMicro.class);
        return util.exportExcel(list, "userMicro");
    }

    /**
     * 获取用户和微生物的中间详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userMicro:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(sysUserMicroService.selectSysUserMicroById(userId));
    }

    /**
     * 新增用户和微生物的中间
     */
    @PreAuthorize("@ss.hasPermi('system:userMicro:add')")
    @Log(title = "用户和微生物的中间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserMicro sysUserMicro)
    {
        return toAjax(sysUserMicroService.insertSysUserMicro(sysUserMicro));
    }

    /**
     * 修改用户和微生物的中间
     */
    @PreAuthorize("@ss.hasPermi('system:userMicro:edit')")
    @Log(title = "用户和微生物的中间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserMicro sysUserMicro)
    {
        return toAjax(sysUserMicroService.updateSysUserMicro(sysUserMicro));
    }

    /**
     * 删除用户和微生物的中间
     */
    @PreAuthorize("@ss.hasPermi('system:userMicro:remove')")
    @Log(title = "用户和微生物的中间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(sysUserMicroService.deleteSysUserMicroByIds(userIds));
    }
}
