package com.ruoyi.lab.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.ZjutLabMapper;
import com.ruoyi.lab.domain.ZjutLab;
import com.ruoyi.lab.service.IZjutLabService;

/**
 * 实验室列表Service业务层处理
 *
 *
 * @date 2021-05-04
 */
@Service
public class ZjutLabServiceImpl implements IZjutLabService
{
    @Autowired
    private ZjutLabMapper zjutLabMapper;

    /**
     * 查询实验室列表
     *
     * @param labId 实验室列表ID
     * @return 实验室列表
     */
    @Override
    public ZjutLab selectZjutLabById(Long labId)
    {
        return zjutLabMapper.selectZjutLabById(labId);
    }

    /**
     * 查询实验室列表列表
     *
     * @param zjutLab 实验室列表
     * @return 实验室列表
     */
    @Override
    public List<ZjutLab> selectZjutLabList(ZjutLab zjutLab)
    {
        return zjutLabMapper.selectZjutLabList(zjutLab);
    }
    public List<ZjutLab> selectZjutLabEquipmentList(ZjutLab zjutLab){return zjutLabMapper.selectZjutLabEquipmentList(zjutLab);}

    /**
     * 新增实验室列表
     *
     * @param zjutLab 实验室列表
     * @return 结果
     */
    @Override
    public int insertZjutLab(ZjutLab zjutLab)
    {
        return zjutLabMapper.insertZjutLab(zjutLab);
    }

    /**
     * 修改实验室列表
     *
     * @param zjutLab 实验室列表
     * @return 结果
     */
    @Override
    public int updateZjutLab(ZjutLab zjutLab)
    {
        return zjutLabMapper.updateZjutLab(zjutLab);
    }

    /**
     * 批量删除实验室列表
     *
     * @param labIds 需要删除的实验室列表ID
     * @return 结果
     */
    @Override
    public int deleteZjutLabByIds(Long[] labIds)
    {
        return zjutLabMapper.deleteZjutLabByIds(labIds);
    }

    /**
     * 删除实验室列表信息
     *
     * @param labId 实验室列表ID
     * @return 结果
     */
    @Override
    public int deleteZjutLabById(Long labId)
    {
        return zjutLabMapper.deleteZjutLabById(labId);
    }
    @Override
    public int selectAmountOfRoomByRoomType(String roomType){
        return zjutLabMapper.selectAmountOfRoomByRoomType(roomType);
    }
}
