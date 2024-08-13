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
import com.ruoyi.system.domain.SysUserPersonSample;
import com.ruoyi.system.service.ISysUserPersonSampleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户和人类样本中间Controller
 *
 */
@RestController
@RequestMapping("/system/userSample")
public class SysUserPersonSampleController extends BaseController
{
    @Autowired
    private ISysUserPersonSampleService sysUserPersonSampleService;

    /**
     * 查询用户和人类样本中间列表
     */
    @PreAuthorize("@ss.hasPermi('system:userSample:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserPersonSample sysUserPersonSample)
    {
        startPage();
        List<SysUserPersonSample> list = sysUserPersonSampleService.selectSysUserPersonSampleList(sysUserPersonSample);
        return getDataTable(list);
    }

    /**
     * 导出用户和人类样本中间列表
     */
    @PreAuthorize("@ss.hasPermi('system:userSample:export')")
    @Log(title = "用户和人类样本中间", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserPersonSample sysUserPersonSample)
    {
        List<SysUserPersonSample> list = sysUserPersonSampleService.selectSysUserPersonSampleList(sysUserPersonSample);
        ExcelUtil<SysUserPersonSample> util = new ExcelUtil<SysUserPersonSample>(SysUserPersonSample.class);
        return util.exportExcel(list, "userSample");
    }

    /**
     * 获取用户和人类样本中间详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userSample:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(sysUserPersonSampleService.selectSysUserPersonSampleById(userId));
    }

    /**
     * 新增用户和人类样本中间
     */
    @PreAuthorize("@ss.hasPermi('system:userSample:add')")
    @Log(title = "用户和人类样本中间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserPersonSample sysUserPersonSample)
    {
        return toAjax(sysUserPersonSampleService.insertSysUserPersonSample(sysUserPersonSample));
    }

    /**
     * 修改用户和人类样本中间
     */
    @PreAuthorize("@ss.hasPermi('system:userSample:edit')")
    @Log(title = "用户和人类样本中间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserPersonSample sysUserPersonSample)
    {
        return toAjax(sysUserPersonSampleService.updateSysUserPersonSample(sysUserPersonSample));
    }

    /**
     * 删除用户和人类样本中间
     */
    @PreAuthorize("@ss.hasPermi('system:userSample:remove')")
    @Log(title = "用户和人类样本中间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(sysUserPersonSampleService.deleteSysUserPersonSampleByIds(userIds));
    }
}
