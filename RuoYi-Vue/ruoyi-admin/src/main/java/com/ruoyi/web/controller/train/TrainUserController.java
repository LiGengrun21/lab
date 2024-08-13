package com.ruoyi.web.controller.train;

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
import com.ruoyi.train.domain.TrainUser;
import com.ruoyi.train.service.ITrainUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 培训-用户Controller
 *
 */
@RestController
@RequestMapping("/train/trainUser")
public class TrainUserController extends BaseController
{
    @Autowired
    private ITrainUserService trainUserService;

    /**
     * 查询培训-用户列表
     */
    @PreAuthorize("@ss.hasPermi('train:trainUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrainUser trainUser)
    {
        startPage();
        List<TrainUser> list = trainUserService.selectTrainUserList(trainUser);
        return getDataTable(list);
    }

    /**
     * 导出培训-用户列表
     */
    @PreAuthorize("@ss.hasPermi('train:trainUser:export')")
    @Log(title = "培训-用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TrainUser trainUser)
    {
        List<TrainUser> list = trainUserService.selectTrainUserList(trainUser);
        ExcelUtil<TrainUser> util = new ExcelUtil<TrainUser>(TrainUser.class);
        return util.exportExcel(list, "trainUser");
    }

    /**
     * 获取培训-用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('train:trainUser:query')")
    @GetMapping(value = "/{trainId}/{userId}")
    public AjaxResult getInfo(@PathVariable("trainId") Long trainId,@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(trainUserService.selectTrainUserById(trainId,userId));
    }

    /**
     * 新增培训-用户
     */
    @PreAuthorize("@ss.hasPermi('train:trainUser:add')")
    @Log(title = "培训-用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody TrainUser trainUser)
    {
        return toAjax(trainUserService.insertTrainUser(trainUser));
    }

    /**
     * 修改培训-用户
     */
    @PreAuthorize("@ss.hasPermi('train:trainUser:edit')")
    @Log(title = "培训-用户", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody TrainUser trainUser)
    {
        return toAjax(trainUserService.updateTrainUser(trainUser));
    }

    /**
     * 删除培训-用户
     */
    @PreAuthorize("@ss.hasPermi('train:trainUser:remove')")
    @Log(title = "培训-用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/delete")
    public AjaxResult remove(@RequestBody TrainUser trainUser)
    {
        return toAjax(trainUserService.deleteTrainUser(trainUser));
    }
}
