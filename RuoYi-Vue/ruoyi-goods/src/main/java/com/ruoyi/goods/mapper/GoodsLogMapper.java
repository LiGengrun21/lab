package com.ruoyi.goods.mapper;

import java.util.List;
import com.ruoyi.goods.domain.GoodsLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 出入库日志Mapper接口
 * 
 * 
 * @date 2021-05-19
 */
@Mapper
public interface GoodsLogMapper 
{
    /**
     * 查询出入库日志
     * 
     * @param goodsLogId 出入库日志ID
     * @return 出入库日志
     */
    public GoodsLog selectGoodsLogById(Long goodsLogId);

    /**
     * 查询出入库日志列表
     * 
     * @param goodsLog 出入库日志
     * @return 出入库日志集合
     */
    public List<GoodsLog> selectGoodsLogList(GoodsLog goodsLog);

    /**
     * 新增出入库日志
     * 
     * @param goodsLog 出入库日志
     * @return 结果
     */
    public int insertGoodsLog(GoodsLog goodsLog);

    /**
     * 修改出入库日志
     * 
     * @param goodsLog 出入库日志
     * @return 结果
     */
    public int updateGoodsLog(GoodsLog goodsLog);

    /**
     * 删除出入库日志
     * 
     * @param goodsLogId 出入库日志ID
     * @return 结果
     */
    public int deleteGoodsLogById(Long goodsLogId);

    /**
     * 批量删除出入库日志
     * 
     * @param goodsLogIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsLogByIds(Long[] goodsLogIds);
}
