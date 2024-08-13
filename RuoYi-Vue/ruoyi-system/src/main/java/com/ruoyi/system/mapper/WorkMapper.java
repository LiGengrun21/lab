package com.ruoyi.system.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.system.domain.Work;
import org.apache.ibatis.annotations.Param;

/**
 * 值班Mapper接口
 *
 *
 * @date 2021-09-19
 */
public interface WorkMapper
{
    /**
     * 根据日期查询值班
     *
     * @param workDate
     * @return
     */
    public List<Work> selectWorkByDate(@Param("workDate") Date workDate);

    /**
     * 查询值班
     * 根据值班记录的id
     *
     * @param workId 值班ID
     * @return 值班
     */
    public Work selectWorkById(Long workId);

    /**
     * 查询值班列表
     * 根据用户id和日期查询
     *
     * @param work 值班
     * @return 值班集合
     */
    public Work selectWorkByUserAndDate(Work work);

    /**
     * 根据月份查询值班列表
     *
     * @param month
     * @return 值班集合
     */
    public List<Work> selectWorkByMonth(@Param("year") int year, @Param("month") int month);

    /**
     * 根据月份删除全部值班
     *
     * @param year
     * @param month
     * @return
     */
    public int deleteWorkByMonth(@Param("year") int year, @Param("month") int month);

    /**
     * 根据用户id查询值班列表
     *
     * @param userId
     * @return
     */
    public List<Work> selectWorkByUser(Long userId);

    /**
     * 新增值班
     *
     * @param work 值班
     * @return 结果
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
     * 删除值班
     *
     * @param workId 值班ID
     * @return 结果
     */
    public int deleteWorkById(Long workId);

    /**
     * 批量删除值班
     *
     * @param workIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkByIds(Long[] workIds);

    /**
     * 根据日期删除数据
     *
     * @param workDate
     * @return
     */
    public int deleteWorkByDate(Date workDate);

    /**
     * 查询值班人员列表
     * @return
     */
    public List<Work> selectWorkUsers();
}
