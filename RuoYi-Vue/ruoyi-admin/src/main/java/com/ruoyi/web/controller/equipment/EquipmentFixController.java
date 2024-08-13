package com.ruoyi.web.controller.equipment;

import java.util.List;

import com.ruoyi.equipment.domain.ZjutEquipment;
import com.ruoyi.equipment.service.IZjutEquipmentService;
import org.checkerframework.checker.units.qual.A;
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
import com.ruoyi.equipment.domain.EquipmentFix;
import com.ruoyi.equipment.service.IEquipmentFixService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import retrofit2.http.PUT;

/**
 * 设备维保Controller
 *
 */
@RestController
@RequestMapping("/equipment/fix")
public class EquipmentFixController extends BaseController{
    @Autowired
    private IEquipmentFixService equipmentFixService;
    @Autowired
    private IZjutEquipmentService equipmentService;
    /**
     * 查询设备维保列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:fix:list')")
    @GetMapping("/list")
    public TableDataInfo list(EquipmentFix equipmentFix){
        startPage();
        List<EquipmentFix> list=equipmentFixService.selectEquipmentFixList(equipmentFix);
        return getDataTable(list);
    }
    /**
     * 导出设备维保列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:fix:export')")
    @Log(title="设备维保", businessType=BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EquipmentFix equipmentFix){
        List<EquipmentFix> list=equipmentFixService.selectEquipmentFixList(equipmentFix);
        ExcelUtil<EquipmentFix> util=new ExcelUtil<EquipmentFix>(EquipmentFix.class);
        return util.exportExcel(list,"fix");
    }
    /**
     * 获取设备维保详细信息
     */
    @PreAuthorize("@ss.hasPermi('equipment:fix:query')")
    @GetMapping(value="/{fixId}")
    public AjaxResult getInfo(@PathVariable("fixId") Long fixId){
        return AjaxResult.success(equipmentFixService.selectEquipmentFixById(fixId));
    }
    /**
     * 修改设备维保
     */
    @PreAuthorize("@ss.hasPermi('equipment:fix:edit')")
    @Log(title="设备维保", businessType=BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentFix equipmentFix){
        return toAjax(equipmentFixService.updateEquipmentFix(equipmentFix));
    }
    /**
     * 新增设备报修
     */
    @PreAuthorize("@ss.hasPermi('equipment:fix:add')")
    @Log(title="设备维保", businessType=BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EquipmentFix equipmentFix){
        //更新设备状态为不可预约
        ZjutEquipment zjutEquipment=new ZjutEquipment();
        zjutEquipment.setEquipmentId(equipmentFix.getEquipmentId());
        zjutEquipment.setEquipmentSubscribeType(ZjutEquipment.CAN_NOT_SUBSCRIBE);
        equipmentService.updateZjutEquipment(zjutEquipment);
        //设置保修状态为预报修
        equipmentFix.setFixStatus(EquipmentFix.PRE_FIX);
        return toAjax(equipmentFixService.insertEquipmentFix(equipmentFix));
    }
    /**
     * 确认报修
     */
    @PutMapping("/confirmFix")
    public AjaxResult confirmFix(EquipmentFix equipmentFix){
        equipmentFix.setFixStatus(EquipmentFix.CONFIRM_FIX);
        return toAjax(equipmentFixService.updateEquipmentFix(equipmentFix));
    }
    /**
     * 设备报修未通过
     */
    @DeleteMapping("/refuseFix")
    public AjaxResult refuseFix(EquipmentFix equipmentFix){
        //设置设备状态为可预约
        updateEquipmentToCanSubscribe(equipmentFix);
        return toAjax(equipmentFixService.deleteEquipmentFixById(equipmentFix.getFixId()));
    }
    /**
     * 报修结束
     */
    @PutMapping("/finishFix")
    public AjaxResult finishFix(EquipmentFix equipmentFix){
        //设置设备状态为可预约
        updateEquipmentToCanSubscribe(equipmentFix);
        equipmentFix.setFixStatus(EquipmentFix.FINISH_FIX);
        return toAjax(equipmentFixService.updateEquipmentFix(equipmentFix));
    }
    /**
     * 设置设备状态为可预约
     */
    private void updateEquipmentToCanSubscribe(EquipmentFix equipmentFix){
        //查到设备id
        EquipmentFix completeEquipmentFix=equipmentFixService.selectEquipmentFixById(equipmentFix.getFixId());
        Long equipmentId=completeEquipmentFix.getEquipmentId();
        ZjutEquipment zjutEquipment=new ZjutEquipment();
        zjutEquipment.setEquipmentId(equipmentId);
        zjutEquipment.setEquipmentSubscribeType(ZjutEquipment.CAN_SUBSCRIBE);
        equipmentService.updateZjutEquipment(zjutEquipment);
    }
    /**
     * 删除设备维保
     */
    @PreAuthorize("@ss.hasPermi('equipment:fix:remove')")
    @Log(title="设备维保", businessType=BusinessType.DELETE)
    @DeleteMapping("/{fixIds}")
    public AjaxResult remove(@PathVariable Long[] fixIds){
        return toAjax(equipmentFixService.deleteEquipmentFixByIds(fixIds));
    }
}
