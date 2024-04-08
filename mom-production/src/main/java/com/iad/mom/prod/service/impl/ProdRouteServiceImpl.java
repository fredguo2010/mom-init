package com.iad.mom.prod.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.prod.mapper.ProdRouteMapper;
import com.iad.mom.prod.domain.ProdRoute;
import com.iad.mom.prod.service.IProdRouteService;

/**
 * 工艺路线Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-27
 */
@Service
public class ProdRouteServiceImpl implements IProdRouteService 
{
    @Autowired
    private ProdRouteMapper prodRouteMapper;

    /**
     * 查询工艺路线
     * 
     * @param id 工艺路线主键
     * @return 工艺路线
     */
    @Override
    public ProdRoute selectProdRouteById(Long id)
    {
        return prodRouteMapper.selectProdRouteById(id);
    }

    /**
     * 查询工艺路线列表
     * 
     * @param prodRoute 工艺路线
     * @return 工艺路线
     */
    @Override
    public List<ProdRoute> selectProdRouteList(ProdRoute prodRoute)
    {
        return prodRouteMapper.selectProdRouteList(prodRoute);
    }

    /**
     * 新增工艺路线
     * 
     * @param prodRoute 工艺路线
     * @return 结果
     */
    @Override
    public int insertProdRoute(ProdRoute prodRoute)
    {
        return prodRouteMapper.insertProdRoute(prodRoute);
    }

    /**
     * 修改工艺路线
     * 
     * @param prodRoute 工艺路线
     * @return 结果
     */
    @Override
    public int updateProdRoute(ProdRoute prodRoute)
    {
        return prodRouteMapper.updateProdRoute(prodRoute);
    }

    /**
     * 批量删除工艺路线
     * 
     * @param ids 需要删除的工艺路线主键
     * @return 结果
     */
    @Override
    public int deleteProdRouteByIds(Long[] ids)
    {
        return prodRouteMapper.deleteProdRouteByIds(ids);
    }

    /**
     * 删除工艺路线信息
     * 
     * @param id 工艺路线主键
     * @return 结果
     */
    @Override
    public int deleteProdRouteById(Long id)
    {
        return prodRouteMapper.deleteProdRouteById(id);
    }
}
