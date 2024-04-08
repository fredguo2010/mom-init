package com.iad.mom.masterdata.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.iad.mom.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iad.mom.common.core.domain.BaseEntity;

/**
 * 物料对象 md_part
 * 
 * @author ra
 * @date 2023-10-04
 */
public class Material extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 物料号 */
    @Excel(name = "物料号")
    private String partNumber;

    /** 物料名 */
    @Excel(name = "物料名")
    private String name;

    /** 版本 */
    @Excel(name = "版本")
    private String revision;

    /** bom_id */
    @Excel(name = "bom_id")
    private Long bomId;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 起效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "起效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectiveStart;

    /** 终效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "终效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectiveEnd;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** site_id */
    @Excel(name = "site_id")
    private Long siteId;

    /** supplier_id */
    @Excel(name = "supplier_id")
    private Long supplierId;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplierName;

    /** lot_generator_id */
    @Excel(name = "lot_generator_id")
    private Long lotGeneratorId;

    /** unit_generator_id */
    @Excel(name = "unit_generator_id")
    private Long unitGeneratorId;

    /** mf_location */
    @Excel(name = "mf_location")
    private String mfLocation;

    /** mf_part_number */
    @Excel(name = "mf_part_number")
    private String mfPartNumber;

    /** texture_id */
    @Excel(name = "texture_id")
    private Long textureId;

    /** 规格 */
    @Excel(name = "规格")
    private String specification;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** uom_id */
    @Excel(name = "uom_id")
    private Long uomId;

    /** 安全库存 */
    @Excel(name = "安全库存")
    private BigDecimal safetyStock;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Long deleteState;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createdBy;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    /** 最后修改者 */
    @Excel(name = "最后修改者")
    private String lastModifiedBy;

    /** 最后修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastModifiedDate;

    /** inner_code */
    @Excel(name = "inner_code")
    private String innerCode;

    /** calculate_exp */
    @Excel(name = "calculate_exp")
    private String calculateExp;

    /** exp_rule */
    @Excel(name = "exp_rule")
    private Long expRule;

    /** exp_rule_time */
    @Excel(name = "exp_rule_time")
    private Long expRuleTime;

    /** exp_rule_uom */
    @Excel(name = "exp_rule_uom")
    private Long expRuleUom;

    /** 重量 */
    @Excel(name = "重量")
    private BigDecimal weight;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPartNumber(String partNumber) 
    {
        this.partNumber = partNumber;
    }

    public String getPartNumber() 
    {
        return partNumber;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setRevision(String revision) 
    {
        this.revision = revision;
    }

    public String getRevision() 
    {
        return revision;
    }
    public void setBomId(Long bomId) 
    {
        this.bomId = bomId;
    }

    public Long getBomId() 
    {
        return bomId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setEffectiveStart(Date effectiveStart) 
    {
        this.effectiveStart = effectiveStart;
    }

    public Date getEffectiveStart() 
    {
        return effectiveStart;
    }
    public void setEffectiveEnd(Date effectiveEnd) 
    {
        this.effectiveEnd = effectiveEnd;
    }

    public Date getEffectiveEnd() 
    {
        return effectiveEnd;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setSiteId(Long siteId) 
    {
        this.siteId = siteId;
    }

    public Long getSiteId() 
    {
        return siteId;
    }
    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setLotGeneratorId(Long lotGeneratorId) 
    {
        this.lotGeneratorId = lotGeneratorId;
    }

    public Long getLotGeneratorId() 
    {
        return lotGeneratorId;
    }
    public void setUnitGeneratorId(Long unitGeneratorId) 
    {
        this.unitGeneratorId = unitGeneratorId;
    }

    public Long getUnitGeneratorId() 
    {
        return unitGeneratorId;
    }
    public void setMfLocation(String mfLocation) 
    {
        this.mfLocation = mfLocation;
    }

    public String getMfLocation() 
    {
        return mfLocation;
    }
    public void setMfPartNumber(String mfPartNumber) 
    {
        this.mfPartNumber = mfPartNumber;
    }

    public String getMfPartNumber() 
    {
        return mfPartNumber;
    }
    public void setTextureId(Long textureId) 
    {
        this.textureId = textureId;
    }

    public Long getTextureId() 
    {
        return textureId;
    }
    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }
    public void setUomId(Long uomId) 
    {
        this.uomId = uomId;
    }

    public Long getUomId() 
    {
        return uomId;
    }
    public void setSafetyStock(BigDecimal safetyStock) 
    {
        this.safetyStock = safetyStock;
    }

    public BigDecimal getSafetyStock() 
    {
        return safetyStock;
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
    public void setInnerCode(String innerCode) 
    {
        this.innerCode = innerCode;
    }

    public String getInnerCode() 
    {
        return innerCode;
    }
    public void setCalculateExp(String calculateExp) 
    {
        this.calculateExp = calculateExp;
    }

    public String getCalculateExp() 
    {
        return calculateExp;
    }
    public void setExpRule(Long expRule) 
    {
        this.expRule = expRule;
    }

    public Long getExpRule() 
    {
        return expRule;
    }
    public void setExpRuleTime(Long expRuleTime) 
    {
        this.expRuleTime = expRuleTime;
    }

    public Long getExpRuleTime() 
    {
        return expRuleTime;
    }
    public void setExpRuleUom(Long expRuleUom) 
    {
        this.expRuleUom = expRuleUom;
    }

    public Long getExpRuleUom() 
    {
        return expRuleUom;
    }
    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("partNumber", getPartNumber())
            .append("name", getName())
            .append("revision", getRevision())
            .append("bomId", getBomId())
            .append("description", getDescription())
            .append("effectiveStart", getEffectiveStart())
            .append("effectiveEnd", getEffectiveEnd())
            .append("type", getType())
            .append("siteId", getSiteId())
            .append("supplierId", getSupplierId())
            .append("supplierName", getSupplierName())
            .append("lotGeneratorId", getLotGeneratorId())
            .append("unitGeneratorId", getUnitGeneratorId())
            .append("mfLocation", getMfLocation())
            .append("mfPartNumber", getMfPartNumber())
            .append("textureId", getTextureId())
            .append("specification", getSpecification())
            .append("brand", getBrand())
            .append("state", getState())
            .append("uomId", getUomId())
            .append("safetyStock", getSafetyStock())
            .append("remark", getRemark())
            .append("deleteState", getDeleteState())
            .append("createdBy", getCreatedBy())
            .append("createdDate", getCreatedDate())
            .append("lastModifiedBy", getLastModifiedBy())
            .append("lastModifiedDate", getLastModifiedDate())
            .append("innerCode", getInnerCode())
            .append("calculateExp", getCalculateExp())
            .append("expRule", getExpRule())
            .append("expRuleTime", getExpRuleTime())
            .append("expRuleUom", getExpRuleUom())
            .append("weight", getWeight())
            .toString();
    }
}
