package com.ruoyi.web.controller.file;

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
import com.ruoyi.file.domain.DangerConditionFile;
import com.ruoyi.file.service.IDangerConditionFileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 危险事故文件Controller
 *
 */
@RestController
@RequestMapping("/file/dangerConditionFile")
public class DangerConditionFileController extends BaseController
{
    @Autowired
    private IDangerConditionFileService dangerConditionFileService;

    /**
     * 查询危险事故文件列表
     */
    @PreAuthorize("@ss.hasPermi('file:dangerConditionFile:list')")
    @GetMapping("/list")
    public TableDataInfo list(DangerConditionFile dangerConditionFile)
    {
        startPage();
        List<DangerConditionFile> list = dangerConditionFileService.selectDangerConditionFileList(dangerConditionFile);
        return getDataTable(list);
    }

    /**
     * 导出危险事故文件列表
     */
    @PreAuthorize("@ss.hasPermi('file:dangerConditionFile:export')")
    @Log(title = "危险事故文件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DangerConditionFile dangerConditionFile)
    {
        List<DangerConditionFile> list = dangerConditionFileService.selectDangerConditionFileList(dangerConditionFile);
        ExcelUtil<DangerConditionFile> util = new ExcelUtil<DangerConditionFile>(DangerConditionFile.class);
        return util.exportExcel(list, "dangerConditionFile");
    }

    /**
     * 获取危险事故文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('file:dangerConditionFile:query')")
    @GetMapping(value = "/{dangerConditionFileId}")
    public AjaxResult getInfo(@PathVariable("dangerConditionFileId") Long dangerConditionFileId)
    {
        return AjaxResult.success(dangerConditionFileService.selectDangerConditionFileById(dangerConditionFileId));
    }

    /**
     * 新增危险事故文件
     */
    @PreAuthorize("@ss.hasPermi('file:dangerConditionFile:add')")
    @Log(title = "危险事故文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DangerConditionFile dangerConditionFile)
    {
        return toAjax(dangerConditionFileService.insertDangerConditionFile(dangerConditionFile));
    }

    /**
     * 修改危险事故文件
     */
    @PreAuthorize("@ss.hasPermi('file:dangerConditionFile:edit')")
    @Log(title = "危险事故文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DangerConditionFile dangerConditionFile)
    {
        return toAjax(dangerConditionFileService.updateDangerConditionFile(dangerConditionFile));
    }

    /**
     * 删除危险事故文件
     */
    @PreAuthorize("@ss.hasPermi('file:dangerConditionFile:remove')")
    @Log(title = "危险事故文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dangerConditionFileIds}")
    public AjaxResult remove(@PathVariable Long[] dangerConditionFileIds)
    {
        return toAjax(dangerConditionFileService.deleteDangerConditionFileByIds(dangerConditionFileIds));
    }
}
