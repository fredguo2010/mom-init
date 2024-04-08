package com.iad.mom.prod.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.iad.mom.prod.domain.ProdRoute;
import com.iad.mom.prod.service.IProdRouteService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 工艺路线Controller
 * 
 * @author ruoyi
 * @date 2023-11-27
 */
@RestController
@RequestMapping("/prod/prodRoute")
public class ProdRouteController extends BaseController
{
    @Autowired
    private IProdRouteService prodRouteService;

    /**
     * 查询工艺路线列表
     */
    @PreAuthorize("@ss.hasPermi('prod:prodRoute:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProdRoute prodRoute)
    {
        startPage();
        List<ProdRoute> list = prodRouteService.selectProdRouteList(prodRoute);
        return getDataTable(list);
    }

    /**
     * 导出工艺路线列表
     */
    @PreAuthorize("@ss.hasPermi('prod:prodRoute:export')")
    @Log(title = "工艺路线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProdRoute prodRoute)
    {
        List<ProdRoute> list = prodRouteService.selectProdRouteList(prodRoute);
        ExcelUtil<ProdRoute> util = new ExcelUtil<ProdRoute>(ProdRoute.class);
        util.exportExcel(response, list, "工艺路线数据");
    }

    /**
     * 获取工艺路线详细信息
     */
    @PreAuthorize("@ss.hasPermi('prod:prodRoute:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(prodRouteService.selectProdRouteById(id));
    }

    /**
     * 新增工艺路线
     */
    @PreAuthorize("@ss.hasPermi('prod:prodRoute:add')")
    @Log(title = "工艺路线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProdRoute prodRoute)
    {
        return toAjax(prodRouteService.insertProdRoute(prodRoute));
    }

    /**
     * 修改工艺路线
     */
    @PreAuthorize("@ss.hasPermi('prod:prodRoute:edit')")
    @Log(title = "工艺路线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProdRoute prodRoute)
    {
        return toAjax(prodRouteService.updateProdRoute(prodRoute));
    }

    /**
     * 删除工艺路线
     */
    @PreAuthorize("@ss.hasPermi('prod:prodRoute:remove')")
    @Log(title = "工艺路线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(prodRouteService.deleteProdRouteByIds(ids));
    }
}
