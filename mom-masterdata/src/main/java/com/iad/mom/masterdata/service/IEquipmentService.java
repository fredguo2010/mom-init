package com.iad.mom.masterdata.service;

import java.util.List;
import com.iad.mom.masterdata.domain.Equipment;

/**
 * 设备Service接口
 * 
 * @author ra
 * @date 2023-10-08
 */
public interface IEquipmentService 
{
    /**
     * 查询设备
     * 
     * @param id 设备主键
     * @return 设备
     */
    public Equipment selectEquipmentById(Long id);

    /**
     * 查询设备列表
     * 
     * @param equipment 设备
     * @return 设备集合
     */
    public List<Equipment> selectEquipmentList(Equipment equipment);

    /**
     * 新增设备
     * 
     * @param equipment 设备
     * @return 结果
     */
    public int insertEquipment(Equipment equipment);

    /**
     * 修改设备
     * 
     * @param equipment 设备
     * @return 结果
     */
    public int updateEquipment(Equipment equipment);

    /**
     * 批量删除设备
     * 
     * @param ids 需要删除的设备主键集合
     * @return 结果
     */
    public int deleteEquipmentByIds(Long[] ids);

    /**
     * 删除设备信息
     * 
     * @param id 设备主键
     * @return 结果
     */
    public int deleteEquipmentById(Long id);
}
