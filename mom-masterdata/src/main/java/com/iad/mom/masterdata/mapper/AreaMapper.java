package com.iad.mom.masterdata.mapper;

import java.util.List;
import com.iad.mom.masterdata.domain.Area;

/**
 * 车间Mapper接口
 * 
 * @author ra
 * @date 2023-10-07
 */
public interface AreaMapper 
{
    /**
     * 查询车间
     * 
     * @param id 车间主键
     * @return 车间
     */
    public Area selectAreaById(Long id);

    /**
     * 查询车间列表
     * 
     * @param area 车间
     * @return 车间集合
     */
    public List<Area> selectAreaList(Area area);

    /**
     * 新增车间
     * 
     * @param area 车间
     * @return 结果
     */
    public int insertArea(Area area);

    /**
     * 修改车间
     * 
     * @param area 车间
     * @return 结果
     */
    public int updateArea(Area area);

    /**
     * 删除车间
     * 
     * @param id 车间主键
     * @return 结果
     */
    public int deleteAreaById(Long id);

    /**
     * 批量删除车间
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAreaByIds(Long[] ids);
}
