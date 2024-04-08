package com.iad.mom.quality.service.impl;

import java.util.List;
import com.iad.mom.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.quality.mapper.QualityOrderMapper;
import com.iad.mom.quality.domain.QualityOrder;
import com.iad.mom.quality.service.IQualityOrderService;

/**
 * 质量工单Service业务层处理
 * 
 * @author rchen9
 * @date 2023-11-21
 */
@Service
public class QualityOrderServiceImpl implements IQualityOrderService 
{
    @Autowired
    private QualityOrderMapper qualityOrderMapper;

    /**
     * 查询质量工单
     * 
     * @param id 质量工单主键
     * @return 质量工单
     */
    @Override
    public QualityOrder selectQualityOrderById(String id)
    {
        return qualityOrderMapper.selectQualityOrderById(id);
    }

    /**
     * 查询质量工单列表
     * 
     * @param qualityOrder 质量工单
     * @return 质量工单
     */
    @Override
    public List<QualityOrder> selectQualityOrderList(QualityOrder qualityOrder)
    {
        return qualityOrderMapper.selectQualityOrderList(qualityOrder);
    }

    /**
     * 新增质量工单
     * 
     * @param qualityOrder 质量工单
     * @return 结果
     */
    @Override
    public int insertQualityOrder(QualityOrder qualityOrder)
    {
        qualityOrder.setCreateTime(DateUtils.getNowDate());
        return qualityOrderMapper.insertQualityOrder(qualityOrder);
    }

    /**
     * 修改质量工单
     * 
     * @param qualityOrder 质量工单
     * @return 结果
     */
    @Override
    public int updateQualityOrder(QualityOrder qualityOrder)
    {
        qualityOrder.setUpdateTime(DateUtils.getNowDate());
        return qualityOrderMapper.updateQualityOrder(qualityOrder);
    }

    /**
     * 批量删除质量工单
     * 
     * @param ids 需要删除的质量工单主键
     * @return 结果
     */
    @Override
    public int deleteQualityOrderByIds(String[] ids)
    {
        return qualityOrderMapper.deleteQualityOrderByIds(ids);
    }

    /**
     * 删除质量工单信息
     * 
     * @param id 质量工单主键
     * @return 结果
     */
    @Override
    public int deleteQualityOrderById(String id)
    {
        return qualityOrderMapper.deleteQualityOrderById(id);
    }
}
