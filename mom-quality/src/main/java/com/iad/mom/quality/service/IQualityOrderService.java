package com.iad.mom.quality.service;

import java.util.List;
import com.iad.mom.quality.domain.QualityOrder;

/**
 * 质量工单Service接口
 * 
 * @author rchen9
 * @date 2023-11-21
 */
public interface IQualityOrderService 
{
    /**
     * 查询质量工单
     * 
     * @param id 质量工单主键
     * @return 质量工单
     */
    public QualityOrder selectQualityOrderById(String id);

    /**
     * 查询质量工单列表
     * 
     * @param qualityOrder 质量工单
     * @return 质量工单集合
     */
    public List<QualityOrder> selectQualityOrderList(QualityOrder qualityOrder);

    /**
     * 新增质量工单
     * 
     * @param qualityOrder 质量工单
     * @return 结果
     */
    public int insertQualityOrder(QualityOrder qualityOrder);

    /**
     * 修改质量工单
     * 
     * @param qualityOrder 质量工单
     * @return 结果
     */
    public int updateQualityOrder(QualityOrder qualityOrder);

    /**
     * 批量删除质量工单
     * 
     * @param ids 需要删除的质量工单主键集合
     * @return 结果
     */
    public int deleteQualityOrderByIds(String[] ids);

    /**
     * 删除质量工单信息
     * 
     * @param id 质量工单主键
     * @return 结果
     */
    public int deleteQualityOrderById(String id);
}
