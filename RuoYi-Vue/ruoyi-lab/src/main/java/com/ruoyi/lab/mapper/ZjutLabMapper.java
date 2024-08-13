package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.ZjutLab;
import org.apache.ibatis.annotations.Param;

/**
 * 实验室列表Mapper接口
 *
 * 
 * @date 2021-05-04
 */
public interface ZjutLabMapper
{
    /**
     * 查询实验室列表
     *
     * @param labId 实验室列表ID
     * @return 实验室列表
     */
    public ZjutLab selectZjutLabById(Long labId);

    /**
     * 查询实验室列表列表
     *
     * @param zjutLab 实验室列表
     * @return 实验室列表集合
     */
    public List<ZjutLab> selectZjutLabList(ZjutLab zjutLab);

    /**
     * 新增实验室列表
     *
     * @param zjutLab 实验室列表
     * @return 结果
     */
    public int insertZjutLab(ZjutLab zjutLab);

    /**
     * 修改实验室列表
     *
     * @param zjutLab 实验室列表
     * @return 结果
     */
    public int updateZjutLab(ZjutLab zjutLab);

    /**
     * 删除实验室列表
     *
     * @param labId 实验室列表ID
     * @return 结果
     */
    public int deleteZjutLabById(Long labId);

    /**
     * 批量删除实验室列表
     *
     * @param labIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteZjutLabByIds(Long[] labIds);

    /**
     *
     * 查询实验室与设备的联表
     *
     */
    public List<ZjutLab> selectZjutLabEquipmentList(ZjutLab zjutLab);
    /**
     * 查询某个准确时间指定房间的总数
     */
    public int selectAmountOfRoomByRoomType(@Param("roomType") String roomType);
}
