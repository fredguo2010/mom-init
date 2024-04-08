package com.iad.mom.prod.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.prod.mapper.ProdIdpatternMapper;
import com.iad.mom.prod.domain.ProdIdpattern;
import com.iad.mom.prod.service.IProdIdpatternService;

/**
 * ID生成器Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-06
 */
@Service
public class ProdIdpatternServiceImpl implements IProdIdpatternService 
{
    @Autowired
    private ProdIdpatternMapper prodIdpatternMapper;

    /**
     * 查询ID生成器
     * 
     * @param id ID生成器主键
     * @return ID生成器
     */
    @Override
    public ProdIdpattern selectProdIdpatternById(Long id)
    {
        return prodIdpatternMapper.selectProdIdpatternById(id);
    }

    /**
     * 查询ID生成器列表
     * 
     * @param prodIdpattern ID生成器
     * @return ID生成器
     */
    @Override
    public List<ProdIdpattern> selectProdIdpatternList(ProdIdpattern prodIdpattern)
    {
        return prodIdpatternMapper.selectProdIdpatternList(prodIdpattern);
    }

    /**
     * 新增ID生成器
     * 
     * @param prodIdpattern ID生成器
     * @return 结果
     */
    @Override
    public int insertProdIdpattern(ProdIdpattern prodIdpattern)
    {
        return prodIdpatternMapper.insertProdIdpattern(prodIdpattern);
    }

    /**
     * 修改ID生成器
     * 
     * @param prodIdpattern ID生成器
     * @return 结果
     */
    @Override
    public int updateProdIdpattern(ProdIdpattern prodIdpattern)
    {
        return prodIdpatternMapper.updateProdIdpattern(prodIdpattern);
    }

    /**
     * 批量删除ID生成器
     * 
     * @param ids 需要删除的ID生成器主键
     * @return 结果
     */
    @Override
    public int deleteProdIdpatternByIds(Long[] ids)
    {
        return prodIdpatternMapper.deleteProdIdpatternByIds(ids);
    }

    /**
     * 删除ID生成器信息
     * 
     * @param id ID生成器主键
     * @return 结果
     */
    @Override
    public int deleteProdIdpatternById(Long id)
    {
        return prodIdpatternMapper.deleteProdIdpatternById(id);
    }
}
