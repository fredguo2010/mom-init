package com.iad.mom.masterdata.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iad.mom.common.annotation.Excel;
import com.iad.mom.common.core.domain.BaseEntity;

/**
 * 设备对象 md_equipment
 * 
 * @author ra
 * @date 2023-10-08
 */
public class Equipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String equipNumber;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** site_id */
    private Long siteId;

    /** location_id */
    private Long locationId;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 类型 */
    private String category;

    /** state */
    private Long state;

    /** delete_state */
    private Long deleteState;

    /** 创建人 */
    private String createdBy;

    /** 创建日期 */
    private Date createdDate;

    /** 修改人 */
    private String lastModifiedBy;

    /** 修改日期 */
    private Date lastModifiedDate;

    /** 设备用途 */
    @Excel(name = "设备用途")
    private Long purpose;

    /** 型号 */
    @Excel(name = "型号")
    private String model;

    /** 负责人 */
    @Excel(name = "负责人")
    private String owner;

    /** 生成日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生成日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date manufactureDate;

    /** 登记日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "登记日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date admissionDate;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNumber;

    /** equipment_type_id */
    private Long equipmentTypeId;

    /** status */
    private Long status;

    /** 制造商 */
    @Excel(name = "制造商")
    private String manufacture;

    /** in_rfid_tag_id */
    private Long inRfidTagId;

    /** out_rfid_tag_id */
    private Long outRfidTagId;

    /** weight_tag_id */
    private Long weightTagId;

    /** in_rfid_finish_tag_id */
    private Long inRfidFinishTagId;

    /** out_rfid_finish_tag_id */
    private Long outRfidFinishTagId;

    /** in_rfid_start_tag_id */
    private Long inRfidStartTagId;

    /** in_rfid_trigger_tag_id */
    private Long inRfidTriggerTagId;

    /** out_rfid_start_tag_id */
    private Long outRfidStartTagId;

    /** out_rfid_trigger_tag_id */
    private Long outRfidTriggerTagId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEquipNumber(String equipNumber) 
    {
        this.equipNumber = equipNumber;
    }

    public String getEquipNumber() 
    {
        return equipNumber;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSiteId(Long siteId) 
    {
        this.siteId = siteId;
    }

    public Long getSiteId() 
    {
        return siteId;
    }
    public void setLocationId(Long locationId) 
    {
        this.locationId = locationId;
    }

    public Long getLocationId() 
    {
        return locationId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }
    public void setDeleteState(Long deleteState) 
    {
        this.deleteState = deleteState;
    }

    public Long getDeleteState() 
    {
        return deleteState;
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
    public void setPurpose(Long purpose) 
    {
        this.purpose = purpose;
    }

    public Long getPurpose() 
    {
        return purpose;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setOwner(String owner) 
    {
        this.owner = owner;
    }

    public String getOwner() 
    {
        return owner;
    }
    public void setManufactureDate(Date manufactureDate) 
    {
        this.manufactureDate = manufactureDate;
    }

    public Date getManufactureDate() 
    {
        return manufactureDate;
    }
    public void setAdmissionDate(Date admissionDate) 
    {
        this.admissionDate = admissionDate;
    }

    public Date getAdmissionDate() 
    {
        return admissionDate;
    }
    public void setSerialNumber(String serialNumber) 
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() 
    {
        return serialNumber;
    }
    public void setEquipmentTypeId(Long equipmentTypeId) 
    {
        this.equipmentTypeId = equipmentTypeId;
    }

    public Long getEquipmentTypeId() 
    {
        return equipmentTypeId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setManufacture(String manufacture) 
    {
        this.manufacture = manufacture;
    }

    public String getManufacture() 
    {
        return manufacture;
    }
    public void setInRfidTagId(Long inRfidTagId) 
    {
        this.inRfidTagId = inRfidTagId;
    }

    public Long getInRfidTagId() 
    {
        return inRfidTagId;
    }
    public void setOutRfidTagId(Long outRfidTagId) 
    {
        this.outRfidTagId = outRfidTagId;
    }

    public Long getOutRfidTagId() 
    {
        return outRfidTagId;
    }
    public void setWeightTagId(Long weightTagId) 
    {
        this.weightTagId = weightTagId;
    }

    public Long getWeightTagId() 
    {
        return weightTagId;
    }
    public void setInRfidFinishTagId(Long inRfidFinishTagId) 
    {
        this.inRfidFinishTagId = inRfidFinishTagId;
    }

    public Long getInRfidFinishTagId() 
    {
        return inRfidFinishTagId;
    }
    public void setOutRfidFinishTagId(Long outRfidFinishTagId) 
    {
        this.outRfidFinishTagId = outRfidFinishTagId;
    }

    public Long getOutRfidFinishTagId() 
    {
        return outRfidFinishTagId;
    }
    public void setInRfidStartTagId(Long inRfidStartTagId) 
    {
        this.inRfidStartTagId = inRfidStartTagId;
    }

    public Long getInRfidStartTagId() 
    {
        return inRfidStartTagId;
    }
    public void setInRfidTriggerTagId(Long inRfidTriggerTagId) 
    {
        this.inRfidTriggerTagId = inRfidTriggerTagId;
    }

    public Long getInRfidTriggerTagId() 
    {
        return inRfidTriggerTagId;
    }
    public void setOutRfidStartTagId(Long outRfidStartTagId) 
    {
        this.outRfidStartTagId = outRfidStartTagId;
    }

    public Long getOutRfidStartTagId() 
    {
        return outRfidStartTagId;
    }
    public void setOutRfidTriggerTagId(Long outRfidTriggerTagId) 
    {
        this.outRfidTriggerTagId = outRfidTriggerTagId;
    }

    public Long getOutRfidTriggerTagId() 
    {
        return outRfidTriggerTagId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("equipNumber", getEquipNumber())
            .append("name", getName())
            .append("siteId", getSiteId())
            .append("locationId", getLocationId())
            .append("description", getDescription())
            .append("category", getCategory())
            .append("state", getState())
            .append("remark", getRemark())
            .append("deleteState", getDeleteState())
            .append("createdBy", getCreatedBy())
            .append("createdDate", getCreatedDate())
            .append("lastModifiedBy", getLastModifiedBy())
            .append("lastModifiedDate", getLastModifiedDate())
            .append("purpose", getPurpose())
            .append("model", getModel())
            .append("owner", getOwner())
            .append("manufactureDate", getManufactureDate())
            .append("admissionDate", getAdmissionDate())
            .append("serialNumber", getSerialNumber())
            .append("equipmentTypeId", getEquipmentTypeId())
            .append("status", getStatus())
            .append("manufacture", getManufacture())
            .append("inRfidTagId", getInRfidTagId())
            .append("outRfidTagId", getOutRfidTagId())
            .append("weightTagId", getWeightTagId())
            .append("inRfidFinishTagId", getInRfidFinishTagId())
            .append("outRfidFinishTagId", getOutRfidFinishTagId())
            .append("inRfidStartTagId", getInRfidStartTagId())
            .append("inRfidTriggerTagId", getInRfidTriggerTagId())
            .append("outRfidStartTagId", getOutRfidStartTagId())
            .append("outRfidTriggerTagId", getOutRfidTriggerTagId())
            .toString();
    }
}
