package com.ruoyi.web.controller.equipment;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.equipment.domain.AggregateResult;
import com.ruoyi.equipment.domain.EquipmentCost;
import com.ruoyi.equipment.domain.ZjutEquipment;
import com.ruoyi.equipment.mapper.EquipmentSubscribeMapper;
import com.ruoyi.equipment.service.IEquipmentCostService;
import com.ruoyi.equipment.service.IZjutEquipmentService;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.SysLogs;
import com.ruoyi.system.service.ISysLogsService;
import com.ruoyi.system.service.ISysUserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.equipment.domain.EquipmentSubscribe;
import com.ruoyi.equipment.service.IEquipmentSubscribeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备预约Controller
 *
 */
@RestController
@RequestMapping("/equipment/subscribe")
public class EquipmentSubscribeController extends BaseController{
    @Autowired
    private IEquipmentCostService equipmentCostService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IZjutEquipmentService zjutEquipmentService;
    @Autowired
    private IEquipmentSubscribeService equipmentSubscribeService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ISysLogsService sysLogsService;

    /**
     * 查询设备预约列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:subscribe:list')")
    @GetMapping("/list")
    public TableDataInfo list(EquipmentSubscribe equipmentSubscribe){
        startPage();
        List<EquipmentSubscribe> list=equipmentSubscribeService.selectEquipmentSubscribeList(equipmentSubscribe);
        return getDataTable(list);
    }
    /**
     * 查询用户预约列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:subscribe:list')")
    @GetMapping("/mylist")
    public TableDataInfo mylist(EquipmentSubscribe equipmentSubscribe){
        startPage();
        List<EquipmentSubscribe> list=equipmentSubscribeService.selectmyEquipmentSubscribeList(equipmentSubscribe);
        return getDataTable(list);
    }
    @GetMapping("/TBDlist")
    public TableDataInfo TBDlist(EquipmentSubscribe equipmentSubscribe){
        startPage();
        List<EquipmentSubscribe> list=equipmentSubscribeService.selectTBDSubscribeList(equipmentSubscribe);
        return getDataTable(list);
    }
    /**
     * 查询审核列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:subscribe:list')")
    @GetMapping("/audit")
    public TableDataInfo audit(EquipmentSubscribe equipmentSubscribe){
        LoginUser loginUser=tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user=loginUser.getUser();
        List<EquipmentSubscribe> list=equipmentSubscribeService.selectAuditSubscribeList(equipmentSubscribe);
        ArrayList<EquipmentSubscribe> auditlist=new ArrayList<>();
        for(EquipmentSubscribe i: list){
            Long id=i.getEquipmentId();
            ZjutEquipment ze=zjutEquipmentService.selectZjutEquipmentById(id);
            if(ifManager(ze.getManagerIds(), user.getUserId())){
                auditlist.add(i);
            }
        }
        return getDataTable(auditlist);
    }
    /**
     * 导出设备预约列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:subscribe:export')")
    @Log(title="设备预约", businessType=BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EquipmentSubscribe equipmentSubscribe){
        List<EquipmentSubscribe> list=equipmentSubscribeService.selectEquipmentSubscribeList(equipmentSubscribe);
        ExcelUtil<EquipmentSubscribe> util=new ExcelUtil<EquipmentSubscribe>(EquipmentSubscribe.class);
        return util.exportExcel(list,"subscribe");
    }
    /**
     * 获取设备预约详细信息
     */
    @PreAuthorize("@ss.hasPermi('equipment:subscribe:query')")
    @GetMapping(value="/{equipmentSubscribeId}")
    public AjaxResult getInfo(@PathVariable("equipmentSubscribeId") Long equipmentSubscribeId){
        return AjaxResult.success(equipmentSubscribeService.selectEquipmentSubscribeById(equipmentSubscribeId));
    }

