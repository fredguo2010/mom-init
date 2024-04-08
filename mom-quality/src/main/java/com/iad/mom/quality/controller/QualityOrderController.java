package com.iad.mom.quality.controller;

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
import com.iad.mom.quality.domain.QualityOrder;
import com.iad.mom.quality.service.IQualityOrderService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 质量工单Controller
 * 
 * @author rchen9
 * @date 2023-11-21
 */
@RestController
@RequestMapping("/quality/order")
public class QualityOrderController extends BaseController
{
    @Autowired
    private IQualityOrderService qualityOrderService;

    /**
     * 查询质量工单列表
     */
    @PreAuthorize("@ss.hasPermi('quality:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualityOrder qualityOrder)
    {
        startPage();
        List<QualityOrder> list = qualityOrderService.selectQualityOrderList(qualityOrder);
        return getDataTable(list);
    }

    /**
     * 导出质量工单列表
     */
    @PreAuthorize("@ss.hasPermi('quality:order:export')")
    @Log(title = "质量工单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QualityOrder qualityOrder)
    {
        List<QualityOrder> list = qualityOrderService.selectQualityOrderList(qualityOrder);
        ExcelUtil<QualityOrder> util = new ExcelUtil<QualityOrder>(QualityOrder.class);
        util.exportExcel(response, list, "质量工单数据");
    }

    /**
     * 获取质量工单详细信息
     */
    @PreAuthorize("@ss.hasPermi('quality:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(qualityOrderService.selectQualityOrderById(id));
    }

    /**
     * 新增质量工单
     */
    @PreAuthorize("@ss.hasPermi('quality:order:add')")
    @Log(title = "质量工单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QualityOrder qualityOrder)
    {
        return toAjax(qualityOrderService.insertQualityOrder(qualityOrder));
    }

    /**
     * 修改质量工单
     */
    @PreAuthorize("@ss.hasPermi('quality:order:edit')")
    @Log(title = "质量工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualityOrder qualityOrder)
    {
        return toAjax(qualityOrderService.updateQualityOrder(qualityOrder));
    }

    /**
     * 删除质量工单
     */
    @PreAuthorize("@ss.hasPermi('quality:order:remove')")
    @Log(title = "质量工单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(qualityOrderService.deleteQualityOrderByIds(ids));
    }
}
