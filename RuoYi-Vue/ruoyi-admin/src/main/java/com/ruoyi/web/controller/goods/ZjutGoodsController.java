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
import com.ruoyi.goods.domain.ZjutGoods;
import com.ruoyi.goods.service.IZjutGoodsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 耗材列表Controller
 *
 */
@RestController
@RequestMapping("/goods/showGoods")
public class ZjutGoodsController extends BaseController
{
    @Autowired
    private IZjutGoodsService zjutGoodsService;

    /**
     * 查询耗材列表列表
     */
    @PreAuthorize("@ss.hasPermi('goods:showGoods:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZjutGoods zjutGoods)
    {
        startPage();
        List<ZjutGoods> list = zjutGoodsService.selectZjutGoodsList(zjutGoods);
        return getDataTable(list);
    }
    @PreAuthorize("@ss.hasPermi('goods:showGoods:list')")
    @GetMapping("/equipmentlist")
    public  TableDataInfo equipmentlist(ZjutGoods zjutGoods)
    {
        startPage();
        List<ZjutGoods> list = zjutGoodsService.selectEquipmentGoodsList(zjutGoods);
        return getDataTable(list);
    }
    @PreAuthorize("@ss.hasPermi('goods:showGoods:list')")
    @GetMapping("/valuablelist")
    public TableDataInfo valuablelist(ZjutGoods zjutGoods)
    {
        startPage();
        List<ZjutGoods> list = zjutGoodsService.selectValuableGoodsList(zjutGoods);
        return getDataTable(list);
    }

    /**
     * 导出耗材列表列表
     */
    @PreAuthorize("@ss.hasPermi('goods:showGoods:export')")
    @Log(title = "耗材列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZjutGoods zjutGoods)
    {
        List<ZjutGoods> list = zjutGoodsService.selectZjutGoodsList(zjutGoods);
        ExcelUtil<ZjutGoods> util = new ExcelUtil<ZjutGoods>(ZjutGoods.class);
        return util.exportExcel(list, "showGoods");
    }

    /**
     * 获取耗材列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:showGoods:query')")
    @GetMapping(value = "/{goodsId}")
    public AjaxResult getInfo(@PathVariable("goodsId") Long goodsId)
    {
        return AjaxResult.success(zjutGoodsService.selectZjutGoodsById(goodsId));
    }

    /**
     * 新增耗材列表
     */
    @PreAuthorize("@ss.hasPermi('goods:showGoods:add')")
    @Log(title = "耗材列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZjutGoods zjutGoods)
    {
        return toAjax(zjutGoodsService.insertZjutGoods(zjutGoods));
    }

    /**
     * 修改耗材列表
     */
    @PreAuthorize("@ss.hasPermi('goods:showGoods:edit')")
    @Log(title = "耗材列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZjutGoods zjutGoods)
    {
        return toAjax(zjutGoodsService.updateZjutGoods(zjutGoods));
    }
    /**
     * 增加耗材
     */
    @Log(title = "耗材列表", businessType = BusinessType.UPDATE)
    @PutMapping("/addgoods")
    public AjaxResult addgoods(@RequestBody ZjutGoods zjutGoods)
    {
        int a = zjutGoodsService.addZjutGoods(zjutGoods);
        if(a > 0){
            return toAjax(a);
        }
        else{
            return AjaxResult.error("库存不足");
        }
    }

    /**
     * 删除耗材列表
     */
    @PreAuthorize("@ss.hasPermi('goods:showGoods:remove')")
    @Log(title = "耗材列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsIds}")
    public AjaxResult remove(@PathVariable Long[] goodsIds)
    {
        return toAjax(zjutGoodsService.deleteZjutGoodsByIds(goodsIds));
    }
}
