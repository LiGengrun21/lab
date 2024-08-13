package com.ruoyi.web.controller.equipment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.equipment.service.IEquipmentSubscribeService;
import com.ruoyi.quartz.task.DeviceTask;
import com.ruoyi.quartz.connector.DeviceConnector;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.equipment.domain.ZjutEquipment;
import com.ruoyi.equipment.service.IZjutEquipmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 设备Controller
 *
 */
@RestController
@RequestMapping("/equipment/show")
public class ZjutEquipmentController extends BaseController{
    @Autowired
    DeviceConnector deviceConnector;
    @Autowired
    private IZjutEquipmentService zjutEquipmentService;
    @Autowired
    private DeviceTask deviceTask;
    @Autowired
    private IEquipmentSubscribeService equipmentSubscribeService;
    /**
     * 查询设备列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:show:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZjutEquipment zjutEquipment){
        startPage();
        List<ZjutEquipment> list=zjutEquipmentService.selectZjutEquipmentList(zjutEquipment);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('equipment:show:list')")
    @GetMapping("/listSubscribe")
    public TableDataInfo listSubscribe(ZjutEquipment zjutEquipment){
        startPage();
        List<ZjutEquipment> list=zjutEquipmentService.selectZjutEquipmentSubscribeList(zjutEquipment);
        return getDataTable(list);
    }
    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:show:export')")
    @Log(title="设备", businessType=BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZjutEquipment zjutEquipment){
        List<ZjutEquipment> list=zjutEquipmentService.selectZjutEquipmentList(zjutEquipment);
        ExcelUtil<ZjutEquipment> util=new ExcelUtil<ZjutEquipment>(ZjutEquipment.class);
        return util.exportExcel(list,"show");
    }
    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('equipment:show:query')")
    @GetMapping(value="/{equipmentId}")
    public AjaxResult getInfo(@PathVariable("equipmentId") Long equipmentId){
        return AjaxResult.success(zjutEquipmentService.selectZjutEquipmentById(equipmentId));
    }
    /**
     * 获取设备及其白名单
     */
    @PreAuthorize("@ss.hasPermi('equipment:show:query')")
    @GetMapping("whitelist/{equipmentId}")
    public AjaxResult getEquipmentAndWhitelist(@PathVariable Long equipmentId){
        return AjaxResult.success(zjutEquipmentService.selectEquipmentAndWhitelist(equipmentId));
    }
    /**
     * 新增设备
     */
    @PreAuthorize("@ss.hasPermi('equipment:show:add')")
    @Log(title="设备", businessType=BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZjutEquipment zjutEquipment){
        return toAjax(zjutEquipmentService.insertZjutEquipment(zjutEquipment));
    }
    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('equipment:show:edit')")
    @Log(title="设备", businessType=BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZjutEquipment zjutEquipment){
        return toAjax(zjutEquipmentService.updateZjutEquipment(zjutEquipment));
    }

    /**
     * 设备图片上传
     */
    @Log(title = "设备图片", businessType = BusinessType.UPDATE)
    @PostMapping("/equipmentPic")
    public AjaxResult avatar(@RequestParam("equipmentPic") MultipartFile file,
                             @RequestParam("equipmentId") Long equipmentId) throws IOException
    {
        if (!file.isEmpty())
        {
            String equipmentPic = FileUploadUtils.upload(RuoYiConfig.getEquipmentPicPath(), file);
            if (zjutEquipmentService.updateZjutEquipmentPic(equipmentId, equipmentPic))
            {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", equipmentPic);
                // todo 更新缓存
                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }
    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('equipment:show:remove')")
    @Log(title="设备", businessType=BusinessType.DELETE)
    @DeleteMapping("/{equipmentIds}")
    public AjaxResult remove(@PathVariable Long[] equipmentIds){
        return toAjax(zjutEquipmentService.deleteZjutEquipmentByIds(equipmentIds));
    }
    @GetMapping("/getById/{deviceId}")
    public TableDataInfo getById(@PathVariable String deviceId){
        ArrayList<Object> result=new ArrayList<>();
        result.add(deviceConnector.getById(deviceId));
        return getDataTable(result);
    }
    @GetMapping("/getDeviceStatus/{deviceId}")
    public TableDataInfo getDeviceStatus(@PathVariable String deviceId){
        ArrayList<Object> result=new ArrayList<>();
        result.add(deviceConnector.getDeviceStatus(deviceId));
        return getDataTable(result);
    }
    @PostMapping("/switchon/{deviceId}")
    public AjaxResult switchOn(@PathVariable String deviceId){
        CommandWrapper cmdWrapper=new CommandWrapper();
        cmdWrapper.commands=new ArrayList<>();
        cmdWrapper.commands.add(new Command());
        cmdWrapper.commands.get(0).code="switch_1";
        cmdWrapper.commands.get(0).value=true;
        deviceConnector.commands(deviceId,cmdWrapper);
        return AjaxResult.success();
    }
    @PostMapping("/switchoff/{deviceId}")
    public AjaxResult switchOff(@PathVariable String deviceId){
        CommandWrapper cmdWrapper=new CommandWrapper();
        cmdWrapper.commands=new ArrayList<>();
        cmdWrapper.commands.add(new Command());
        cmdWrapper.commands.get(0).code="switch_1";
        cmdWrapper.commands.get(0).value=false;
        deviceConnector.commands(deviceId,cmdWrapper);
        return AjaxResult.success();
    }
    static class Command{
        String code;
        Object value;
    }
    static class CommandWrapper{
        List<Command> commands;
    }
    @GetMapping("/test")
    public AjaxResult test(){
        deviceTask.deviceTaskTest();
        return AjaxResult.success();
    }
    /**
     * 获取某个准确时间设备的个数(分为[被预约的]和[未被预约的]两种)
     */
    @GetMapping("/getAmountOfEquipmentByDefiniteTime")
    public AjaxResult getAmountOfEquipmentByDefiniteTime(String definiteTime){
        //获得date和time
        String[] strings=definiteTime.split(" ");
        String date=strings[0];
        String time=null;
        if(strings.length==1){
            time="00:00:00";
        }
        else{
            time=strings[1];
        }
        //获取已经被预约的设备的个数
        int subscribedAmount=equipmentSubscribeService.selectAmountOfSubscribedEquipmentByDefiniteTime(date,time);
        HashMap<String,Integer> res=new HashMap<>();
        res.put("subscribedAmount",subscribedAmount);
        //获取到设备总数量
        int totalAmount=zjutEquipmentService.selectAmountByCondition(null);
        //没有被预约的设备的个数
        int notSubcribedAmount=totalAmount-subscribedAmount;
        res.put("notSubcribedAmount",notSubcribedAmount);
        return AjaxResult.success(res);
    }
}
