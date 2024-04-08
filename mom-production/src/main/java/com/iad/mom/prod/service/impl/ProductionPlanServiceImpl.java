package com.iad.mom.prod.service.impl;

import java.util.List;
import com.iad.mom.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.prod.mapper.ProductionPlanMapper;
import com.iad.mom.prod.domain.ProductionPlan;
import com.iad.mom.prod.service.IProductionPlanService;

/**
 * 生产计划Service业务层处理
 * 
 * @author iad
 * @date 2023-11-20
 */
@Service
public class ProductionPlanServiceImpl implements IProductionPlanService 
{
    @Autowired
    private ProductionPlanMapper productionPlanMapper;

    /**
     * 查询生产计划
     * 
     * @param id 生产计划主键
     * @return 生产计划
     */
    @Override
    public ProductionPlan selectProductionPlanById(String id)
    {
        return productionPlanMapper.selectProductionPlanById(id);
    }

    /**
     * 查询生产计划列表
     * 
     * @param productionPlan 生产计划
     * @return 生产计划
     */
    @Override
    public List<ProductionPlan> selectProductionPlanList(ProductionPlan productionPlan)
    {
        return productionPlanMapper.selectProductionPlanList(productionPlan);
    }

    /**
     * 新增生产计划
     * 
     * @param productionPlan 生产计划
     * @return 结果
     */
    @Override
    public int insertProductionPlan(ProductionPlan productionPlan)
    {
        productionPlan.setCreateTime(DateUtils.getNowDate());
        return productionPlanMapper.insertProductionPlan(productionPlan);
    }

    /**
     * 修改生产计划
     * 
     * @param productionPlan 生产计划
     * @return 结果
     */
    @Override
    public int updateProductionPlan(ProductionPlan productionPlan)
    {
        productionPlan.setUpdateTime(DateUtils.getNowDate());
        return productionPlanMapper.updateProductionPlan(productionPlan);
    }

    /**
     * 批量删除生产计划
     * 
     * @param ids 需要删除的生产计划主键
     * @return 结果
     */
    @Override
    public int deleteProductionPlanByIds(String[] ids)
    {
        return productionPlanMapper.deleteProductionPlanByIds(ids);
    }

    /**
     * 删除生产计划信息
     * 
     * @param id 生产计划主键
     * @return 结果
     */
    @Override
    public int deleteProductionPlanById(String id)
    {
        return productionPlanMapper.deleteProductionPlanById(id);
    }
}
