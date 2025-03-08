package com.ruoyi.ry.user.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ruoyi.ry.user.domain.RySocialLinks;
import com.ruoyi.ry.user.mapper.RySocialLinksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ry.user.mapper.RyUserInfoMapper;
import com.ruoyi.ry.user.domain.RyUserInfo;
import com.ruoyi.ry.user.service.IRyUserInfoService;

/**
 * 用户信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-03-05
 */
@Service
public class RyUserInfoServiceImpl implements IRyUserInfoService
{
    @Autowired
    private RyUserInfoMapper ryUserInfoMapper;

    @Autowired
    private RySocialLinksMapper rySocialLinksMapper;

    /**
     * 查询用户信息
     *
     * @param id 用户信息主键
     * @return 用户信息
     */
    @Override
    public RyUserInfo selectRyUserInfoById(Long id)
    {
        RyUserInfo ryUserInfo = ryUserInfoMapper.selectRyUserInfoById(id);

        //放入socialLinks
        // RySocialLinks rySocialLinks = new RySocialLinks();
        RySocialLinks rySocialLinks = rySocialLinksMapper.selectRySocialLinksByUserId(id);
        ryUserInfo.setSocialLinks(rySocialLinks);

        return ryUserInfo;
    }

    /**
     * 查询用户信息列表
     *
     * @param ryUserInfo 用户信息
     * @return 用户信息
     */
    @Override
    public List<RyUserInfo> selectRyUserInfoList(RyUserInfo ryUserInfo)
    {
        List<RyUserInfo> ryUserInfoList = ryUserInfoMapper.selectRyUserInfoList(ryUserInfo);
        List<RyUserInfo> userInfos = getUserInfoAddSocialLinks(ryUserInfoList);//加入属性爱好
        return userInfos;
    }

    /**
     * 新增用户信息
     *
     * @param ryUserInfo 用户信息
     * @return 结果
     */
    @Override
    public int insertRyUserInfo(RyUserInfo ryUserInfo)
    {
        //插入的时候把标签改成数组存进去
        // ryUserInfo.setTags(Arrays.toString(ryUserInfo.getTags().split(",")));
        ryUserInfo.setTags("["+ryUserInfo.getTags()+"]");
        //新增用户信息不需要加入爱好
        return ryUserInfoMapper.insertRyUserInfo(ryUserInfo);
    }

    /**
     * 修改用户信息及社交属性
     *
     * @param ryUserInfo 用户信息
     * @return 结果
     */
    @Override
    public int updateRyUserInfo(RyUserInfo ryUserInfo)
    {
        //标签也要改成数组存进去
        if(ryUserInfo.getTags() != null){
            // ryUserInfo.setTags(Arrays.toString(ryUserInfo.getTags().split(",")));
            ryUserInfo.setTags("["+ryUserInfo.getTags()+"]");
        }
        // //更新时间
        // ryUserInfo.setUpdateTime(new Date());
        //修改属性
        Long userId = ryUserInfo.getId();
        System.out.println("userId= " +userId);
        //如果没有传社交链接就是不需要更改社交链接
        if(ryUserInfo.getSocialLinks() != null){
            RySocialLinks rySocialLinks = rySocialLinksMapper.selectRySocialLinksByUserId(userId);
            RySocialLinks socialLinks = ryUserInfo.getSocialLinks();
            //给链接设置用户id
            socialLinks.setUserId(userId);
            if(rySocialLinks != null){
                //原本就有社交属性的话就修改社交属性
                rySocialLinksMapper.updateRySocialLinks(socialLinks);
            }else{
                //新增
                //加入userId
                rySocialLinksMapper.insertRySocialLinks(ryUserInfo.getSocialLinks());
            }
        }


        return ryUserInfoMapper.updateRyUserInfo(ryUserInfo);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteRyUserInfoByIds(Long[] ids)
    {
        return ryUserInfoMapper.deleteRyUserInfoByIds(ids);
    }

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteRyUserInfoById(Long id)
    {
        return ryUserInfoMapper.deleteRyUserInfoById(id);
    }

    private List<RyUserInfo> getUserInfoAddSocialLinks(List<RyUserInfo> ryUserInfos){
        RySocialLinks rySocialLinks = new RySocialLinks();
        if(ryUserInfos != null && ryUserInfos.size() > 0){
            //不为空加入社交属性
            for (RyUserInfo ryUserInfo : ryUserInfos) {
                Long userId = ryUserInfo.getId();
                RySocialLinks SocialLinks = rySocialLinksMapper.selectRySocialLinksById(userId);
                ryUserInfo.setSocialLinks(SocialLinks);
            }
        }
        return ryUserInfos;
    }
}
