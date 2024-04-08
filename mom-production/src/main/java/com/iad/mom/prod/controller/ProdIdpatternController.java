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
import com.iad.mom.prod.domain.ProdIdpattern;
import com.iad.mom.prod.service.IProdIdpatternService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * ID生成器Controller
 * 
 * @author ruoyi
 * @date 2023-12-06
 */
@RestController
@RequestMapping("/prod/Idpattern")
public class ProdIdpatternController extends BaseController
{
    @Autowired
    private IProdIdpatternService prodIdpatternService;

    /**
     * 查询ID生成器列表
     */
    @PreAuthorize("@ss.hasPermi('prod:Idpattern:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProdIdpattern prodIdpattern)
    {
        startPage();
        List<ProdIdpattern> list = prodIdpatternService.selectProdIdpatternList(prodIdpattern);
        return getDataTable(list);
    }

    /**
     * 导出ID生成器列表
     */
    @PreAuthorize("@ss.hasPermi('prod:Idpattern:export')")
    @Log(title = "ID生成器", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProdIdpattern prodIdpattern)
    {
        List<ProdIdpattern> list = prodIdpatternService.selectProdIdpatternList(prodIdpattern);
        ExcelUtil<ProdIdpattern> util = new ExcelUtil<ProdIdpattern>(ProdIdpattern.class);
        util.exportExcel(response, list, "ID生成器数据");
    }

    /**
     * 获取ID生成器详细信息
     */
    @PreAuthorize("@ss.hasPermi('prod:Idpattern:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(prodIdpatternService.selectProdIdpatternById(id));
    }

    /**
     * 新增ID生成器
     */
    @PreAuthorize("@ss.hasPermi('prod:Idpattern:add')")
    @Log(title = "ID生成器", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProdIdpattern prodIdpattern)
    {
        return toAjax(prodIdpatternService.insertProdIdpattern(prodIdpattern));
    }

    /**
     * 修改ID生成器
     */
    @PreAuthorize("@ss.hasPermi('prod:Idpattern:edit')")
    @Log(title = "ID生成器", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProdIdpattern prodIdpattern)
    {
        return toAjax(prodIdpatternService.updateProdIdpattern(prodIdpattern));
    }

    /**
     * 删除ID生成器
     */
    @PreAuthorize("@ss.hasPermi('prod:Idpattern:remove')")
    @Log(title = "ID生成器", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(prodIdpatternService.deleteProdIdpatternByIds(ids));
    }
}
