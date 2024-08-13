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
import com.ruoyi.system.domain.UserCollectLab;
import com.ruoyi.system.service.IUserCollectLabService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户收藏房间Controller
 *
 */
@RestController
@RequestMapping("/system/userCollectLab")
public class UserCollectLabController extends BaseController
{
    @Autowired
    private IUserCollectLabService userCollectLabService;

    /**
     * 查询用户收藏房间列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserCollectLab userCollectLab)
    {
        startPage();
        List<UserCollectLab> list = userCollectLabService.selectUserCollectLabList(userCollectLab);
        return getDataTable(list);
    }

    /**
     * 导出用户收藏房间列表
     */
    @Log(title = "用户收藏房间", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(UserCollectLab userCollectLab)
    {
        List<UserCollectLab> list = userCollectLabService.selectUserCollectLabList(userCollectLab);
        ExcelUtil<UserCollectLab> util = new ExcelUtil<UserCollectLab>(UserCollectLab.class);
        return util.exportExcel(list, "lab");
    }

    /**
     * 获取用户收藏房间详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userCollectLabService.selectUserCollectLabById(id));
    }

    /**
     * 新增用户收藏房间
     */
    @Log(title = "用户收藏房间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserCollectLab userCollectLab)
    {
        return toAjax(userCollectLabService.insertUserCollectLab(userCollectLab));
    }

    /**
     * 修改用户收藏房间
     */
    @Log(title = "用户收藏房间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserCollectLab userCollectLab)
    {
        return toAjax(userCollectLabService.updateUserCollectLab(userCollectLab));
    }

    /**
     * 删除用户收藏房间
     */
    @Log(title = "用户收藏房间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userCollectLabService.deleteUserCollectLabByIds(ids));
    }
}
