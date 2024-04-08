package com.iad.mom.quality.service;

import java.util.List;
import com.iad.mom.quality.domain.QualityInspection;

/**
 * 质量检测Service接口
 * 
 * @author rchen9
 * @date 2023-11-21
 */
public interface IQualityInspectionService 
{
    /**
     * 查询质量检测
     * 
     * @param id 质量检测主键
     * @return 质量检测
     */
    public QualityInspection selectQualityInspectionById(String id);

    /**
     * 查询质量检测列表
     * 
     * @param qualityInspection 质量检测
     * @return 质量检测集合
     */
    public List<QualityInspection> selectQualityInspectionList(QualityInspection qualityInspection);

    /**
     * 新增质量检测
     * 
     * @param qualityInspection 质量检测
     * @return 结果
     */
    public int insertQualityInspection(QualityInspection qualityInspection);

    /**
     * 修改质量检测
     * 
     * @param qualityInspection 质量检测
     * @return 结果
     */
    public int updateQualityInspection(QualityInspection qualityInspection);

    /**
     * 批量删除质量检测
     * 
     * @param ids 需要删除的质量检测主键集合
     * @return 结果
     */
    public int deleteQualityInspectionByIds(String[] ids);

    /**
     * 删除质量检测信息
     * 
     * @param id 质量检测主键
     * @return 结果
     */
    public int deleteQualityInspectionById(String id);
}
