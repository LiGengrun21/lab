package com.ruoyi.file.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.file.mapper.FixFileMapper;
import com.ruoyi.file.domain.FixFile;
import com.ruoyi.file.service.IFixFileService;

/**
 * 设备维保附件Service业务层处理
 * 
 *
 * @date 2021-05-28
 */
@Service
public class FixFileServiceImpl implements IFixFileService 
{
    @Autowired
    private FixFileMapper fixFileMapper;

    /**
     * 查询设备维保附件
     * 
     * @param fixFileId 设备维保附件ID
     * @return 设备维保附件
     */
    @Override
    public FixFile selectFixFileById(Long fixFileId)
    {
        return fixFileMapper.selectFixFileById(fixFileId);
    }

    /**
     * 查询设备维保附件列表
     * 
     * @param fixFile 设备维保附件
     * @return 设备维保附件
     */
    @Override
    public List<FixFile> selectFixFileList(FixFile fixFile)
    {
        return fixFileMapper.selectFixFileList(fixFile);
    }

    /**
     * 新增设备维保附件
     * 
     * @param fixFile 设备维保附件
     * @return 结果
     */
    @Override
    public int insertFixFile(FixFile fixFile)
    {
        return fixFileMapper.insertFixFile(fixFile);
    }

    /**
     * 修改设备维保附件
     * 
     * @param fixFile 设备维保附件
     * @return 结果
     */
    @Override
    public int updateFixFile(FixFile fixFile)
    {
        return fixFileMapper.updateFixFile(fixFile);
    }

    /**
     * 批量删除设备维保附件
     * 
     * @param fixFileIds 需要删除的设备维保附件ID
     * @return 结果
     */
    @Override
    public int deleteFixFileByIds(Long[] fixFileIds)
    {
        return fixFileMapper.deleteFixFileByIds(fixFileIds);
    }

    /**
     * 删除设备维保附件信息
     * 
     * @param fixFileId 设备维保附件ID
     * @return 结果
     */
    @Override
    public int deleteFixFileById(Long fixFileId)
    {
        return fixFileMapper.deleteFixFileById(fixFileId);
    }
}
