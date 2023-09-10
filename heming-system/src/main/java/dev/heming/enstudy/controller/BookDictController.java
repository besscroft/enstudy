package dev.heming.enstudy.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import dev.heming.enstudy.common.constant.RoleConstants;
import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.common.result.CommonResult;
import dev.heming.enstudy.common.utils.CommonPage;
import dev.heming.enstudy.service.BookDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
