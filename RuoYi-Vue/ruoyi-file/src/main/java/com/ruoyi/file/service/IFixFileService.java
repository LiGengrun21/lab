package com.ruoyi.file.service;

import java.util.List;
import com.ruoyi.file.domain.FixFile;

/**
 * 设备维保附件Service接口
 * 
 *
 * @date 2021-05-28
 */
public interface IFixFileService 
{
    /**
     * 查询设备维保附件
     * 
     * @param fixFileId 设备维保附件ID
     * @return 设备维保附件
     */
    public FixFile selectFixFileById(Long fixFileId);

    /**
     * 查询设备维保附件列表
     * 
     * @param fixFile 设备维保附件
     * @return 设备维保附件集合
     */
    public List<FixFile> selectFixFileList(FixFile fixFile);

    /**
     * 新增设备维保附件
     * 
     * @param fixFile 设备维保附件
     * @return 结果
     */
    public int insertFixFile(FixFile fixFile);

    /**
     * 修改设备维保附件
     * 
     * @param fixFile 设备维保附件
     * @return 结果
     */
    public int updateFixFile(FixFile fixFile);

    /**
     * 批量删除设备维保附件
     * 
     * @param fixFileIds 需要删除的设备维保附件ID
     * @return 结果
     */
    public int deleteFixFileByIds(Long[] fixFileIds);

    /**
     * 删除设备维保附件信息
     * 
     * @param fixFileId 设备维保附件ID
     * @return 结果
     */
    public int deleteFixFileById(Long fixFileId);
}
