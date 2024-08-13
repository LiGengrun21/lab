package com.ruoyi.web.controller.equipment;

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
import com.ruoyi.equipment.domain.EquipmentWarning;
import com.ruoyi.equipment.service.IEquipmentWarningService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备报警记录Controller
 *
 */
@RestController
@RequestMapping("/equipment/warning")
public class EquipmentWarningController extends BaseController
{
    @Autowired
    private IEquipmentWarningService equipmentWarningService;

    /**
     * 查询设备报警记录列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:warning:list')")
    @GetMapping("/list")
    public TableDataInfo list(EquipmentWarning equipmentWarning)
    {
        startPage();
        List<EquipmentWarning> list = equipmentWarningService.selectEquipmentWarningList(equipmentWarning);
        return getDataTable(list);
    }

    /**
     * 导出设备报警记录列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:warning:export')")
    @Log(title = "设备报警记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EquipmentWarning equipmentWarning)
    {
        List<EquipmentWarning> list = equipmentWarningService.selectEquipmentWarningList(equipmentWarning);
        ExcelUtil<EquipmentWarning> util = new ExcelUtil<EquipmentWarning>(EquipmentWarning.class);
        return util.exportExcel(list, "warning");
    }

    /**
     * 获取设备报警记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('equipment:warning:query')")
    @GetMapping(value = "/{warningId}")
    public AjaxResult getInfo(@PathVariable("warningId") Long warningId)
    {
        return AjaxResult.success(equipmentWarningService.selectEquipmentWarningById(warningId));
    }

    /**
     * 新增设备报警记录
     */
    @PreAuthorize("@ss.hasPermi('equipment:warning:add')")
    @Log(title = "设备报警记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EquipmentWarning equipmentWarning)
    {
        return toAjax(equipmentWarningService.insertEquipmentWarning(equipmentWarning));
    }

    /**
     * 修改设备报警记录
     */
    @PreAuthorize("@ss.hasPermi('equipment:warning:edit')")
    @Log(title = "设备报警记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentWarning equipmentWarning)
    {
        return toAjax(equipmentWarningService.updateEquipmentWarning(equipmentWarning));
    }

    /**
     * 删除设备报警记录
     */
    @PreAuthorize("@ss.hasPermi('equipment:warning:remove')")
    @Log(title = "设备报警记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{warningIds}")
    public AjaxResult remove(@PathVariable Long[] warningIds)
    {
        return toAjax(equipmentWarningService.deleteEquipmentWarningByIds(warningIds));
    }
}
