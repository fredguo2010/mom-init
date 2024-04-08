package com.iad.mom.masterdata.service;

import java.util.List;
import com.iad.mom.masterdata.domain.ProductionLine;

/**
 * 产线Service接口
 * 
 * @author iad
 * @date 2023-10-07
 */
public interface IProductionLineService 
{
    /**
     * 查询产线
     * 
     * @param id 产线主键
     * @return 产线
     */
    public ProductionLine selectProductionLineById(Long id);

    /**
     * 查询产线列表
     * 
     * @param productionLine 产线
     * @return 产线集合
     */
    public List<ProductionLine> selectProductionLineList(ProductionLine productionLine);

    /**
     * 新增产线
     * 
     * @param productionLine 产线
     * @return 结果
     */
    public int insertProductionLine(ProductionLine productionLine);

    /**
     * 修改产线
     * 
     * @param productionLine 产线
     * @return 结果
     */
    public int updateProductionLine(ProductionLine productionLine);

    /**
     * 批量删除产线
     * 
     * @param ids 需要删除的产线主键集合
     * @return 结果
     */
    public int deleteProductionLineByIds(Long[] ids);

    /**
     * 删除产线信息
     * 
     * @param id 产线主键
     * @return 结果
     */
    public int deleteProductionLineById(Long id);
}
