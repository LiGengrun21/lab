package com.ruoyi.web.controller.equipment;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.equipment.domain.EquipmentSubscribe;
import com.ruoyi.equipment.service.IEquipmentSubscribeService;
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
import com.ruoyi.equipment.domain.EquipmentUse;
import com.ruoyi.equipment.service.IEquipmentUseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备实际使用记录Controller
 *
 */
@RestController
@RequestMapping("/equipment/use")
public class EquipmentUseController extends BaseController
{
    @Autowired
    private IEquipmentUseService equipmentUseService;

    @Autowired
    private IEquipmentSubscribeService equipmentSubscribeService;


    /**
     * 查询设备实际使用记录列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:use:list')")
    @GetMapping("/list")
    public TableDataInfo list(EquipmentUse equipmentUse)
    {
        startPage();
        List<EquipmentUse> list = equipmentUseService.selectEquipmentUseList(equipmentUse);
        return getDataTable(list);
    }

    /**
     * 导出设备实际使用记录列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:use:export')")
    @Log(title = "设备实际使用记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EquipmentUse equipmentUse)
    {
        List<EquipmentUse> list = equipmentUseService.selectEquipmentUseList(equipmentUse);
        ExcelUtil<EquipmentUse> util = new ExcelUtil<EquipmentUse>(EquipmentUse.class);
        return util.exportExcel(list, "use");
    }

    /**
     * 获取设备实际使用记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('equipment:use:query')")
    @GetMapping(value = "/{equipmentUseId}")
    public AjaxResult getInfo(@PathVariable("equipmentUseId") Long equipmentUseId)
    {
        return AjaxResult.success(equipmentUseService.selectEquipmentUseById(equipmentUseId));
    }

    /**
     * 新增设备实际使用记录
     */
    @PreAuthorize("@ss.hasPermi('equipment:use:add')")
    @Log(title = "设备实际使用记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EquipmentUse equipmentUse)
    {
        return toAjax(equipmentUseService.insertEquipmentUse(equipmentUse));
    }

    /**
     * 修改设备实际使用记录
     */
    @PreAuthorize("@ss.hasPermi('equipment:use:edit')")
    @Log(title = "设备实际使用记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentUse equipmentUse)
    {
        return toAjax(equipmentUseService.updateEquipmentUse(equipmentUse));
    }

    /**
     * 删除设备实际使用记录
     */
    @PreAuthorize("@ss.hasPermi('equipment:use:remove')")
    @Log(title = "设备实际使用记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{equipmentUseIds}")
    public AjaxResult remove(@PathVariable Long[] equipmentUseIds)
    {
        return toAjax(equipmentUseService.deleteEquipmentUseByIds(equipmentUseIds));
    }

    /**
     * 根据id计算费用
     */
    @PutMapping("/cost/{equipmentUseId}")
    public AjaxResult useCost(@PathVariable Long equipmentUseId) throws ParseException {
        EquipmentUse u = equipmentUseService.selectEquipmentUseById(equipmentUseId);
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        EquipmentSubscribe e = equipmentSubscribeService.selectEquipmentSubscribeById(u.getEquipmentSubscribeId());
        float timeSubStart = df.parse(e.getSubscribeStartTime()).getTime();
        float timeSubEnd = df.parse(e.getSubscribeEndTime()).getTime();
        float CostSubscribe = e.getTotalCost().floatValue();
        float timeUseStart = df.parse(u.getUseStartTime()).getTime();
        float timeUseEnd = df.parse(u.getUseEndTime()).getTime();
        float CostUse = CostSubscribe*((timeUseEnd - timeUseStart)/(timeSubEnd - timeSubStart));
        System.out.println(CostUse);
        u.setUseCost(BigDecimal.valueOf(CostUse));
        equipmentUseService.updateEquipmentUse(u);
        return AjaxResult.success();
    }
}
