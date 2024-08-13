package com.ruoyi.goods.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.goods.mapper.GoodsLogMapper;
import com.ruoyi.goods.domain.GoodsLog;
import com.ruoyi.goods.service.IGoodsLogService;

/**
 * 出入库日志Service业务层处理
 * 
 *
 * @date 2021-05-19
 */
@Service
public class GoodsLogServiceImpl implements IGoodsLogService 
{
    @Autowired
    private GoodsLogMapper goodsLogMapper;

    /**
     * 查询出入库日志
     * 
     * @param goodsLogId 出入库日志ID
     * @return 出入库日志
     */
    @Override
    public GoodsLog selectGoodsLogById(Long goodsLogId)
    {
        return goodsLogMapper.selectGoodsLogById(goodsLogId);
    }

    /**
     * 查询出入库日志列表
     * 
     * @param goodsLog 出入库日志
     * @return 出入库日志
     */
    @Override
    public List<GoodsLog> selectGoodsLogList(GoodsLog goodsLog)
    {
        return goodsLogMapper.selectGoodsLogList(goodsLog);
    }

    /**
     * 新增出入库日志
     * 
     * @param goodsLog 出入库日志
     * @return 结果
     */
    @Override
    public int insertGoodsLog(GoodsLog goodsLog)
    {
        return goodsLogMapper.insertGoodsLog(goodsLog);
    }

    /**
     * 修改出入库日志
     * 
     * @param goodsLog 出入库日志
     * @return 结果
     */
    @Override
    public int updateGoodsLog(GoodsLog goodsLog)
    {
        return goodsLogMapper.updateGoodsLog(goodsLog);
    }

    /**
     * 批量删除出入库日志
     * 
     * @param goodsLogIds 需要删除的出入库日志ID
     * @return 结果
     */
    @Override
    public int deleteGoodsLogByIds(Long[] goodsLogIds)
    {
        return goodsLogMapper.deleteGoodsLogByIds(goodsLogIds);
    }

    /**
     * 删除出入库日志信息
     * 
     * @param goodsLogId 出入库日志ID
     * @return 结果
     */
    @Override
    public int deleteGoodsLogById(Long goodsLogId)
    {
        return goodsLogMapper.deleteGoodsLogById(goodsLogId);
    }
}
