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
import com.iad.mom.prod.domain.ProdStep;
import com.iad.mom.prod.service.IProdStepService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 工序管理Controller
 * 
 * @author ruoyi
 * @date 2023-11-23
 */
@RestController
@RequestMapping("/prod/ProdStep")
public class ProdStepController extends BaseController
{
    @Autowired
    private IProdStepService prodStepService;

    /**
     * 查询工序管理列表
     */
    @PreAuthorize("@ss.hasPermi('prod:ProdStep:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProdStep prodStep)
    {
        startPage();
        List<ProdStep> list = prodStepService.selectProdStepList(prodStep);
        return getDataTable(list);
    }

    /**
     * 导出工序管理列表
     */
    @PreAuthorize("@ss.hasPermi('prod:ProdStep:export')")
    @Log(title = "工序管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProdStep prodStep)
    {
        List<ProdStep> list = prodStepService.selectProdStepList(prodStep);
        ExcelUtil<ProdStep> util = new ExcelUtil<ProdStep>(ProdStep.class);
        util.exportExcel(response, list, "工序管理数据");
    }

    /**
     * 获取工序管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('prod:ProdStep:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(prodStepService.selectProdStepById(id));
    }

    /**
     * 新增工序管理
     */
    @PreAuthorize("@ss.hasPermi('prod:ProdStep:add')")
    @Log(title = "工序管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProdStep prodStep)
    {
        return toAjax(prodStepService.insertProdStep(prodStep));
    }

    /**
     * 修改工序管理
     */
    @PreAuthorize("@ss.hasPermi('prod:ProdStep:edit')")
    @Log(title = "工序管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProdStep prodStep)
    {
        return toAjax(prodStepService.updateProdStep(prodStep));
    }

    /**
     * 删除工序管理
     */
    @PreAuthorize("@ss.hasPermi('prod:ProdStep:remove')")
    @Log(title = "工序管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(prodStepService.deleteProdStepByIds(ids));
    }
}
