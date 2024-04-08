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
import com.iad.mom.prod.domain.ProductionPlan;
import com.iad.mom.prod.service.IProductionPlanService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 生产计划Controller
 * 
 * @author iad
 * @date 2023-11-20
 */
@RestController
@RequestMapping("/prod/plan")
public class ProductionPlanController extends BaseController
{
    @Autowired
    private IProductionPlanService productionPlanService;

    /**
     * 查询生产计划列表
     */
    @PreAuthorize("@ss.hasPermi('prod:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductionPlan productionPlan)
    {
        startPage();
        List<ProductionPlan> list = productionPlanService.selectProductionPlanList(productionPlan);
        return getDataTable(list);
    }

    /**
     * 导出生产计划列表
     */
    @PreAuthorize("@ss.hasPermi('prod:plan:export')")
    @Log(title = "生产计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductionPlan productionPlan)
    {
        List<ProductionPlan> list = productionPlanService.selectProductionPlanList(productionPlan);
        ExcelUtil<ProductionPlan> util = new ExcelUtil<ProductionPlan>(ProductionPlan.class);
        util.exportExcel(response, list, "生产计划数据");
    }

    /**
     * 获取生产计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('prod:plan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(productionPlanService.selectProductionPlanById(id));
    }

    /**
     * 新增生产计划
     */
    @PreAuthorize("@ss.hasPermi('prod:plan:add')")
    @Log(title = "生产计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductionPlan productionPlan)
    {
        return toAjax(productionPlanService.insertProductionPlan(productionPlan));
    }

    /**
     * 修改生产计划
     */
    @PreAuthorize("@ss.hasPermi('prod:plan:edit')")
    @Log(title = "生产计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductionPlan productionPlan)
    {
        return toAjax(productionPlanService.updateProductionPlan(productionPlan));
    }

    /**
     * 删除生产计划
     */
    @PreAuthorize("@ss.hasPermi('prod:plan:remove')")
    @Log(title = "生产计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(productionPlanService.deleteProductionPlanByIds(ids));
    }
}
