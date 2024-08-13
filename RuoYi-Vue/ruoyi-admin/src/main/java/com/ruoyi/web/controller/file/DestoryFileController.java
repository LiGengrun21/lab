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
import com.ruoyi.file.domain.DestoryFile;
import com.ruoyi.file.service.IDestoryFileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备报废文件Controller
 *
 */
@RestController
@RequestMapping("/file/destoryfile")
public class DestoryFileController extends BaseController
{
    @Autowired
    private IDestoryFileService destoryFileService;

    /**
     * 查询设备报废文件列表
     */
    @PreAuthorize("@ss.hasPermi('file:destoryfile:list')")
    @GetMapping("/list")
    public TableDataInfo list(DestoryFile destoryFile)
    {
        startPage();
        List<DestoryFile> list = destoryFileService.selectDestoryFileList(destoryFile);
        return getDataTable(list);
    }

    /**
     * 导出设备报废文件列表
     */
    @PreAuthorize("@ss.hasPermi('file:destoryfile:export')")
    @Log(title = "设备报废文件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DestoryFile destoryFile)
    {
        List<DestoryFile> list = destoryFileService.selectDestoryFileList(destoryFile);
        ExcelUtil<DestoryFile> util = new ExcelUtil<DestoryFile>(DestoryFile.class);
        return util.exportExcel(list, "destoryfile");
    }

    /**
     * 获取设备报废文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('file:destoryfile:query')")
    @GetMapping(value = "/{destoryFileId}")
    public AjaxResult getInfo(@PathVariable("destoryFileId") Long destoryFileId)
    {
        return AjaxResult.success(destoryFileService.selectDestoryFileById(destoryFileId));
    }

    /**
     * 新增设备报废文件
     */
    @PreAuthorize("@ss.hasPermi('file:destoryfile:add')")
    @Log(title = "设备报废文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DestoryFile destoryFile)
    {
        return toAjax(destoryFileService.insertDestoryFile(destoryFile));
    }

    /**
     * 修改设备报废文件
     */
    @PreAuthorize("@ss.hasPermi('file:destoryfile:edit')")
    @Log(title = "设备报废文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DestoryFile destoryFile)
    {
        return toAjax(destoryFileService.updateDestoryFile(destoryFile));
    }

    /**
     * 删除设备报废文件
     */
    @PreAuthorize("@ss.hasPermi('file:destoryfile:remove')")
    @Log(title = "设备报废文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{destoryFileIds}")
    public AjaxResult remove(@PathVariable Long[] destoryFileIds)
    {
        return toAjax(destoryFileService.deleteDestoryFileByIds(destoryFileIds));
    }
}
