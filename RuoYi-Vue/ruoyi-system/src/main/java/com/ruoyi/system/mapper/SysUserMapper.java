package com.ruoyi.system.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.SysChemical;
import com.ruoyi.system.domain.SysMicro;
import com.ruoyi.system.domain.SysPersonSample;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * 用户表 数据层
 *
 * 
 */
@Service
public interface SysUserMapper{
    /**
     * 根据条件分页查询用户列表
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser sysUser);
    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);
    public SysUser selectUserByNickName(String nickName);
    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);
    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);
    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);
    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar   头像地址
     * @return 结果
     */
    public int updateUserAvatar(@Param("userName") String userName,@Param("avatar") String avatar);
    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(@Param("userName") String userName,@Param("password") String password);
    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId);
    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] userIds);
    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public int checkUserNameUnique(String userName);
    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public SysUser checkPhoneUnique(String phonenumber);
    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public SysUser checkEmailUnique(String email);
    /**
     * 查所有角色id包括指定id的用户的用户名和id
     *
     * @param roleId 角色id
     * @return 符合的用户的用户名和id构成的SysUser对象
     */
    ArrayList<SysUser> selectUsernamesAndIdsByRoleId(Long roleId);
    /**
     * 通过用户id查询用户涉及到的所有危化品
     *
     * @param id 用户ID
     * @return
     */
    ArrayList<SysChemical> selectChemicalsOfUserById(Long id);
    /**
     * 通过用户id查询用户涉及到的所有人类样本
     *
     * @param id 用户ID
     * @return
     */
    ArrayList<SysPersonSample> selectPersonSamplesOfUserById(Long id);
    /**
     * 通过用户id查询用户涉及到的所有微生物
     *
     * @param id 用户ID
     * @return
     */
    ArrayList<SysMicro> selectMicrosOfUserById(Long id);
    /**
     * 查询PI手下的用户
     * @param piId pi的id
     * @return
     */
    ArrayList<SysUser> selectUsersOfPiByPiId(Long piId);
    /**
     * 查询PI手下待审核的用户
     * @param piId pi的id
     * @return
     */
    ArrayList<SysUser> selectAuditsOfPiByPiId(Long piId);
    /**
     * 查询所有PI
     * @return 所有PI组成的列表
     */
    ArrayList<SysUser> selectAllPis();
}

