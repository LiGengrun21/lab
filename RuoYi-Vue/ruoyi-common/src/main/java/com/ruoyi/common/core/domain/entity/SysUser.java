package com.ruoyi.common.core.domain.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户对象 sys_user
 */
public class SysUser extends BaseEntity{
    private static final long serialVersionUID=1L;
    /** 用户ID */
    @Excel(name="用户序号", cellType=ColumnType.NUMERIC, prompt="用户编号")
    private Long userId;
    /** 部门ID */
    @Excel(name="部门编号", type=Type.IMPORT)
    private Long deptId;
    /** 用户账号 */
    @Excel(name="登录名称")
    private String userName;
    /** 用户昵称 */
    @Excel(name="用户名称")
    private String nickName;
    /** 用户邮箱 */
    @Excel(name="用户邮箱")
    private String email;
    /** 手机号码 */
    @Excel(name="手机号码")
    private String phonenumber;
    /** 用户性别 */
    @Excel(name="用户性别", readConverterExp="0=男,1=女,2=未知")
    private String sex;
    /** 用户头像 */
    private String avatar;
    /** 密码 */
    private String password;
    /**
     * 来自地
     */
    private String comeFrom;
    /** 此用户pi的Id */
    private Long piId;
    /** 此用户pi的username */
    private String piUsername;
    /** 盐加密 */
    private String salt;
    /** 帐号状态（0正常 1停用） */
    @Excel(name="帐号状态", readConverterExp="0=正常,1=停用")
    private String status;
    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;
    /** 工号*/
    private String jobNumber;
    /** 所在学校 */
    private String school;
    /** 学位 */
    private String degree;
    /** 研究期限 */
    private Date studyDuration;
    /** 项目研究简介 */
    private String studyIntroduction;
    /** 涉及微生物 */
    private Boolean isMicro;
    /** 涉及人类样本 */
    private Boolean isPersonSample;
    /** 涉及危化品*/
    private Boolean isChemical;
    /** 最后登录IP */
    @Excel(name="最后登录IP", type=Type.EXPORT)
    private String loginIp;
    /** 最后登录时间 */
    @Excel(name="最后登录时间", width=30, dateFormat="yyyy-MM-dd HH:mm:ss", type=Type.EXPORT)
    private Date loginDate;
    /** 部门对象 */
    @Excels({@Excel(name="部门名称", targetAttr="deptName", type=Type.EXPORT),@Excel(name="部门负责人", targetAttr="leader", type=Type.EXPORT)})
    private SysDept dept;
    /** 角色对象 */
    private List<SysRole> roles;
    /** 角色组 */
    private Long[] roleIds;
    /** 岗位组 */
    private Long[] postIds;
    /** 账户过期时间 **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name="账户过期时间", width=30, dateFormat="yyyy-MM-dd HH:mm:ss", type=Type.EXPORT)
    private Date expiredTime;

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public SysUser(){
    }
    public SysUser(Long userId){
        this.userId=userId;
    }
    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId=userId;
    }
    public boolean isAdmin(){
        return isAdmin(this.userId);
    }
    public static boolean isAdmin(Long userId){
        return userId!=null&&1L==userId;
    }
    public Long getDeptId(){
        return deptId;
    }
    public void setDeptId(Long deptId){
        this.deptId=deptId;
    }
    @Size(min=0, max=30, message="用户昵称长度不能超过30个字符")
    public String getNickName(){
        return nickName;
    }
    public void setNickName(String nickName){
        this.nickName=nickName;
    }
    @NotBlank(message="用户账号不能为空")
    @Size(min=0, max=30, message="用户账号长度不能超过30个字符")
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    @Email(message="邮箱格式不正确")
    @Size(min=0, max=50, message="邮箱长度不能超过50个字符")
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    @Size(min=0, max=11, message="手机号码长度不能超过11个字符")
    public String getPhonenumber(){
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber){
        this.phonenumber=phonenumber;
    }
    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public String getAvatar(){
        return avatar;
    }
    public void setAvatar(String avatar){
        this.avatar=avatar;
    }
    @JsonIgnore
    @JsonProperty
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getSalt(){
        return salt;
    }
    public void setSalt(String salt){
        this.salt=salt;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public String getDelFlag(){
        return delFlag;
    }
    public void setDelFlag(String delFlag){
        this.delFlag=delFlag;
    }
    public String getLoginIp(){
        return loginIp;
    }
    public void setLoginIp(String loginIp){
        this.loginIp=loginIp;
    }
    public Date getLoginDate(){
        return loginDate;
    }
    public void setLoginDate(Date loginDate){
        this.loginDate=loginDate;
    }
    public SysDept getDept(){
        return dept;
    }
    public void setDept(SysDept dept){
        this.dept=dept;
    }
    public List<SysRole> getRoles(){
        return roles;
    }
    public void setRoles(List<SysRole> roles){
        this.roles=roles;
    }
    public Long[] getRoleIds(){
        return roleIds;
    }
    public void setRoleIds(Long[] roleIds){
        this.roleIds=roleIds;
    }
    public Long[] getPostIds(){
        return postIds;
    }
    public void setPostIds(Long[] postIds){
        this.postIds=postIds;
    }
    public Long getPiId(){
        return piId;
    }
    public void setPiId(Long piId){
        this.piId=piId;
    }
    public String getPiUsername(){
        return piUsername;
    }
    public void setPiUsername(String piUsername){
        this.piUsername=piUsername;
    }
    public String getJobNumber(){
        return jobNumber;
    }
    public void setJobNumber(String jobNumber){
        this.jobNumber=jobNumber;
    }
    public String getSchool(){
        return school;
    }
    public void setSchool(String school){
        this.school=school;
    }
    public String getDegree(){
        return degree;
    }
    public void setDegree(String degree){
        this.degree=degree;
    }
    public Date getStudyDuration(){
        return studyDuration;
    }
    public void setStudyDuration(Date studyDuration){
        this.studyDuration=studyDuration;
    }
    public String getStudyIntroduction(){
        return studyIntroduction;
    }
    public void setStudyIntroduction(String studyIntroduction){
        this.studyIntroduction=studyIntroduction;
    }
    public Boolean getMicro(){
        return isMicro;
    }
    public void setMicro(Boolean micro){
        isMicro=micro;
    }
    public Boolean getPersonSample(){
        return isPersonSample;
    }
    public void setPersonSample(Boolean personSample){
        isPersonSample=personSample;
    }
    public Boolean getChemical(){
        return isChemical;
    }
    public void setChemical(Boolean chemical){
        isChemical=chemical;
    }
    public String getComeFrom(){
        return comeFrom;
    }
    public void setComeFrom(String comeFrom){
        this.comeFrom=comeFrom;
    }
    @Override
    public String toString(){
        return "SysUser{"+"userId="+userId+", deptId="+deptId+", userName='"+userName+'\''+", nickName='"+nickName+'\''+", email='"+email+'\''+", phonenumber='"+phonenumber+'\''+", sex='"+sex+'\''+", avatar='"+avatar+'\''+", password='"+password+'\''+", comeFrom='"+comeFrom+'\''+", piId="+piId+", piUsername='"+piUsername+'\''+", salt='"+salt+'\''+", status='"+status+'\''+", delFlag='"+delFlag+'\''+", jobNumber='"+jobNumber+'\''+", school='"+school+'\''+", degree='"+degree+'\''+", studyDuration="+studyDuration+", studyIntroduction='"+studyIntroduction+'\''+", isMicro="+isMicro+", isPersonSample="+isPersonSample+", isChemical="+isChemical+", loginIp='"+loginIp+'\''+", loginDate="+loginDate+", dept="+dept+", roles="+roles+", roleIds="+Arrays.toString(roleIds)+", postIds="+Arrays.toString(postIds)+'}';
    }
}
