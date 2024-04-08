package com.iad.mom.masterdata.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iad.mom.common.annotation.Excel;
import com.iad.mom.common.core.domain.BaseEntity;

/**
 * 产线对象 md_production_line
 * 
 * @author iad
 * @date 2023-10-07
 */
public class ProductionLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 产线名 */
    @Excel(name = "产线名")
    private String name;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** area_id */
    @Excel(name = "area_id")
    private Long areaId;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    /** 修改人 */
    @Excel(name = "修改人")
    private String lastModifiedBy;

    /** 修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastModifiedDate;

    /** delete_state */
    private Long deleteState;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setAreaId(Long areaId) 
    {
        this.areaId = areaId;
    }

    public Long getAreaId() 
    {
        return areaId;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedDate(Date createdDate) 
    {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() 
    {
        return createdDate;
    }
    public void setLastModifiedBy(String lastModifiedBy) 
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedBy() 
    {
        return lastModifiedBy;
    }
    public void setLastModifiedDate(Date lastModifiedDate) 
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getLastModifiedDate() 
    {
        return lastModifiedDate;
    }
    public void setDeleteState(Long deleteState) 
    {
        this.deleteState = deleteState;
    }

    public Long getDeleteState() 
    {
        return deleteState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("description", getDescription())
            .append("remark", getRemark())
            .append("areaId", getAreaId())
            .append("createdBy", getCreatedBy())
            .append("createdDate", getCreatedDate())
            .append("lastModifiedBy", getLastModifiedBy())
            .append("lastModifiedDate", getLastModifiedDate())
            .append("deleteState", getDeleteState())
            .toString();
    }
}
