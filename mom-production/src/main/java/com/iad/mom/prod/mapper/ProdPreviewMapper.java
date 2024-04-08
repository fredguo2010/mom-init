package com.iad.mom.prod.mapper;

import java.util.List;
import com.iad.mom.prod.domain.ProdPreview;

/**
 * 操作手册Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-14
 */
public interface ProdPreviewMapper 
{
    /**
     * 查询操作手册
     * 
     * @param id 操作手册主键
     * @return 操作手册
     */
    public ProdPreview selectProdPreviewById(Long id);

    /**
     * 查询操作手册列表
     * 
     * @param prodPreview 操作手册
     * @return 操作手册集合
     */
    public List<ProdPreview> selectProdPreviewList(ProdPreview prodPreview);

    /**
     * 新增操作手册
     * 
     * @param prodPreview 操作手册
     * @return 结果
     */
    public int insertProdPreview(ProdPreview prodPreview);

    /**
     * 修改操作手册
     * 
     * @param prodPreview 操作手册
     * @return 结果
     */
    public int updateProdPreview(ProdPreview prodPreview);

    /**
     * 删除操作手册
     * 
     * @param id 操作手册主键
     * @return 结果
     */
    public int deleteProdPreviewById(Long id);

    /**
     * 批量删除操作手册
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProdPreviewByIds(Long[] ids);
}
