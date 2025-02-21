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
import com.ruoyi.system.domain.UserCollectEquipment;
import com.ruoyi.system.service.IUserCollectEquipmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户收藏设备Controller
 *
 */
@RestController
@RequestMapping("/system/userCollectEquipment")
public class UserCollectEquipmentController extends BaseController
{
    @Autowired
    private IUserCollectEquipmentService userCollectEquipmentService;

    /**
     * 查询用户收藏设备列表
     */
    @PreAuthorize("@ss.hasPermi('system:userCollectEquipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserCollectEquipment userCollectEquipment)
    {
        startPage();
        List<UserCollectEquipment> list = userCollectEquipmentService.selectUserCollectEquipmentList(userCollectEquipment);
        return getDataTable(list);
    }

    /**
     * 导出用户收藏设备列表
     */
    @PreAuthorize("@ss.hasPermi('system:userCollectEquipment:export')")
    @Log(title = "用户收藏设备", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(UserCollectEquipment userCollectEquipment)
    {
        List<UserCollectEquipment> list = userCollectEquipmentService.selectUserCollectEquipmentList(userCollectEquipment);
        ExcelUtil<UserCollectEquipment> util = new ExcelUtil<UserCollectEquipment>(UserCollectEquipment.class);
        return util.exportExcel(list, "userCollectEquipment");
    }

    /**
     * 获取用户收藏设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userCollectEquipment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userCollectEquipmentService.selectUserCollectEquipmentById(id));
    }

    /**
     * 新增用户收藏设备
     */
    @PreAuthorize("@ss.hasPermi('system:userCollectEquipment:add')")
    @Log(title = "用户收藏设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserCollectEquipment userCollectEquipment)
    {
        return toAjax(userCollectEquipmentService.insertUserCollectEquipment(userCollectEquipment));
    }

    /**
     * 修改用户收藏设备
     */
    @PreAuthorize("@ss.hasPermi('system:userCollectEquipment:edit')")
    @Log(title = "用户收藏设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserCollectEquipment userCollectEquipment)
    {
        return toAjax(userCollectEquipmentService.updateUserCollectEquipment(userCollectEquipment));
    }

    /**
     * 删除用户收藏设备
     */
    @PreAuthorize("@ss.hasPermi('system:userCollectEquipment:remove')")
    @Log(title = "用户收藏设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userCollectEquipmentService.deleteUserCollectEquipmentByIds(ids));
    }
}
