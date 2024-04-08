package com.iad.mom.masterdata.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.masterdata.mapper.AreaMapper;
import com.iad.mom.masterdata.domain.Area;
import com.iad.mom.masterdata.service.IAreaService;

/**
 * 车间Service业务层处理
 * 
 * @author ra
 * @date 2023-10-07
 */
@Service
public class AreaServiceImpl implements IAreaService 
{
    @Autowired
    private AreaMapper areaMapper;

    /**
     * 查询车间
     * 
     * @param id 车间主键
     * @return 车间
     */
    @Override
    public Area selectAreaById(Long id)
    {
        return areaMapper.selectAreaById(id);
    }

    /**
     * 查询车间列表
     * 
     * @param area 车间
     * @return 车间
     */
    @Override
    public List<Area> selectAreaList(Area area)
    {
        return areaMapper.selectAreaList(area);
    }

    /**
     * 新增车间
     * 
     * @param area 车间
     * @return 结果
     */
    @Override
    public int insertArea(Area area)
    {
        return areaMapper.insertArea(area);
    }

    /**
     * 修改车间
     * 
     * @param area 车间
     * @return 结果
     */
    @Override
    public int updateArea(Area area)
    {
        return areaMapper.updateArea(area);
    }

    /**
     * 批量删除车间
     * 
     * @param ids 需要删除的车间主键
     * @return 结果
     */
    @Override
    public int deleteAreaByIds(Long[] ids)
    {
        return areaMapper.deleteAreaByIds(ids);
    }

    /**
     * 删除车间信息
     * 
     * @param id 车间主键
     * @return 结果
     */
    @Override
    public int deleteAreaById(Long id)
    {
        return areaMapper.deleteAreaById(id);
    }
}
