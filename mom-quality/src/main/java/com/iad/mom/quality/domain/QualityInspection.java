package com.iad.mom.quality.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iad.mom.common.annotation.Excel;
import com.iad.mom.common.core.domain.BaseEntity;

/**
 * 质量检测对象 mes_quality_inspection
 * 
 * @author rchen9
 * @date 2023-12-04
 */
public class QualityInspection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 产品编号 */
    private String productId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 检验项目 */
    @Excel(name = "检验项目")
    private String inspectionItem;

    /** 检验员 */
    @Excel(name = "检验员")
    private String inspector;

    /** 检验时间 */
    private Date inspectionTime;

    /** 检验测量值 */
    @Excel(name = "检验测量值")
    private Long inspectionValue;

    /** 上限规格 */
    private Long upperLimit;

    /** 下限规格 */
    private Long lowerLimit;

    /** 合格状态 */
    private Long qualityStatus;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setProductId(String productId) 
    {
        this.productId = productId;
    }

    public String getProductId() 
    {
        return productId;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setInspectionItem(String inspectionItem) 
    {
        this.inspectionItem = inspectionItem;
    }

    public String getInspectionItem() 
    {
        return inspectionItem;
    }
    public void setInspector(String inspector) 
    {
        this.inspector = inspector;
    }

    public String getInspector() 
    {
        return inspector;
    }
    public void setInspectionTime(Date inspectionTime) 
    {
        this.inspectionTime = inspectionTime;
    }

    public Date getInspectionTime() 
    {
        return inspectionTime;
    }
    public void setInspectionValue(Long inspectionValue) 
    {
        this.inspectionValue = inspectionValue;
    }

    public Long getInspectionValue() 
    {
        return inspectionValue;
    }
    public void setUpperLimit(Long upperLimit) 
    {
        this.upperLimit = upperLimit;
    }

    public Long getUpperLimit() 
    {
        return upperLimit;
    }
    public void setLowerLimit(Long lowerLimit) 
    {
        this.lowerLimit = lowerLimit;
    }

    public Long getLowerLimit() 
    {
        return lowerLimit;
    }
    public void setQualityStatus(Long qualityStatus) 
    {
        this.qualityStatus = qualityStatus;
    }

    public Long getQualityStatus() 
    {
        return qualityStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("batchNo", getBatchNo())
            .append("inspectionItem", getInspectionItem())
            .append("inspector", getInspector())
            .append("inspectionTime", getInspectionTime())
            .append("inspectionValue", getInspectionValue())
            .append("upperLimit", getUpperLimit())
            .append("lowerLimit", getLowerLimit())
            .append("qualityStatus", getQualityStatus())
            .toString();
    }
}
