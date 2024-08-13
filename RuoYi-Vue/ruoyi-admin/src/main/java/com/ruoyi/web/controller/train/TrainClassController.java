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
import com.ruoyi.train.domain.TrainClass;
import com.ruoyi.train.service.ITrainClassService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程Controller
 *
 */
@RestController
@RequestMapping("/train/class")
public class TrainClassController extends BaseController
{
    @Autowired
    private ITrainClassService trainClassService;

    /**
     * 查询课程列表
     */
    @PreAuthorize("@ss.hasPermi('train:class:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrainClass trainClass)
    {
        startPage();
        List<TrainClass> list = trainClassService.selectTrainClassList(trainClass);
        return getDataTable(list);
    }

    /**
     * 导出课程列表
     */
    @PreAuthorize("@ss.hasPermi('train:class:export')")
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TrainClass trainClass)
    {
        List<TrainClass> list = trainClassService.selectTrainClassList(trainClass);
        ExcelUtil<TrainClass> util = new ExcelUtil<TrainClass>(TrainClass.class);
        return util.exportExcel(list, "class");
    }

    /**
     * 获取课程详细信息
     */
    @PreAuthorize("@ss.hasPermi('train:class:query')")
    @GetMapping(value = "/{classId}")
    public AjaxResult getInfo(@PathVariable("classId") Long classId)
    {
        return AjaxResult.success(trainClassService.selectTrainClassById(classId));
    }

    /**
     * 新增课程
     */
    @PreAuthorize("@ss.hasPermi('train:class:add')")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrainClass trainClass)
    {
        return toAjax(trainClassService.insertTrainClass(trainClass));
    }

    /**
     * 修改课程
     */
    @PreAuthorize("@ss.hasPermi('train:class:edit')")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrainClass trainClass)
    {
        return toAjax(trainClassService.updateTrainClass(trainClass));
    }

    /**
     * 删除课程
     */
    @PreAuthorize("@ss.hasPermi('train:class:remove')")
    @Log(title = "课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{classIds}")
    public AjaxResult remove(@PathVariable Long[] classIds)
    {
        return toAjax(trainClassService.deleteTrainClassByIds(classIds));
    }
}
