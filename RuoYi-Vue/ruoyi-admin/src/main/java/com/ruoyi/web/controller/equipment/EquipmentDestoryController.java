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
import com.ruoyi.equipment.domain.EquipmentDestory;
import com.ruoyi.equipment.service.IEquipmentDestoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备报废Controller
 *
 */
@RestController
@RequestMapping("/equipment/destory")
public class EquipmentDestoryController extends BaseController
{
    @Autowired
    private IEquipmentDestoryService equipmentDestoryService;

    /**
     * 查询设备报废列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:destory:list')")
    @GetMapping("/list")
    public TableDataInfo list(EquipmentDestory equipmentDestory)
    {
        startPage();
        List<EquipmentDestory> list = equipmentDestoryService.selectEquipmentDestoryList(equipmentDestory);
        return getDataTable(list);
    }

    /**
     * 导出设备报废列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:destory:export')")
    @Log(title = "设备报废", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EquipmentDestory equipmentDestory)
    {
        List<EquipmentDestory> list = equipmentDestoryService.selectEquipmentDestoryList(equipmentDestory);
        ExcelUtil<EquipmentDestory> util = new ExcelUtil<EquipmentDestory>(EquipmentDestory.class);
        return util.exportExcel(list, "destory");
    }

    /**
     * 获取设备报废详细信息
     */
    @PreAuthorize("@ss.hasPermi('equipment:destory:query')")
    @GetMapping(value = "/{destoryId}")
    public AjaxResult getInfo(@PathVariable("destoryId") Long destoryId)
    {
        return AjaxResult.success(equipmentDestoryService.selectEquipmentDestoryById(destoryId));
    }

    /**
     * 新增设备报废
     */
    @PreAuthorize("@ss.hasPermi('equipment:destory:add')")
    @Log(title = "设备报废", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EquipmentDestory equipmentDestory)
    {
        return toAjax(equipmentDestoryService.insertEquipmentDestory(equipmentDestory));
    }

    /**
     * 修改设备报废
     */
    @PreAuthorize("@ss.hasPermi('equipment:destory:edit')")
    @Log(title = "设备报废", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentDestory equipmentDestory)
    {
        return toAjax(equipmentDestoryService.updateEquipmentDestory(equipmentDestory));
    }

    /**
     * 删除设备报废
     */
    @PreAuthorize("@ss.hasPermi('equipment:destory:remove')")
    @Log(title = "设备报废", businessType = BusinessType.DELETE)
	@DeleteMapping("/{destoryIds}")
    public AjaxResult remove(@PathVariable Long[] destoryIds)
    {
        return toAjax(equipmentDestoryService.deleteEquipmentDestoryByIds(destoryIds));
    }
}
