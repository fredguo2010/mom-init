package com.iad.mom.prod.service;

import java.util.List;
import com.iad.mom.prod.domain.ProdIdpattern;

/**
 * ID生成器Service接口
 * 
 * @author ruoyi
 * @date 2023-12-06
 */
public interface IProdIdpatternService 
{
    /**
     * 查询ID生成器
     * 
     * @param id ID生成器主键
     * @return ID生成器
     */
    public ProdIdpattern selectProdIdpatternById(Long id);

    /**
     * 查询ID生成器列表
     * 
     * @param prodIdpattern ID生成器
     * @return ID生成器集合
     */
    public List<ProdIdpattern> selectProdIdpatternList(ProdIdpattern prodIdpattern);

    /**
     * 新增ID生成器
     * 
     * @param prodIdpattern ID生成器
     * @return 结果
     */
    public int insertProdIdpattern(ProdIdpattern prodIdpattern);

    /**
     * 修改ID生成器
     * 
     * @param prodIdpattern ID生成器
     * @return 结果
     */
    public int updateProdIdpattern(ProdIdpattern prodIdpattern);

    /**
     * 批量删除ID生成器
     * 
     * @param ids 需要删除的ID生成器主键集合
     * @return 结果
     */
    public int deleteProdIdpatternByIds(Long[] ids);

    /**
     * 删除ID生成器信息
     * 
     * @param id ID生成器主键
     * @return 结果
     */
    public int deleteProdIdpatternById(Long id);
}
