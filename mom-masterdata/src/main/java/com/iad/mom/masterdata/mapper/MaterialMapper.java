package com.iad.mom.masterdata.mapper;

import java.util.List;

import com.iad.mom.masterdata.domain.Material;

/**
 * 物料Mapper接口
 * 
 * @author ra
 * @date 2023-10-04
 */
public interface MaterialMapper 
{
    /**
     * 查询物料
     * 
     * @param id 物料主键
     * @return 物料
     */
    public Material selectMaterialById(Long id);

    /**
     * 查询物料列表
     * 
     * @param material 物料
     * @return 物料集合
     */
    public List<Material> selectMaterialList(Material material);

    /**
     * 新增物料
     * 
     * @param material 物料
     * @return 结果
     */
    public int insertMaterial(Material material);

    /**
     * 修改物料
     * 
     * @param material 物料
     * @return 结果
     */
    public int updateMaterial(Material material);

    /**
     * 删除物料
     * 
     * @param id 物料主键
     * @return 结果
     */
    public int deleteMaterialById(Long id);

    /**
     * 批量删除物料
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaterialByIds(Long[] ids);
}
