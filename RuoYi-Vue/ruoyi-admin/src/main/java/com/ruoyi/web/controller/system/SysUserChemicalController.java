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
import com.ruoyi.system.domain.SysUserChemical;
import com.ruoyi.system.service.ISysUserChemicalService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户和危化品中间Controller
 *
 */
@RestController
@RequestMapping("/system/userChemical")
public class SysUserChemicalController extends BaseController
{
    @Autowired
    private ISysUserChemicalService sysUserChemicalService;

    /**
     * 查询用户和危化品中间列表
     */
    @PreAuthorize("@ss.hasPermi('system:userChemical:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserChemical sysUserChemical)
    {
        startPage();
        List<SysUserChemical> list = sysUserChemicalService.selectSysUserChemicalList(sysUserChemical);
        return getDataTable(list);
    }

    /**
     * 导出用户和危化品中间列表
     */
    @PreAuthorize("@ss.hasPermi('system:userChemical:export')")
    @Log(title = "用户和危化品中间", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserChemical sysUserChemical)
    {
        List<SysUserChemical> list = sysUserChemicalService.selectSysUserChemicalList(sysUserChemical);
        ExcelUtil<SysUserChemical> util = new ExcelUtil<SysUserChemical>(SysUserChemical.class);
        return util.exportExcel(list, "userChemical");
    }

    /**
     * 获取用户和危化品中间详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userChemical:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(sysUserChemicalService.selectSysUserChemicalById(userId));
    }

    /**
     * 新增用户和危化品中间
     */
    @PreAuthorize("@ss.hasPermi('system:userChemical:add')")
    @Log(title = "用户和危化品中间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserChemical sysUserChemical)
    {
        return toAjax(sysUserChemicalService.insertSysUserChemical(sysUserChemical));
    }

    /**
     * 修改用户和危化品中间
     */
    @PreAuthorize("@ss.hasPermi('system:userChemical:edit')")
    @Log(title = "用户和危化品中间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserChemical sysUserChemical)
    {
        return toAjax(sysUserChemicalService.updateSysUserChemical(sysUserChemical));
    }

    /**
     * 删除用户和危化品中间
     */
    @PreAuthorize("@ss.hasPermi('system:userChemical:remove')")
    @Log(title = "用户和危化品中间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(sysUserChemicalService.deleteSysUserChemicalByIds(userIds));
    }
}
