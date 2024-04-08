package com.iad.mom.quality.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iad.mom.common.annotation.Excel;
import com.iad.mom.common.core.domain.BaseEntity;

/**
 * 质量工单对象 mes_quality_order
 * 
 * @author rchen9
 * @date 2023-11-21
 */
public class QualityOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 质量工单编号 */
    @Excel(name = "质量工单编号")
    private String orderNo;

    /** product_id */
    private String productId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 生产批号 */
    @Excel(name = "生产批号")
    private String batchNo;

    /** 样品数量 */
    @Excel(name = "样品数量")
    private Long quantity;

    /** 检验员 */
    @Excel(name = "检验员")
    private String inspector;

    /** 检验开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检验开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectionStartDate;

    /** 检验结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检验结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectionEndDate;

    /** 工单状态 */
    @Excel(name = "工单状态")
    private Long status;

    /** 检验结果 */
    @Excel(name = "检验结果")
    private Long result;

    /** 返工说明 */
    @Excel(name = "返工说明")
    private String reworkNotes;

    /** 创建人 */
    private String creator;

    /** 更新人 */
    private String updater;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
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
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setInspector(String inspector) 
    {
        this.inspector = inspector;
    }

    public String getInspector() 
    {
        return inspector;
    }
    public void setInspectionStartDate(Date inspectionStartDate) 
    {
        this.inspectionStartDate = inspectionStartDate;
    }

    public Date getInspectionStartDate() 
    {
        return inspectionStartDate;
    }
    public void setInspectionEndDate(Date inspectionEndDate) 
    {
        this.inspectionEndDate = inspectionEndDate;
    }

    public Date getInspectionEndDate() 
    {
        return inspectionEndDate;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setResult(Long result) 
    {
        this.result = result;
    }

    public Long getResult() 
    {
        return result;
    }
    public void setReworkNotes(String reworkNotes) 
    {
        this.reworkNotes = reworkNotes;
    }

    public String getReworkNotes() 
    {
        return reworkNotes;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }
    public void setUpdater(String updater) 
    {
        this.updater = updater;
    }

    public String getUpdater() 
    {
        return updater;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNo", getOrderNo())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("batchNo", getBatchNo())
            .append("quantity", getQuantity())
            .append("inspector", getInspector())
            .append("inspectionStartDate", getInspectionStartDate())
            .append("inspectionEndDate", getInspectionEndDate())
            .append("status", getStatus())
            .append("result", getResult())
            .append("reworkNotes", getReworkNotes())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("updater", getUpdater())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
