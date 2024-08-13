package com.ruoyi.web.controller.lab;

import java.util.HashMap;
import java.util.List;

import com.ruoyi.lab.service.ILabSubscribeService;
import io.swagger.models.auth.In;
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
import com.ruoyi.lab.domain.ZjutLab;
import com.ruoyi.lab.service.IZjutLabService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 实验室列表Controller
 *
 */
@RestController
@RequestMapping("/lab/showlab")
public class ZjutLabController extends BaseController{
    @Autowired
    private IZjutLabService zjutLabService;
    @Autowired
    private ILabSubscribeService labSubscribeService;
    /**
     * 查询实验室列表
     */
    @PreAuthorize("@ss.hasPermi('lab:showlab:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZjutLab zjutLab){
        startPage();
        List<ZjutLab> list=zjutLabService.selectZjutLabList(zjutLab);
        return getDataTable(list);
    }
    @PreAuthorize("@ss.hasPermi('lab:showlab:list')")
    @GetMapping("/listEquip")
    public TableDataInfo listEquip(ZjutLab zjutLab){
        startPage();
        List<ZjutLab> list=zjutLabService.selectZjutLabEquipmentList(zjutLab);
        return getDataTable(list);
    }
    /**
     * 导出实验室列表列表
     */
    @PreAuthorize("@ss.hasPermi('lab:showlab:export')")
    @Log(title="实验室列表", businessType=BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZjutLab zjutLab){
        List<ZjutLab> list=zjutLabService.selectZjutLabList(zjutLab);
        ExcelUtil<ZjutLab> util=new ExcelUtil<ZjutLab>(ZjutLab.class);
        return util.exportExcel(list,"showlab");
    }
    /**
     * 获取实验室列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:showlab:query')")
    @GetMapping(value="/{labId}")
    public AjaxResult getInfo(@PathVariable("labId") Long labId){
        return AjaxResult.success(zjutLabService.selectZjutLabById(labId));
    }
    /**
     * 新增实验室列表
     */
    @PreAuthorize("@ss.hasPermi('lab:showlab:add')")
    @Log(title="实验室列表", businessType=BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZjutLab zjutLab){
        return toAjax(zjutLabService.insertZjutLab(zjutLab));
    }
    /**
     * 修改实验室列表
     */
    @PreAuthorize("@ss.hasPermi('lab:showlab:edit')")
    @Log(title="实验室列表", businessType=BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZjutLab zjutLab){
        return toAjax(zjutLabService.updateZjutLab(zjutLab));
    }
    /**
     * 删除实验室列表
     */
    @PreAuthorize("@ss.hasPermi('lab:showlab:remove')")
    @Log(title="实验室列表", businessType=BusinessType.DELETE)
    @DeleteMapping("/{labIds}")
    public AjaxResult remove(@PathVariable Long[] labIds){
        return toAjax(zjutLabService.deleteZjutLabByIds(labIds));
    }
    /**
     * 获取某个准确时间实验室的个数(分为[被预约的]和[未被预约的]两种)
     */
    @GetMapping("/getAmountOfLabByDefiniteTime")
    public AjaxResult getAmountOfLabByDefiniteTime(String definiteTime){
        return doGetAmountOfRoomByDefiniteTime(definiteTime,true);
    }
    /**
     * 获取某个准确时间会议室的个数(分为[被预约的]和[未被预约的]两种)
     */
    @GetMapping("/getAmountOfConferenceRoomByDefiniteTime")
    public AjaxResult getAmountOfConferenceRoomByDefiniteTime(String definiteTime){
        return doGetAmountOfRoomByDefiniteTime(definiteTime,false);
    }
    /**
     * 完成上面两个的功能
     */
    private AjaxResult doGetAmountOfRoomByDefiniteTime(String definiteTime,boolean isLab){
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
        //房间类型
        String roomType=isLab?ZjutLab.LAB:ZjutLab.CONFERENCE_ROOM;
        //获取已经被预约的指定房间的个数
        int subscribedAmount=labSubscribeService.selectAmountOfSubscribedRoomByDefiniteTimeAndRoomType(date,time,roomType);
        HashMap<String,Integer> res=new HashMap<>();
        res.put("subscribedAmount",subscribedAmount);
        //获取到指定房间总数量
        int totalAmount=zjutLabService.selectAmountOfRoomByRoomType(roomType);
        //没有被预约的指定房间的个数
        int notSubcribedAmount=totalAmount-subscribedAmount;
        res.put("notSubcribedAmount",notSubcribedAmount);
        return AjaxResult.success(res);
    }
}
