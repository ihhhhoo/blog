package com.ruoyi.ry.comment.controller;

import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.ry.comment.domain.RyComment;
import com.ruoyi.ry.comment.service.IRyCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评论Controller
 *
 * @author ruoyi
 * @date 2025-02-26
 */
@RestController
@RequestMapping("/ry/comment")
@Api(value = "评论信息接口", tags = "评论信息相关的接口")
public class RyCommentController extends BaseController
{
    @Autowired
    private IRyCommentService ryCommentService;

    /**
     * 查询评论列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有评论信息列表" ,notes = "查询评论信息列表")
    public TableDataInfo list(RyComment ryComment)
    {
        startPage();

        ryComment.setDelFlag("0");
        List<RyComment> list = ryCommentService.selectRyCommentList(ryComment);
        return getDataTable(list);
    }


    /**
     * 获取评论详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id获取评论信息", notes = "获取评论信息详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ryCommentService.selectRyCommentById(id));
    }

    /**
     * 新增评论
     */
    @Log(title = "评论", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增评论", notes = "新增评论")
    @ApiImplicitParam(name = "ryComment", value = "评论信息", required = true, dataType = "RyComment", paramType = "body")
    public AjaxResult add(@RequestBody RyComment ryComment)
    {
        return toAjax(ryCommentService.insertRyComment(ryComment));
    }

    /**
     * 修改评论
     */
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改评论", notes = "修改评论")
    @ApiImplicitParam(name = "ryComment", value = "评论信息", required = true, dataType = "RyComment", paramType = "body")
    public AjaxResult edit(@RequestBody RyComment ryComment)
    {
        return toAjax(ryCommentService.updateRyComment(ryComment));
    }

    /**
     * 删除评论
     */
    @Log(title = "评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "根据id删除评论信息", notes = "删除评论信息")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ryCommentService.deleteRyCommentByIds(ids));
    }
}
