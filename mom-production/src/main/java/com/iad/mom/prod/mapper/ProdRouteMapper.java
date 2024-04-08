package com.iad.mom.prod.mapper;

import java.util.List;
import com.iad.mom.prod.domain.ProdRoute;

/**
 * 工艺路线Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-27
 */
public interface ProdRouteMapper 
{
    /**
     * 查询工艺路线
     * 
     * @param id 工艺路线主键
     * @return 工艺路线
     */
    public ProdRoute selectProdRouteById(Long id);

    /**
     * 查询工艺路线列表
     * 
     * @param prodRoute 工艺路线
     * @return 工艺路线集合
     */
    public List<ProdRoute> selectProdRouteList(ProdRoute prodRoute);

    /**
     * 新增工艺路线
     * 
     * @param prodRoute 工艺路线
     * @return 结果
     */
    public int insertProdRoute(ProdRoute prodRoute);

    /**
     * 修改工艺路线
     * 
     * @param prodRoute 工艺路线
     * @return 结果
     */
    public int updateProdRoute(ProdRoute prodRoute);

    /**
     * 删除工艺路线
     * 
     * @param id 工艺路线主键
     * @return 结果
     */
    public int deleteProdRouteById(Long id);

    /**
     * 批量删除工艺路线
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProdRouteByIds(Long[] ids);
}
