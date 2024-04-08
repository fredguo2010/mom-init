package com.iad.mom.masterdata.service;

import java.util.List;
import com.iad.mom.masterdata.domain.Site;

/**
 * 工厂Service接口
 * 
 * @author ra
 * @date 2023-10-07
 */
public interface ISiteService 
{
    /**
     * 查询工厂
     * 
     * @param id 工厂主键
     * @return 工厂
     */
    public Site selectSiteById(Long id);

    /**
     * 查询工厂列表
     * 
     * @param site 工厂
     * @return 工厂集合
     */
    public List<Site> selectSiteList(Site site);

    /**
     * 新增工厂
     * 
     * @param site 工厂
     * @return 结果
     */
    public int insertSite(Site site);

    /**
     * 修改工厂
     * 
     * @param site 工厂
     * @return 结果
     */
    public int updateSite(Site site);

    /**
     * 批量删除工厂
     * 
     * @param ids 需要删除的工厂主键集合
     * @return 结果
     */
    public int deleteSiteByIds(Long[] ids);

    /**
     * 删除工厂信息
     * 
     * @param id 工厂主键
     * @return 结果
     */
    public int deleteSiteById(Long id);
}
