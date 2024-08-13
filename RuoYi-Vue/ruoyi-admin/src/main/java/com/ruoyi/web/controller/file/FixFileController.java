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
import com.ruoyi.file.domain.FixFile;
import com.ruoyi.file.service.IFixFileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备维保附件Controller
 *
 */
@RestController
@RequestMapping("/file/fixfile")
public class FixFileController extends BaseController
{
    @Autowired
    private IFixFileService fixFileService;

    /**
     * 查询设备维保附件列表
     */
    @PreAuthorize("@ss.hasPermi('file:fixfile:list')")
    @GetMapping("/list")
    public TableDataInfo list(FixFile fixFile)
    {
        startPage();
        List<FixFile> list = fixFileService.selectFixFileList(fixFile);
        return getDataTable(list);
    }

    /**
     * 导出设备维保附件列表
     */
    @PreAuthorize("@ss.hasPermi('file:fixfile:export')")
    @Log(title = "设备维保附件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FixFile fixFile)
    {
        List<FixFile> list = fixFileService.selectFixFileList(fixFile);
        ExcelUtil<FixFile> util = new ExcelUtil<FixFile>(FixFile.class);
        return util.exportExcel(list, "fixfile");
    }

    /**
     * 获取设备维保附件详细信息
     */
    @PreAuthorize("@ss.hasPermi('file:fixfile:query')")
    @GetMapping(value = "/{fixFileId}")
    public AjaxResult getInfo(@PathVariable("fixFileId") Long fixFileId)
    {
        return AjaxResult.success(fixFileService.selectFixFileById(fixFileId));
    }

    /**
     * 新增设备维保附件
     */
    @PreAuthorize("@ss.hasPermi('file:fixfile:add')")
    @Log(title = "设备维保附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FixFile fixFile)
    {
        return toAjax(fixFileService.insertFixFile(fixFile));
    }

    /**
     * 修改设备维保附件
     */
    @PreAuthorize("@ss.hasPermi('file:fixfile:edit')")
    @Log(title = "设备维保附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FixFile fixFile)
    {
        return toAjax(fixFileService.updateFixFile(fixFile));
    }

    /**
     * 删除设备维保附件
     */
    @PreAuthorize("@ss.hasPermi('file:fixfile:remove')")
    @Log(title = "设备维保附件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fixFileIds}")
    public AjaxResult remove(@PathVariable Long[] fixFileIds)
    {
        return toAjax(fixFileService.deleteFixFileByIds(fixFileIds));
    }
}
