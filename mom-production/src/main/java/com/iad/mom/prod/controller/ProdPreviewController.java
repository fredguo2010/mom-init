package com.iad.mom.prod.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.web.multipart.MultipartFile;

import com.iad.mom.common.annotation.Log;
import com.iad.mom.common.core.controller.BaseController;
import com.iad.mom.common.core.domain.AjaxResult;
import com.iad.mom.common.enums.BusinessType;
import com.iad.mom.prod.domain.ProdPreview;
import com.iad.mom.prod.service.IProdPreviewService;
import com.iad.mom.common.utils.poi.ExcelUtil;
import com.iad.mom.common.core.page.TableDataInfo;

/**
 * 操作手册Controller
 * 
 * @author ruoyi
 * @date 2023-12-14
 */
@RestController
@RequestMapping("/prod/preview")
public class ProdPreviewController extends BaseController {
    @Autowired
    private IProdPreviewService prodPreviewService;

    /**
     * 查询操作手册列表
     */
    @PreAuthorize("@ss.hasPermi('prod:preview:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProdPreview prodPreview) {
        startPage();
        List<ProdPreview> list = prodPreviewService.selectProdPreviewList(prodPreview);
        return getDataTable(list);
    }

    /**
     * 导出操作手册列表
     */
    @PreAuthorize("@ss.hasPermi('prod:preview:export')")
    @Log(title = "操作手册", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProdPreview prodPreview) {
        List<ProdPreview> list = prodPreviewService.selectProdPreviewList(prodPreview);
        ExcelUtil<ProdPreview> util = new ExcelUtil<ProdPreview>(ProdPreview.class);
        util.exportExcel(response, list, "操作手册数据");
    }

    /**
     * 获取操作手册详细信息
     */
    @PreAuthorize("@ss.hasPermi('prod:preview:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(prodPreviewService.selectProdPreviewById(id));
    }

    /**
     * 新增操作手册
     */
    @PreAuthorize("@ss.hasPermi('prod:preview:add')")
    @Log(title = "操作手册", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProdPreview prodPreview) {
        return toAjax(prodPreviewService.insertProdPreview(prodPreview));
    }

    /**
     * 修改操作手册
     */
    @PreAuthorize("@ss.hasPermi('prod:preview:edit')")
    @Log(title = "操作手册", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProdPreview prodPreview) {
        return toAjax(prodPreviewService.updateProdPreview(prodPreview));
    }

    /**
     * 删除操作手册
     */
    @PreAuthorize("@ss.hasPermi('prod:preview:remove')")
    @Log(title = "操作手册", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(prodPreviewService.deleteProdPreviewByIds(ids));
    }

    @PostMapping(value = "/uploadFile")
    public AjaxResult uploadFile(MultipartFile file) throws IOException {

        saveFileToCDrive(file.getInputStream(), file.getOriginalFilename());
        return AjaxResult.success(AjaxResult.success());
    }

    public static void saveFileToCDrive(InputStream fileInputStream, String fileName) {
        String path = "C:\\download\\"; // C盘根目录
        String filePath = path + fileName;

        try (OutputStream outputStream = new FileOutputStream(new File(filePath))) {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            System.out.println("文件保存成功：" + filePath);
        } catch (IOException e) {
            System.out.println("文件保存失败：" + e.getMessage());
        }
    }

}
