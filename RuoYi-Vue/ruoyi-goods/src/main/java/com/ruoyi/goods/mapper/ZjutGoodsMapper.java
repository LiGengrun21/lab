package com.ruoyi.goods.mapper;

import java.util.List;
import com.ruoyi.goods.domain.ZjutGoods;
import org.apache.ibatis.annotations.Mapper;

/**
 * 耗材列表Mapper接口
 *
 *
 * @date 2021-05-18
 */
@Mapper
public interface ZjutGoodsMapper
{
    /**
     * 查询耗材列表
     *
     * @param goodsId 耗材列表ID
     * @return 耗材列表
     */
    public ZjutGoods selectZjutGoodsById(Long goodsId);

    /**
     * 查询耗材列表列表
     *
     * @param zjutGoods 耗材列表
     * @return 耗材列表集合
     */
    public List<ZjutGoods> selectZjutGoodsList(ZjutGoods zjutGoods);
    public List<ZjutGoods> selectEquipmentGoodsList(ZjutGoods zjutGoods);
    public List<ZjutGoods> selectValuableGoodsList(ZjutGoods zjutGoods);

    /**
     * 新增耗材列表
     *
     * @param zjutGoods 耗材列表
     * @return 结果
     */
    public int insertZjutGoods(ZjutGoods zjutGoods);

    /**
     * 修改耗材列表
     *
     * @param zjutGoods 耗材列表
     * @return 结果
     */
    public int updateZjutGoods(ZjutGoods zjutGoods);

    /**
     * 删除耗材列表
     *
     * @param goodsId 耗材列表ID
     * @return 结果
     */
    public int deleteZjutGoodsById(Long goodsId);

    /**
     * 批量删除耗材列表
     *
     * @param goodsIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteZjutGoodsByIds(Long[] goodsIds);
}
