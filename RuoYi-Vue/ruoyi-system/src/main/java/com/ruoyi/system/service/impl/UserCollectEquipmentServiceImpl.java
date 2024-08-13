package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserCollectEquipmentMapper;
import com.ruoyi.system.domain.UserCollectEquipment;
import com.ruoyi.system.service.IUserCollectEquipmentService;

/**
 * 用户收藏设备Service业务层处理
 * 
 *
 * @date 2021-12-09
 */
@Service
public class UserCollectEquipmentServiceImpl implements IUserCollectEquipmentService 
{
    @Autowired
    private UserCollectEquipmentMapper userCollectEquipmentMapper;

    /**
     * 查询用户收藏设备
     * 
     * @param id 用户收藏设备ID
     * @return 用户收藏设备
     */
    @Override
    public UserCollectEquipment selectUserCollectEquipmentById(Long id)
    {
        return userCollectEquipmentMapper.selectUserCollectEquipmentById(id);
    }

    /**
     * 查询用户收藏设备列表
     * 
     * @param userCollectEquipment 用户收藏设备
     * @return 用户收藏设备
     */
    @Override
    public List<UserCollectEquipment> selectUserCollectEquipmentList(UserCollectEquipment userCollectEquipment)
    {
        return userCollectEquipmentMapper.selectUserCollectEquipmentList(userCollectEquipment);
    }

    /**
     * 新增用户收藏设备
     * 
     * @param userCollectEquipment 用户收藏设备
     * @return 结果
     */
    @Override
    public int insertUserCollectEquipment(UserCollectEquipment userCollectEquipment)
    {
        return userCollectEquipmentMapper.insertUserCollectEquipment(userCollectEquipment);
    }

    /**
     * 修改用户收藏设备
     * 
     * @param userCollectEquipment 用户收藏设备
     * @return 结果
     */
    @Override
    public int updateUserCollectEquipment(UserCollectEquipment userCollectEquipment)
    {
        return userCollectEquipmentMapper.updateUserCollectEquipment(userCollectEquipment);
    }

    /**
     * 批量删除用户收藏设备
     * 
     * @param ids 需要删除的用户收藏设备ID
     * @return 结果
     */
    @Override
    public int deleteUserCollectEquipmentByIds(Long[] ids)
    {
        return userCollectEquipmentMapper.deleteUserCollectEquipmentByIds(ids);
    }

    /**
     * 删除用户收藏设备信息
     * 
     * @param id 用户收藏设备ID
     * @return 结果
     */
    @Override
    public int deleteUserCollectEquipmentById(Long id)
    {
        return userCollectEquipmentMapper.deleteUserCollectEquipmentById(id);
    }
}
