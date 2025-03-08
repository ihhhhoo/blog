package com.ruoyi.ry.user.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 社交链接对象 ry_social_links
 *
 * @author ruoyi
 * @date 2025-03-05
 */
public class RySocialLinks implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** GitHub URL */
    @Excel(name = "GitHub URL")
    private String githubUrl;

    /** Git URL */
    @Excel(name = "Git URL")
    private String gitUrl;

    /** QQ URL */
    @Excel(name = "QQ URL")
    private String qqUrl;

    /** Bilibili URL */
    @Excel(name = "Bilibili URL")
    private String bilibiliUrl;

    /** CSDN URL */
    @Excel(name = "CSDN URL")
    private String csdnUrl;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setGithubUrl(String githubUrl)
    {
        this.githubUrl = githubUrl;
    }

    public String getGithubUrl()
    {
        return githubUrl;
    }
    public void setGitUrl(String gitUrl)
    {
        this.gitUrl = gitUrl;
    }

    public String getGitUrl()
    {
        return gitUrl;
    }
    public void setQqUrl(String qqUrl)
    {
        this.qqUrl = qqUrl;
    }

    public String getQqUrl()
    {
        return qqUrl;
    }
    public void setBilibiliUrl(String bilibiliUrl)
    {
        this.bilibiliUrl = bilibiliUrl;
    }

    public String getBilibiliUrl()
    {
        return bilibiliUrl;
    }
    public void setCsdnUrl(String csdnUrl)
    {
        this.csdnUrl = csdnUrl;
    }

    public String getCsdnUrl()
    {
        return csdnUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("githubUrl", getGithubUrl())
            .append("gitUrl", getGitUrl())
            .append("qqUrl", getQqUrl())
            .append("bilibiliUrl", getBilibiliUrl())
            .append("csdnUrl", getCsdnUrl())
            .toString();
    }
}
