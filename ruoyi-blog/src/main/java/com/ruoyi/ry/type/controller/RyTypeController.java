package com.ruoyi.ry.type.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.ry.type.domain.RyType;
import com.ruoyi.ry.type.service.IRyTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 分类信息Controller
 *
 * @author ruoyi
 * @date 2025-02-26
 */
@RestController
@RequestMapping("/ry/type")
@Api(value = "分类信息接口", tags = "分类信息相关的接口")
public class RyTypeController extends BaseController
{
    @Autowired
    private IRyTypeService ryTypeService;

    /**
     * 查询分类信息列表
     */

    @GetMapping("/list")
    @ApiOperation(value = "查询分类信息列表", notes = "查询分类信息列表")
    public TableDataInfo list(RyType ryType)
    {
        startPage();
        List<RyType> list = ryTypeService.selectRyTypeList(ryType);
        return getDataTable(list);
    }

    /**
     * 导出分类信息列表
     */

    @Log(title = "分类信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RyType ryType)
    {
        List<RyType> list = ryTypeService.selectRyTypeList(ryType);
        ExcelUtil<RyType> util = new ExcelUtil<RyType>(RyType.class);
        util.exportExcel(response, list, "分类信息数据");
    }

    /**
     * 获取分类信息详细信息
     */

    @GetMapping(value = "/{typeId}")
    @ApiOperation(value = "根据typeId获取分类信息", notes = "获取分类信息")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return success(ryTypeService.selectRyTypeByTypeId(typeId));
    }

    /**
     * 新增分类信息
     */

    @Log(title = "分类信息", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增分类信息", notes = "新增分类信息")
    @ApiImplicitParam(name = "ryType", value = "分类信息", required = true, dataType = "RyType", paramType = "body")
    @PostMapping
    public AjaxResult add(@RequestBody RyType ryType)
    {
        return toAjax(ryTypeService.insertRyType(ryType));
    }

    /**
     * 修改分类信息
     */

    @Log(title = "分类信息", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改分类信息", notes = "修改分类信息")
    @ApiImplicitParam(name = "ryType", value = "分类信息", required = true, dataType = "RyType", paramType = "body")
    @PutMapping
    public AjaxResult edit(@RequestBody RyType ryType)
    {
        ryType.setUpdateTime(new Date());
        ryType.setUpdateBy(getUsername());
        return toAjax(ryTypeService.updateRyType(ryType));
    }

    /**
     * 删除分类信息
     */

    @Log(title = "分类信息", businessType = BusinessType.DELETE)
    @ApiOperation(value = "根据typeIds删除分类信息", notes = "删除分类信息")
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(ryTypeService.deleteRyTypeByTypeIds(typeIds));
    }
}
