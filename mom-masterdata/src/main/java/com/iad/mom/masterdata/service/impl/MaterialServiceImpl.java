package com.iad.mom.masterdata.service.impl;

import java.util.List;

import com.iad.mom.masterdata.domain.Material;
import com.iad.mom.masterdata.mapper.MaterialMapper;
import com.iad.mom.masterdata.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 物料Service业务层处理
 * 
 * @author ra
 * @date 2023-10-04
 */
@Service
public class MaterialServiceImpl implements IMaterialService
{
    @Autowired
    private MaterialMapper materialMapper;

    /**
     * 查询物料
     * 
     * @param id 物料主键
     * @return 物料
     */
    @Override
    public Material selectMaterialById(Long id)
    {
        return materialMapper.selectMaterialById(id);
    }

    /**
     * 查询物料列表
     * 
     * @param material 物料
     * @return 物料
     */
    @Override
    public List<Material> selectMaterialList(Material material)
    {
        return materialMapper.selectMaterialList(material);
    }

    /**
     * 新增物料
     * 
     * @param material 物料
     * @return 结果
     */
    @Override
    public int insertMaterial(Material material)
    {
        return materialMapper.insertMaterial(material);
    }

    /**
     * 修改物料
     * 
     * @param material 物料
     * @return 结果
     */
    @Override
    public int updateMaterial(Material material)
    {
        return materialMapper.updateMaterial(material);
    }

    /**
     * 批量删除物料
     * 
     * @param ids 需要删除的物料主键
     * @return 结果
     */
    @Override
    public int deleteMaterialByIds(Long[] ids)
    {
        return materialMapper.deleteMaterialByIds(ids);
    }

    /**
     * 删除物料信息
     * 
     * @param id 物料主键
     * @return 结果
     */
    @Override
    public int deleteMaterialById(Long id)
    {
        return materialMapper.deleteMaterialById(id);
    }
}
