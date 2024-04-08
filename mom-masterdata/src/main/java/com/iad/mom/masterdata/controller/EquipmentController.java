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
import com.iad.mom.masterdata.domain.Equipment;
import com.iad.mom.masterdata.service.IEquipmentService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 设备Controller
 * 
 * @author ra
 * @date 2023-10-08
 */
@RestController
@RequestMapping("/masterdata/equipment")
public class EquipmentController extends BaseController
{
    @Autowired
    private IEquipmentService equipmentService;

    /**
     * 查询设备列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:equipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Equipment equipment)
    {
        startPage();
        List<Equipment> list = equipmentService.selectEquipmentList(equipment);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('masterdata:equipment:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Equipment equipment)
    {
        List<Equipment> list = equipmentService.selectEquipmentList(equipment);
        ExcelUtil<Equipment> util = new ExcelUtil<Equipment>(Equipment.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('masterdata:equipment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(equipmentService.selectEquipmentById(id));
    }

    /**
     * 新增设备
     */
    @PreAuthorize("@ss.hasPermi('masterdata:equipment:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Equipment equipment)
    {
        LoginUser loginUser = getLoginUser();
        String username = loginUser.getUsername();
        equipment.setCreatedBy(username);
        equipment.setCreatedDate(new Date());
        return toAjax(equipmentService.insertEquipment(equipment));
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('masterdata:equipment:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Equipment equipment)
    {
        LoginUser loginUser = getLoginUser();
        String username = loginUser.getUsername();
        equipment.setLastModifiedBy(username);
        equipment.setLastModifiedDate(new Date());
        return toAjax(equipmentService.updateEquipment(equipment));
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('masterdata:equipment:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(equipmentService.deleteEquipmentByIds(ids));
    }
}
