package com.iad.mom.prod.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iad.mom.common.annotation.Excel;
import com.iad.mom.common.core.domain.BaseEntity;

/**
 * 工单管理对象 mes_work_order
 * 
 * @author kris
 * @date 2023-11-21
 */
public class WorkOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键,自增长 */
    private String id;

    /** 工单编号 */
    @Excel(name = "工单编号")
    private String orderNo;

    /** 产品编号 */
    private String productId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 计划开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedStartDate;

    /** 计划结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedEndDate;

    /** 计划产量 */
    @Excel(name = "计划产量")
    private Long quantity;

    /** 产线编号 */
    private String lineId;

    /** 工作站编号 */
    private String workstationId;

    /** 优先级 */
    @Excel(name = "优先级")
    private Long priority;

    /** 工单状态   */
    @Excel(name = "工单状态  ")
    private Long status;

    /** 下发人 */
    @Excel(name = "下发人")
    private String releasedBy;

    /** 下发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releasedDate;

    /** 闭单人  */
    @Excel(name = "闭单人 ")
    private String closedBy;

    /** 闭单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "闭单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date closedDate;

    /** 版本号 */
    private Long version;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creator;

    /** 最后更新人  */
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
    public void setPlannedStartDate(Date plannedStartDate) 
    {
        this.plannedStartDate = plannedStartDate;
    }

    public Date getPlannedStartDate() 
    {
        return plannedStartDate;
    }
    public void setPlannedEndDate(Date plannedEndDate) 
    {
        this.plannedEndDate = plannedEndDate;
    }

    public Date getPlannedEndDate() 
    {
        return plannedEndDate;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
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
    public void setPriority(Long priority) 
    {
        this.priority = priority;
    }

    public Long getPriority() 
    {
        return priority;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setReleasedBy(String releasedBy) 
    {
        this.releasedBy = releasedBy;
    }

    public String getReleasedBy() 
    {
        return releasedBy;
    }
    public void setReleasedDate(Date releasedDate) 
    {
        this.releasedDate = releasedDate;
    }

    public Date getReleasedDate() 
    {
        return releasedDate;
    }
    public void setClosedBy(String closedBy) 
    {
        this.closedBy = closedBy;
    }

    public String getClosedBy() 
    {
        return closedBy;
    }
    public void setClosedDate(Date closedDate) 
    {
        this.closedDate = closedDate;
    }

    public Date getClosedDate() 
    {
        return closedDate;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
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
            .append("plannedStartDate", getPlannedStartDate())
            .append("plannedEndDate", getPlannedEndDate())
            .append("quantity", getQuantity())
            .append("lineId", getLineId())
            .append("workstationId", getWorkstationId())
            .append("priority", getPriority())
            .append("status", getStatus())
            .append("releasedBy", getReleasedBy())
            .append("releasedDate", getReleasedDate())
            .append("closedBy", getClosedBy())
            .append("closedDate", getClosedDate())
            .append("version", getVersion())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("updater", getUpdater())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
