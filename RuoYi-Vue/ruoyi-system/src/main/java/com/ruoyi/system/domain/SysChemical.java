package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 危化品对象 sys_chemical
 * 
 * 
 * @date 2021-08-06
 */
public class SysChemical extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long chemicalId;

    /** 化学品类型 */
    @Excel(name = "化学品类型")
    private String chemicalType;
    public SysChemical(){
    }
    public void setChemicalId(Long chemicalId)
    {
        this.chemicalId = chemicalId;
    }

    public Long getChemicalId() 
    {
        return chemicalId;
    }
    public void setChemicalType(String chemicalType) 
    {
        this.chemicalType = chemicalType;
    }

    public String getChemicalType() 
    {
        return chemicalType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("chemicalId", getChemicalId())
            .append("chemicalType", getChemicalType())
            .toString();
    }
}
