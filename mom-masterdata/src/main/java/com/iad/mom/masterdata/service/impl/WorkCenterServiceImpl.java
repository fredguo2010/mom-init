package com.iad.mom.masterdata.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iad.mom.masterdata.mapper.WorkCenterMapper;
import com.iad.mom.masterdata.domain.WorkCenter;
import com.iad.mom.masterdata.service.IWorkCenterService;

/**
 * 工作中心Service业务层处理
 * 
 * @author iad
 * @date 2023-10-08
 */
@Service
public class WorkCenterServiceImpl implements IWorkCenterService 
{
    @Autowired
    private WorkCenterMapper workCenterMapper;

    /**
     * 查询工作中心
     * 
     * @param id 工作中心主键
     * @return 工作中心
     */
    @Override
    public WorkCenter selectWorkCenterById(Long id)
    {
        return workCenterMapper.selectWorkCenterById(id);
    }

    /**
     * 查询工作中心列表
     * 
     * @param workCenter 工作中心
     * @return 工作中心
     */
    @Override
    public List<WorkCenter> selectWorkCenterList(WorkCenter workCenter)
    {
        return workCenterMapper.selectWorkCenterList(workCenter);
    }

    /**
     * 新增工作中心
     * 
     * @param workCenter 工作中心
     * @return 结果
     */
    @Override
    public int insertWorkCenter(WorkCenter workCenter)
    {
        return workCenterMapper.insertWorkCenter(workCenter);
    }

    /**
     * 修改工作中心
     * 
     * @param workCenter 工作中心
     * @return 结果
     */
    @Override
    public int updateWorkCenter(WorkCenter workCenter)
    {
        return workCenterMapper.updateWorkCenter(workCenter);
    }

    /**
     * 批量删除工作中心
     * 
     * @param ids 需要删除的工作中心主键
     * @return 结果
     */
    @Override
    public int deleteWorkCenterByIds(Long[] ids)
    {
        return workCenterMapper.deleteWorkCenterByIds(ids);
    }

    /**
     * 删除工作中心信息
     * 
     * @param id 工作中心主键
     * @return 结果
     */
    @Override
    public int deleteWorkCenterById(Long id)
    {
        return workCenterMapper.deleteWorkCenterById(id);
    }
}
