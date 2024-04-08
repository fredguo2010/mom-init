package com.iad.mom.prod.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iad.mom.common.annotation.Excel;
import com.iad.mom.common.core.domain.BaseEntity;

/**
 * 生产计划对象 mes_prod_plan
 * 
 * @author iad
 * @date 2023-11-20
 */
public class ProductionPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 生产计划号 */
    @Excel(name = "生产计划号")
    private String planNo;

    /** product_id */
    private String productId;

    /** 产品名 */
    @Excel(name = "产品名")
    private String productName;

    /** 计划数量 */
    @Excel(name = "计划数量")
    private Long planQuantity;

    /** 计划开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planStartDate;

    /** 计划结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planEndDate;

    /** line_id */
    private String lineId;

    /** workstation_id */
    private String workstationId;

    /** 物料号 */
    @Excel(name = "物料号")
    private String materialId;

    /** 物料数量 */
    @Excel(name = "物料数量")
    private Long materialQuantity;

    /** staff_id */
    private String staffId;

    /** staff_hours */
    private Long staffHours;

    /** 状态 */
    private Long status;

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
    public void setPlanNo(String planNo) 
    {
        this.planNo = planNo;
    }

    public String getPlanNo() 
    {
        return planNo;
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
    public void setPlanQuantity(Long planQuantity) 
    {
        this.planQuantity = planQuantity;
    }

    public Long getPlanQuantity() 
    {
        return planQuantity;
    }
    public void setPlanStartDate(Date planStartDate) 
    {
        this.planStartDate = planStartDate;
    }

    public Date getPlanStartDate() 
    {
        return planStartDate;
    }
    public void setPlanEndDate(Date planEndDate) 
    {
        this.planEndDate = planEndDate;
    }

    public Date getPlanEndDate() 
    {
        return planEndDate;
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
    public void setMaterialId(String materialId) 
    {
        this.materialId = materialId;
    }

    public String getMaterialId() 
    {
        return materialId;
    }
    public void setMaterialQuantity(Long materialQuantity) 
    {
        this.materialQuantity = materialQuantity;
    }

    public Long getMaterialQuantity() 
    {
        return materialQuantity;
    }
    public void setStaffId(String staffId) 
    {
        this.staffId = staffId;
    }

    public String getStaffId() 
    {
        return staffId;
    }
    public void setStaffHours(Long staffHours) 
    {
        this.staffHours = staffHours;
    }

    public Long getStaffHours() 
    {
        return staffHours;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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
            .append("planNo", getPlanNo())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("planQuantity", getPlanQuantity())
            .append("planStartDate", getPlanStartDate())
            .append("planEndDate", getPlanEndDate())
            .append("lineId", getLineId())
            .append("workstationId", getWorkstationId())
            .append("materialId", getMaterialId())
            .append("materialQuantity", getMaterialQuantity())
            .append("staffId", getStaffId())
            .append("staffHours", getStaffHours())
            .append("status", getStatus())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("updater", getUpdater())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
