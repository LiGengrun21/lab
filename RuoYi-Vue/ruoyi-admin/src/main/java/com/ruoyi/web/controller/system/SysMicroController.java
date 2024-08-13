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
import com.ruoyi.system.domain.SysMicro;
import com.ruoyi.system.service.ISysMicroService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 微生物Controller
 *
 */
@RestController
@RequestMapping("/system/micro")
public class SysMicroController extends BaseController
{
    @Autowired
    private ISysMicroService sysMicroService;

    /**
     * 查询微生物列表
     */
    @PreAuthorize("@ss.hasPermi('system:micro:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMicro sysMicro)
    {
        startPage();
        List<SysMicro> list = sysMicroService.selectSysMicroList(sysMicro);
        return getDataTable(list);
    }

    /**
     * 导出微生物列表
     */
    @PreAuthorize("@ss.hasPermi('system:micro:export')")
    @Log(title = "微生物", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysMicro sysMicro)
    {
        List<SysMicro> list = sysMicroService.selectSysMicroList(sysMicro);
        ExcelUtil<SysMicro> util = new ExcelUtil<SysMicro>(SysMicro.class);
        return util.exportExcel(list, "micro");
    }

    /**
     * 获取微生物详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:micro:query')")
    @GetMapping(value = "/{microId}")
    public AjaxResult getInfo(@PathVariable("microId") Long microId)
    {
        return AjaxResult.success(sysMicroService.selectSysMicroById(microId));
    }

    /**
     * 新增微生物
     */
    @PreAuthorize("@ss.hasPermi('system:micro:add')")
    @Log(title = "微生物", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMicro sysMicro)
    {
        return toAjax(sysMicroService.insertSysMicro(sysMicro));
    }

    /**
     * 修改微生物
     */
    @PreAuthorize("@ss.hasPermi('system:micro:edit')")
    @Log(title = "微生物", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMicro sysMicro)
    {
        return toAjax(sysMicroService.updateSysMicro(sysMicro));
    }

    /**
     * 删除微生物
     */
    @PreAuthorize("@ss.hasPermi('system:micro:remove')")
    @Log(title = "微生物", businessType = BusinessType.DELETE)
	@DeleteMapping("/{microIds}")
    public AjaxResult remove(@PathVariable Long[] microIds)
    {
        return toAjax(sysMicroService.deleteSysMicroByIds(microIds));
    }
}
