package com.ruoyi.quartz.task;
import com.ruoyi.equipment.domain.*;
import com.ruoyi.equipment.service.*;
import com.ruoyi.quartz.connector.DeviceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component("deviceLogTask")
public class DeviceLogTask {
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

    //获取设备运行信息
    public void getDeviceLogStatus(String deviceId) throws ParseException {
        System.out.println(deviceId);
        int flag = 0;
        int type = 0;
        ArrayList<Object> status = deviceConnector.getDeviceStatus(deviceId);
        System.out.println(status.toString());
        DeviceLog deviceLog = new DeviceLog();
        deviceLog.setDeviceId(deviceId);
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
                            deviceLog.setDevicePower(Double.toString(Double.parseDouble(s2) / 10));
                            break;
                        case 5:
                            deviceLog.setDeviceVoltage(Double.toString(Double.parseDouble(s2) / 10));
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
        System.out.println(deviceLog);
        ZjutEquipment zjutEquipment = zjutEquipmentService.selectZjutEquipmentByDeviceId(deviceId);
        String s = zjutEquipment.getEquipmentSnNumber()+"_device_log";
        deviceLogService.insertOneDeviceLog(deviceLog,s);
    }

    //自动删除监控记录，防止数据库数据过多
    public void deleteDeviceLogStatus(Integer t){
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

    /*public void warningDeviceLogStatus(String deviceId) throws ParseException {
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
    }*/


    static class Command{
        String code;
        Object value;
    }
    static class CommandWrapper{
        List<Command> commands;
    }
}

