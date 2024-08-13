package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.DangerCondition;

/**
 * 危险事故Service接口
 * 
 *
 * @date 2021-09-19
 */
public interface IDangerConditionService 
{
    /**
     * 查询危险事故
     * 
     * @param id 危险事故ID
     * @return 危险事故
     */
    public DangerCondition selectDangerConditionById(Long id);

    /**
     * 查询危险事故列表
     * 
     * @param dangerCondition 危险事故
     * @return 危险事故集合
     */
    public List<DangerCondition> selectDangerConditionList(DangerCondition dangerCondition);

    /**
     * 新增危险事故
     * 
     * @param dangerCondition 危险事故
     * @return 结果
     */
    public int insertDangerCondition(DangerCondition dangerCondition);

    /**
     * 修改危险事故
     * 
     * @param dangerCondition 危险事故
     * @return 结果
     */
    public int updateDangerCondition(DangerCondition dangerCondition);

    /**
     * 批量删除危险事故
     * 
     * @param ids 需要删除的危险事故ID
     * @return 结果
     */
    public int deleteDangerConditionByIds(Long[] ids);

    /**
     * 删除危险事故信息
     * 
     * @param id 危险事故ID
     * @return 结果
     */
    public int deleteDangerConditionById(Long id);
}
