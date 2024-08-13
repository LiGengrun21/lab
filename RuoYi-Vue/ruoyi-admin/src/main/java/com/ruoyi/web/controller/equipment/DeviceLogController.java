package com.ruoyi.web.controller.equipment;

import java.util.Date;
import java.util.List;

import com.ruoyi.equipment.domain.ZjutEquipment;
import com.ruoyi.equipment.service.IZjutEquipmentService;
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
import com.ruoyi.equipment.domain.DeviceLog;
import com.ruoyi.equipment.service.IDeviceLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备运行统计Controller
 *
 */
@RestController
@RequestMapping("/equipment/devicelog")
public class DeviceLogController extends BaseController
{
    @Autowired
    private IDeviceLogService deviceLogService;

    @Autowired
    private IZjutEquipmentService equipmentService;

    /**
     * 查询设备运行统计列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:devicelog:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceLog deviceLog)
    {
        startPage();
        List<DeviceLog> list = deviceLogService.selectDeviceLogList(deviceLog);
        return getDataTable(list);
    }

    @GetMapping("/3Hourlist")
    public TableDataInfo ThreeHourslist(DeviceLog deviceLog)
    {
        List<DeviceLog> list = deviceLogService.select3HoursDeviceLogList(deviceLog);
        return getDataTable(list);
    }

    @GetMapping("/1Hourlist")
    public TableDataInfo OneHourslist(DeviceLog deviceLog)
    {
        List<DeviceLog> list = deviceLogService.select1HoursDeviceLogList(deviceLog);
        return getDataTable(list);
    }

    @GetMapping("/24Hourlist")
    public TableDataInfo twentyFourHourslist(DeviceLog deviceLog)
    {
        List<DeviceLog> list = deviceLogService.select24HoursDeviceLogList(deviceLog);
        return getDataTable(list);
    }

    @PostMapping("/oneDaylist")
    public TableDataInfo OneDaylist(DeviceLog deviceLog, @RequestBody String date)
    {
        List<DeviceLog> list = deviceLogService.selectOneDayDeviceLogList(deviceLog,date);
        return getDataTable(list);
    }


    /**
     * 导出设备运行统计列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:devicelog:export')")
    @Log(title = "设备运行统计", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DeviceLog deviceLog)
    {
        List<DeviceLog> list = deviceLogService.selectDeviceLogList(deviceLog);
        ExcelUtil<DeviceLog> util = new ExcelUtil<DeviceLog>(DeviceLog.class);
        return util.exportExcel(list, "devicelog");
    }

    /**
     * 获取设备运行统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('equipment:devicelog:query')")
    @GetMapping(value = "/{deviceLogId}")
    public AjaxResult getInfo(@PathVariable("deviceLogId") Long deviceLogId)
    {
        return AjaxResult.success(deviceLogService.selectDeviceLogById(deviceLogId));
    }

    /**
     * 新增设备运行统计
     */
    @PreAuthorize("@ss.hasPermi('equipment:devicelog:add')")
    @Log(title = "设备运行统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceLog deviceLog)
    {
        return toAjax(deviceLogService.insertDeviceLog(deviceLog));
    }

    /**
     * 修改设备运行统计
     */
    @PreAuthorize("@ss.hasPermi('equipment:devicelog:edit')")
    @Log(title = "设备运行统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceLog deviceLog)
    {
        return toAjax(deviceLogService.updateDeviceLog(deviceLog));
    }

    /**
     * 删除设备运行统计
     */
    @PreAuthorize("@ss.hasPermi('equipment:devicelog:remove')")
    @Log(title = "设备运行统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deviceLogIds}")
    public AjaxResult remove(@PathVariable Long[] deviceLogIds)
    {
        return toAjax(deviceLogService.deleteDeviceLogByIds(deviceLogIds));
    }

    @PutMapping("/createNewDeviceLog")
    public AjaxResult createLog(@RequestBody String equipmentId){
        String id = equipmentId.substring(1,equipmentId.length()-1);
        Long equipmentIdValue = Long.valueOf(id);
        ZjutEquipment equipment = equipmentService.selectZjutEquipmentById(equipmentIdValue);
        System.out.println(equipment);
        String tableName = equipment.getEquipmentSnNumber() + "_device_log";
        deviceLogService.createNewDeviceLog(tableName);
        return AjaxResult.success();
    }

    @PutMapping("/test")
    public AjaxResult test(){
        deviceLogService.createNewDeviceLog("test_device_log");
        return AjaxResult.success("创建成功");
    }
}
