package com.ruoyi.file.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 危险事故文件对象 danger_condition_file
 * 
 * 
 * @date 2021-09-19
 */
public class DangerConditionFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 危险事故文件id */
    private Long dangerConditionFileId;

    /** 危险事故id */
    @Excel(name = "危险事故id")
    private Long dangerConditionId;

    /** 文件名 */
    @Excel(name = "文件名")
    private String dangerConditionFileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String dangerConditionFilePath;

    public void setDangerConditionFileId(Long dangerConditionFileId) 
    {
        this.dangerConditionFileId = dangerConditionFileId;
    }

    public Long getDangerConditionFileId() 
    {
        return dangerConditionFileId;
    }
    public void setDangerConditionId(Long dangerConditionId) 
    {
        this.dangerConditionId = dangerConditionId;
    }

    public Long getDangerConditionId() 
    {
        return dangerConditionId;
    }
    public void setDangerConditionFileName(String dangerConditionFileName) 
    {
        this.dangerConditionFileName = dangerConditionFileName;
    }

    public String getDangerConditionFileName() 
    {
        return dangerConditionFileName;
    }
    public void setDangerConditionFilePath(String dangerConditionFilePath) 
    {
        this.dangerConditionFilePath = dangerConditionFilePath;
    }

    public String getDangerConditionFilePath() 
    {
        return dangerConditionFilePath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dangerConditionFileId", getDangerConditionFileId())
            .append("dangerConditionId", getDangerConditionId())
            .append("dangerConditionFileName", getDangerConditionFileName())
            .append("dangerConditionFilePath", getDangerConditionFilePath())
            .toString();
    }
}
