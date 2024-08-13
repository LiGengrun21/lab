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
import com.ruoyi.system.domain.DangerCondition;
import com.ruoyi.system.service.IDangerConditionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 危险事故Controller
 *
 */
@RestController
@RequestMapping("/system/danger")
public class DangerConditionController extends BaseController
{
    @Autowired
    private IDangerConditionService dangerConditionService;

    /**
     * 查询危险事故列表
     */
    @PreAuthorize("@ss.hasPermi('system:danger:list')")
    @GetMapping("/list")
    public TableDataInfo list(DangerCondition dangerCondition)
    {
        startPage();
        List<DangerCondition> list = dangerConditionService.selectDangerConditionList(dangerCondition);
        return getDataTable(list);
    }

    /**
     * 导出危险事故列表
     */
    @PreAuthorize("@ss.hasPermi('system:danger:export')")
    @Log(title = "危险事故", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DangerCondition dangerCondition)
    {
        List<DangerCondition> list = dangerConditionService.selectDangerConditionList(dangerCondition);
        ExcelUtil<DangerCondition> util = new ExcelUtil<DangerCondition>(DangerCondition.class);
        return util.exportExcel(list, "danger");
    }

    /**
     * 获取危险事故详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:danger:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(dangerConditionService.selectDangerConditionById(id));
    }

    /**
     * 新增危险事故
     */
    @PreAuthorize("@ss.hasPermi('system:danger:add')")
    @Log(title = "危险事故", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody DangerCondition dangerCondition)
    {
        return toAjax(dangerConditionService.insertDangerCondition(dangerCondition));
    }

    /**
     * 修改危险事故
     */
    @PreAuthorize("@ss.hasPermi('system:danger:edit')")
    @Log(title = "危险事故", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DangerCondition dangerCondition)
    {
        return toAjax(dangerConditionService.updateDangerCondition(dangerCondition));
    }

    /**
     * 删除危险事故
     */
    @PreAuthorize("@ss.hasPermi('system:danger:remove')")
    @Log(title = "危险事故", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dangerConditionService.deleteDangerConditionByIds(ids));
    }
}
