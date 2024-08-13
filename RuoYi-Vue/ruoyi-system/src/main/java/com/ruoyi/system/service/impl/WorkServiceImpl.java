package com.ruoyi.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysLogs;
import com.ruoyi.system.domain.WorkUser;
import com.ruoyi.system.mapper.SysLogsMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysLogsService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IWorkUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WorkMapper;
import com.ruoyi.system.domain.Work;
import com.ruoyi.system.service.IWorkService;

/**
 * 值班Service业务层处理
 *
 * 
 * @date 2021-09-19
 */
@Service
public class WorkServiceImpl implements IWorkService
{
    private static final Logger log= LoggerFactory.getLogger(WorkServiceImpl.class);

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysLogsMapper sysLogsMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysLogsService sysLogsService;

    @Autowired
    private IWorkUserService workUserService;

    @Override
    public List<Work> selectUserList() {
        return workMapper.selectWorkUsers();
    }

    /**
     * 根据日期查询值班
     *
     * @param date
     * @return
     */
    @Override
    public List<Work> selectWorkByDate(Date date)
    {
        return workMapper.selectWorkByDate(date);
    }

    /**
     * 查询值班
     *
     * @param workId 值班ID
     * @return 值班
     */
    @Override
    public Work selectWorkById(Long workId)
    {
        return workMapper.selectWorkById(workId);
    }

    /**
     * 查询值班列表
     *
     * @param work 值班
     * @return 值班
     */
    @Override
    public Work selectWorkByUserAndDate(Work work)
    {
        return workMapper.selectWorkByUserAndDate(work);
    }

    /**
     * 根据月份查询值班列表
     *
     * @param month
     * @return
     */
    @Override
    public List<Work> selectWorkByMonth(int year,int month) {
        if (month<1 || month>12){
            return null;
        }
        return workMapper.selectWorkByMonth(year,month);
    }

    /**
     * 根据用户id查询值班列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Work> selectWorkByUser(Long userId) {
        return workMapper.selectWorkByUser(userId);
    }

    /**
     * 根据月份添加值班
     * 旧的数据直接删掉，换成新的数据
     *
     * @param workList
     * @return
     */
    @Override
    public int insertMonthWork(List<Work> workList) {
        //拿到年月
        Calendar cal = Calendar.getInstance();
        cal.setTime(workList.get(0).getWorkDate());
        int month = cal.get(Calendar.MONTH)+1;
        int year=cal.get(Calendar.YEAR);
        //int month=workList.get(0).getWorkDate().getMonth()+1;
        //int year=workList.get(0).getWorkDate().getYear();
        //删除旧的值班数据
        workMapper.deleteWorkByMonth(year,month);

        /**
         * 根据年月以及type=2删除旧日志
         * wordDate相当于日志系统里的subscribeDate
         */
        sysLogsMapper.deleteLogByTypeAndSubscribeMonth(2,year,month);

        for (Work work : workList) {
            //添加新的值班数据
            workMapper.insertWork(work);
            //在日志管理里添加一条记录
            SysLogs sysLogs=new SysLogs();
            sysLogs.setUserId(work.getUserId());
            sysLogs.setType(2);
            sysLogs.setSubscribeDate(work.getWorkDate());
            sysLogs.setRemark("添加值班");
            /**
             * 因为userId和workDate可以唯一确定workId，所以只加进去userId就i好
             */
            //sysLogs.setSubscribeId(work.getWorkId());
            sysLogsMapper.insertLogs(sysLogs);
        }
        return 1;
    }

