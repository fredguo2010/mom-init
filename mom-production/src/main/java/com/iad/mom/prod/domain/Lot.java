package com.iad.mom.prod.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iad.mom.common.annotation.Excel;
import com.iad.mom.common.core.domain.BaseEntity;

/**
 * 批次管理对象 mes_lot
 * 
 * @author kris
 * @date 2023-11-22
 */
public class Lot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键,自增长 */
    private String id;

    /** 批次编号 */
    @Excel(name = "批次编号")
    private String lotNo;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String productId;

    /** 批次数量 */
    @Excel(name = "批次数量")
    private Long quantity;

    /** 批次状态 */
    @Excel(name = "批次状态")
    private Long status;

    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 产线编号 */
    private String lineId;

    /** 工作站编号 */
    private String workstationId;

    /** 操作工 */
    @Excel(name = "操作工")
    private String worker;

    /** 是否重工 */
    @Excel(name = "是否重工")
    private Long isReworked;

    /** 父级批次 */
    @Excel(name = "父级批次")
    private String parentLot;

    /** 报废数量 */
    @Excel(name = "报废数量")
    private Long scrapQuantity;

    /** 报废原因 */
    @Excel(name = "报废原因")
    private String scrapCause;

    /** 检验人员 */
    @Excel(name = "检验人员")
    private String inspector;

    /** 检验日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检验日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectionDate;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

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
    public void setLotNo(String lotNo) 
    {
        this.lotNo = lotNo;
    }

    public String getLotNo() 
    {
        return lotNo;
    }
    public void setProductId(String productId) 
    {
        this.productId = productId;
    }

    public String getProductId() 
    {
        return productId;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setLineId(String lineId) 
    {
        this.lineId = lineId;
    }

    public String getLineId() 
    {
        return lineId;
    }
    public void setWorkstationId(String workstationId) 
    {
        this.workstationId = workstationId;
    }

    public String getWorkstationId() 
    {
        return workstationId;
    }
    public void setWorker(String worker) 
    {
        this.worker = worker;
    }

    public String getWorker() 
    {
        return worker;
    }
    public void setIsReworked(Long isReworked) 
    {
        this.isReworked = isReworked;
    }

    public Long getIsReworked() 
    {
        return isReworked;
    }
    public void setParentLot(String parentLot) 
    {
        this.parentLot = parentLot;
    }

    public String getParentLot() 
    {
        return parentLot;
    }
    public void setScrapQuantity(Long scrapQuantity) 
    {
        this.scrapQuantity = scrapQuantity;
    }

    public Long getScrapQuantity() 
    {
        return scrapQuantity;
    }
    public void setScrapCause(String scrapCause) 
    {
        this.scrapCause = scrapCause;
    }

    public String getScrapCause() 
    {
        return scrapCause;
    }
    public void setInspector(String inspector) 
    {
        this.inspector = inspector;
    }

    public String getInspector() 
    {
        return inspector;
    }
    public void setInspectionDate(Date inspectionDate) 
    {
        this.inspectionDate = inspectionDate;
    }

    public Date getInspectionDate() 
    {
        return inspectionDate;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
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
            .append("lotNo", getLotNo())
            .append("productId", getProductId())
            .append("quantity", getQuantity())
            .append("status", getStatus())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("lineId", getLineId())
            .append("workstationId", getWorkstationId())
            .append("worker", getWorker())
            .append("isReworked", getIsReworked())
            .append("parentLot", getParentLot())
            .append("scrapQuantity", getScrapQuantity())
            .append("scrapCause", getScrapCause())
            .append("inspector", getInspector())
            .append("inspectionDate", getInspectionDate())
            .append("comments", getComments())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("updater", getUpdater())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
