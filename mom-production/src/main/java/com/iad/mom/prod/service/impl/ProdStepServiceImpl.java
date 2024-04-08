package com.iad.mom.prod.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.prod.mapper.ProdStepMapper;
import com.iad.mom.prod.domain.ProdStep;
import com.iad.mom.prod.service.IProdStepService;

/**
 * 工序管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-23
 */
@Service
public class ProdStepServiceImpl implements IProdStepService 
{
    @Autowired
    private ProdStepMapper prodStepMapper;

    /**
     * 查询工序管理
     * 
     * @param id 工序管理主键
     * @return 工序管理
     */
    @Override
    public ProdStep selectProdStepById(Long id)
    {
        return prodStepMapper.selectProdStepById(id);
    }

    /**
     * 查询工序管理列表
     * 
     * @param prodStep 工序管理
     * @return 工序管理
     */
    @Override
    public List<ProdStep> selectProdStepList(ProdStep prodStep)
    {
        return prodStepMapper.selectProdStepList(prodStep);
    }

    /**
     * 新增工序管理
     * 
     * @param prodStep 工序管理
     * @return 结果
     */
    @Override
    public int insertProdStep(ProdStep prodStep)
    {
        return prodStepMapper.insertProdStep(prodStep);
    }

    /**
     * 修改工序管理
     * 
     * @param prodStep 工序管理
     * @return 结果
     */
    @Override
    public int updateProdStep(ProdStep prodStep)
    {
        return prodStepMapper.updateProdStep(prodStep);
    }

    /**
     * 批量删除工序管理
     * 
     * @param ids 需要删除的工序管理主键
     * @return 结果
     */
    @Override
    public int deleteProdStepByIds(Long[] ids)
    {
        return prodStepMapper.deleteProdStepByIds(ids);
    }

    /**
     * 删除工序管理信息
     * 
     * @param id 工序管理主键
     * @return 结果
     */
    @Override
    public int deleteProdStepById(Long id)
    {
        return prodStepMapper.deleteProdStepById(id);
    }
}