    /**
     * 新增设备预约
     */
    @PreAuthorize("@ss.hasPermi('equipment:subscribe:add')")
    @Log(title="设备预约", businessType=BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody EquipmentSubscribe equipmentSubscribe) throws ParseException {
       if(equipmentSubscribeService.checkConflictSubscribe(equipmentSubscribe) > 0)
           return AjaxResult.error("设备预约失败！该时间段已被预约！");
        //计算费用
        HashMap<String,Object> map=calculateCost(equipmentSubscribe);
        double equipmentCost=(double)map.get("equipmentCost");
        double artificialCost=(double)map.get("artificialCost");
        double materialCost=(double)map.get("materialCost");
        double totalCost=(double)map.get("totalCost");
        SysUser user=(SysUser)map.get("user");
        //设置设备费
        equipmentSubscribe.setEquipmentCost(new BigDecimal(equipmentCost));
        //设置人工费
        equipmentSubscribe.setArtificialCost(new BigDecimal(artificialCost));
        //设置耗材费
        equipmentSubscribe.setMaterialCost(new BigDecimal(materialCost));
        //设置总费用
        equipmentSubscribe.setTotalCost(new BigDecimal(totalCost));
        //设置真实总费用,此时真实总费用为0.
        equipmentSubscribe.setActualTotalCost(new BigDecimal(0));
        //查询用户的nickname
        String subscribeName=user.getNickName();
        equipmentSubscribe.setSubscribeName(subscribeName);
        //查询用户PI的Id
        Long piId=user.getPiId();
        equipmentSubscribe.setPiId(piId);
        //查询部门名称
        String equipmentName=zjutEquipmentService.selectZjutEquipmentById(equipmentSubscribe.getEquipmentId()).getEquipmentName();
        equipmentSubscribe.setEquipmentName(equipmentName);
        equipmentSubscribeService.insertEquipmentSubscribe(equipmentSubscribe);

        //在日志管理里添加一条记录
        SysLogs sysLogs=new SysLogs();
        sysLogs.setUserId(user.getUserId());
        sysLogs.setType(0);
        sysLogs.setSubscribeStartTime(equipmentSubscribe.getSubscribeStartTime());
        sysLogs.setSubscribeEndTime(equipmentSubscribe.getSubscribeEndTime());
        sysLogs.setSubscribeDate(equipmentSubscribe.getSubscribeDate());
        sysLogs.setRemark("subscribe the equipment");
        sysLogs.setSubscribeId(equipmentSubscribe.getEquipmentSubscribeId());
        sysLogsService.insertLogs(sysLogs);

        return AjaxResult.success("设备预约成功");
    }
    /**
     * 获取虚假预约的费用
     */
    @GetMapping("/getFakeCost")
    public AjaxResult getCost(EquipmentSubscribe equipmentSubscribe){
        HashMap<String,Object> res=calculateCost(equipmentSubscribe);
        res.remove("user");
        return AjaxResult.success(res);
    }
    /**
     * 计算费用(供上面两个方法使用)
     */
    private HashMap<String,Object> calculateCost(EquipmentSubscribe equipmentSubscribe){
        //计算预约时长(h)
        String strStartTime=equipmentSubscribe.getSubscribeStartTime();
        String strEndTime=equipmentSubscribe.getSubscribeEndTime();
        long startTime=0;
        long endTime=0;
        try{
            SimpleDateFormat parser=new SimpleDateFormat("HH:mm");
            endTime=parser.parse(strEndTime).getTime();
            startTime=parser.parse(strStartTime).getTime();
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        //设备id
        Long equipmentId=equipmentSubscribe.getEquipmentId();
        //决定收费类型
        SysUser user=sysUserService.selectUserById(equipmentSubscribe.getUserId());
        String comeFrom=user.getComeFrom();
        String costType=comeFrom;
        //查询以上两项对应的收费情况
        EquipmentCost equipmentCostCondition=new EquipmentCost();
        equipmentCostCondition.setEquipmentId(equipmentId);
        equipmentCostCondition.setCostType(costType);
        EquipmentCost equipmentCostRes=equipmentCostService.selectEquipmentCostList(equipmentCostCondition).get(0);
        //设备费单价
        BigDecimal price=equipmentCostRes.getCostNumber();
        //最小时间段
        double minInterval=equipmentCostRes.getMinInterval();
        //计算时间
        BigDecimal time=new BigDecimal(Math.ceil((endTime-startTime)/(1000*minInterval*3600)));
        //计算设备费
        BigDecimal equipmentCost=price.multiply(time);
        //计算人工费
        boolean hasHelp=equipmentSubscribe.isHasHelp();
        BigDecimal artificialCost=null;
        if(hasHelp){
            //人工费单价
            BigDecimal artificialPrice=equipmentCostRes.getArtificialCostNumber();
            artificialCost=artificialPrice.multiply(time);
        }
        else{
            artificialCost=new BigDecimal(0);
        }
        //耗材费目前不知道如何算,统一先记为0.
        BigDecimal materialCost=new BigDecimal(0);
        //计算总费用
        BigDecimal totalCost=equipmentCost.add(artificialCost).add(materialCost);
        //结果Map
        HashMap<String,Object> res=new HashMap<>();
        res.put("equipmentCost",equipmentCost.doubleValue());
        res.put("artificialCost",artificialCost.doubleValue());
        res.put("materialCost",materialCost.doubleValue());
        res.put("totalCost",totalCost.doubleValue());
        //给add方法用
        res.put("user",user);
        return res;
    }
    /**
     * 设置真实总费用
     */
    @PutMapping("/setActualTotalCost")
    public AjaxResult setActualTotalCost(Long id,Double actualTotalCost){
        EquipmentSubscribe equipmentSubscribe=new EquipmentSubscribe();
        equipmentSubscribe.setEquipmentSubscribeId(id);
        equipmentSubscribe.setActualTotalCost(new BigDecimal(actualTotalCost));
        equipmentSubscribeService.updateEquipmentSubscribe(equipmentSubscribe);
        return AjaxResult.success();
    }
    /**
     * 查询PI以及PI的组员的预约记录
     */
    @GetMapping("/getSubscribesOfPiAndMembers")
    public AjaxResult getSubscribesOfPiAndMembers(int piId,String beginDate,String endDate){
        if(beginDate==null){
            beginDate="2001-08-26";
        }
        if(endDate==null){
            endDate="3001-08-26";
        }
        ArrayList<AggregateResult> aggregateResults=equipmentSubscribeService.selectSubscribesStatisticOfPiAndMembers((long)piId,beginDate,endDate);
        //以下这部分为【总计】
        //总次数
        int allCount=0;
        //总设备费
        double allEquipmentMoney=0;
        //总人工费
        double allArtificialMoney=0;
        //总耗材费
        double allMaterialMoney=0;
        //总费用
        double allTotalMoney=0;
        for(AggregateResult aggregateResult: aggregateResults){
            allCount+=aggregateResult.getCount();
            allEquipmentMoney+=aggregateResult.getEquipmentMoney();
            allArtificialMoney+=aggregateResult.getArtificialMoney();
            allMaterialMoney+=aggregateResult.getMaterialMoney();
            allTotalMoney+=aggregateResult.getTotalMoney();
        }
        //将总计加入到统计结果中
        AggregateResult allAggregateResult=new AggregateResult();
        allAggregateResult.setCount(allCount);
        allAggregateResult.setEquipmentMoney(allEquipmentMoney);
        allAggregateResult.setArtificialMoney(allArtificialMoney);
        allAggregateResult.setMaterialMoney(allMaterialMoney);
        allAggregateResult.setTotalMoney(allTotalMoney);
        allAggregateResult.setName("总计");
        aggregateResults.add(allAggregateResult);
        return AjaxResult.success(aggregateResults);
    }
    /**
     * 以PI为单位进行预约统计
     */
    @GetMapping("/getSubscribesByPiUnit")
    public AjaxResult getSubscribesByPiUnit(String beginDate,String endDate){
        if(beginDate==null){
            beginDate="2001-08-26";
        }
        if(endDate==null){
            endDate="3001-08-26";
        }
        //存放统计结果的列表
        ArrayList<AggregateResult> aggregateResults=new ArrayList<>();
        /*因为可能会出现【只有PI预约过而组员未预约过或者只有组员预约过而PI未预约过】这两种情况,
        所有需要遍历所有的PI才能确保统计完全正确.*/
        //获取到所有PI
        ArrayList<SysUser> sysUsers=sysUserService.selectAllPis();
        //总次数
        int allCount=0;
        //总设备费
        double allEquipmentMoney=0;
        //总人工费
        double allArtificialMoney=0;
        //总耗材费
        double allMaterialMoney=0;
        //预约时计算的总钱数
        double allTotalMoney=0;
        //真实的总钱数
        double allActualTotalMoney=0;
        //统计所有pi,pi及其组员均没有预约记录的话则不计入统计结果。
        for(SysUser pi: sysUsers){
            Long piId=pi.getUserId();
            AggregateResult aggregateResult=equipmentSubscribeService.selectSubscribesStatisticByPiUnit(piId,beginDate,endDate);
            //如果此PI及其组员有预约记录,则进行统计.
            if(aggregateResult.getCount()!=0){
                allCount+=aggregateResult.getCount();
                allEquipmentMoney+=aggregateResult.getEquipmentMoney();
                allArtificialMoney+=aggregateResult.getArtificialMoney();
                allMaterialMoney+=aggregateResult.getMaterialMoney();
                allTotalMoney+=aggregateResult.getTotalMoney();
                allActualTotalMoney+=aggregateResult.getActualTotalMoney();
                aggregateResult.setName(pi.getNickName());
                aggregateResults.add(aggregateResult);
            }
        }
        AggregateResult allAggregateResult=new AggregateResult();
        allAggregateResult.setCount(allCount);
        allAggregateResult.setEquipmentMoney(allEquipmentMoney);
        allAggregateResult.setArtificialMoney(allArtificialMoney);
        allAggregateResult.setMaterialMoney(allMaterialMoney);
        allAggregateResult.setTotalMoney(allTotalMoney);
        allAggregateResult.setActualTotalMoney(allActualTotalMoney);
        allAggregateResult.setName("总计");
        aggregateResults.add(allAggregateResult);
        return AjaxResult.success(aggregateResults);
    }
    /**
     * 查询指定用户的预约记录
     */
    @GetMapping("/getSubscribesByUserId")
    public AjaxResult getSubscribesByUserId(Long userId,String beginDate,String endDate,String equipmentName){
        if(beginDate==null){
            beginDate="2001-08-26";
        }
        if(endDate==null){
            endDate="3001-08-26";
        }
        ArrayList<EquipmentSubscribe> equipmentSubscribes=equipmentSubscribeService.selectSubscribesByUserId(userId,beginDate,endDate,equipmentName);
        return AjaxResult.success(equipmentSubscribes);
    }
    /**
     * 获取指定用户的预约记录的统计结果,此结果是按照某一时间单位来统计的,因此结果是一个列表.
     */
    @GetMapping("/getSubscribesStatisticByUserId")
    public AjaxResult getSubscribesStatisticByUserId(Long userId,String beginDate,String endDate,String unit) throws Exception{
        return doStatistic(userId,beginDate,endDate,unit,false);
    }
    /**
     * 获取PI及其组员的预约记录的统计结果,此结果是按照某一时间单位来统计的,因此结果是一个列表.
     */
    @GetMapping("/getSubscribesStatisticByPiId")
    public AjaxResult getSubscribesStatisticByPiId(Long piId,String beginDate,String endDate,String unit) throws Exception{
        return doStatistic(piId,beginDate,endDate,unit,true);
    }
    /**
     * 真正处理以上两个请求的业务代码
     */
    private AjaxResult doStatistic(Long userId,String beginDate,String endDate,String unit,boolean isPi) throws Exception{
        //设置开始时间
        Date startDate=new Date(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate).getTime());
        Calendar startCalendar=Calendar.getInstance();
        startCalendar.setTime(startDate);
        //设置结束时间
        Date stopDate=new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime());
        Calendar stopCalendar=Calendar.getInstance();
        stopCalendar.setTime(stopDate);
        //此列表存放最终结果
        ArrayList<Double> totalMoneys=new ArrayList<>();
        if("month".equalsIgnoreCase(unit)){
            //按月统计
            //将开始日期和结束日期的【日】均置为1号
            startCalendar.set(Calendar.DAY_OF_MONTH,1);
            stopCalendar.set(Calendar.DAY_OF_MONTH,1);
            while(startCalendar.before(stopCalendar)||startCalendar.equals(stopCalendar)){
                //开始日期
                String currentBeginDate=new SimpleDateFormat("yyyy-MM-dd").format(startCalendar.getTime());
                //将日期设为本月的最后一天
                startCalendar.set(Calendar.DAY_OF_MONTH,startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                //结束日期
                String currentEndDate=new SimpleDateFormat("yyyy-MM-dd").format(startCalendar.getTime());
                //查询到统计结果
                double totalMoney=0;
                //如果是统计pi及其组员,就调用这个查询.
                if(isPi){
                    totalMoney=equipmentSubscribeService.selectSubscribesStatisticByPiUnit(userId,currentBeginDate,currentEndDate).getTotalMoney();
                }
                //否则调用这个查询
                else{
                    totalMoney=equipmentSubscribeService.selectSubscribesStatisticByUserId(userId,currentBeginDate,currentEndDate);
                }
                //将结果添加
                totalMoneys.add(totalMoney);
                //将startCalendar的【日】置回1号
                startCalendar.set(Calendar.DAY_OF_MONTH,1);
                //startCalendar加一月
                startCalendar.add(Calendar.MONTH,1);
            }
        }
        else if("year".equalsIgnoreCase(unit)){
            //按年统计
            //将开始日期和结束日期均置为1月1日
            startCalendar.set(Calendar.MONTH,0);
            startCalendar.set(Calendar.DAY_OF_MONTH,1);
            stopCalendar.set(Calendar.MONTH,0);
            stopCalendar.set(Calendar.DAY_OF_MONTH,1);
            while(startCalendar.before(stopCalendar)||startCalendar.equals(stopCalendar)){
                //开始日期
                String currentBeginDate=new SimpleDateFormat("yyyy-MM-dd").format(startCalendar.getTime());
                //将日期设为12月31日
                startCalendar.set(Calendar.MONTH,11);
                startCalendar.set(Calendar.DAY_OF_MONTH,31);
                //结束日期
                String currentEndDate=new SimpleDateFormat("yyyy-MM-dd").format(startCalendar.getTime());
                //查询到统计结果
                double totalMoney=0;
                //如果是统计pi及其组员,就调用这个查询.
                if(isPi){
                    totalMoney=equipmentSubscribeService.selectSubscribesStatisticByPiUnit(userId,currentBeginDate,currentEndDate).getTotalMoney();
                }
                //否则调用这个查询
                else{
                    totalMoney=equipmentSubscribeService.selectSubscribesStatisticByUserId(userId,currentBeginDate,currentEndDate);
                }
                //将结果添加
                totalMoneys.add(totalMoney);
                //将startCalendar的置回1月1日
                startCalendar.set(Calendar.MONTH,0);
                startCalendar.set(Calendar.DAY_OF_MONTH,1);
                //startCalendar加一年
                startCalendar.add(Calendar.YEAR,1);
            }
        }
        else{
            //按天统计
            while(startCalendar.before(stopCalendar)||startCalendar.equals(stopCalendar)){
                String currentDate=new SimpleDateFormat("yyyy-MM-dd").format(startCalendar.getTime());
                //查询到统计结果
                double totalMoney=0;
                //如果是统计pi及其组员,就调用这个查询.
                if(isPi){
                    totalMoney=equipmentSubscribeService.selectSubscribesStatisticByPiUnit(userId,currentDate,currentDate).getTotalMoney();
                }
                //否则调用这个查询
                else{
                    totalMoney=equipmentSubscribeService.selectSubscribesStatisticByUserId(userId,currentDate,currentDate);
                }
                //将结果添加
                totalMoneys.add(totalMoney);
                //startCalendar加一天
                startCalendar.add(Calendar.DAY_OF_MONTH,1);
            }
        }
        return AjaxResult.success(totalMoneys);
    }
    /**
     * 修改设备预约
     */
    @PreAuthorize("@ss.hasPermi('equipment:subscribe:edit')")
    @Log(title="设备预约", businessType=BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentSubscribe equipmentSubscribe) throws ParseException {
        //System.out.println("传入参数："+equipmentSubscribe);
        EquipmentSubscribe equipmentSubscribe1=equipmentSubscribeService.selectEquipmentSubscribeById(equipmentSubscribe.getEquipmentSubscribeId());
        equipmentSubscribe1.setSubscribeEndTime(equipmentSubscribe.getSubscribeEndTime());
        equipmentSubscribe1.setSubscribePhone(equipmentSubscribe.getSubscribePhone());
        equipmentSubscribe1.setSubscribeStatus(equipmentSubscribe.getSubscribeStatus());
        //System.out.println("修改后："+equipmentSubscribe1);
        //根据类型和设备id查询到对应的日志
        SysLogs sysLogs=sysLogsService.selectLogsBySubscribeId(0,equipmentSubscribe.getEquipmentSubscribeId());
        //只有修改了结束时间才影响到SysLogs表
        //非空判断用于避免日志功能引入前的预约修改报错
        if(sysLogs != null) {
            sysLogs.setSubscribeEndTime(equipmentSubscribe.getSubscribeEndTime());
            sysLogsService.updateLogs(sysLogs);
        }
        return toAjax(equipmentSubscribeService.updateEquipmentSubscribe(equipmentSubscribe1));
    }
    /**
     * 删除设备预约
     */
    //@PreAuthorize("@ss.hasPermi('equipment:subscribe:remove')")
    @Log(title="设备预约", businessType=BusinessType.DELETE)
    @DeleteMapping("/{equipmentSubscribeIds}")
    public AjaxResult remove(@PathVariable Long[] equipmentSubscribeIds){
        if(!SecurityUtils.haveAdmin())return AjaxResult.error("没有操作权限，请联系管理员！");
        //删除这些设备在日志管理系统对应的记录
        for (Long equipmentSubscribeId : equipmentSubscribeIds) {
            SysLogs sysLogs=sysLogsService.selectLogsBySubscribeId(0,equipmentSubscribeId);
            sysLogsService.deleteLogs(sysLogs.getLogsId());
        }
        return toAjax(equipmentSubscribeService.deleteEquipmentSubscribeByIds(equipmentSubscribeIds));
    }
    /**
     * 查询PI及其组员的预约记录在通过设备id分组后的设备费
     */
    @GetMapping("/getSubscribesOfPiAndMembersByEquipmentUnit")
    public AjaxResult getSubscribesOfPiAndMembersByEquipmentUnit(int piId,String beginDate,String endDate){
        if(beginDate==null){
            beginDate="2001-08-26";
        }
        if(endDate==null){
            endDate="3001-08-26";
        }
        ArrayList<AggregateResult> aggregateResults=equipmentSubscribeService.selectSubscribesCostOfPiAndMembersByEquipmentUnit((long)piId,beginDate,endDate);
        return AjaxResult.success(aggregateResults);
    }
    /**
     * 取消预约
     */
    @DeleteMapping("/cancelSubscribe")
    public AjaxResult cancelSubscribe(Long equipmentSubscribeId){
        //当前时间
        java.util.Date currentTime=new java.util.Date();
        EquipmentSubscribe equipmentSubscribe=equipmentSubscribeService.selectEquipmentSubscribeById(equipmentSubscribeId);
        //设备id
        Long equipmentId=equipmentSubscribe.getEquipmentId();
        //决定收费类型
        SysUser user=sysUserService.selectUserById(equipmentSubscribe.getUserId());
        String comeFrom=user.getComeFrom();
        String costType=comeFrom;
        //查出以上两项对应的收费情况
        EquipmentCost equipmentCostCondition=new EquipmentCost();
        equipmentCostCondition.setEquipmentId(equipmentId);
        equipmentCostCondition.setCostType(costType);
        EquipmentCost equipmentCostRes=equipmentCostService.selectEquipmentCostList(equipmentCostCondition).get(0);
        //查询缓冲时间段
        //旧版本--使用收费对象的缓冲时间段
        //Double lastTimeInterval=equipmentCostRes.getLastTimeInterval();
        //todo 根据需求临时改造版本
        Double lastTimeInterval=zjutEquipmentService.
                selectZjutEquipmentById(equipmentId).getAllowCancel();
        //预约开始时间对应的时间戳
        long beginTimestamp=0;
        try{
            beginTimestamp=equipmentSubscribe.getSubscribeDate().getTime()+new SimpleDateFormat("HH:mm:ss").parse(equipmentSubscribe.getSubscribeStartTime()).getTime()+8*3600*1000;
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        //缓冲时间段对应的毫秒数
        long interval=(long)(lastTimeInterval*3600*1000);
        //最后可以取消的时间戳
        long finallyCancelTimestamp=beginTimestamp-interval;
        //最后可以取消的时间
        Date finallyCancelTime=new Date(finallyCancelTimestamp);
        //如果当前时间在最后可以取消的时间之前则取消,否则不可取消.
        if(currentTime.before(finallyCancelTime)){
            //删除日志管理的记录
            SysLogs sysLogs=sysLogsService.selectLogsBySubscribeId(0,equipmentSubscribeId);
            sysLogsService.deleteLogs(sysLogs.getLogsId());

            return toAjax(equipmentSubscribeService.deleteEquipmentSubscribeById(equipmentSubscribeId));
        }
        else{
            return AjaxResult.error("当前时间不可取消预约!");
        }
    }
    /**
     * 查询用户可预约时间段
     */
    @GetMapping("/getSubscribableTimeInterval")
    public AjaxResult getSubscribableTimeInterval(Long userId,Long equipmentId){
        //查询此用户的来自地
        String comeFrom=sysUserService.selectUserById(userId).getComeFrom();
        //查询设备详细信息
        ZjutEquipment equipment=zjutEquipmentService.selectZjutEquipmentById(equipmentId);
        //存储结果的map
        HashMap<String,Time> res=new HashMap<>();
        Time beginTime=null;
        Time endTime=null;
        //院内
        if(comeFrom.equals(ZjutEquipment.INNER)){
            beginTime=equipment.getInnerBeginTime();
            endTime=equipment.getInnerEndTime();
        }
        //院外
        else{
            beginTime=equipment.getOuterBeginTime();
            endTime=equipment.getOuterEndTime();
        }
        res.put("beginTime",beginTime);
        res.put("endTime",endTime);
        return AjaxResult.success(res);
    }
    /**
     * 查询到目前为止预约还未结束的预约信息
     */
    @GetMapping("/getNotEndSubscribe")
    public AjaxResult getNotEndSubscribe(){
        //获取当前时间
        String currentTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        //分割日期和时间
        String[] s=currentTime.split(" ");
        //日期
        String date=s[0];
        //时间
        String time=s[1];
        //查询
        ArrayList<EquipmentSubscribe> equipmentSubscribes=equipmentSubscribeService.selectInfoThatEndTimeAfterIndicatedDateTime(date,time);
        return AjaxResult.success(equipmentSubscribes);
    }
    /**
     * 查询用户预约的各种费用统计
     */
    @GetMapping("/getEveryCostAggregationByUserId")
    public AjaxResult getEveryCostAggregationByUserId(Long userId){
        //key为费用名称,value为费用值.
        //totalArtificialCost为总的人工费,totalMaterialCost为总的耗材费,
        //totalEquipmentCost为总的设备费,totalTotalCost为总的总费用,
        //totalActualTotalCost为总的真实总费用.
        Map<String,Double> res=equipmentSubscribeService.selectEveryCostAggregationByUserId(userId);
        return AjaxResult.success(res);
    }

    /**
     * 判断是否为设备管理员
     * @return boolean
     */
    private boolean ifManager(List<Long> managerIds, Long userId){
        for(Long managerId : managerIds){
            if(managerId.equals(userId))return true;
        }
        return false;
    }
}
