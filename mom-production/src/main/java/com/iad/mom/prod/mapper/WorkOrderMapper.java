package com.iad.mom.prod.mapper;

import java.util.List;
import com.iad.mom.prod.domain.WorkOrder;

/**
 * 工单管理Mapper接口
 * 
 * @author kris
 * @date 2023-11-21
 */
public interface WorkOrderMapper 
{
    /**
     * 查询工单管理
     * 
     * @param id 工单管理主键
     * @return 工单管理
     */
    public WorkOrder selectWorkOrderById(String id);

    /**
     * 查询工单管理列表
     * 
     * @param workOrder 工单管理
     * @return 工单管理集合
     */
    public List<WorkOrder> selectWorkOrderList(WorkOrder workOrder);

    /**
     * 新增工单管理
     * 
     * @param workOrder 工单管理
     * @return 结果
     */
    public int insertWorkOrder(WorkOrder workOrder);

    /**
     * 修改工单管理
     * 
     * @param workOrder 工单管理
     * @return 结果
     */
    public int updateWorkOrder(WorkOrder workOrder);

    /**
     * 删除工单管理
     * 
     * @param id 工单管理主键
     * @return 结果
     */
    public int deleteWorkOrderById(String id);

    /**
     * 批量删除工单管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkOrderByIds(String[] ids);
}
