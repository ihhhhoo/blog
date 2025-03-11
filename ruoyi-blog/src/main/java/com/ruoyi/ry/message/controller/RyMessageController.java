package com.ruoyi.ry.message.controller;

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
import com.ruoyi.ry.message.domain.RyMessage;
import com.ruoyi.ry.message.service.IRyMessageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 留言Controller
 *
 * @author ruoyi
 * @date 2025-02-26
 */
@RestController
@RequestMapping("/ry/message")
@Api(value = "留言信息接口", tags = "留言信息相关的接口")
public class RyMessageController extends BaseController
{
    @Autowired
    private IRyMessageService ryMessageService;



    /**
     * 查询留言列表
     */

    @GetMapping("/list")
    @ApiOperation(value = "查询所有留言信息列表", notes = "查询留言信息列表")
    public TableDataInfo list(RyMessage ryMessage)
    {
        startPage();

        ryMessage.setDelFlag("0");
        //查询没有删除的留言 0存在 1删除
        List<RyMessage> list = ryMessageService.selectRyMessageList(ryMessage);
        return getDataTable(list);
    }



    /**
     * 获取留言详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id获取留言信息", notes = "获取留言信息详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ryMessageService.selectRyMessageById(id));
    }

    /**
     * 新增留言
     */
    @Log(title = "留言", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增留言信息", notes = "新增留言信息")
    @ApiImplicitParam(name = "ryMessage", value = "留言信息", required = true, dataType = "RyMessage", paramType = "body")
    public AjaxResult add(@RequestBody RyMessage ryMessage)
    {
        return toAjax(ryMessageService.insertRyMessage(ryMessage));
    }

    /**
     * 修改留言
     */
    @Log(title = "留言", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改留言信息", notes = "修改留言信息")
    @ApiImplicitParam(name = "ryMessage", value = "留言信息", required = true, dataType = "RyMessage", paramType = "body")
    public AjaxResult edit(@RequestBody RyMessage ryMessage)
    {
        return toAjax(ryMessageService.updateRyMessage(ryMessage));
    }

    /**
     * 删除留言
     */
    @Log(title = "留言", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "根据id删除留言信息", notes = "删除留言信息")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ryMessageService.deleteRyMessageByIds(ids));
    }
}
