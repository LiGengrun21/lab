package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangerConditionMapper;
import com.ruoyi.system.domain.DangerCondition;
import com.ruoyi.system.service.IDangerConditionService;

/**
 * 危险事故Service业务层处理
 * 
 * 
 * @date 2021-09-19
 */
@Service
public class DangerConditionServiceImpl implements IDangerConditionService 
{
    @Autowired
    private DangerConditionMapper dangerConditionMapper;

    /**
     * 查询危险事故
     * 
     * @param id 危险事故ID
     * @return 危险事故
     */
    @Override
    public DangerCondition selectDangerConditionById(Long id)
    {
        return dangerConditionMapper.selectDangerConditionById(id);
    }

    /**
     * 查询危险事故列表
     * 
     * @param dangerCondition 危险事故
     * @return 危险事故
     */
    @Override
    public List<DangerCondition> selectDangerConditionList(DangerCondition dangerCondition)
    {
        return dangerConditionMapper.selectDangerConditionList(dangerCondition);
    }

    /**
     * 新增危险事故
     * 
     * @param dangerCondition 危险事故
     * @return 结果
     */
    @Override
    public int insertDangerCondition(DangerCondition dangerCondition)
    {
        return dangerConditionMapper.insertDangerCondition(dangerCondition);
    }

    /**
     * 修改危险事故
     * 
     * @param dangerCondition 危险事故
     * @return 结果
     */
    @Override
    public int updateDangerCondition(DangerCondition dangerCondition)
    {
        return dangerConditionMapper.updateDangerCondition(dangerCondition);
    }

    /**
     * 批量删除危险事故
     * 
     * @param ids 需要删除的危险事故ID
     * @return 结果
     */
    @Override
    public int deleteDangerConditionByIds(Long[] ids)
    {
        return dangerConditionMapper.deleteDangerConditionByIds(ids);
    }

    /**
     * 删除危险事故信息
     * 
     * @param id 危险事故ID
     * @return 结果
     */
    @Override
    public int deleteDangerConditionById(Long id)
    {
        return dangerConditionMapper.deleteDangerConditionById(id);
    }
}
