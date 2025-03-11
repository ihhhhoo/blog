package com.ruoyi.ry.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
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
import com.ruoyi.ry.user.domain.RyUserInfo;
import com.ruoyi.ry.user.service.IRyUserInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户信息Controller
 *
 * @author ruoyi
 * @date 2025-03-05
 */
@RestController
@RequestMapping("/ry/user")
@Api(value = "测试接口", tags = "用户管理相关的接口")
public class RyUserInfoController extends BaseController
{
    @Autowired
    private IRyUserInfoService ryUserInfoService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('ry:user:list')")
    @ApiOperation(value = "查询所有用户信息列表", notes = "查询用户信息列表")
    @GetMapping("/list")
    public TableDataInfo list(RyUserInfo ryUserInfo)
    {
        startPage();

        List<RyUserInfo> list = ryUserInfoService.selectRyUserInfoList(ryUserInfo);
        return getDataTable(list);
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ry:user:query')")
    @ApiOperation(value = "根据id获取用户信息", notes = "获取用户信息详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {

        return success(ryUserInfoService.selectRyUserInfoById(id));
    }

    /**
     * 新增用户信息
     */
    @PreAuthorize("@ss.hasPermi('ry:user:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @ApiImplicitParam(name = "ryUserInfo", value = "用户信息", required = true, dataType = "RyUserInfo", paramType = "body")
    @ApiOperation(value = "新增用户信息", notes = "新增用户信息")
    @PostMapping
    public AjaxResult add(@RequestBody RyUserInfo ryUserInfo)
    {
        return toAjax(ryUserInfoService.insertRyUserInfo(ryUserInfo));
    }

    /**
     * 修改用户信息
     */
    @PreAuthorize("@ss.hasPermi('ry:user:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @ApiImplicitParam(name = "ryUserInfo", value = "用户信息", required = true, dataType = "RyUserInfo", paramType = "body")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PutMapping
    public AjaxResult edit(@RequestBody RyUserInfo ryUserInfo)
    {
        return toAjax(ryUserInfoService.updateRyUserInfo(ryUserInfo));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPermi('ry:user:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @ApiOperation(value = "根据id删除用户信息", notes = "删除用户信息")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ryUserInfoService.deleteRyUserInfoByIds(ids));
    }
}
