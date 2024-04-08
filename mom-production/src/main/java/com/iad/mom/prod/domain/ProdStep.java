package com.iad.mom.prod.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iad.mom.common.annotation.Excel;
import com.iad.mom.common.core.domain.BaseEntity;

/**
 * 工序管理对象 mes_prod_step
 * 
 * @author ruoyi
 * @date 2023-11-23
 */
public class ProdStep extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键,自增长 */
    private Long id;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String name;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 画板名称 */
    @Excel(name = "画板名称")
    private String displayName;

    /** operation_id */
    private Long operationId;

    /** 工艺路线id */
    @Excel(name = "工艺路线id")
    private Long routeId;

    /** 工序宽 */
    @Excel(name = "工序宽")
    private Long stepWidth;

    /** 工序高 */
    @Excel(name = "工序高")
    private Long stepHeight;

    /** 左 */
    @Excel(name = "左")
    private Long positionLeft;

    /** 上 */
    @Excel(name = "上")
    private Long positionTop;

    /** 工作中心 */
    @Excel(name = "工作中心")
    private String workCenters;

    /** fifo */
    @Excel(name = "fifo")
    private Integer fifo;

    /** 一般优先级 */
    @Excel(name = "一般优先级")
    private Long normalPriority;

    /** 返工优先级 */
    @Excel(name = "返工优先级")
    private Long reworkPriority;

    /** 拆分合并优先级 */
    @Excel(name = "拆分合并优先级")
    private Long splitMergePriority;

    /** 连载 */
    @Excel(name = "连载")
    private Integer serialize;

    /** 连续添加部件 */
    @Excel(name = "连续添加部件")
    private Integer continueAddPart;

    /** batch_pass */
    @Excel(name = "batch_pass")
    private Integer batchPass;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    /** 更新人 */
    @Excel(name = "更新人")
    private String lastModifiedBy;

    /** 更新日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastModifiedDate;

    /** 删除状态 */
    private Long deleteState;

    /** 标签id */
    private Long tagId;

    /** 自动输入 */
    @Excel(name = "自动输入")
    private Integer autoInput;

    /** type */
    @Excel(name = "type")
    private Long type;

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
    public void setDisplayName(String displayName) 
    {
        this.displayName = displayName;
    }

    public String getDisplayName() 
    {
        return displayName;
    }
    public void setOperationId(Long operationId) 
    {
        this.operationId = operationId;
    }

    public Long getOperationId() 
    {
        return operationId;
    }
    public void setRouteId(Long routeId) 
    {
        this.routeId = routeId;
    }

    public Long getRouteId() 
    {
        return routeId;
    }
    public void setStepWidth(Long stepWidth) 
    {
        this.stepWidth = stepWidth;
    }

    public Long getStepWidth() 
    {
        return stepWidth;
    }
    public void setStepHeight(Long stepHeight) 
    {
        this.stepHeight = stepHeight;
    }

    public Long getStepHeight() 
    {
        return stepHeight;
    }
    public void setPositionLeft(Long positionLeft) 
    {
        this.positionLeft = positionLeft;
    }

    public Long getPositionLeft() 
    {
        return positionLeft;
    }
    public void setPositionTop(Long positionTop) 
    {
        this.positionTop = positionTop;
    }

    public Long getPositionTop() 
    {
        return positionTop;
    }
    public void setWorkCenters(String workCenters) 
    {
        this.workCenters = workCenters;
    }

    public String getWorkCenters() 
    {
        return workCenters;
    }
    public void setFifo(Integer fifo) 
    {
        this.fifo = fifo;
    }

    public Integer getFifo() 
    {
        return fifo;
    }
    public void setNormalPriority(Long normalPriority) 
    {
        this.normalPriority = normalPriority;
    }

    public Long getNormalPriority() 
    {
        return normalPriority;
    }
    public void setReworkPriority(Long reworkPriority) 
    {
        this.reworkPriority = reworkPriority;
    }

    public Long getReworkPriority() 
    {
        return reworkPriority;
    }
    public void setSplitMergePriority(Long splitMergePriority) 
    {
        this.splitMergePriority = splitMergePriority;
    }

    public Long getSplitMergePriority() 
    {
        return splitMergePriority;
    }
    public void setSerialize(Integer serialize) 
    {
        this.serialize = serialize;
    }

    public Integer getSerialize() 
    {
        return serialize;
    }
    public void setContinueAddPart(Integer continueAddPart) 
    {
        this.continueAddPart = continueAddPart;
    }

    public Integer getContinueAddPart() 
    {
        return continueAddPart;
    }
    public void setBatchPass(Integer batchPass) 
    {
        this.batchPass = batchPass;
    }

    public Integer getBatchPass() 
    {
        return batchPass;
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
    public void setTagId(Long tagId) 
    {
        this.tagId = tagId;
    }

    public Long getTagId() 
    {
        return tagId;
    }
    public void setAutoInput(Integer autoInput) 
    {
        this.autoInput = autoInput;
    }

    public Integer getAutoInput() 
    {
        return autoInput;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("description", getDescription())
            .append("displayName", getDisplayName())
            .append("operationId", getOperationId())
            .append("routeId", getRouteId())
            .append("stepWidth", getStepWidth())
            .append("stepHeight", getStepHeight())
            .append("positionLeft", getPositionLeft())
            .append("positionTop", getPositionTop())
            .append("workCenters", getWorkCenters())
            .append("fifo", getFifo())
            .append("normalPriority", getNormalPriority())
            .append("reworkPriority", getReworkPriority())
            .append("splitMergePriority", getSplitMergePriority())
            .append("serialize", getSerialize())
            .append("continueAddPart", getContinueAddPart())
            .append("batchPass", getBatchPass())
            .append("createdBy", getCreatedBy())
            .append("createdDate", getCreatedDate())
            .append("lastModifiedBy", getLastModifiedBy())
            .append("lastModifiedDate", getLastModifiedDate())
            .append("deleteState", getDeleteState())
            .append("tagId", getTagId())
            .append("autoInput", getAutoInput())
            .append("type", getType())
            .toString();
    }
}
