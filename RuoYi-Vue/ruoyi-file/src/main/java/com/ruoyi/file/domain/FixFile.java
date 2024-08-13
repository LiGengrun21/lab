package com.ruoyi.file.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备维保附件对象 fix_file
 * 
 * 
 * @date 2021-05-28
 */
public class FixFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备维保附件id */
    private Long fixFileId;

    /** 设备维保id */
    @Excel(name = "设备维保id")
    private Long fixId;

    /** 维保文件名 */
    @Excel(name = "维保文件名")
    private String fixFileName;

    /** 维保文件路径 */
    @Excel(name = "维保文件路径")
    private String fixFilePath;

    public void setFixFileId(Long fixFileId) 
    {
        this.fixFileId = fixFileId;
    }

    public Long getFixFileId() 
    {
        return fixFileId;
    }
    public void setFixId(Long fixId) 
    {
        this.fixId = fixId;
    }

    public Long getFixId() 
    {
        return fixId;
    }
    public void setFixFileName(String fixFileName) 
    {
        this.fixFileName = fixFileName;
    }

    public String getFixFileName() 
    {
        return fixFileName;
    }
    public void setFixFilePath(String fixFilePath) 
    {
        this.fixFilePath = fixFilePath;
    }

    public String getFixFilePath() 
    {
        return fixFilePath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fixFileId", getFixFileId())
            .append("fixId", getFixId())
            .append("fixFileName", getFixFileName())
            .append("fixFilePath", getFixFilePath())
            .toString();
    }
}
