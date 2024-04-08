package com.iad.mom.masterdata.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.masterdata.mapper.ProductionLineMapper;
import com.iad.mom.masterdata.domain.ProductionLine;
import com.iad.mom.masterdata.service.IProductionLineService;

/**
 * 产线Service业务层处理
 * 
 * @author iad
 * @date 2023-10-07
 */
@Service
public class ProductionLineServiceImpl implements IProductionLineService 
{
    @Autowired
    private ProductionLineMapper productionLineMapper;

    /**
     * 查询产线
     * 
     * @param id 产线主键
     * @return 产线
     */
    @Override
    public ProductionLine selectProductionLineById(Long id)
    {
        return productionLineMapper.selectProductionLineById(id);
    }

    /**
     * 查询产线列表
     * 
     * @param productionLine 产线
     * @return 产线
     */
    @Override
    public List<ProductionLine> selectProductionLineList(ProductionLine productionLine)
    {
        return productionLineMapper.selectProductionLineList(productionLine);
    }

    /**
     * 新增产线
     * 
     * @param productionLine 产线
     * @return 结果
     */
    @Override
    public int insertProductionLine(ProductionLine productionLine)
    {
        return productionLineMapper.insertProductionLine(productionLine);
    }

    /**
     * 修改产线
     * 
     * @param productionLine 产线
     * @return 结果
     */
    @Override
    public int updateProductionLine(ProductionLine productionLine)
    {
        return productionLineMapper.updateProductionLine(productionLine);
    }

    /**
     * 批量删除产线
     * 
     * @param ids 需要删除的产线主键
     * @return 结果
     */
    @Override
    public int deleteProductionLineByIds(Long[] ids)
    {
        return productionLineMapper.deleteProductionLineByIds(ids);
    }

    /**
     * 删除产线信息
     * 
     * @param id 产线主键
     * @return 结果
     */
    @Override
    public int deleteProductionLineById(Long id)
    {
        return productionLineMapper.deleteProductionLineById(id);
    }
}
