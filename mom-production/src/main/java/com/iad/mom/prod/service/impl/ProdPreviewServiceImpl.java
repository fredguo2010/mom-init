package com.iad.mom.prod.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.prod.mapper.ProdPreviewMapper;
import com.iad.mom.prod.domain.ProdPreview;
import com.iad.mom.prod.service.IProdPreviewService;

/**
 * 操作手册Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-14
 */
@Service
public class ProdPreviewServiceImpl implements IProdPreviewService 
{
    @Autowired
    private ProdPreviewMapper prodPreviewMapper;

    /**
     * 查询操作手册
     * 
     * @param id 操作手册主键
     * @return 操作手册
     */
    @Override
    public ProdPreview selectProdPreviewById(Long id)
    {
        return prodPreviewMapper.selectProdPreviewById(id);
    }

    /**
     * 查询操作手册列表
     * 
     * @param prodPreview 操作手册
     * @return 操作手册
     */
    @Override
    public List<ProdPreview> selectProdPreviewList(ProdPreview prodPreview)
    {
        return prodPreviewMapper.selectProdPreviewList(prodPreview);
    }

    /**
     * 新增操作手册
     * 
     * @param prodPreview 操作手册
     * @return 结果
     */
    @Override
    public int insertProdPreview(ProdPreview prodPreview)
    {
        return prodPreviewMapper.insertProdPreview(prodPreview);
    }

    /**
     * 修改操作手册
     * 
     * @param prodPreview 操作手册
     * @return 结果
     */
    @Override
    public int updateProdPreview(ProdPreview prodPreview)
    {
        return prodPreviewMapper.updateProdPreview(prodPreview);
    }

    /**
     * 批量删除操作手册
     * 
     * @param ids 需要删除的操作手册主键
     * @return 结果
     */
    @Override
    public int deleteProdPreviewByIds(Long[] ids)
    {
        return prodPreviewMapper.deleteProdPreviewByIds(ids);
    }

    /**
     * 删除操作手册信息
     * 
     * @param id 操作手册主键
     * @return 结果
     */
    @Override
    public int deleteProdPreviewById(Long id)
    {
        return prodPreviewMapper.deleteProdPreviewById(id);
    }
}
