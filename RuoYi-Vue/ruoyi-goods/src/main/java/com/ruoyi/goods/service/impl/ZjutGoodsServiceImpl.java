package com.ruoyi.goods.service.impl;

import java.util.List;

import com.ruoyi.goods.domain.GoodsLog;
import com.ruoyi.goods.mapper.GoodsLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.goods.mapper.ZjutGoodsMapper;
import com.ruoyi.goods.domain.ZjutGoods;
import com.ruoyi.goods.service.IZjutGoodsService;

/**
 * 耗材列表Service业务层处理
 *
 *
 * @date 2021-05-18
 */
@Service
public class ZjutGoodsServiceImpl implements IZjutGoodsService
{
    @Autowired
    private ZjutGoodsMapper zjutGoodsMapper;


    /**
     * 查询耗材列表
     *
     * @param goodsId 耗材列表ID
     * @return 耗材列表
     */
    @Override
    public ZjutGoods selectZjutGoodsById(Long goodsId)
    {
        return zjutGoodsMapper.selectZjutGoodsById(goodsId);
    }

    /**
     * 查询耗材列表列表
     *
     * @param zjutGoods 耗材列表
     * @return 耗材列表
     */
    @Override
    public List<ZjutGoods> selectZjutGoodsList(ZjutGoods zjutGoods)
    {
        return zjutGoodsMapper.selectZjutGoodsList(zjutGoods);
    }

    @Override
    public List<ZjutGoods> selectEquipmentGoodsList(ZjutGoods zjutGoods) {
        return zjutGoodsMapper.selectEquipmentGoodsList(zjutGoods);
    }

    @Override
    public List<ZjutGoods> selectValuableGoodsList(ZjutGoods zjutGoods) {
        return zjutGoodsMapper.selectValuableGoodsList(zjutGoods);
    }

    /**
     * 新增耗材列表
     *
     * @param zjutGoods 耗材列表
     * @return 结果
     */
    @Override
    public int insertZjutGoods(ZjutGoods zjutGoods)
    {
        return zjutGoodsMapper.insertZjutGoods(zjutGoods);
    }

    /**
     * 修改耗材列表
     *
     * @param zjutGoods 耗材列表
     * @return 结果
     */
    @Override
    public int updateZjutGoods(ZjutGoods zjutGoods)
    {
        return zjutGoodsMapper.updateZjutGoods(zjutGoods);
    }

    /**
     * 添加耗材数量
     *
     * @param zjutGoods
     * @return 结果
     */
    @Override
    public int addZjutGoods(ZjutGoods zjutGoods) {
        ZjutGoods zjutGoods1 = zjutGoodsMapper.selectZjutGoodsById(zjutGoods.getGoodsId());
        Long a = zjutGoods1.getGoodsQuantity() + zjutGoods.getGoodsQuantity();
        if(a >= 0){
            zjutGoods.setGoodsQuantity(a);
            return zjutGoodsMapper.updateZjutGoods(zjutGoods);
        }
        else{
            return -1;
        }
    }

    /**
     * 批量删除耗材列表
     *
     * @param goodsIds 需要删除的耗材列表ID
     * @return 结果
     */
    @Override
    public int deleteZjutGoodsByIds(Long[] goodsIds)
    {
        return zjutGoodsMapper.deleteZjutGoodsByIds(goodsIds);
    }

    /**
     * 删除耗材列表信息
     *
     * @param goodsId 耗材列表ID
     * @return 结果
     */
    @Override
    public int deleteZjutGoodsById(Long goodsId)
    {
        return zjutGoodsMapper.deleteZjutGoodsById(goodsId);
    }
}
