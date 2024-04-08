package com.iad.mom.prod.service;

import java.util.List;
import com.iad.mom.prod.domain.Lot;

/**
 * 批次管理Service接口
 * 
 * @author kris
 * @date 2023-11-22
 */
public interface ILotService 
{
    /**
     * 查询批次管理
     * 
     * @param id 批次管理主键
     * @return 批次管理
     */
    public Lot selectLotById(String id);

    /**
     * 查询批次管理列表
     * 
     * @param lot 批次管理
     * @return 批次管理集合
     */
    public List<Lot> selectLotList(Lot lot);

    /**
     * 新增批次管理
     * 
     * @param lot 批次管理
     * @return 结果
     */
    public int insertLot(Lot lot);

    /**
     * 修改批次管理
     * 
     * @param lot 批次管理
     * @return 结果
     */
    public int updateLot(Lot lot);

    /**
     * 批量删除批次管理
     * 
     * @param ids 需要删除的批次管理主键集合
     * @return 结果
     */
    public int deleteLotByIds(String[] ids);

    /**
     * 删除批次管理信息
     * 
     * @param id 批次管理主键
     * @return 结果
     */
    public int deleteLotById(String id);
}
