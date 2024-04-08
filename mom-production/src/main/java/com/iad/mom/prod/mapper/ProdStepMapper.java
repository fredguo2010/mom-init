package com.iad.mom.prod.mapper;

import java.util.List;
import com.iad.mom.prod.domain.ProdStep;

/**
 * 工序管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-23
 */
public interface ProdStepMapper 
{
    /**
     * 查询工序管理
     * 
     * @param id 工序管理主键
     * @return 工序管理
     */
    public ProdStep selectProdStepById(Long id);

    /**
     * 查询工序管理列表
     * 
     * @param prodStep 工序管理
     * @return 工序管理集合
     */
    public List<ProdStep> selectProdStepList(ProdStep prodStep);

    /**
     * 新增工序管理
     * 
     * @param prodStep 工序管理
     * @return 结果
     */
    public int insertProdStep(ProdStep prodStep);

    /**
     * 修改工序管理
     * 
     * @param prodStep 工序管理
     * @return 结果
     */
    public int updateProdStep(ProdStep prodStep);

    /**
     * 删除工序管理
     * 
     * @param id 工序管理主键
     * @return 结果
     */
    public int deleteProdStepById(Long id);

    /**
     * 批量删除工序管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProdStepByIds(Long[] ids);
}
