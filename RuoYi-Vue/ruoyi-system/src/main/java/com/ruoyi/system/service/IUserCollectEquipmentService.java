package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.UserCollectEquipment;

/**
 * 用户收藏设备Service接口
 * 
 *
 * @date 2021-12-09
 */
public interface IUserCollectEquipmentService 
{
    /**
     * 查询用户收藏设备
     * 
     * @param id 用户收藏设备ID
     * @return 用户收藏设备
     */
    public UserCollectEquipment selectUserCollectEquipmentById(Long id);

    /**
     * 查询用户收藏设备列表
     * 
     * @param userCollectEquipment 用户收藏设备
     * @return 用户收藏设备集合
     */
    public List<UserCollectEquipment> selectUserCollectEquipmentList(UserCollectEquipment userCollectEquipment);

    /**
     * 新增用户收藏设备
     * 
     * @param userCollectEquipment 用户收藏设备
     * @return 结果
     */
    public int insertUserCollectEquipment(UserCollectEquipment userCollectEquipment);

    /**
     * 修改用户收藏设备
     * 
     * @param userCollectEquipment 用户收藏设备
     * @return 结果
     */
    public int updateUserCollectEquipment(UserCollectEquipment userCollectEquipment);

    /**
     * 批量删除用户收藏设备
     * 
     * @param ids 需要删除的用户收藏设备ID
     * @return 结果
     */
    public int deleteUserCollectEquipmentByIds(Long[] ids);

    /**
     * 删除用户收藏设备信息
     * 
     * @param id 用户收藏设备ID
     * @return 结果
     */
    public int deleteUserCollectEquipmentById(Long id);
}
