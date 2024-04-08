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
import com.iad.mom.prod.domain.WorkOrder;
import com.iad.mom.prod.service.IWorkOrderService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 工单管理Controller
 * 
 * @author kris
 * @date 2023-11-21
 */
@RestController
@RequestMapping("/prod/order")
public class WorkOrderController extends BaseController
{
    @Autowired
    private IWorkOrderService workOrderService;

    /**
     * 查询工单管理列表
     */
    @PreAuthorize("@ss.hasPermi('prod:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkOrder workOrder)
    {
        startPage();
        List<WorkOrder> list = workOrderService.selectWorkOrderList(workOrder);
        return getDataTable(list);
    }

    /**
     * 导出工单管理列表
     */
    @PreAuthorize("@ss.hasPermi('prod:order:export')")
    @Log(title = "工单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkOrder workOrder)
    {
        List<WorkOrder> list = workOrderService.selectWorkOrderList(workOrder);
        ExcelUtil<WorkOrder> util = new ExcelUtil<WorkOrder>(WorkOrder.class);
        util.exportExcel(response, list, "工单管理数据");
    }

    /**
     * 获取工单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('prod:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(workOrderService.selectWorkOrderById(id));
    }

    /**
     * 新增工单管理
     */
    @PreAuthorize("@ss.hasPermi('prod:order:add')")
    @Log(title = "工单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkOrder workOrder)
    {
        return toAjax(workOrderService.insertWorkOrder(workOrder));
    }

    /**
     * 修改工单管理
     */
    @PreAuthorize("@ss.hasPermi('prod:order:edit')")
    @Log(title = "工单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkOrder workOrder)
    {
        return toAjax(workOrderService.updateWorkOrder(workOrder));
    }

    /**
     * 删除工单管理
     */
    @PreAuthorize("@ss.hasPermi('prod:order:remove')")
    @Log(title = "工单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(workOrderService.deleteWorkOrderByIds(ids));
    }
}
