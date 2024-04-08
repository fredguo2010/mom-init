package com.iad.mom.masterdata.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iad.mom.common.annotation.Excel;
import com.iad.mom.common.core.domain.BaseEntity;

/**
 * 工厂对象 md_site
 * 
 * @author ra
 * @date 2023-10-07
 */
public class Site extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 工厂编号 */
    @Excel(name = "工厂编号")
    private String siteNumber;

    /** 工厂名 */
    @Excel(name = "工厂名")
    private String name;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 邮编 */
    @Excel(name = "邮编")
    private String postCode;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String telNumber;

    /** 传真号 */
    @Excel(name = "传真号")
    private String faxNumber;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 市区 */
    @Excel(name = "市区")
    private String timeZone;

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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSiteNumber(String siteNumber) 
    {
        this.siteNumber = siteNumber;
    }

    public String getSiteNumber() 
    {
        return siteNumber;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPostCode(String postCode) 
    {
        this.postCode = postCode;
    }

    public String getPostCode() 
    {
        return postCode;
    }
    public void setTelNumber(String telNumber) 
    {
        this.telNumber = telNumber;
    }

    public String getTelNumber() 
    {
        return telNumber;
    }
    public void setFaxNumber(String faxNumber) 
    {
        this.faxNumber = faxNumber;
    }

    public String getFaxNumber() 
    {
        return faxNumber;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setTimeZone(String timeZone) 
    {
        this.timeZone = timeZone;
    }

    public String getTimeZone() 
    {
        return timeZone;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("siteNumber", getSiteNumber())
            .append("name", getName())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("address", getAddress())
            .append("postCode", getPostCode())
            .append("telNumber", getTelNumber())
            .append("faxNumber", getFaxNumber())
            .append("state", getState())
            .append("description", getDescription())
            .append("timeZone", getTimeZone())
            .append("remark", getRemark())
            .append("deleteState", getDeleteState())
            .append("createdBy", getCreatedBy())
            .append("createdDate", getCreatedDate())
            .append("lastModifiedBy", getLastModifiedBy())
            .append("lastModifiedDate", getLastModifiedDate())
            .toString();
    }
}
