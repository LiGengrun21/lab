package com.ruoyi.web.controller.equipment;

import java.util.ArrayList;
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
import com.ruoyi.equipment.domain.EquipmentCost;
import com.ruoyi.equipment.service.IEquipmentCostService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备收费Controller
 *
 */
@RestController
@RequestMapping("/equipment/cost")
public class EquipmentCostController extends BaseController
{
    @Autowired
    private IEquipmentCostService equipmentCostService;

    /**
     * 查询设备收费列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:cost:list')")
    @GetMapping("/list")
    public TableDataInfo list(EquipmentCost equipmentCost)
    {
        startPage();
        List<EquipmentCost> list = equipmentCostService.selectEquipmentCostList(equipmentCost);
        return getDataTable(list);
    }

    /**
     * 导出设备收费列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:cost:export')")
    @Log(title = "设备收费", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EquipmentCost equipmentCost)
    {
        List<EquipmentCost> list = equipmentCostService.selectEquipmentCostList(equipmentCost);
        ExcelUtil<EquipmentCost> util = new ExcelUtil<EquipmentCost>(EquipmentCost.class);
        return util.exportExcel(list, "cost");
    }

    /**
     * 获取设备收费详细信息
     */
    @PreAuthorize("@ss.hasPermi('equipment:cost:query')")
    @GetMapping(value = "/{costId}")
    public AjaxResult getInfo(@PathVariable("costId") Long costId)
    {
        return AjaxResult.success(equipmentCostService.selectEquipmentCostById(costId));
    }

    /**
     * 新增设备收费
     */
    @PreAuthorize("@ss.hasPermi('equipment:cost:add')")
    @Log(title = "设备收费", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EquipmentCost equipmentCost)
    {
        return toAjax(equipmentCostService.insertEquipmentCost(equipmentCost));
    }

    /**
     * 修改设备收费
     */
    @PreAuthorize("@ss.hasPermi('equipment:cost:edit')")
    @Log(title = "设备收费", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentCost equipmentCost)
    {
        return toAjax(equipmentCostService.updateEquipmentCost(equipmentCost));
    }

    /**
     * 删除设备收费
     */
    @PreAuthorize("@ss.hasPermi('equipment:cost:remove')")
    @Log(title = "设备收费", businessType = BusinessType.DELETE)
	@DeleteMapping("/{costIds}")
    public AjaxResult remove(@PathVariable Long[] costIds)
    {
        return toAjax(equipmentCostService.deleteEquipmentCostByIds(costIds));
    }
    /**
     *通过设备id查询设备的所有收费类型
     */
    @GetMapping("/getCostTypesByEquipmentId")
    public AjaxResult getCostTypesByEquipmentId(int equipmentId){
        ArrayList<String> costTypes=equipmentCostService.selectCostTypesByEquipmentId((long)equipmentId);
        return AjaxResult.success(costTypes);
    }
}
