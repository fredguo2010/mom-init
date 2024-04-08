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
import com.iad.mom.masterdata.domain.Area;
import com.iad.mom.masterdata.service.IAreaService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 车间Controller
 * 
 * @author ra
 * @date 2023-10-07
 */
@RestController
@RequestMapping("/masterdata/area")
public class AreaController extends BaseController
{
    @Autowired
    private IAreaService areaService;

    /**
     * 查询车间列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:area:list')")
    @GetMapping("/list")
    public TableDataInfo list(Area area)
    {
        startPage();
        List<Area> list = areaService.selectAreaList(area);
        return getDataTable(list);
    }

    /**
     * 导出车间列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:area:export')")
    @Log(title = "车间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Area area)
    {
        List<Area> list = areaService.selectAreaList(area);
        ExcelUtil<Area> util = new ExcelUtil<Area>(Area.class);
        util.exportExcel(response, list, "车间数据");
    }

    /**
     * 获取车间详细信息
     */
    @PreAuthorize("@ss.hasPermi('masterdata:area:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(areaService.selectAreaById(id));
    }

    /**
     * 新增车间
     */
    @PreAuthorize("@ss.hasPermi('masterdata:area:add')")
    @Log(title = "车间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Area area)
    {
        LoginUser loginUser = getLoginUser();
        String username = loginUser.getUsername();
        area.setCreatedBy(username);
        area.setCreatedDate(new Date());
        return toAjax(areaService.insertArea(area));
    }

    /**
     * 修改车间
     */
    @PreAuthorize("@ss.hasPermi('masterdata:area:edit')")
    @Log(title = "车间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Area area)
    {
        LoginUser loginUser = getLoginUser();
        String username = loginUser.getUsername();
        area.setLastModifiedBy(username);
        area.setLastModifiedDate(new Date());
        return toAjax(areaService.updateArea(area));
    }

    /**
     * 删除车间
     */
    @PreAuthorize("@ss.hasPermi('masterdata:area:remove')")
    @Log(title = "车间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(areaService.deleteAreaByIds(ids));
    }
}
