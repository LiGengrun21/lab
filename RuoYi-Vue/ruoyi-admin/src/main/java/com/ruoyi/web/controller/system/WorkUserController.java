package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.system.domain.Work;
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
import com.ruoyi.system.domain.WorkUser;
import com.ruoyi.system.service.IWorkUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 值班人员列表 Controller
 *
 */
@RestController
@RequestMapping("/system/workUser")
public class WorkUserController extends BaseController
{
    @Autowired
    private IWorkUserService workUserService;

    /**
     * 查询值班人员列表
     */
    //@PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list()
    {
        startPage();
        List<WorkUser> list = workUserService.selectAll();
        return getDataTable(list);
    }

    /**
     * 导出值班人员excel表格
     */
    //@PreAuthorize("@ss.hasPermi('system:user:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export()
    {
        List<WorkUser> list = workUserService.selectAll();
        ExcelUtil<WorkUser> util = new ExcelUtil<WorkUser>(WorkUser.class);
        return util.exportExcel(list, "值班人员总表");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = "/{workUserId}")
    public AjaxResult getInfo(@PathVariable("workUserId") Long workUserId)
    {
        return AjaxResult.success(workUserService.selectWorkUserById(workUserId));
    }

//    /**
//     * 新增值班人员
//     */
//    //@PreAuthorize("@ss.hasPermi('system:user:add')")
//    @Log(title = "新增值班人员", businessType = BusinessType.INSERT)
//    @PostMapping("/one")
//    public AjaxResult addOne(@RequestBody WorkUser workUser)
//    {
//        return toAjax(workUserService.insertWorkUser(workUser));
//    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkUser workUser)
    {
        return toAjax(workUserService.updateWorkUser(workUser));
    }

    /**
     * 批量删除值班人员（使用）
     */
    //@PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "批量删除值班人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workUserIds}")
    public AjaxResult remove(@PathVariable Long[] workUserIds)
    {
        return toAjax(workUserService.deleteWorkUserByIds(workUserIds));
    }

    /**
     * 批量添加值班人员（使用）
     * @param workUserList
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "批量添加值班人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WorkUser> workUserList)
    {
        return toAjax(workUserService.insertWorkUserList(workUserList));
    }
}
