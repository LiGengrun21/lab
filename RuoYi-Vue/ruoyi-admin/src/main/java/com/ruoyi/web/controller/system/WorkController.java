package com.ruoyi.web.controller.system;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Work;
import com.ruoyi.system.service.IWorkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 值班Controller
 *
 */
@RestController
@RequestMapping("/system/work")
public class WorkController extends BaseController
{
    @Autowired
    private IWorkService workService;

    /**
     * 获取值班人员列表（弃用）
     * @return TableDataInfo
     */
    //@PreAuthorize("@ss.hasPermi('system:work:getUserList')")
    @GetMapping("/getUserList")
    public TableDataInfo getUserList()
    {
        startPage();
        List<Work> list = workService.selectUserList();
        return getDataTable(list);
    }

//    /**
//     * 查询值班列表(某个用户在某天的所有值班)
//     */
//    //@PreAuthorize("@ss.hasPermi('system:work:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(Work work)
//    {
//        startPage();
//        List<Work> list = workService.selectWorkList(work);
//        return getDataTable(list);
//    }

    /**
     * 根据月份查询值班列表（新）
     * @param
     * @return AjaxResult
     */
    //@PreAuthorize("@ss.hasPermi('system:work:getWorkListByMonth')")
    @GetMapping("/getWorkListByMonth")
    public TableDataInfo getWorkListByMonth(int year,int month)
    {
        startPage();
        List<Work> workList=workService.selectWorkByMonth(year,month);
        return getDataTable(workList);
    }

//    /**
//     * 导出值班列表(某人在某天的值班)
//     */
//    //@PreAuthorize("@ss.hasPermi('system:work:export')")
//    @Log(title = "值班", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(Work work)
//    {
//        List<Work> list = workService.selectWorkList(work);
//        ExcelUtil<Work> util = new ExcelUtil<Work>(Work.class);
//        return util.exportExcel(list, "work");
//    }

    /**
     * 导出一个月的值班列表（新）
     *
     * @param month
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:work:export:month')")
    @Log(title = "值班", businessType = BusinessType.EXPORT)
    @GetMapping("/export/month")
    public AjaxResult exportMonth(int year, int month)
    {
        List<Work> list=workService.selectWorkByMonth(year, month);
        ExcelUtil<Work> util = new ExcelUtil<Work>(Work.class);
        return util.exportExcel(list, month+"月的值班表");
    }

    /**
     * 导出一个用户的值班列表（新）
     *
     * @param userId
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:work:export:query')")
    @Log(title = "值班", businessType = BusinessType.EXPORT)
    @GetMapping("/export/user")
    public AjaxResult exportUser(Long userId)
    {
        List<Work> list=workService.selectWorkByUser(userId);
        ExcelUtil<Work> util = new ExcelUtil<Work>(Work.class);
        return util.exportExcel(list, list.get(0).getUser().getUserName()+"的值班表");
    }

    /**
     * 获取值班详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:work:query')")
    @GetMapping(value = "/{workId}")
    public AjaxResult getInfo(@PathVariable("workId") Long workId)
    {
        return AjaxResult.success(workService.selectWorkById(workId));
    }

//    /**
//     * 按照日期查询值班用户
//     */
//    @GetMapping("/getWorkUserByDate")
//    public AjaxResult getWorkUserByDate(Work work){
//        SysUser user=workService.selectWorkList(work).get(0).getUser();
//        return AjaxResult.success(user);
//    }

    /**
     * 根据月份批量添加值班（新）
     *
     * @param workList
     * @return AjaxResult
     */
    //@PreAuthorize("@ss.hasPermi('system:work:month:addMonth')")
    @Log(title = "值班", businessType = BusinessType.INSERT)
    @PostMapping("/month")
    public AjaxResult addMonth(@RequestBody List<Work> workList)
    {
        return toAjax(workService.insertMonthWork(workList));
    }

    /**
     * 根据日期批量新增值班
     */
    //@PreAuthorize("@ss.hasPermi('system:work:add')")
    @Log(title = "值班", businessType = BusinessType.INSERT)
    @PostMapping("/date")
    public AjaxResult add(@RequestBody List<Work> workList)
    {
        return toAjax(workService.insertDateWork(workList));
    }

//    /**
//     * 修改值班
//     */
//    @PreAuthorize("@ss.hasPermi('system:work:edit')")
//    @Log(title = "值班", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody Work work)
//    {
//        return toAjax(workService.updateWork(work));
//    }

    /**
     * 批量删除值班
     */
    //@PreAuthorize("@ss.hasPermi('system:work:remove')")
    @Log(title = "值班", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workIds}")
    public AjaxResult remove(@PathVariable Long[] workIds)
    {
        return toAjax(workService.deleteWorkByIds(workIds));
    }

    @Log(title="用户管理", businessType=BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception{
        ExcelUtil<Work> util=new ExcelUtil<Work>(Work.class);
        List<Work> workList=util.importExcel(file.getInputStream());
        String message=workService.importWork(workList,updateSupport);
        return AjaxResult.success(message);
    }
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate(){
        ExcelUtil<Work> util=new ExcelUtil<Work>(Work.class);
        return util.importTemplateExcel("值班数据");
    }

}

