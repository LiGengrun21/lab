package com.ruoyi.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.Work;

/**
 * 值班Service接口
 *
 *
 * @date 2021-09-19
 */
public interface IWorkService
{
    /**
     * 获取该日期的值班
     *
     * @param date
     * @return
     */
    public List<Work> selectWorkByDate(Date date);
    /**
     * 获取值班人员列表
     *
     * @return 值班集合
     */
    public Work selectWorkByUserAndDate(Work work);

    /**
     * 查询值班
     *
     * @param workId 值班ID
     * @return 值班
     */
    public Work selectWorkById(Long workId);

    /**
     * 查询值班列表
     *
     * @param
     * @return 值班集合
     */
    public List<Work> selectUserList();

    /**
     * 根据月份查询值班列表
     *
     * @param
     * @return
     */
    public List<Work> selectWorkByMonth(int year,int month);

    /**
     * 根据用户id查询值班列表
     *
     * @param userId
     * @return
     */
    public List<Work> selectWorkByUser(Long userId);

    /**
     * 根据月份批量添加值班
     *
     * @param workList
     * @return int
     */
    public int insertMonthWork(List<Work> workList);

    /**
     * 新增值班
     *
     * @param workList 值班
     * @return 结果
     */
    public int insertDateWork(List<Work> workList);

    /**
     * 单个添加（代码自动生成）
     * @param work
     * @return
     */
    public int insertWork(Work work);

    /**
     * 修改值班
     *
     * @param work 值班
     * @return 结果
     */
    public int updateWork(Work work);

    /**
     * 批量删除值班
     *
     * @param workIds 需要删除的值班ID
     * @return 结果
     */
    public int deleteWorkByIds(Long[] workIds);

    /**
     * 删除值班信息
     *
     * @param workId 值班ID
     * @return 结果
     */
    public int deleteWorkById(Long workId);

    public String importWork(List<Work> userList, Boolean isUpdateSupport);
}
