package com.ruoyi.ry.user.service;

import java.util.List;
import com.ruoyi.ry.user.domain.RyUserInfo;

/**
 * 用户信息Service接口
 * 
 * @author ruoyi
 * @date 2025-03-05
 */
public interface IRyUserInfoService 
{
    /**
     * 查询用户信息
     * 
     * @param id 用户信息主键
     * @return 用户信息
     */
    public RyUserInfo selectRyUserInfoById(Long id);

    /**
     * 查询用户信息列表
     * 
     * @param ryUserInfo 用户信息
     * @return 用户信息集合
     */
    public List<RyUserInfo> selectRyUserInfoList(RyUserInfo ryUserInfo);

    /**
     * 新增用户信息
     * 
     * @param ryUserInfo 用户信息
     * @return 结果
     */
    public int insertRyUserInfo(RyUserInfo ryUserInfo);

    /**
     * 修改用户信息
     * 
     * @param ryUserInfo 用户信息
     * @return 结果
     */
    public int updateRyUserInfo(RyUserInfo ryUserInfo);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的用户信息主键集合
     * @return 结果
     */
    public int deleteRyUserInfoByIds(Long[] ids);

    /**
     * 删除用户信息信息
     * 
     * @param id 用户信息主键
     * @return 结果
     */
    public int deleteRyUserInfoById(Long id);
}