    /**
     * 新增某一天的值班
     * 旧的数据直接删掉，换成新的数据
     *
     * @param workList 值班
     * @return 结果
     */
    @Override
    public int insertDateWork(List<Work> workList)
    {
        /**
         * 因为可以保证待添加的都是值班名单上的
         */
//        //检查值班列表，不在列表里的拒绝排班
//        WorkUser workUser=workUserService.selectWorkUserByUserId(work.getUserId());
//        if (workUser==null)
//        {
//            return 0;
//        }

        //拿到值班日期
        Date workDate=workList.get(0).getWorkDate();
        //根据日期删除旧的值班
        workMapper.deleteWorkByDate(workDate);
//        //查询这个日期的值班列表
//        List<Work> workListDate=workMapper.selectWorkByDate(workDate);
//        //拿到这些值班的workId
//        List<Long> workIdsList=new ArrayList<>();
//        for (Work work : workListDate) {
//            workIdsList.add(work.getWorkId());
//        }
//        Long[] workIds=new Long[workIdsList.size()];
//        workIdsList.toArray(workIds);
//        //根据workIds删除旧值班
//        workMapper.deleteWorkByIds(workIds);
        /**
         * 根据workDate以及type=2删除旧日志
         * wordDate相当于日志系统里的subscribeDate
         */
        sysLogsMapper.deleteLogByTypeAndSubscribeDate(2,workDate);

        for (Work work : workList) {
            //添加新的值班数据
            workMapper.insertWork(work);
            //在日志管理里添加一条新记录
            SysLogs sysLogs=new SysLogs();
            sysLogs.setUserId(work.getUserId());
            sysLogs.setType(2);
            sysLogs.setSubscribeDate(work.getWorkDate());
            sysLogs.setRemark("添加值班");
            sysLogsMapper.insertLogs(sysLogs);
        }
        return 1;
    }

    @Override
    public int insertWork(Work work) {
        return workMapper.insertWork(work);
    }

    /**
     * 修改值班
     *
     * @param work 值班
     * @return 结果
     */
    @Override
    public int updateWork(Work work)
    {
        return workMapper.updateWork(work);
    }

    /**
     * 批量删除值班
     *
     * @param workIds 需要删除的值班ID
     * @return 结果
     */
    @Override
    public int deleteWorkByIds(Long[] workIds)
    {
        //删除日志
        for (Long workId : workIds) {
            SysLogs sysLogs=sysLogsService.selectLogsBySubscribeId(2,workId);
            if (sysLogs==null){
                continue;
            }
            sysLogsService.deleteLogs(sysLogs.getLogsId());
        }

        return workMapper.deleteWorkByIds(workIds);
    }

    /**
     * 删除值班信息
     *
     * @param workId 值班ID
     * @return 结果
     */
    @Override
    public int deleteWorkById(Long workId)
    {
        //删除日志
        SysLogs sysLogs=sysLogsService.selectLogsBySubscribeId(2,workId);
        sysLogsService.deleteLogs(sysLogs.getLogsId());

        return workMapper.deleteWorkById(workId);
    }

    @Override
    public String importWork(List<Work> workList,Boolean isUpdateSupport){
        if(StringUtils.isNull(workList)||workList.size()==0){
            throw new CustomException("导入数据不能为空！");
        }
        int successNum=0;
        int failureNum=0;
        StringBuilder successMsg=new StringBuilder();
        StringBuilder failureMsg=new StringBuilder();
        for(Work i: workList){
            try{
                // 验证是否存在这个用户
                SysUser u=userMapper.selectUserByNickName(i.getNickName());
                if(!(StringUtils.isNull(u))){
                    i.setUserId(sysUserService.selectUserByNickName(i.getNickName()).getUserId());
                    this.insertWork(i);
                    successNum++;
                    successMsg.append("<br/>"+successNum+"、值班 "+i.getNickName()+" 导入成功");
                }
                else{
                    failureNum++;
                    failureMsg.append("<br/>"+failureNum+"、值班人员 "+i.getNickName()+" 账号不存在");
                }
            }
            catch(Exception e){
                failureNum++;
                String msg="<br/>"+failureNum+"、账号 "+i.getNickName()+" 导入失败：";
                failureMsg.append(msg+e.getMessage());
                log.error(msg,e);
            }
        }
        if(failureNum>0){
            failureMsg.insert(0,"很抱歉，导入失败！共 "+failureNum+" 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else{
            successMsg.insert(0,"恭喜您，数据已全部导入成功！共 "+successNum+" 条，数据如下：");
        }
        return successMsg.toString();
    }
}
