package com.ruoyi.file.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.file.mapper.DestoryFileMapper;
import com.ruoyi.file.domain.DestoryFile;
import com.ruoyi.file.service.IDestoryFileService;

/**
 * 设备报废文件Service业务层处理
 * 
 * 
 * @date 2021-05-11
 */
@Service
public class DestoryFileServiceImpl implements IDestoryFileService 
{
    @Autowired
    private DestoryFileMapper destoryFileMapper;

    /**
     * 查询设备报废文件
     * 
     * @param destoryFileId 设备报废文件ID
     * @return 设备报废文件
     */
    @Override
    public DestoryFile selectDestoryFileById(Long destoryFileId)
    {
        return destoryFileMapper.selectDestoryFileById(destoryFileId);
    }

    /**
     * 查询设备报废文件列表
     * 
     * @param destoryFile 设备报废文件
     * @return 设备报废文件
     */
    @Override
    public List<DestoryFile> selectDestoryFileList(DestoryFile destoryFile)
    {
        return destoryFileMapper.selectDestoryFileList(destoryFile);
    }

    /**
     * 新增设备报废文件
     * 
     * @param destoryFile 设备报废文件
     * @return 结果
     */
    @Override
    public int insertDestoryFile(DestoryFile destoryFile)
    {
        return destoryFileMapper.insertDestoryFile(destoryFile);
    }

    /**
     * 修改设备报废文件
     * 
     * @param destoryFile 设备报废文件
     * @return 结果
     */
    @Override
    public int updateDestoryFile(DestoryFile destoryFile)
    {
        return destoryFileMapper.updateDestoryFile(destoryFile);
    }

    /**
     * 批量删除设备报废文件
     * 
     * @param destoryFileIds 需要删除的设备报废文件ID
     * @return 结果
     */
    @Override
    public int deleteDestoryFileByIds(Long[] destoryFileIds)
    {
        return destoryFileMapper.deleteDestoryFileByIds(destoryFileIds);
    }

    /**
     * 删除设备报废文件信息
     * 
     * @param destoryFileId 设备报废文件ID
     * @return 结果
     */
    @Override
    public int deleteDestoryFileById(Long destoryFileId)
    {
        return destoryFileMapper.deleteDestoryFileById(destoryFileId);
    }
}
