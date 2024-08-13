package com.ruoyi.file.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.file.mapper.DangerConditionFileMapper;
import com.ruoyi.file.domain.DangerConditionFile;
import com.ruoyi.file.service.IDangerConditionFileService;

/**
 * 危险事故文件Service业务层处理
 * 
 * 
 * @date 2021-09-19
 */
@Service
public class DangerConditionFileServiceImpl implements IDangerConditionFileService 
{
    @Autowired
    private DangerConditionFileMapper dangerConditionFileMapper;

    /**
     * 查询危险事故文件
     * 
     * @param dangerConditionFileId 危险事故文件ID
     * @return 危险事故文件
     */
    @Override
    public DangerConditionFile selectDangerConditionFileById(Long dangerConditionFileId)
    {
        return dangerConditionFileMapper.selectDangerConditionFileById(dangerConditionFileId);
    }

    /**
     * 查询危险事故文件列表
     * 
     * @param dangerConditionFile 危险事故文件
     * @return 危险事故文件
     */
    @Override
    public List<DangerConditionFile> selectDangerConditionFileList(DangerConditionFile dangerConditionFile)
    {
        return dangerConditionFileMapper.selectDangerConditionFileList(dangerConditionFile);
    }

    /**
     * 新增危险事故文件
     * 
     * @param dangerConditionFile 危险事故文件
     * @return 结果
     */
    @Override
    public int insertDangerConditionFile(DangerConditionFile dangerConditionFile)
    {
        return dangerConditionFileMapper.insertDangerConditionFile(dangerConditionFile);
    }

    /**
     * 修改危险事故文件
     * 
     * @param dangerConditionFile 危险事故文件
     * @return 结果
     */
    @Override
    public int updateDangerConditionFile(DangerConditionFile dangerConditionFile)
    {
        return dangerConditionFileMapper.updateDangerConditionFile(dangerConditionFile);
    }

    /**
     * 批量删除危险事故文件
     * 
     * @param dangerConditionFileIds 需要删除的危险事故文件ID
     * @return 结果
     */
    @Override
    public int deleteDangerConditionFileByIds(Long[] dangerConditionFileIds)
    {
        return dangerConditionFileMapper.deleteDangerConditionFileByIds(dangerConditionFileIds);
    }

    /**
     * 删除危险事故文件信息
     * 
     * @param dangerConditionFileId 危险事故文件ID
     * @return 结果
     */
    @Override
    public int deleteDangerConditionFileById(Long dangerConditionFileId)
    {
        return dangerConditionFileMapper.deleteDangerConditionFileById(dangerConditionFileId);
    }
}
