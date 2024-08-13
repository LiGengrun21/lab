package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserCollectLabMapper;
import com.ruoyi.system.domain.UserCollectLab;
import com.ruoyi.system.service.IUserCollectLabService;

/**
 * 用户收藏房间Service业务层处理
 * 
 *
 * @date 2021-12-22
 */
@Service
public class UserCollectLabServiceImpl implements IUserCollectLabService 
{
    @Autowired
    private UserCollectLabMapper userCollectLabMapper;

    /**
     * 查询用户收藏房间
     * 
     * @param id 用户收藏房间ID
     * @return 用户收藏房间
     */
    @Override
    public UserCollectLab selectUserCollectLabById(Long id)
    {
        return userCollectLabMapper.selectUserCollectLabById(id);
    }

    /**
     * 查询用户收藏房间列表
     * 
     * @param userCollectLab 用户收藏房间
     * @return 用户收藏房间
     */
    @Override
    public List<UserCollectLab> selectUserCollectLabList(UserCollectLab userCollectLab)
    {
        return userCollectLabMapper.selectUserCollectLabList(userCollectLab);
    }

    /**
     * 新增用户收藏房间
     * 
     * @param userCollectLab 用户收藏房间
     * @return 结果
     */
    @Override
    public int insertUserCollectLab(UserCollectLab userCollectLab)
    {
        return userCollectLabMapper.insertUserCollectLab(userCollectLab);
    }

    /**
     * 修改用户收藏房间
     * 
     * @param userCollectLab 用户收藏房间
     * @return 结果
     */
    @Override
    public int updateUserCollectLab(UserCollectLab userCollectLab)
    {
        return userCollectLabMapper.updateUserCollectLab(userCollectLab);
    }

    /**
     * 批量删除用户收藏房间
     * 
     * @param ids 需要删除的用户收藏房间ID
     * @return 结果
     */
    @Override
    public int deleteUserCollectLabByIds(Long[] ids)
    {
        return userCollectLabMapper.deleteUserCollectLabByIds(ids);
    }

    /**
     * 删除用户收藏房间信息
     * 
     * @param id 用户收藏房间ID
     * @return 结果
     */
    @Override
    public int deleteUserCollectLabById(Long id)
    {
        return userCollectLabMapper.deleteUserCollectLabById(id);
    }
}
