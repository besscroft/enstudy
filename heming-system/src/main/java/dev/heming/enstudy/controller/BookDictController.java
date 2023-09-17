package dev.heming.enstudy.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import dev.heming.enstudy.common.constant.MessageConstants;
import dev.heming.enstudy.common.constant.RoleConstants;
import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.common.param.dict.BookDictAddParam;
import dev.heming.enstudy.common.param.dict.BookDictUpdateParam;
import dev.heming.enstudy.common.result.CommonResult;
import dev.heming.enstudy.common.utils.CommonPage;
import dev.heming.enstudy.service.BookDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 词典控制器
 * @Author Bess Croft
 * @Date 2023/8/26 17:06
 */
@Tag(name = "词典管理")
@RestController
@RequestMapping("/bookDict")
@RequiredArgsConstructor
public class BookDictController {

    private final BookDictService bookDictService;

    @GetMapping("/dictPage")
    @SaCheckRole(
            value = {
                    RoleConstants.PLATFORM_SUPER_ADMIN,
                    RoleConstants.PLATFORM_ADMIN,
                    RoleConstants.PLATFORM_SELF_PROVISIONER,
                    RoleConstants.PLATFORM_VIEW
            },
            mode = SaMode.OR
    )
    @Operation(summary = "词典分页列表")
    public CommonResult<CommonPage<BookDict>> bookDictPage(@RequestParam("pageNum") Integer pageNum,
                                                           @RequestParam("pageSize") Integer pageSize) {
        List<BookDict> bookDictPageVoList = bookDictService.bookDictPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(bookDictPageVoList));
    }

    @PostMapping("/addDict")
    @SaCheckRole(
            value = {
                    RoleConstants.PLATFORM_SUPER_ADMIN,
                    RoleConstants.PLATFORM_ADMIN,
                    RoleConstants.PLATFORM_SELF_PROVISIONER
            },
            mode = SaMode.OR
    )
    @Operation(summary = "新增词典接口")
    public CommonResult<Void> addDict(@RequestBody @Valid BookDictAddParam param) {
        bookDictService.addDict(param);
        return CommonResult.success(MessageConstants.ADD_SUCCESS);
    }

    @PutMapping("/updateDict")
    @SaCheckRole(
            value = {
                    RoleConstants.PLATFORM_SUPER_ADMIN,
                    RoleConstants.PLATFORM_ADMIN,
                    RoleConstants.PLATFORM_SELF_PROVISIONER
            },
            mode = SaMode.OR
    )
    @Operation(summary = "更新词典接口")
    public CommonResult<Void> updateDict(@RequestBody @Valid BookDictUpdateParam param) {
        bookDictService.updateDict(param);
        return CommonResult.success(MessageConstants.UPDATE_SUCCESS);
    }

    @Operation(summary = "词典删除接口")
    @SaCheckRole(
            value = {
                    RoleConstants.PLATFORM_SUPER_ADMIN,
                    RoleConstants.PLATFORM_ADMIN,
                    RoleConstants.PLATFORM_SELF_PROVISIONER
            },
            mode = SaMode.OR
    )
    @DeleteMapping("/delete/{dictId:[\\d]+}")
    public CommonResult<Void> delete(@PathVariable(name = "dictId") Long dictId) {
        bookDictService.deleteDict(dictId);
        return CommonResult.success(MessageConstants.DELETE_SUCCESS);
    }

    @Operation(summary = "词典详情接口")
    @SaCheckRole(
            value = {
                    RoleConstants.PLATFORM_SUPER_ADMIN,
                    RoleConstants.PLATFORM_ADMIN,
                    RoleConstants.PLATFORM_SELF_PROVISIONER
            },
            mode = SaMode.OR
    )
    @GetMapping("/get/{dictId:[\\d]+}")
    public CommonResult<BookDict> getDictById(@PathVariable(name = "dictId") Long dictId) {
        return CommonResult.success(bookDictService.getDictById(dictId));
    }

}
