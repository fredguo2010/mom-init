package com.iad.mom.masterdata.service;

import java.util.List;
import com.iad.mom.masterdata.domain.WorkCenter;

/**
 * 工作中心Service接口
 * 
 * @author iad
 * @date 2023-10-08
 */
public interface IWorkCenterService 
{
    /**
     * 查询工作中心
     * 
     * @param id 工作中心主键
     * @return 工作中心
     */
    public WorkCenter selectWorkCenterById(Long id);

    /**
     * 查询工作中心列表
     * 
     * @param workCenter 工作中心
     * @return 工作中心集合
     */
    public List<WorkCenter> selectWorkCenterList(WorkCenter workCenter);

    /**
     * 新增工作中心
     * 
     * @param workCenter 工作中心
     * @return 结果
     */
    public int insertWorkCenter(WorkCenter workCenter);

    /**
     * 修改工作中心
     * 
     * @param workCenter 工作中心
     * @return 结果
     */
    public int updateWorkCenter(WorkCenter workCenter);

    /**
     * 批量删除工作中心
     * 
     * @param ids 需要删除的工作中心主键集合
     * @return 结果
     */
    public int deleteWorkCenterByIds(Long[] ids);

    /**
     * 删除工作中心信息
     * 
     * @param id 工作中心主键
     * @return 结果
     */
    public int deleteWorkCenterById(Long id);
}
