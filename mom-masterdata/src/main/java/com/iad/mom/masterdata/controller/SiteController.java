package com.iad.mom.masterdata.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.iad.mom.common.core.domain.model.LoginUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iad.mom.common.annotation.Log;
import com.iad.mom.common.core.controller.BaseController;
import com.iad.mom.common.core.domain.AjaxResult;
import com.iad.mom.common.enums.BusinessType;
import com.iad.mom.masterdata.domain.Site;
import com.iad.mom.masterdata.service.ISiteService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 工厂Controller
 * 
 * @author ra
 * @date 2023-10-07
 */
@RestController
@RequestMapping("/masterdata/site")
public class SiteController extends BaseController
{
    @Autowired
    private ISiteService siteService;

    /**
     * 查询工厂列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:site:list')")
    @GetMapping("/list")
    public TableDataInfo list(Site site)
    {
        startPage();
        List<Site> list = siteService.selectSiteList(site);
        return getDataTable(list);
    }

    /**
     * 导出工厂列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:site:export')")
    @Log(title = "工厂", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Site site)
    {
        List<Site> list = siteService.selectSiteList(site);
        ExcelUtil<Site> util = new ExcelUtil<Site>(Site.class);
        util.exportExcel(response, list, "工厂数据");
    }

    /**
     * 获取工厂详细信息
     */
    @PreAuthorize("@ss.hasPermi('masterdata:site:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(siteService.selectSiteById(id));
    }

    /**
     * 新增工厂
     */
    @PreAuthorize("@ss.hasPermi('masterdata:site:add')")
    @Log(title = "工厂", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Site site)
    {
        LoginUser loginUser = getLoginUser();
        String username = loginUser.getUsername();
        site.setCreatedBy(username);
        site.setCreatedDate(new Date());
        return toAjax(siteService.insertSite(site));
    }

    /**
     * 修改工厂
     */
    @PreAuthorize("@ss.hasPermi('masterdata:site:edit')")
    @Log(title = "工厂", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Site site)
    {
        LoginUser loginUser = getLoginUser();
        String username = loginUser.getUsername();
        site.setLastModifiedBy(username);
        site.setLastModifiedDate(new Date());
        return toAjax(siteService.updateSite(site));
    }

    /**
     * 删除工厂
     */
    @PreAuthorize("@ss.hasPermi('masterdata:site:remove')")
    @Log(title = "工厂", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(siteService.deleteSiteByIds(ids));
    }
}
