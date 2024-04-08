package com.iad.mom.prod.service.impl;

import java.util.List;
import com.iad.mom.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.prod.mapper.LotMapper;
import com.iad.mom.prod.domain.Lot;
import com.iad.mom.prod.service.ILotService;

/**
 * 批次管理Service业务层处理
 * 
 * @author kris
 * @date 2023-11-22
 */
@Service
public class LotServiceImpl implements ILotService 
{
    @Autowired
    private LotMapper lotMapper;

    /**
     * 查询批次管理
     * 
     * @param id 批次管理主键
     * @return 批次管理
     */
    @Override
    public Lot selectLotById(String id)
    {
        return lotMapper.selectLotById(id);
    }

    /**
     * 查询批次管理列表
     * 
     * @param lot 批次管理
     * @return 批次管理
     */
    @Override
    public List<Lot> selectLotList(Lot lot)
    {
        return lotMapper.selectLotList(lot);
    }

    /**
     * 新增批次管理
     * 
     * @param lot 批次管理
     * @return 结果
     */
    @Override
    public int insertLot(Lot lot)
    {
        lot.setCreateTime(DateUtils.getNowDate());
        return lotMapper.insertLot(lot);
    }

    /**
     * 修改批次管理
     * 
     * @param lot 批次管理
     * @return 结果
     */
    @Override
    public int updateLot(Lot lot)
    {
        lot.setUpdateTime(DateUtils.getNowDate());
        return lotMapper.updateLot(lot);
    }

    /**
     * 批量删除批次管理
     * 
     * @param ids 需要删除的批次管理主键
     * @return 结果
     */
    @Override
    public int deleteLotByIds(String[] ids)
    {
        return lotMapper.deleteLotByIds(ids);
    }

    /**
     * 删除批次管理信息
     * 
     * @param id 批次管理主键
     * @return 结果
     */
    @Override
    public int deleteLotById(String id)
    {
        return lotMapper.deleteLotById(id);
    }
}
