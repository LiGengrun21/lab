package com.ruoyi.file.service;

import java.util.List;
import com.ruoyi.file.domain.DangerConditionFile;

/**
 * 危险事故文件Service接口
 * 
 *
 * @date 2021-09-19
 */
public interface IDangerConditionFileService 
{
    /**
     * 查询危险事故文件
     * 
     * @param dangerConditionFileId 危险事故文件ID
     * @return 危险事故文件
     */
    public DangerConditionFile selectDangerConditionFileById(Long dangerConditionFileId);

    /**
     * 查询危险事故文件列表
     * 
     * @param dangerConditionFile 危险事故文件
     * @return 危险事故文件集合
     */
    public List<DangerConditionFile> selectDangerConditionFileList(DangerConditionFile dangerConditionFile);

    /**
     * 新增危险事故文件
     * 
     * @param dangerConditionFile 危险事故文件
     * @return 结果
     */
    public int insertDangerConditionFile(DangerConditionFile dangerConditionFile);

    /**
     * 修改危险事故文件
     * 
     * @param dangerConditionFile 危险事故文件
     * @return 结果
     */
    public int updateDangerConditionFile(DangerConditionFile dangerConditionFile);

    /**
     * 批量删除危险事故文件
     * 
     * @param dangerConditionFileIds 需要删除的危险事故文件ID
     * @return 结果
     */
    public int deleteDangerConditionFileByIds(Long[] dangerConditionFileIds);

    /**
     * 删除危险事故文件信息
     * 
     * @param dangerConditionFileId 危险事故文件ID
     * @return 结果
     */
    public int deleteDangerConditionFileById(Long dangerConditionFileId);
}
