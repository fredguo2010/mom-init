package com.iad.mom.masterdata.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.masterdata.mapper.SiteMapper;
import com.iad.mom.masterdata.domain.Site;
import com.iad.mom.masterdata.service.ISiteService;

/**
 * 工厂Service业务层处理
 * 
 * @author ra
 * @date 2023-10-07
 */
@Service
public class SiteServiceImpl implements ISiteService 
{
    @Autowired
    private SiteMapper siteMapper;

    /**
     * 查询工厂
     * 
     * @param id 工厂主键
     * @return 工厂
     */
    @Override
    public Site selectSiteById(Long id)
    {
        return siteMapper.selectSiteById(id);
    }

    /**
     * 查询工厂列表
     * 
     * @param site 工厂
     * @return 工厂
     */
    @Override
    public List<Site> selectSiteList(Site site)
    {
        return siteMapper.selectSiteList(site);
    }

    /**
     * 新增工厂
     * 
     * @param site 工厂
     * @return 结果
     */
    @Override
    public int insertSite(Site site)
    {
        return siteMapper.insertSite(site);
    }

    /**
     * 修改工厂
     * 
     * @param site 工厂
     * @return 结果
     */
    @Override
    public int updateSite(Site site)
    {
        return siteMapper.updateSite(site);
    }

    /**
     * 批量删除工厂
     * 
     * @param ids 需要删除的工厂主键
     * @return 结果
     */
    @Override
    public int deleteSiteByIds(Long[] ids)
    {
        return siteMapper.deleteSiteByIds(ids);
    }

    /**
     * 删除工厂信息
     * 
     * @param id 工厂主键
     * @return 结果
     */
    @Override
    public int deleteSiteById(Long id)
    {
        return siteMapper.deleteSiteById(id);
    }
}
