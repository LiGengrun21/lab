package com.ruoyi.file.service;

import java.util.List;
import com.ruoyi.file.domain.DestoryFile;

/**
 * 设备报废文件Service接口
 * 
 *
 * @date 2021-05-11
 */
public interface IDestoryFileService 
{
    /**
     * 查询设备报废文件
     * 
     * @param destoryFileId 设备报废文件ID
     * @return 设备报废文件
     */
    public DestoryFile selectDestoryFileById(Long destoryFileId);

    /**
     * 查询设备报废文件列表
     * 
     * @param destoryFile 设备报废文件
     * @return 设备报废文件集合
     */
    public List<DestoryFile> selectDestoryFileList(DestoryFile destoryFile);

    /**
     * 新增设备报废文件
     * 
     * @param destoryFile 设备报废文件
     * @return 结果
     */
    public int insertDestoryFile(DestoryFile destoryFile);

    /**
     * 修改设备报废文件
     * 
     * @param destoryFile 设备报废文件
     * @return 结果
     */
    public int updateDestoryFile(DestoryFile destoryFile);

    /**
     * 批量删除设备报废文件
     * 
     * @param destoryFileIds 需要删除的设备报废文件ID
     * @return 结果
     */
    public int deleteDestoryFileByIds(Long[] destoryFileIds);

    /**
     * 删除设备报废文件信息
     * 
     * @param destoryFileId 设备报废文件ID
     * @return 结果
     */
    public int deleteDestoryFileById(Long destoryFileId);
}
