package com.ruoyi.lab.service.impl;

import com.ruoyi.lab.domain.DateMassage;
import com.ruoyi.lab.service.IDateMassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DateMassageServiceImpl implements IDateMassageService {

    @Override
    public List<String> ShowThisWeek() {
        DateMassage dateMassage = new DateMassage();
        List<String> thisWeek = new ArrayList<>();
        LocalDate date = LocalDate.now();
        int today = date.getDayOfWeek().getValue();
        date = date.minusDays(today - 1);

        for(int i = 0; i < 7 ; i++){
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();
            String monthStr;
            String dayStr;
            if( month < 10){
                monthStr = month + "";
                monthStr = "0" + monthStr;
            }
            else{
                monthStr = month + "";
            }

            if( day < 10){
                dayStr = day + "";
                dayStr = "0" + dayStr;
            }
            else{
                dayStr = day + "";
            }
            String dateStr = monthStr + "-" + dayStr ;
            thisWeek.add(dateStr);
            date = date.plusDays(1);
        }
        dateMassage.setThisWeek(thisWeek);
        return dateMassage.getThisWeek();
    }

    @Override
    public List<String> Show24Hours() {
        ArrayList<String> result = new ArrayList<>();
        Date time1 = new Date();
        Date time2 = new Date(time1.getTime() -600000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String t1 = df.format(time1);
        String t2 = df.format(time2);
        result.add(t1);
        result.add(t2);
        return  result;
    }
}
