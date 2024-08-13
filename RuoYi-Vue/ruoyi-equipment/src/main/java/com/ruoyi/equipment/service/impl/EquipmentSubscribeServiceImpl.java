package com.ruoyi.equipment.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.equipment.domain.AggregateResult;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equipment.mapper.EquipmentSubscribeMapper;
import com.ruoyi.equipment.domain.EquipmentSubscribe;
import com.ruoyi.equipment.service.IEquipmentSubscribeService;

/**
 * 设备预约Service业务层处理
 *
 */
@Service
public class EquipmentSubscribeServiceImpl implements IEquipmentSubscribeService{
    @Autowired
    private EquipmentSubscribeMapper equipmentSubscribeMapper;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询设备预约
     *
     * @param equipmentSubscribeId 设备预约ID
     * @return 设备预约
     */
    @Override
    public EquipmentSubscribe selectEquipmentSubscribeById(Long equipmentSubscribeId){
        return equipmentSubscribeMapper.selectEquipmentSubscribeById(equipmentSubscribeId);
    }
    /**
     * 查询设备预约列表
     *
     * @param equipmentSubscribe 设备预约
     * @return 设备预约
     */
    @Override
    public List<EquipmentSubscribe> selectEquipmentSubscribeList(EquipmentSubscribe equipmentSubscribe){
        return equipmentSubscribeMapper.selectEquipmentSubscribeList(equipmentSubscribe);
    }
    @Override
    public List<EquipmentSubscribe> selectmyEquipmentSubscribeList(EquipmentSubscribe equipmentSubscribe){
        List<EquipmentSubscribe> list=equipmentSubscribeMapper.selectEquipmentSubscribeList(equipmentSubscribe);
        System.out.println(list);
        LoginUser loginUser=tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user=loginUser.getUser();
        ArrayList<EquipmentSubscribe> mylist=new ArrayList<>();
        for(EquipmentSubscribe i: list){
            if(i.getSubscribeName().equals(user.getNickName())){
                mylist.add(i);
            }
        }
        System.out.println(mylist);
        return mylist;
    }
    @Override
    public List<EquipmentSubscribe> selectAuditSubscribeList(EquipmentSubscribe equipmentSubscribe){
        return equipmentSubscribeMapper.selectAuditSubscribeList(equipmentSubscribe);
    }
    @Override
    public List<EquipmentSubscribe> selectTodaySubscribe(){
        LocalDate date=LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String subscribeDate=date.format(formatter);
        return equipmentSubscribeMapper.selectTodaySubscribe(subscribeDate);
    }
    @Override
    public List<EquipmentSubscribe> selectTBDSubscribeList(EquipmentSubscribe equipmentSubscribe){
        List<EquipmentSubscribe> list=equipmentSubscribeMapper.selectTBDSubscribeList(equipmentSubscribe);
        LoginUser loginUser=tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user=loginUser.getUser();
        ArrayList<EquipmentSubscribe> mylist=new ArrayList<>();
        for(EquipmentSubscribe i: list){
            if(i.getSubscribeName().equals(user.getNickName())){
                mylist.add(i);
            }
        }
        System.out.println(mylist);
        return mylist;
    }
    /**
     * 新增设备预约
     *
     * @param equipmentSubscribe 设备预约
     * @return 结果
     */
    @Override
    public int insertEquipmentSubscribe(EquipmentSubscribe equipmentSubscribe){
        return equipmentSubscribeMapper.insertEquipmentSubscribe(equipmentSubscribe);
    }
    /**
     * 修改设备预约
     *
     * @param equipmentSubscribe 设备预约
     * @return 结果
     */
    @Override
    public int updateEquipmentSubscribe(EquipmentSubscribe equipmentSubscribe){
        return equipmentSubscribeMapper.updateEquipmentSubscribe(equipmentSubscribe);
    }
    /**
     * 批量删除设备预约
     *
     * @param equipmentSubscribeIds 需要删除的设备预约ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentSubscribeByIds(Long[] equipmentSubscribeIds){
        return equipmentSubscribeMapper.deleteEquipmentSubscribeByIds(equipmentSubscribeIds);
    }
    /**
     * 删除设备预约信息
     *
     * @param equipmentSubscribeId 设备预约ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentSubscribeById(Long equipmentSubscribeId){
        return equipmentSubscribeMapper.deleteEquipmentSubscribeById(equipmentSubscribeId);
    }
    @Override
    public ArrayList<AggregateResult> selectSubscribesStatisticOfPiAndMembers(Long piId,String beginDate,String endDate){
        return equipmentSubscribeMapper.selectSubscribesStatisticOfPiAndMembers(piId,beginDate,endDate);
    }
    @Override
    public AggregateResult selectSubscribesStatisticByPiUnit(Long piId,String beginDate,String endDate){
        return equipmentSubscribeMapper.selectSubscribesStatisticByPiUnit(piId,beginDate,endDate);
    }
    @Override
    public ArrayList<EquipmentSubscribe> selectSubscribesByUserId(Long userId,String beginDate,String endDate,String equipmentName){
        return equipmentSubscribeMapper.selectSubscribesByUserId(userId,beginDate,endDate,equipmentName);
    }
    @Override
    public double selectSubscribesStatisticByUserId(Long userId,String beginDate,String endDate){
        return equipmentSubscribeMapper.selectSubscribesStatisticByUserId(userId,beginDate,endDate);
    }
    @Override
    public ArrayList<AggregateResult> selectSubscribesCostOfPiAndMembersByEquipmentUnit(Long piId,String beginDate,String endDate){
        return equipmentSubscribeMapper.selectSubscribesCostOfPiAndMembersByEquipmentUnit(piId,beginDate,endDate);
    }
    @Override
    public int selectAmountOfSubscribedEquipmentByDefiniteTime(String date,String time){
        return equipmentSubscribeMapper.selectAmountOfSubscribedEquipmentByDefiniteTime(date,time);
    }
    @Override
    public ArrayList<EquipmentSubscribe> selectInfoThatEndTimeAfterIndicatedDateTime(String date,String time){
        return equipmentSubscribeMapper.selectInfoThatEndTimeAfterIndicatedDateTime(date,time);
    }
    @Override
    public int checkConflictSubscribe(EquipmentSubscribe equipmentSubscribe){
        return equipmentSubscribeMapper.selectConflictSubscribe(equipmentSubscribe);
    }
    @Override
    public Map<String,Double> selectEveryCostAggregationByUserId(Long userId){
        return equipmentSubscribeMapper.selectEveryCostAggregationByUserId(userId);
    }
}
