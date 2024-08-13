package com.ruoyi.web.controller.train;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.train.domain.TrainUserScore;
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
import com.ruoyi.train.domain.TrainItem;
import com.ruoyi.train.service.ITrainItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 培训Controller
 *
 */
@RestController
@RequestMapping("/train/item")
public class TrainItemController extends BaseController
{
    @Autowired
    private ITrainItemService trainItemService;

    /**
     * 查询培训列表
     */
    @PreAuthorize("@ss.hasPermi('train:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrainItem trainItem)
    {
        startPage();
        List<TrainItem> list = trainItemService.selectTrainItemList(trainItem);
        return getDataTable(list);
    }

    /**
     * 导出培训列表
     */
    @PreAuthorize("@ss.hasPermi('train:item:export')")
    @Log(title = "培训", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TrainItem trainItem)
    {
        List<TrainItem> list = trainItemService.selectTrainItemList(trainItem);
        ExcelUtil<TrainItem> util = new ExcelUtil<TrainItem>(TrainItem.class);
        return util.exportExcel(list, "item");
    }

    /**
     * 获取培训详细信息
     */
    @PreAuthorize("@ss.hasPermi('train:item:query')")
    @GetMapping(value = "/{trainId}")
    public AjaxResult getInfo(@PathVariable("trainId") Long trainId)
    {
        return AjaxResult.success(trainItemService.selectTrainItemById(trainId));
    }

    //生成成绩单
    @GetMapping(value = "/trainAndUser/{trainId}")
    public TableDataInfo getTrainAndUser(@PathVariable("trainId") Long trainId)
    {
        TrainItem trainItem = trainItemService.selectTrainAndUserListById(trainId);
        List<TrainUserScore> trainUserScores = new ArrayList<>();
        for(int i = 0;i<trainItem.getTrainUser().size();i++){
            TrainUserScore trainUserScore = new TrainUserScore();
            trainUserScore.setTrainId(trainId);
            trainUserScore.setClassId(trainItem.getClassId());
            trainUserScore.setUserId(trainItem.getSysUser().get(i).getUserId());
            trainUserScore.setNickName(trainItem.getSysUser().get(i).getNickName());
            trainUserScore.setEmail(trainItem.getSysUser().get(i).getEmail());
            trainUserScore.setPhonenumber(trainItem.getSysUser().get(i).getPhonenumber());
            trainUserScore.setUserScore(trainItem.getTrainUser().get(i).getUserScore());
            trainUserScores.add(trainUserScore);
        }
        return getDataTable(trainUserScores);
    }

    /**
     * 新增培训
     */
    @PreAuthorize("@ss.hasPermi('train:item:add')")
    @Log(title = "培训", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrainItem trainItem)
    {
        return toAjax(trainItemService.insertTrainItem(trainItem));
    }

    /**
     * 修改培训
     */
    @PreAuthorize("@ss.hasPermi('train:item:edit')")
    @Log(title = "培训", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrainItem trainItem)
    {
        return toAjax(trainItemService.updateTrainItem(trainItem));
    }

    /**
     * 删除培训
     */
    @PreAuthorize("@ss.hasPermi('train:item:remove')")
    @Log(title = "培训", businessType = BusinessType.DELETE)
	@DeleteMapping("/{trainIds}")
    public AjaxResult remove(@PathVariable Long[] trainIds)
    {
        return toAjax(trainItemService.deleteTrainItemByIds(trainIds));
    }
}
