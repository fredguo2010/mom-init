package com.iad.mom.quality.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.quality.mapper.QualityInspectionMapper;
import com.iad.mom.quality.domain.QualityInspection;
import com.iad.mom.quality.service.IQualityInspectionService;

/**
 * 质量检测Service业务层处理
 * 
 * @author rchen9
 * @date 2023-11-21
 */
@Service
public class QualityInspectionServiceImpl implements IQualityInspectionService 
{
    @Autowired
    private QualityInspectionMapper qualityInspectionMapper;

    /**
     * 查询质量检测
     * 
     * @param id 质量检测主键
     * @return 质量检测
     */
    @Override
    public QualityInspection selectQualityInspectionById(String id)
    {
        return qualityInspectionMapper.selectQualityInspectionById(id);
    }

    /**
     * 查询质量检测列表
     * 
     * @param qualityInspection 质量检测
     * @return 质量检测
     */
    @Override
    public List<QualityInspection> selectQualityInspectionList(QualityInspection qualityInspection)
    {
        return qualityInspectionMapper.selectQualityInspectionList(qualityInspection);
    }

    /**
     * 新增质量检测
     * 
     * @param qualityInspection 质量检测
     * @return 结果
     */
    @Override
    public int insertQualityInspection(QualityInspection qualityInspection)
    {
        return qualityInspectionMapper.insertQualityInspection(qualityInspection);
    }

    /**
     * 修改质量检测
     * 
     * @param qualityInspection 质量检测
     * @return 结果
     */
    @Override
    public int updateQualityInspection(QualityInspection qualityInspection)
    {
        return qualityInspectionMapper.updateQualityInspection(qualityInspection);
    }

    /**
     * 批量删除质量检测
     * 
     * @param ids 需要删除的质量检测主键
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionByIds(String[] ids)
    {
        return qualityInspectionMapper.deleteQualityInspectionByIds(ids);
    }

    /**
     * 删除质量检测信息
     * 
     * @param id 质量检测主键
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionById(String id)
    {
        return qualityInspectionMapper.deleteQualityInspectionById(id);
    }
}
