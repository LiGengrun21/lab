package com.ruoyi.quartz.task;

import com.ruoyi.equipment.domain.EquipmentSubscribe;
import com.ruoyi.equipment.domain.EquipmentUse;
import com.ruoyi.equipment.service.IEquipmentSubscribeService;
import com.ruoyi.equipment.service.IEquipmentUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 定时任务调度测试
 *
 * 
 */
@Component("equipmentTask")
public class EquipmentTask
{
    @Autowired
    private IEquipmentSubscribeService equipmentSubscribeService;

    @Autowired
    private IEquipmentUseService equipmentUseService;

    public void subscribeStatus() throws ParseException {
        List<EquipmentSubscribe> todayList = equipmentSubscribeService.selectTodaySubscribe();
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String s = df.format(date);
        Date dateNow = df.parse(s);
        for(EquipmentSubscribe i:todayList){
            System.out.println(i);
            if(dateNow.getTime()>df.parse(i.getSubscribeEndTime()).getTime()){
                i.setSubscribeStatus("1");
                equipmentSubscribeService.updateEquipmentSubscribe(i);
            }
        }
    }

    public void useCost() throws ParseException {
        List<EquipmentSubscribe> todayList = equipmentSubscribeService.selectTodaySubscribe();
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        for(EquipmentSubscribe i:todayList){
            BigDecimal b = i.getTotalCost();
            System.out.println(b);
            double Scost = b.doubleValue();
            double Ucost = 0;
            double UTotalcost = 0;
            long timeSStart = df.parse(i.getSubscribeStartTime()).getTime();
            long timeSEnd = df.parse(i.getSubscribeEndTime()).getTime();
            long Stime = timeSEnd - timeSStart;
            List<EquipmentUse> equipmentUses = equipmentUseService.selectEquipmentUseListBySubscribeId(i.getEquipmentSubscribeId());
            for(EquipmentUse j:equipmentUses) {
                if (j.getUseEndTime() != null) {
                    long timeUStart = df.parse(j.getUseStartTime()).getTime();
                    long timeUEnd = df.parse(j.getUseEndTime()).getTime();
                    long Utime = timeUEnd - timeUStart;
                    double time = (double) Utime / (double) Stime;
                    Ucost = time * Scost;
                    UTotalcost += time * Scost;
                    System.out.println("预约id" + " " + i.getEquipmentSubscribeId() + "花费" + Ucost);
                    j.setUseCost(new BigDecimal(Ucost));
                    equipmentUseService.updateEquipmentUse(j);
                }
            }
            if(UTotalcost!=0)
                System.out.println("预约id" + " " + i.getEquipmentSubscribeId() + "总花费:" + UTotalcost);
                i.setActualTotalCost(new BigDecimal(UTotalcost));
                equipmentSubscribeService.updateEquipmentSubscribe(i);
        }
    }
}
