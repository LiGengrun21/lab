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
import com.ruoyi.system.domain.SysChemical;
import com.ruoyi.system.service.ISysChemicalService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 危化品Controller
 *
 */
@RestController
@RequestMapping("/system/chemical")
public class SysChemicalController extends BaseController
{
    @Autowired
    private ISysChemicalService sysChemicalService;

    /**
     * 查询危化品列表
     */
    @PreAuthorize("@ss.hasPermi('system:chemical:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysChemical sysChemical)
    {
        startPage();
        List<SysChemical> list = sysChemicalService.selectSysChemicalList(sysChemical);
        return getDataTable(list);
    }

    /**
     * 导出危化品列表
     */
    @PreAuthorize("@ss.hasPermi('system:chemical:export')")
    @Log(title = "危化品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysChemical sysChemical)
    {
        List<SysChemical> list = sysChemicalService.selectSysChemicalList(sysChemical);
        ExcelUtil<SysChemical> util = new ExcelUtil<SysChemical>(SysChemical.class);
        return util.exportExcel(list, "chemical");
    }

    /**
     * 获取危化品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:chemical:query')")
    @GetMapping(value = "/{chemicalId}")
    public AjaxResult getInfo(@PathVariable("chemicalId") Long chemicalId)
    {
        return AjaxResult.success(sysChemicalService.selectSysChemicalById(chemicalId));
    }

    /**
     * 新增危化品
     */
    @PreAuthorize("@ss.hasPermi('system:chemical:add')")
    @Log(title = "危化品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysChemical sysChemical)
    {
        return toAjax(sysChemicalService.insertSysChemical(sysChemical));
    }

    /**
     * 修改危化品
     */
    @PreAuthorize("@ss.hasPermi('system:chemical:edit')")
    @Log(title = "危化品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysChemical sysChemical)
    {
        return toAjax(sysChemicalService.updateSysChemical(sysChemical));
    }

    /**
     * 删除危化品
     */
    @PreAuthorize("@ss.hasPermi('system:chemical:remove')")
    @Log(title = "危化品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{chemicalIds}")
    public AjaxResult remove(@PathVariable Long[] chemicalIds)
    {
        return toAjax(sysChemicalService.deleteSysChemicalByIds(chemicalIds));
    }
}
