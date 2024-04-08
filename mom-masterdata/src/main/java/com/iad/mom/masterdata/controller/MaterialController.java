package com.iad.mom.masterdata.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.iad.mom.masterdata.domain.Material;
import com.iad.mom.common.annotation.Log;
import com.iad.mom.common.utils.poi.ExcelUtil;
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
import com.iad.mom.common.core.controller.BaseController;
import com.iad.mom.common.core.domain.AjaxResult;
import com.iad.mom.common.enums.BusinessType;
import com.iad.mom.masterdata.service.IMaterialService;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 物料Controller
 * 
 * @author ra
 * @date 2023-10-04
 */
@RestController
@RequestMapping("/masterdata/part")
public class MaterialController extends BaseController
{
    @Autowired
    private IMaterialService materialService;

    /**
     * 查询物料列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:part:list')")
    @GetMapping("/list")
    public TableDataInfo list(Material material)
    {
        startPage();
        List<Material> list = materialService.selectMaterialList(material);
        return getDataTable(list);
    }

    /**
     * 导出物料列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:part:export')")
    @Log(title = "物料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Material material)
    {
        List<Material> list = materialService.selectMaterialList(material);
        ExcelUtil<Material> util = new ExcelUtil<Material>(Material.class);
        util.exportExcel(response, list, "物料数据");
    }

    /**
     * 获取物料详细信息
     */
    @PreAuthorize("@ss.hasPermi('masterdata:part:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(materialService.selectMaterialById(id));
    }

    /**
     * 新增物料
     */
    @PreAuthorize("@ss.hasPermi('masterdata:part:add')")
    @Log(title = "物料", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Material material)
    {
        return toAjax(materialService.insertMaterial(material));
    }

    /**
     * 修改物料
     */
    @PreAuthorize("@ss.hasPermi('masterdata:part:edit')")
    @Log(title = "物料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Material material)
    {
        return toAjax(materialService.updateMaterial(material));
    }

    /**
     * 删除物料
     */
    @PreAuthorize("@ss.hasPermi('masterdata:part:remove')")
    @Log(title = "物料", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(materialService.deleteMaterialByIds(ids));
    }
}
