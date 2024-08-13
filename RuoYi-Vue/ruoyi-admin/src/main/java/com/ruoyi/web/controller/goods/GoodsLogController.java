package com.ruoyi.web.controller.goods;

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
import com.ruoyi.goods.domain.GoodsLog;
import com.ruoyi.goods.service.IGoodsLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出入库日志Controller
 *
 */
@RestController
@RequestMapping("/goods/log")
public class GoodsLogController extends BaseController
{
    @Autowired
    private IGoodsLogService goodsLogService;

    /**
     * 查询出入库日志列表
     */
    @PreAuthorize("@ss.hasPermi('goods:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsLog goodsLog)
    {
        startPage();
        List<GoodsLog> list = goodsLogService.selectGoodsLogList(goodsLog);
        return getDataTable(list);
    }

    /**
     * 导出出入库日志列表
     */
    @PreAuthorize("@ss.hasPermi('goods:log:export')")
    @Log(title = "出入库日志", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(GoodsLog goodsLog)
    {
        List<GoodsLog> list = goodsLogService.selectGoodsLogList(goodsLog);
        ExcelUtil<GoodsLog> util = new ExcelUtil<GoodsLog>(GoodsLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 获取出入库日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:log:query')")
    @GetMapping(value = "/{goodsLogId}")
    public AjaxResult getInfo(@PathVariable("goodsLogId") Long goodsLogId)
    {
        return AjaxResult.success(goodsLogService.selectGoodsLogById(goodsLogId));
    }

    /**
     * 新增出入库日志
     */
    @PreAuthorize("@ss.hasPermi('goods:log:add')")
    @Log(title = "出入库日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsLog goodsLog)
    {
        return toAjax(goodsLogService.insertGoodsLog(goodsLog));
    }

    /**
     * 修改出入库日志
     */
    @PreAuthorize("@ss.hasPermi('goods:log:edit')")
    @Log(title = "出入库日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsLog goodsLog)
    {
        return toAjax(goodsLogService.updateGoodsLog(goodsLog));
    }

    /**
     * 删除出入库日志
     */
    @PreAuthorize("@ss.hasPermi('goods:log:remove')")
    @Log(title = "出入库日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsLogIds}")
    public AjaxResult remove(@PathVariable Long[] goodsLogIds)
    {
        return toAjax(goodsLogService.deleteGoodsLogByIds(goodsLogIds));
    }
}
