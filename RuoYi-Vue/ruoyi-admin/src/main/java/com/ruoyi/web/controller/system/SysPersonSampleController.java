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
import com.ruoyi.system.domain.SysPersonSample;
import com.ruoyi.system.service.ISysPersonSampleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 人类样本Controller
 *
 */
@RestController
@RequestMapping("/system/sample")
public class SysPersonSampleController extends BaseController
{
    @Autowired
    private ISysPersonSampleService sysPersonSampleService;

    /**
     * 查询人类样本列表
     */
    @PreAuthorize("@ss.hasPermi('system:sample:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPersonSample sysPersonSample)
    {
        startPage();
        List<SysPersonSample> list = sysPersonSampleService.selectSysPersonSampleList(sysPersonSample);
        return getDataTable(list);
    }

    /**
     * 导出人类样本列表
     */
    @PreAuthorize("@ss.hasPermi('system:sample:export')")
    @Log(title = "人类样本", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysPersonSample sysPersonSample)
    {
        List<SysPersonSample> list = sysPersonSampleService.selectSysPersonSampleList(sysPersonSample);
        ExcelUtil<SysPersonSample> util = new ExcelUtil<SysPersonSample>(SysPersonSample.class);
        return util.exportExcel(list, "sample");
    }

    /**
     * 获取人类样本详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sample:query')")
    @GetMapping(value = "/{sampleId}")
    public AjaxResult getInfo(@PathVariable("sampleId") Long sampleId)
    {
        return AjaxResult.success(sysPersonSampleService.selectSysPersonSampleById(sampleId));
    }

    /**
     * 新增人类样本
     */
    @PreAuthorize("@ss.hasPermi('system:sample:add')")
    @Log(title = "人类样本", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPersonSample sysPersonSample)
    {
        return toAjax(sysPersonSampleService.insertSysPersonSample(sysPersonSample));
    }

    /**
     * 修改人类样本
     */
    @PreAuthorize("@ss.hasPermi('system:sample:edit')")
    @Log(title = "人类样本", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPersonSample sysPersonSample)
    {
        return toAjax(sysPersonSampleService.updateSysPersonSample(sysPersonSample));
    }

    /**
     * 删除人类样本
     */
    @PreAuthorize("@ss.hasPermi('system:sample:remove')")
    @Log(title = "人类样本", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sampleIds}")
    public AjaxResult remove(@PathVariable Long[] sampleIds)
    {
        return toAjax(sysPersonSampleService.deleteSysPersonSampleByIds(sampleIds));
    }
}
