package com.ruoyi.equipment.service.impl;

import java.util.List;

//import com.tuya.connector.api.annotations.Body;
//import com.tuya.connector.api.annotations.GET;
//import com.tuya.connector.api.annotations.POST;
//import com.tuya.connector.api.annotations.Path;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.equipment.domain.EquipmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.equipment.domain.EquipmentSubscribe;
import com.ruoyi.equipment.mapper.ZjutEquipmentMapper;
import com.ruoyi.equipment.domain.ZjutEquipment;
import com.ruoyi.equipment.service.IZjutEquipmentService;
/**
 * 设备Service业务层处理
 *
 * 
 * @date 2021-04-25
 */
@Service
public class ZjutEquipmentServiceImpl implements IZjutEquipmentService
{
    @Autowired
    private ZjutEquipmentMapper zjutEquipmentMapper;

    /**
     * 查询设备
     *
     * @param equipmentId 设备ID
     * @return 设备
     */
    @Override
    public ZjutEquipment selectZjutEquipmentById(Long equipmentId)
    {
        return zjutEquipmentMapper.selectZjutEquipmentById(equipmentId);
    }

    @Override
    public ZjutEquipment selectZjutEquipmentByDeviceId(String equipmentDeviceId) {
        return zjutEquipmentMapper.selectZjutEquipmentByDeviceId(equipmentDeviceId);
    }

    /**
     * 查询设备及其白名单
     *
     * @param equipmentId 设备ID
     * @return 设备
     */
    @Override
    public List<ZjutEquipment> selectEquipmentAndWhitelist(Long equipmentId) {
        return zjutEquipmentMapper.selectEquipmentAndWhitelist(equipmentId);
    }

    /**
     * 查询设备列表
     *
     * @param zjutEquipment 设备
     * @return 设备
     */
    @Override
    public List<ZjutEquipment> selectZjutEquipmentList(ZjutEquipment zjutEquipment)
    {
        return zjutEquipmentMapper.selectZjutEquipmentList(zjutEquipment);
    }

    /**
     * 查询可预约的设备列表
     *
     * @param zjutEquipment 设备
     * @return 设备
     */
    @Override
    public List<ZjutEquipment> selectZjutEquipmentSubscribeList(ZjutEquipment zjutEquipment)
    {
        return zjutEquipmentMapper.selectZjutEquipmentSubscribeList(zjutEquipment);
    }

    /**
     * 新增设备
     *
     * @param zjutEquipment 设备
     * @return 结果
     */
    @Transactional
    @Override
    public int insertZjutEquipment(ZjutEquipment zjutEquipment)
    {
        int rows = zjutEquipmentMapper.insertZjutEquipment(zjutEquipment);
        //新增设备-管理员关系
        insertEquipmentManager(zjutEquipment);
        insertEquipmentSubscribe(zjutEquipment);
        return rows;
    }

    /**
     * 修改设备
     *
     * @param zjutEquipment 设备
     * @return 结果
     */
    @Transactional
    @Override
    public int updateZjutEquipment(ZjutEquipment zjutEquipment)
    {
        //删除该设备管理员关系
        deleteEquipmentManager(zjutEquipment);
        //添加该设备管理员关系
        insertEquipmentManager(zjutEquipment);
        return zjutEquipmentMapper.updateZjutEquipment(zjutEquipment);
    }

    /**
     * 修改设备图片
     *
     * @param  equipmentId, String equipmentPic
     * @return 结果
     */
    public boolean updateZjutEquipmentPic(Long equipmentId, String equipmentPic){
        return zjutEquipmentMapper.updateZjutEquipmentPic(equipmentId, equipmentPic) > 0;
    }

    /**
     * 批量删除设备
     *
     * @param equipmentIds 需要删除的设备ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteZjutEquipmentByIds(Long[] equipmentIds)
    {
        zjutEquipmentMapper.deleteEquipmentSubscribeByEquipmentIds(equipmentIds);
        zjutEquipmentMapper.deleteEquipmentManagerByEquipmentIds(equipmentIds);
        return zjutEquipmentMapper.deleteZjutEquipmentByIds(equipmentIds);
    }

    /**
     * 删除设备信息
     *
     * @param equipmentId 设备ID
     * @return 结果
     */
    @Override
    public int deleteZjutEquipmentById(Long equipmentId)
    {
        zjutEquipmentMapper.deleteEquipmentSubscribeByEquipmentId(equipmentId);
        zjutEquipmentMapper.deleteEquipmentManagerByEquipmentId(equipmentId);
        return zjutEquipmentMapper.deleteZjutEquipmentById(equipmentId);
    }

    /**
     * 新增设备预约信息
     *
     * @param zjutEquipment 设备对象
     */
    public void insertEquipmentSubscribe(ZjutEquipment zjutEquipment)
    {
        List<EquipmentSubscribe> equipmentSubscribeList = zjutEquipment.getEquipmentSubscribeList();
        Long equipmentId = zjutEquipment.getEquipmentId();
        String equipmentName = zjutEquipment.getEquipmentName();
        if (StringUtils.isNotNull(equipmentSubscribeList))
        {
            List<EquipmentSubscribe> list = new ArrayList<EquipmentSubscribe>();
            for (EquipmentSubscribe equipmentSubscribe : equipmentSubscribeList)
            {
                equipmentSubscribe.setEquipmentName(equipmentName);
                equipmentSubscribe.setEquipmentId(equipmentId);
                list.add(equipmentSubscribe);
            }
            if (list.size() > 0)
            {
                zjutEquipmentMapper.batchEquipmentSubscribe(list);
            }
        }
    }

    /**
     * 新增设备管理员列表
     *
     * @param zjutEquipment 设备对象
     */
    public void insertEquipmentManager(ZjutEquipment zjutEquipment)
    {
        List<Long> equipmentManagerList = zjutEquipment.getManagerIds();
        Long equipmentId = zjutEquipment.getEquipmentId();
        //String equipmentName = zjutEquipment.getEquipmentName();
        if (StringUtils.isNotNull(equipmentManagerList))
        {
            List<EquipmentManager> list = new ArrayList<EquipmentManager>();
            for (Long managerId : equipmentManagerList)
            {
                EquipmentManager equipmentManager = new EquipmentManager();
                equipmentManager.setEquipmentId(equipmentId);
                equipmentManager.setUserId(managerId);
                list.add(equipmentManager);
            }
            if (list.size() > 0)
            {
                zjutEquipmentMapper.batchEquipmentManager(list);
            }
        }
    }

    /**
     * 删除设备管理员列表
     *
     * @param zjutEquipment 设备对象
     */
    public void deleteEquipmentManager(ZjutEquipment zjutEquipment){
        zjutEquipmentMapper.deleteEquipmentManagerByEquipmentId(zjutEquipment.getEquipmentId());
    }

    @Override
    public int selectAmountByCondition(ZjutEquipment equipment){
        return zjutEquipmentMapper.selectAmountByCondition(equipment);
    }
}
