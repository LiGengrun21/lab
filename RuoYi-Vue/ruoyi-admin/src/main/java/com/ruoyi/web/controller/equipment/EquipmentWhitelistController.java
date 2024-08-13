package com.ruoyi.web.controller.equipment;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.equipment.mapper.EquipmentWhitelistMapper;
import com.ruoyi.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;
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
import com.ruoyi.equipment.domain.EquipmentWhitelist;
import com.ruoyi.equipment.service.IEquipmentWhitelistService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备白名单Controller
 *
 */
@RestController
@RequestMapping("/equipment/whitelist")
public class EquipmentWhitelistController extends BaseController
{
    @Autowired
    private IEquipmentWhitelistService equipmentWhitelistService;

    @Autowired
    private EquipmentWhitelistMapper equipmentWhitelistMapper;

    /**
     * 查询设备白名单列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:whitelist:list')")
    @GetMapping("/list")
    public TableDataInfo list(EquipmentWhitelist equipmentWhitelist)
    {
        startPage();
        List<EquipmentWhitelist> list = equipmentWhitelistService.selectEquipmentWhitelistList(equipmentWhitelist);
        return getDataTable(list);
    }

    /**
     * 导出设备白名单列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:whitelist:export')")
    @Log(title = "设备白名单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EquipmentWhitelist equipmentWhitelist)
    {
        List<EquipmentWhitelist> list = equipmentWhitelistService.selectEquipmentWhitelistList(equipmentWhitelist);
        ExcelUtil<EquipmentWhitelist> util = new ExcelUtil<EquipmentWhitelist>(EquipmentWhitelist.class);
        return util.exportExcel(list, "whitelist");
    }

    /**
     * 获取设备白名单详细信息
     */
    @PreAuthorize("@ss.hasPermi('equipment:whitelist:query')")
    @GetMapping(value = "/{equipmentId}")
    public AjaxResult getInfo(@PathVariable("equipmentId") Long equipmentId)
    {
        return AjaxResult.success(equipmentWhitelistService.selectEquipmentWhitelistById(equipmentId));
    }

    /**
     * 新增设备白名单
     */
    @PreAuthorize("@ss.hasPermi('equipment:whitelist:add')")
    @Log(title = "设备白名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EquipmentWhitelist equipmentWhitelist)
    {
        return toAjax(equipmentWhitelistService.insertEquipmentWhitelist(equipmentWhitelist));
    }

    /**
     * 批量新增设备白名单
     */
    @PreAuthorize("@ss.hasPermi('equipment:whitelist:add')")
    @PostMapping("/batchAdd")
    public AjaxResult batchAdd( @RequestBody List<EquipmentWhitelist> equipmentWhitelistList)
    {
        //equipmentWhitelistMapper.batchEquipmentWhitelist(equipmentWhitelistList);
        return AjaxResult.success(equipmentWhitelistService.batchEquipmentWhitelist(equipmentWhitelistList));
    }
    /**
     * 修改设备白名单
     */
    @PreAuthorize("@ss.hasPermi('equipment:whitelist:edit')")
    @Log(title = "设备白名单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentWhitelist equipmentWhitelist)
    {
        return toAjax(equipmentWhitelistService.updateEquipmentWhitelist(equipmentWhitelist));
    }

    /**
     * 批量删除设备白名单
     */
    @PreAuthorize("@ss.hasPermi('equipment:whitelist:remove')")
    @Log(title = "设备白名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/batchdelete/{equipmentId}")
    public AjaxResult remove(@PathVariable Long equipmentId, @RequestBody Long[] userIds)
    {
        return toAjax(equipmentWhitelistService.deleteEquipmentWhitelistByIds(equipmentId,userIds));
    }

    /**
     * 批量删除设备白名单
     */
    @PreAuthorize("@ss.hasPermi('equipment:whitelist:remove')")
    @Log(title = "设备白名单", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult delete(@RequestBody EquipmentWhitelist equipmentWhitelist)
    {
        return toAjax(equipmentWhitelistService.deleteEquipmentWhitelist(equipmentWhitelist));
    }
}
