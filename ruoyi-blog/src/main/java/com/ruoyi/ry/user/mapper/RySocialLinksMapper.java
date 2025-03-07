package com.ruoyi.ry.user.mapper;

import java.util.List;
import com.ruoyi.ry.user.domain.RySocialLinks;

/**
 * 社交链接Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-05
 */
public interface RySocialLinksMapper
{
    /**
     * 查询社交链接
     *
     * @param id 社交链接主键
     * @return 社交链接
     */
    public RySocialLinks selectRySocialLinksById(Long id);

    /**
     * 通过user_id查询社交链接
     */
    public RySocialLinks selectRySocialLinksByUserId(Long userId);

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
     * 删除社交链接
     *
     * @param id 社交链接主键
     * @return 结果
     */
    public int deleteRySocialLinksById(Long id);

    /**
     * 批量删除社交链接
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRySocialLinksByIds(Long[] ids);
}
