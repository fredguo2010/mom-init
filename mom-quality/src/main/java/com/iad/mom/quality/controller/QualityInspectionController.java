package com.iad.mom.quality.controller;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.iad.mom.quality.common.Statistics;
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
import com.iad.mom.quality.domain.QualityInspection;
import com.iad.mom.quality.service.IQualityInspectionService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 质量检测Controller
 * 
 * @author rchen9
 * @date 2023-11-21
 */
@RestController
@RequestMapping("/quality/inspection")
public class QualityInspectionController extends BaseController
{
    @Autowired
    private IQualityInspectionService qualityInspectionService;

    /**
     * 查询质量检测列表
     */
    @PreAuthorize("@ss.hasPermi('quality:inspection:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualityInspection qualityInspection)
    {
        startPage();
        List<QualityInspection> list = qualityInspectionService.selectQualityInspectionList(qualityInspection);
        return getDataTable(list);
    }

    /**
     * 查询质量检测列表
     */
    @PreAuthorize("@ss.hasPermi('quality:inspection:list')")
    @GetMapping("/listAll")
    public List<QualityInspection> listAll(QualityInspection qualityInspection)
    {
        List<QualityInspection> list = qualityInspectionService.selectQualityInspectionList(qualityInspection);
        return list;
    }

    /**
     * 查询spc数据
     */
    @GetMapping("/spcData")
    public Map<String, Object> getSpcData(QualityInspection qualityInspection) {
        List<Double> avgArray = new ArrayList<>();
        List<Double> uclArray = new ArrayList<>();
        List<Double> lclArray = new ArrayList<>();
        List<String> xAxisArray = new ArrayList<>();
        List<QualityInspection> list = qualityInspectionService.selectQualityInspectionList(qualityInspection);
        List<Double> inspectionValues = new ArrayList<>();

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.UP);

        for (QualityInspection inspection : list) {
            Long inspectionValue = inspection.getInspectionValue();
            inspectionValues.add(Double.valueOf(nf.format(inspectionValue.doubleValue())));
            xAxisArray.add(inspection.getId());
        }

        Statistics statistics = new Statistics();
        double avg = statistics.average(inspectionValues);
        double mrAvg = statistics.movingRangeAvg(inspectionValues);
        double E2 = 2.66;
        double delta = E2 * mrAvg;
        double ucl = avg + delta;
        double lcl = avg - delta;
        double stdDev = statistics.stdDeviation(inspectionValues);
        double cp = (ucl - lcl) / (6 * stdDev);

        double minCpk = Math.min((ucl - avg) / (3 * stdDev), (avg - lcl) / (3 * stdDev));
        double cpk = minCpk;

        for (int i = 0; i < inspectionValues.size(); i++) {
            avgArray.add(Double.valueOf(nf.format(avg)));
            uclArray.add(Double.valueOf(nf.format(ucl)));
            lclArray.add(Double.valueOf(nf.format(lcl)));
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("inspectionValues", inspectionValues);
        resultMap.put("uclArray", uclArray);
        resultMap.put("lclArray", lclArray);
        resultMap.put("xAxisData", xAxisArray);
        resultMap.put("avgArray", avgArray);
        // Add cp, cpk
        // cp = (USL - LSL) / (6 * std dev)
        // cpk = Min[(USL - Mean)/(3 * StdDev), (Mean - LSL)/(3 * StdDev)]
        resultMap.put("cp", Double.valueOf(cp));
        resultMap.put("cpk", Double.valueOf(cpk));

        return resultMap;
    }

    /**
     * 导出质量检测列表
     */
    @PreAuthorize("@ss.hasPermi('quality:inspection:export')")
    @Log(title = "质量检测", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QualityInspection qualityInspection)
    {
        List<QualityInspection> list = qualityInspectionService.selectQualityInspectionList(qualityInspection);
        ExcelUtil<QualityInspection> util = new ExcelUtil<QualityInspection>(QualityInspection.class);
        util.exportExcel(response, list, "质量检测数据");
    }

    /**
     * 获取质量检测详细信息
     */
    @PreAuthorize("@ss.hasPermi('quality:inspection:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(qualityInspectionService.selectQualityInspectionById(id));
    }

    /**
     * 新增质量检测
     */
    @PreAuthorize("@ss.hasPermi('quality:inspection:add')")
    @Log(title = "质量检测", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QualityInspection qualityInspection)
    {
        return toAjax(qualityInspectionService.insertQualityInspection(qualityInspection));
    }

    /**
     * 修改质量检测
     */
    @PreAuthorize("@ss.hasPermi('quality:inspection:edit')")
    @Log(title = "质量检测", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualityInspection qualityInspection)
    {
        return toAjax(qualityInspectionService.updateQualityInspection(qualityInspection));
    }

    /**
     * 删除质量检测
     */
    @PreAuthorize("@ss.hasPermi('quality:inspection:remove')")
    @Log(title = "质量检测", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(qualityInspectionService.deleteQualityInspectionByIds(ids));
    }
}
