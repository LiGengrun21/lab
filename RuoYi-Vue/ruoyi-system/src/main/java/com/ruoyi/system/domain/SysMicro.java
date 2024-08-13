package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微生物对象 sys_micro
 * 
 * 
 * @date 2021-08-06
 */
public class SysMicro extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long microId;

    /** 微生物名称 */
    @Excel(name = "微生物名称")
    private String microName;

    /** 微生物危害程度 */
    @Excel(name = "微生物危害程度")
    private String microDamage;

    /** 微生物药物 */
    @Excel(name = "微生物药物")
    private String microMedicinal;

    /** 微生物疫苗 */
    @Excel(name = "微生物疫苗")
    private String microVaccine;

    public void setMicroId(Long microId) 
    {
        this.microId = microId;
    }

    public Long getMicroId() 
    {
        return microId;
    }
    public void setMicroName(String microName) 
    {
        this.microName = microName;
    }

    public String getMicroName() 
    {
        return microName;
    }
    public void setMicroDamage(String microDamage) 
    {
        this.microDamage = microDamage;
    }

    public String getMicroDamage() 
    {
        return microDamage;
    }
    public void setmicroMedicinal(String microMedicinal) 
    {
        this.microMedicinal = microMedicinal;
    }

    public String getmicroMedicinal() 
    {
        return microMedicinal;
    }
    public void setmicroVaccine(String microVaccine) 
    {
        this.microVaccine = microVaccine;
    }

    public String getmicroVaccine() 
    {
        return microVaccine;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("microId", getMicroId())
            .append("microName", getMicroName())
            .append("microDamage", getMicroDamage())
            .append("microMedicinal", getmicroMedicinal())
            .append("microVaccine", getmicroVaccine())
            .toString();
    }
}
