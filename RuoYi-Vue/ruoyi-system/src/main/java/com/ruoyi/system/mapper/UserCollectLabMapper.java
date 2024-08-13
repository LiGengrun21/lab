package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.UserCollectLab;

/**
 * 用户收藏房间Mapper接口
 * 
 *
 * @date 2021-12-22
 */
public interface UserCollectLabMapper 
{
    /**
     * 查询用户收藏房间
     * 
     * @param id 用户收藏房间ID
     * @return 用户收藏房间
     */
    public UserCollectLab selectUserCollectLabById(Long id);

    /**
     * 查询用户收藏房间列表
     * 
     * @param userCollectLab 用户收藏房间
     * @return 用户收藏房间集合
     */
    public List<UserCollectLab> selectUserCollectLabList(UserCollectLab userCollectLab);

    /**
     * 新增用户收藏房间
     * 
     * @param userCollectLab 用户收藏房间
     * @return 结果
     */
    public int insertUserCollectLab(UserCollectLab userCollectLab);

    /**
     * 修改用户收藏房间
     * 
     * @param userCollectLab 用户收藏房间
     * @return 结果
     */
    public int updateUserCollectLab(UserCollectLab userCollectLab);

    /**
     * 删除用户收藏房间
     * 
     * @param id 用户收藏房间ID
     * @return 结果
     */
    public int deleteUserCollectLabById(Long id);

    /**
     * 批量删除用户收藏房间
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserCollectLabByIds(Long[] ids);
}
