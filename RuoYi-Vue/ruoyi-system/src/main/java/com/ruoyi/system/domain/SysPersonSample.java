package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人类样本对象 sys_person_sample
 * 
 *
 * @date 2021-08-06
 */
public class SysPersonSample extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long sampleId;

    /** 伦理编号 */
    @Excel(name = "伦理编号")
    private String sampleNumber;

    public void setSampleId(Long sampleId) 
    {
        this.sampleId = sampleId;
    }

    public Long getSampleId() 
    {
        return sampleId;
    }
    public void setSampleNumber(String sampleNumber) 
    {
        this.sampleNumber = sampleNumber;
    }

    public String getSampleNumber() 
    {
        return sampleNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sampleId", getSampleId())
            .append("sampleNumber", getSampleNumber())
            .toString();
    }
}
