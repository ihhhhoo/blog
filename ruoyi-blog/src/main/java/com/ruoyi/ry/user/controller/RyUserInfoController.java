package com.ruoyi.ry.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
public class RyUserInfoController extends BaseController
{
    @Autowired
    private IRyUserInfoService ryUserInfoService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('ry:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(RyUserInfo ryUserInfo)
    {
        startPage();
        if(!SecurityUtils.isAdmin(getUserId())){
            ryUserInfo.setCreateBy(getUsername());
        }
        List<RyUserInfo> list = ryUserInfoService.selectRyUserInfoList(ryUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('ry:user:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RyUserInfo ryUserInfo)
    {
        List<RyUserInfo> list = ryUserInfoService.selectRyUserInfoList(ryUserInfo);
        ExcelUtil<RyUserInfo> util = new ExcelUtil<RyUserInfo>(RyUserInfo.class);
        util.exportExcel(response, list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ry:user:query')")
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
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ryUserInfoService.deleteRyUserInfoByIds(ids));
    }
}
