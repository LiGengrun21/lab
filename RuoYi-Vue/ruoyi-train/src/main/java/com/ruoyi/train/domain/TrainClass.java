package com.ruoyi.train.domain;

import com.ruoyi.common.core.domain.entity.SysRole;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 课程对象 train_class
 *
 */
public class TrainClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程id */
    private Long classId;

    private Long roleId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String className;

    /** 课程备注 */
    @Excel(name = "课程备注")
    private String classTip;

    /*private List<SysRole> sysRole;*/

    public void setClassId(Long classId)
    {
        this.classId = classId;
    }

    public Long getClassId()
    {
        return classId;
    }
    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassTip(String classTip)
    {
        this.classTip = classTip;
    }

    public String getClassTip()
    {
        return classTip;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /*public List<SysRole> getSysRole() {
        return sysRole;
    }

    public void setSysRole(List<SysRole> sysRole) {
        this.sysRole = sysRole;
    }*/

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("classId", getClassId())
            .append("className", getClassName())
            .append("classTip", getClassTip())
            .append("roleId",getRoleId())
            //.append("sysRole",getSysRole())
            .toString();
    }
}
