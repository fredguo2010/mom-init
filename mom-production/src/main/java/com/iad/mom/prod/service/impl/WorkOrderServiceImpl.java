package com.iad.mom.prod.service.impl;

import java.util.List;
import com.iad.mom.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.prod.mapper.WorkOrderMapper;
import com.iad.mom.prod.domain.WorkOrder;
import com.iad.mom.prod.service.IWorkOrderService;

/**
 * 工单管理Service业务层处理
 * 
 * @author kris
 * @date 2023-11-21
 */
@Service
public class WorkOrderServiceImpl implements IWorkOrderService 
{
    @Autowired
    private WorkOrderMapper workOrderMapper;

    /**
     * 查询工单管理
     * 
     * @param id 工单管理主键
     * @return 工单管理
     */
    @Override
    public WorkOrder selectWorkOrderById(String id)
    {
        return workOrderMapper.selectWorkOrderById(id);
    }

    /**
     * 查询工单管理列表
     * 
     * @param workOrder 工单管理
     * @return 工单管理
     */
    @Override
    public List<WorkOrder> selectWorkOrderList(WorkOrder workOrder)
    {
        return workOrderMapper.selectWorkOrderList(workOrder);
    }

    /**
     * 新增工单管理
     * 
     * @param workOrder 工单管理
     * @return 结果
     */
    @Override
    public int insertWorkOrder(WorkOrder workOrder)
    {
        workOrder.setCreateTime(DateUtils.getNowDate());
        return workOrderMapper.insertWorkOrder(workOrder);
    }

    /**
     * 修改工单管理
     * 
     * @param workOrder 工单管理
     * @return 结果
     */
    @Override
    public int updateWorkOrder(WorkOrder workOrder)
    {
        workOrder.setUpdateTime(DateUtils.getNowDate());
        return workOrderMapper.updateWorkOrder(workOrder);
    }

    /**
     * 批量删除工单管理
     * 
     * @param ids 需要删除的工单管理主键
     * @return 结果
     */
    @Override
    public int deleteWorkOrderByIds(String[] ids)
    {
        return workOrderMapper.deleteWorkOrderByIds(ids);
    }

    /**
     * 删除工单管理信息
     * 
     * @param id 工单管理主键
     * @return 结果
     */
    @Override
    public int deleteWorkOrderById(String id)
    {
        return workOrderMapper.deleteWorkOrderById(id);
    }
}
