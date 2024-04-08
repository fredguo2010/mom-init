package com.iad.mom.masterdata.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.masterdata.mapper.EquipmentMapper;
import com.iad.mom.masterdata.domain.Equipment;
import com.iad.mom.masterdata.service.IEquipmentService;

/**
 * 设备Service业务层处理
 * 
 * @author ra
 * @date 2023-10-08
 */
@Service
public class EquipmentServiceImpl implements IEquipmentService 
{
    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * 查询设备
     * 
     * @param id 设备主键
     * @return 设备
     */
    @Override
    public Equipment selectEquipmentById(Long id)
    {
        return equipmentMapper.selectEquipmentById(id);
    }

    /**
     * 查询设备列表
     * 
     * @param equipment 设备
     * @return 设备
     */
    @Override
    public List<Equipment> selectEquipmentList(Equipment equipment)
    {
        return equipmentMapper.selectEquipmentList(equipment);
    }

    /**
     * 新增设备
     * 
     * @param equipment 设备
     * @return 结果
     */
    @Override
    public int insertEquipment(Equipment equipment)
    {
        return equipmentMapper.insertEquipment(equipment);
    }

    /**
     * 修改设备
     * 
     * @param equipment 设备
     * @return 结果
     */
    @Override
    public int updateEquipment(Equipment equipment)
    {
        return equipmentMapper.updateEquipment(equipment);
    }

    /**
     * 批量删除设备
     * 
     * @param ids 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentByIds(Long[] ids)
    {
        return equipmentMapper.deleteEquipmentByIds(ids);
    }

    /**
     * 删除设备信息
     * 
     * @param id 设备主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentById(Long id)
    {
        return equipmentMapper.deleteEquipmentById(id);
    }
}
