package com.ruoyi.ry.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ry.user.mapper.RySocialLinksMapper;
import com.ruoyi.ry.user.domain.RySocialLinks;
import com.ruoyi.ry.user.service.IRySocialLinksService;

/**
 * 社交链接Service业务层处理
 *
 * @author ruoyi
 * @date 2025-03-05
 */
@Service
public class RySocialLinksServiceImpl implements IRySocialLinksService
{
    @Autowired
    private RySocialLinksMapper rySocialLinksMapper;

    /**
     * 查询社交链接
     *
     * @param id 社交链接主键
     * @return 社交链接
     */
    @Override
    public RySocialLinks selectRySocialLinksById(Long id)
    {
        return rySocialLinksMapper.selectRySocialLinksById(id);
    }



    /**
     * 新增社交链接
     *
     * @param rySocialLinks 社交链接
     * @return 结果
     */
    @Override
    public int insertRySocialLinks(RySocialLinks rySocialLinks)
    {
        return rySocialLinksMapper.insertRySocialLinks(rySocialLinks);
    }

    /**
     * 修改社交链接
     *
     * @param rySocialLinks 社交链接
     * @return 结果
     */
    @Override
    public int updateRySocialLinks(RySocialLinks rySocialLinks)
    {
        return rySocialLinksMapper.updateRySocialLinks(rySocialLinks);
    }

    /**
     * 批量删除社交链接
     *
     * @param ids 需要删除的社交链接主键
     * @return 结果
     */
    @Override
    public int deleteRySocialLinksByIds(Long[] ids)
    {
        return rySocialLinksMapper.deleteRySocialLinksByIds(ids);
    }

    /**
     * 删除社交链接信息
     *
     * @param id 社交链接主键
     * @return 结果
     */
    @Override
    public int deleteRySocialLinksById(Long id)
    {
        return rySocialLinksMapper.deleteRySocialLinksById(id);
    }
}
