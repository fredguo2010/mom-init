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
import com.iad.mom.masterdata.domain.WorkCenter;
import com.iad.mom.masterdata.service.IWorkCenterService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 工作中心Controller
 * 
 * @author iad
 * @date 2023-10-08
 */
@RestController
@RequestMapping("/masterdata/WorkCenter")
public class WorkCenterController extends BaseController
{
    @Autowired
    private IWorkCenterService workCenterService;

    /**
     * 查询工作中心列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:WorkCenter:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkCenter workCenter)
    {
        startPage();
        List<WorkCenter> list = workCenterService.selectWorkCenterList(workCenter);
        return getDataTable(list);
    }

    /**
     * 导出工作中心列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:WorkCenter:export')")
    @Log(title = "工作中心", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkCenter workCenter)
    {
        List<WorkCenter> list = workCenterService.selectWorkCenterList(workCenter);
        ExcelUtil<WorkCenter> util = new ExcelUtil<WorkCenter>(WorkCenter.class);
        util.exportExcel(response, list, "工作中心数据");
    }

    /**
     * 获取工作中心详细信息
     */
    @PreAuthorize("@ss.hasPermi('masterdata:WorkCenter:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(workCenterService.selectWorkCenterById(id));
    }

    /**
     * 新增工作中心
     */
    @PreAuthorize("@ss.hasPermi('masterdata:WorkCenter:add')")
    @Log(title = "工作中心", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkCenter workCenter)
    {
        LoginUser loginUser = getLoginUser();
        String username = loginUser.getUsername();
        workCenter.setCreatedBy(username);
        workCenter.setCreatedDate(new Date());
        return toAjax(workCenterService.insertWorkCenter(workCenter));
    }

    /**
     * 修改工作中心
     */
    @PreAuthorize("@ss.hasPermi('masterdata:WorkCenter:edit')")
    @Log(title = "工作中心", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkCenter workCenter)
    {
        LoginUser loginUser = getLoginUser();
        String username = loginUser.getUsername();
        workCenter.setLastModifiedBy(username);
        workCenter.setLastModifiedDate(new Date());
        return toAjax(workCenterService.updateWorkCenter(workCenter));
    }

    /**
     * 删除工作中心
     */
    @PreAuthorize("@ss.hasPermi('masterdata:WorkCenter:remove')")
    @Log(title = "工作中心", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(workCenterService.deleteWorkCenterByIds(ids));
    }
}
