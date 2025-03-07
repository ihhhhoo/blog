package com.ruoyi.ry.user.service;

import java.util.List;
import com.ruoyi.ry.user.domain.RySocialLinks;

/**
 * 社交链接Service接口
 *
 * @author ruoyi
 * @date 2025-03-05
 */
public interface IRySocialLinksService
{
    /**
     * 查询社交链接
     *
     * @param id 社交链接主键
     * @return 社交链接
     */
    public RySocialLinks selectRySocialLinksById(Long id);


    /**
     * 新增社交链接
     *
     * @param rySocialLinks 社交链接
     * @return 结果
     */
    public int insertRySocialLinks(RySocialLinks rySocialLinks);

    /**
     * 修改社交链接
     *
     * @param rySocialLinks 社交链接
     * @return 结果
     */
    public int updateRySocialLinks(RySocialLinks rySocialLinks);

    /**
     * 批量删除社交链接
     *
     * @param ids 需要删除的社交链接主键集合
     * @return 结果
     */
    public int deleteRySocialLinksByIds(Long[] ids);

    /**
     * 删除社交链接信息
     *
     * @param id 社交链接主键
     * @return 结果
     */
    public int deleteRySocialLinksById(Long id);
}
