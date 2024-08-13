package com.ruoyi.file.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 设备报废文件对象 destory_file
 *
 *
 * @date 2021-05-11
 */
public class DestoryFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报废文件id */
    private Long destoryFileId;

    /** 报废文件名 */
    @Excel(name = "报废文件名")
    private String destoryFileName;

    /** 报废文件路径 */
    @Excel(name = "报废文件路径")
    private String destoryFilePath;

    /** 设备报废id */
    private Long destoryId;

    public void setDestoryFileId(Long destoryFileId)
    {
        this.destoryFileId = destoryFileId;
    }

    public Long getDestoryFileId()
    {
        return destoryFileId;
    }
    public void setDestoryFileName(String destoryFileName)
    {
        this.destoryFileName = destoryFileName;
    }

    public String getDestoryFileName()
    {
        return destoryFileName;
    }
    public void setDestoryFilePath(String destoryFilePath)
    {
        this.destoryFilePath = destoryFilePath;
    }

    public void setDestoryId(Long destoryId)
    {
        this.destoryId = destoryId;
    }
    public Long getDestoryId()
    {
        return destoryId;
    }

    public String getDestoryFilePath()
    {
        return destoryFilePath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("destoryId", getDestoryId())
            .append("destoryFileId", getDestoryFileId())
            .append("destoryFileName", getDestoryFileName())
            .append("destoryFilePath", getDestoryFilePath())
            .toString();
    }
}
