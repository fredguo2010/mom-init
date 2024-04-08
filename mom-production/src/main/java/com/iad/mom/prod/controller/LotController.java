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
import com.iad.mom.prod.domain.Lot;
import com.iad.mom.prod.service.ILotService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 批次管理Controller
 * 
 * @author kris
 * @date 2023-11-22
 */
@RestController
@RequestMapping("/prod/lot")
public class LotController extends BaseController
{
    @Autowired
    private ILotService lotService;

    /**
     * 查询批次管理列表
     */
    @PreAuthorize("@ss.hasPermi('prod:lot:list')")
    @GetMapping("/list")
    public TableDataInfo list(Lot lot)
    {
        startPage();
        List<Lot> list = lotService.selectLotList(lot);
        return getDataTable(list);
    }

    /**
     * 导出批次管理列表
     */
    @PreAuthorize("@ss.hasPermi('prod:lot:export')")
    @Log(title = "批次管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Lot lot)
    {
        List<Lot> list = lotService.selectLotList(lot);
        ExcelUtil<Lot> util = new ExcelUtil<Lot>(Lot.class);
        util.exportExcel(response, list, "批次管理数据");
    }

    /**
     * 获取批次管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('prod:lot:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(lotService.selectLotById(id));
    }

    /**
     * 新增批次管理
     */
    @PreAuthorize("@ss.hasPermi('prod:lot:add')")
    @Log(title = "批次管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Lot lot)
    {
        return toAjax(lotService.insertLot(lot));
    }

    /**
     * 修改批次管理
     */
    @PreAuthorize("@ss.hasPermi('prod:lot:edit')")
    @Log(title = "批次管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Lot lot)
    {
        return toAjax(lotService.updateLot(lot));
    }

    /**
     * 删除批次管理
     */
    @PreAuthorize("@ss.hasPermi('prod:lot:remove')")
    @Log(title = "批次管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(lotService.deleteLotByIds(ids));
    }
}
