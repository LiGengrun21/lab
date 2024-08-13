package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WorkUserMapper;
import com.ruoyi.system.domain.WorkUser;
import com.ruoyi.system.service.IWorkUserService;

/**
 * 值班人员列表 Service业务层处理
 *
 *
 * @date 2022-07-12
 */
@Service
public class WorkUserServiceImpl implements IWorkUserService
{
    @Autowired
    private WorkUserMapper workUserMapper;

    /**
     * 查询
     *
     * @param workUserId ID
     * @return
     */
    @Override
    public WorkUser selectWorkUserById(Long workUserId)
    {
        return workUserMapper.selectWorkUserById(workUserId);
    }

    /**
     * 根据用户id查询值班人员(使用)
     *
     * @param userId
     * @return WorkUser
     */
    @Override
    public WorkUser selectWorkUserByUserId(Long userId)
    {
        return workUserMapper.selectWorkUserByUserId(userId);
    }

    /**
     * 新增值班人员
     *
     * @param workUser
     * @return 结果
     */
    @Override
    public int insertWorkUser(WorkUser workUser)
    {
        return workUserMapper.insertWorkUser(workUser);
    }

    /**
     * 修改
     *
     * @param workUser
     * @return 结果
     */
    @Override
    public int updateWorkUser(WorkUser workUser)
    {
        return workUserMapper.updateWorkUser(workUser);
    }

    /**
     * 批量删除值班人员（使用）
     *
     * @param workUserIds 需要删除的ID
     * @return 结果
     */
    @Override
    public int deleteWorkUserByIds(Long[] workUserIds)
    {
        return workUserMapper.deleteWorkUserByIds(workUserIds);
    }

    /**
     * 批量添加值班人员（使用）
     *
     * @param workUserList
     * @return
     */
    @Override
    public int insertWorkUserList(List<WorkUser> workUserList)
    {
        int flag=0;
        for (WorkUser workUser : workUserList) {
            //判断这个用户是否已经在列表里
            WorkUser workUser1=workUserMapper.selectWorkUserByUserId(workUser.getUserId());
            if (workUser1==null)
            {
                //一个可以添加就算成功
                flag=1;
                workUserMapper.insertWorkUser(workUser);
            }
            else
            {
                //已经注册过了，跳过
                continue;
            }
        }
        return flag;
    }

    /**
     * 删除值班人员
     *
     * @param workUserId
     * @return 结果
     */
    @Override
    public int deleteWorkUserById(Long workUserId)
    {
        return workUserMapper.deleteWorkUserById(workUserId);
    }

    @Override
    public List<WorkUser> selectAll() {
        return workUserMapper.selectAll();
    }
}
