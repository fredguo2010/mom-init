package com.iad.mom.prod.service;

import java.util.List;
import com.iad.mom.prod.domain.ProductionPlan;

/**
 * 生产计划Service接口
 * 
 * @author iad
 * @date 2023-11-20
 */
public interface IProductionPlanService 
{
    /**
     * 查询生产计划
     * 
     * @param id 生产计划主键
     * @return 生产计划
     */
    public ProductionPlan selectProductionPlanById(String id);

    /**
     * 查询生产计划列表
     * 
     * @param productionPlan 生产计划
     * @return 生产计划集合
     */
    public List<ProductionPlan> selectProductionPlanList(ProductionPlan productionPlan);

    /**
     * 新增生产计划
     * 
     * @param productionPlan 生产计划
     * @return 结果
     */
    public int insertProductionPlan(ProductionPlan productionPlan);

    /**
     * 修改生产计划
     * 
     * @param productionPlan 生产计划
     * @return 结果
     */
    public int updateProductionPlan(ProductionPlan productionPlan);

    /**
     * 批量删除生产计划
     * 
     * @param ids 需要删除的生产计划主键集合
     * @return 结果
     */
    public int deleteProductionPlanByIds(String[] ids);

    /**
     * 删除生产计划信息
     * 
     * @param id 生产计划主键
     * @return 结果
     */
    public int deleteProductionPlanById(String id);
}
