package com.ruoyi.quartz.task;
import afu.org.checkerframework.checker.igj.qual.I;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.equipment.domain.*;
import com.ruoyi.equipment.service.*;
import com.ruoyi.quartz.connector.DeviceConnector;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Component("deviceTask")
public class DeviceTask {
    @Autowired
    DeviceConnector deviceConnector;

    @Autowired
    private IEquipmentSubscribeService equipmentSubscribeService;

    @Autowired
    private IZjutEquipmentService zjutEquipmentService;

    @Autowired
    private IEquipmentUseService equipmentUseService;

    @Autowired
    private IDeviceLogService deviceLogService;

    @Autowired
    private IEquipmentWarningService equipmentWarningService;

    //设备自动关闭 建议不开启
    public void deviceTaskTest() {
        System.out.println("成功调用定时任务");
        EquipmentUse equipment = new EquipmentUse();
        EquipmentUse equipmentUse = new EquipmentUse();
        List<EquipmentSubscribe> list = equipmentSubscribeService.selectTodaySubscribe();
        Date time = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String s = df.format(time);
        List<EquipmentUse> useList = equipmentUseService.selectEquipmentUseList(equipment);
        for (EquipmentSubscribe i : list) {
            for(EquipmentUse j : useList){
                if(j.getEquipmentSubscribeId().equals(i.getEquipmentSubscribeId())) {
                    equipmentUse = j;
                    System.out.println(equipmentUse);
                }
            }
            try {
                Date timeNow = df.parse(s);
                Date timeEnd = df.parse(i.getSubscribeEndTime());
                Date timeEnd_10 = new Date(timeEnd.getTime() + 600000);
                Date timeEnd_20 = new Date(timeEnd.getTime() + 1200000);
                if(timeNow.getTime() > timeEnd_10.getTime() && timeNow.getTime() < timeEnd_20.getTime()){
                    System.out.println("比较成功");
                    ArrayList<Object> status = deviceConnector.getDeviceStatus(zjutEquipmentService.selectZjutEquipmentById(i.getEquipmentId()).getDeviceId());
                    String a = zjutEquipmentService.selectZjutEquipmentById(i.getEquipmentId()).getAutoOff();
                    if(status.get(0).toString().equals("{code=switch_1, value=true}") && a.equals("1")){
                        System.out.println("SUCCESS");
                        CommandWrapper cmdWrapper = new CommandWrapper();
                        cmdWrapper.commands = new ArrayList<>();
                        cmdWrapper.commands.add(new Command());
                        cmdWrapper.commands.get(0).code = "switch_1";
                        cmdWrapper.commands.get(0).value = false;
                        deviceConnector.commands(zjutEquipmentService.selectZjutEquipmentById(i.getEquipmentId()).getDeviceId(),cmdWrapper);
                        Date time2 = new Date();
                        String s2 = df.format(time2);
                        equipmentUse.setUseEndTime(s2);
                        equipmentUseService.updateEquipmentUse(equipmentUse);
                    }
                }
                else
                    System.out.println("不在时间段内或该设备不需要自动关闭");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


    //获取设备运行信息
    public void getDeviceStatus() throws ParseException {
        ArrayList<String> deviceIds = new ArrayList<>();
        List<ZjutEquipment> equipmentList = zjutEquipmentService.selectZjutEquipmentList(new ZjutEquipment());
        //获取插座LIST表、之后使用
        for(ZjutEquipment j:equipmentList){
            if((j.getDeviceId() != null) && !(j.getDeviceId().equals("")))
            deviceIds.add(j.getDeviceId());
        }
        System.out.println(deviceIds);
        //对插座LIST表里的每个插座进行电流查询与记录
        for(String k:deviceIds) {
            int flag = 0;
            int type = 0;
            ArrayList<Object> status = deviceConnector.getDeviceStatus(k);
            System.out.println(status.toString());
            DeviceLog deviceLog = new DeviceLog();
            deviceLog.setDeviceId(k);
            for (Object i : status) {
                String s = i.toString();
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == '=')
                        flag++;
                    if (flag == 2) {
                        String s2 = s.substring(j + 1, s.length() - 1);
                        flag = 0;
                        switch (type) {
                            case 0:
                                deviceLog.setDeviceStatus(s2);
                                break;
                            case 3:
                                deviceLog.setDeviceCurrent(s2);
                                break;
                            case 4:
                                deviceLog.setDevicePower(Double.toString(Double.parseDouble(s2)/10));
                                break;
                            case 5:
                                deviceLog.setDeviceVoltage(Double.toString(Double.parseDouble(s2)/10));
                                break;
                            default:
                                break;
                        }
                        type++;
                    }
                }
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(new Date());
            Date timenow = df.parse(time);
            deviceLog.setLogTime(timenow);
            deviceLogService.insertDeviceLog(deviceLog);
        }
    }

    //自动删除监控记录，防止数据库数据过多
    public void deleteDeviceStatus(Integer t){
        Date time = new Date();
        Date time_5min = new Date(time.getTime() - t);
        List<DeviceLog> deviceLog = deviceLogService.selectDeviceLogList(new DeviceLog());
        for(DeviceLog i:deviceLog){
            System.out.println(i.getLogTime());
            if(time_5min.getTime() > i.getLogTime().getTime()){
                deviceLogService.deleteDeviceLogById(i.getDeviceLogId());
            }
        }
    }

    public void warningDeviceStatus() throws ParseException {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> deviceIdlist = new ArrayList();
        List<ZjutEquipment> equipmentList = zjutEquipmentService.selectZjutEquipmentList(new ZjutEquipment());
        List<DeviceLog> deviceLog = deviceLogService.selectDeviceLogList(new DeviceLog());
        for(ZjutEquipment e:equipmentList){
            if((e.getDeviceId() != null) && !(e.getDeviceId().equals(""))){
                deviceIdlist.add(e.getDeviceId());
            }
        }
        for(String s:deviceIdlist){
            ArrayList<DeviceLog> equipmentDeviceLog = new ArrayList();
            for(DeviceLog d:deviceLog){
                if(d.getDeviceId().equals(s)){
                    equipmentDeviceLog.add(d);
                }
            }
            int i = equipmentDeviceLog.size();
                if(Double.parseDouble(equipmentDeviceLog.get(i-1).getDeviceCurrent()) == 0 && Double.parseDouble(equipmentDeviceLog.get(i-2).getDeviceCurrent()) != 0){
                    System.out.println("插座断电");
                    for(ZjutEquipment e:equipmentList){
                        if(e.getDeviceId().equals(s)){
                            EquipmentWarning equipmentWarning = new EquipmentWarning();
                            equipmentWarning.setEquipmentId(e.getEquipmentId());
                            equipmentWarning.setEquipmentName(e.getEquipmentName());
                            equipmentWarning.setWarningTime(df2.format(new Date()));
                            equipmentWarning.setWarningType("插座断电");
                            equipmentWarningService.insertEquipmentWarning(equipmentWarning);
                        }
                    }
                }
                if(Double.parseDouble(equipmentDeviceLog.get(i-1).getDeviceCurrent())-Double.parseDouble(equipmentDeviceLog.get(i-2).getDeviceCurrent()) >=50) {
                    System.out.println("插座:" + equipmentDeviceLog.get(i - 1).getDeviceId() + "开机");
                    System.out.println("插座:" + equipmentDeviceLog.get(i - 1).getDeviceId() + "开机");
                    System.out.println("插座:" + equipmentDeviceLog.get(i - 1).getDeviceId() + "开机");
                    List<EquipmentSubscribe> todayList = equipmentSubscribeService.selectTodaySubscribe();
                    Date time = new Date();
                    String time_s = df.format(time);
                    System.out.println(todayList);
                    if (todayList.size() != 0) {
                        int flag = 1;
                        for (EquipmentSubscribe l : todayList) {
                            Date timeStart = df.parse(l.getSubscribeStartTime());
                            Date timeEnd = df.parse(l.getSubscribeEndTime());
                            Date timeNow = df.parse(time_s);
                            if (timeNow.getTime() > timeStart.getTime() - 60000 && timeNow.getTime() < timeEnd.getTime() && equipmentDeviceLog.get(i - 1).getDeviceId().equals(zjutEquipmentService.selectZjutEquipmentById(l.getEquipmentId()).getDeviceId())) {
                                System.out.println("正常开机");
                                EquipmentUse equipmentUse = new EquipmentUse();
                                equipmentUse.setEquipmentSubscribeId(l.getEquipmentSubscribeId());
                                equipmentUse.setEquipmentId(l.getEquipmentId());
                                equipmentUse.setUseName(l.getSubscribeName());
                                Date date = new Date();
                                String s1 = df3.format(date);
                                String s2 = df.format(date);
                                equipmentUse.setUseDate(s1);
                                equipmentUse.setUseStartTime(s2);
                                equipmentUseService.insertEquipmentUse(equipmentUse);
                                flag = 0;
                                break;
                            }
                        }
                        if(flag == 1){
                            System.out.println("预约违规开机");
                            EquipmentWarning equipmentWarning = new EquipmentWarning();
                            equipmentWarning.setEquipmentId(todayList.get(0).getEquipmentId());
                            equipmentWarning.setEquipmentName(todayList.get(0).getEquipmentName());
                            equipmentWarning.setWarningTime(df2.format(new Date()));
                            equipmentWarning.setWarningType("预约时间外开机");
                            equipmentWarningService.insertEquipmentWarning(equipmentWarning);
                        }
                    }
                    else{
                        System.out.println("违规开机");
                        for(ZjutEquipment e:equipmentList){
                            if(e.getDeviceId().equals(s)){
                                EquipmentWarning equipmentWarning = new EquipmentWarning();
                                equipmentWarning.setEquipmentId(e.getEquipmentId());
                                equipmentWarning.setEquipmentName(e.getEquipmentName());
                                equipmentWarning.setWarningTime(df2.format(new Date()));
                                equipmentWarning.setWarningType("预约时间外开机");
                                equipmentWarningService.insertEquipmentWarning(equipmentWarning);
                            }
                        }
                    }
                }
                if(Double.parseDouble(equipmentDeviceLog.get(i-1).getDeviceCurrent())-Double.parseDouble(equipmentDeviceLog.get(i-2).getDeviceCurrent()) <=(-50)){
                    System.out.println("插座:"+equipmentDeviceLog.get(i-1).getDeviceId()+"关机");
                    System.out.println("插座:"+equipmentDeviceLog.get(i-1).getDeviceId()+"关机");
                    System.out.println("插座:"+equipmentDeviceLog.get(i-1).getDeviceId()+"关机");
                    List<EquipmentUse> useList = equipmentUseService.selectEquipmentUseList(new EquipmentUse());
                    for(EquipmentUse u:useList){
                        if((zjutEquipmentService.selectZjutEquipmentById(u.getEquipmentId()).getDeviceId()).equals(equipmentDeviceLog.get(i-1).getDeviceId())&&u.getUseEndTime()==null){
                            System.out.println("正常关机");
                            Date date = new Date();
                            String s1 = df3.format(date);
                            String s2 = df.format(date);
                            u.setUseDate(s1);
                            u.setUseEndTime(s2);
                            EquipmentSubscribe e = equipmentSubscribeService.selectEquipmentSubscribeById(u.getEquipmentSubscribeId());
                            long timeStart = df.parse(e.getSubscribeStartTime()).getTime();
                            long timeEnd = df.parse(e.getSubscribeEndTime()).getTime();
                            long time = timeStart - timeEnd;
                            System.out.println(time);
                            BigDecimal CostSubscribe = e.getTotalCost();
                            equipmentUseService.updateEquipmentUse(u);
                        }
                    }
                }
            }
        }

    static class Command{
        String code;
        Object value;
    }
    static class CommandWrapper{
        List<Command> commands;
    }
}

