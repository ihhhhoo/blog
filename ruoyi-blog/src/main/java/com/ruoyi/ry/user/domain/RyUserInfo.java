package com.ruoyi.ry.user.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 用户信息对象 ry_user_info
 *
 * @author ruoyi
 * @date 2025-03-05
 */
public class RyUserInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户名/博客名称 */
    @Excel(name = "用户名/博客名称")
    private String username;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String userImg;

    /** 过渡头像 */
    @Excel(name = "过渡头像")
    private String loadingImgUrl;

    /** 主页背景图片 */
    @Excel(name = "主页背景图片")
    private String indexBackImg;

    /** 爱好列表 */
    @Excel(name = "爱好列表")
    private String like;

    /** 标签列表 */
    @Excel(name = "标签列表")
    private String tags;

    /** 经纬度 */
    @Excel(name = "经纬度")
    private String longitude;

    /** 卡片背景 */
    @Excel(name = "卡片背景")
    private String drawUrl;

    /** 卡片标题 */
    @Excel(name = "卡片标题")
    private String drawTitle;

    /** 开始标题 */
    @Excel(name = "开始标题")
    private String startTitle;

    public String getCreateBy() {
        return createBy;
    }

    private String createBy;


    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String context;

    /** 社交属性 */
    private RySocialLinks  socialLinks;
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public RySocialLinks getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(RySocialLinks rySocialLinks) {
        this.socialLinks = rySocialLinks;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setUserImg(String userImg)
    {
        this.userImg = userImg;
    }

    public String getUserImg()
    {
        return userImg;
    }
    public void setLoadingImgUrl(String loadingImgUrl)
    {
        this.loadingImgUrl = loadingImgUrl;
    }

    public String getLoadingImgUrl()
    {
        return loadingImgUrl;
    }
    public void setIndexBackImg(String indexBackImg)
    {
        this.indexBackImg = indexBackImg;
    }

    public String getIndexBackImg()
    {
        return indexBackImg;
    }
    public void setLike(String like)
    {
        this.like = like;
    }

    public String getLike()
    {
        return like;
    }
    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public String getTags()
    {
        return tags;
    }
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLongitude()
    {
        return longitude;
    }
    public void setDrawUrl(String drawUrl)
    {
        this.drawUrl = drawUrl;
    }

    public String getDrawUrl()
    {
        return drawUrl;
    }
    public void setDrawTitle(String drawTitle)
    {
        this.drawTitle = drawTitle;
    }

    public String getDrawTitle()
    {
        return drawTitle;
    }
    public void setStartTitle(String startTitle)
    {
        this.startTitle = startTitle;
    }

    public String getStartTitle()
    {
        return startTitle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("userImg", getUserImg())
            .append("loadingImgUrl", getLoadingImgUrl())
            .append("indexBackImg", getIndexBackImg())
            .append("like", getLike())
            .append("tags", getTags())
            .append("longitude", getLongitude())
            .append("drawUrl", getDrawUrl())
            .append("drawTitle", getDrawTitle())
            .append("startTitle", getStartTitle())
                .append("context",getContext())
            .toString();
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
