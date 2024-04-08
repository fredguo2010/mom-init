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
import com.iad.mom.masterdata.domain.ProductionLine;
import com.iad.mom.masterdata.service.IProductionLineService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 产线Controller
 * 
 * @author iad
 * @date 2023-10-07
 */
@RestController
@RequestMapping("/masterdata/productionline")
public class ProductionLineController extends BaseController
{
    @Autowired
    private IProductionLineService productionLineService;

    /**
     * 查询产线列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:productionline:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductionLine productionLine)
    {
        startPage();
        List<ProductionLine> list = productionLineService.selectProductionLineList(productionLine);
        return getDataTable(list);
    }

    /**
     * 导出产线列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:productionline:export')")
    @Log(title = "产线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductionLine productionLine)
    {
        List<ProductionLine> list = productionLineService.selectProductionLineList(productionLine);
        ExcelUtil<ProductionLine> util = new ExcelUtil<ProductionLine>(ProductionLine.class);
        util.exportExcel(response, list, "产线数据");
    }

    /**
     * 获取产线详细信息
     */
    @PreAuthorize("@ss.hasPermi('masterdata:productionline:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(productionLineService.selectProductionLineById(id));
    }

    /**
     * 新增产线
     */
    @PreAuthorize("@ss.hasPermi('masterdata:productionline:add')")
    @Log(title = "产线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductionLine productionLine)
    {
        LoginUser loginUser = getLoginUser();
        String username = loginUser.getUsername();
        productionLine.setCreatedBy(username);
        productionLine.setCreatedDate(new Date());
        return toAjax(productionLineService.insertProductionLine(productionLine));
    }

    /**
     * 修改产线
     */
    @PreAuthorize("@ss.hasPermi('masterdata:productionline:edit')")
    @Log(title = "产线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductionLine productionLine)
    {
        LoginUser loginUser = getLoginUser();
        String username = loginUser.getUsername();
        productionLine.setLastModifiedBy(username);
        productionLine.setLastModifiedDate(new Date());
        return toAjax(productionLineService.updateProductionLine(productionLine));
    }

    /**
     * 删除产线
     */
    @PreAuthorize("@ss.hasPermi('masterdata:productionline:remove')")
    @Log(title = "产线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productionLineService.deleteProductionLineByIds(ids));
    }
}
