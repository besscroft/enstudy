package dev.heming.enstudy.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import dev.heming.enstudy.common.constant.RoleConstants;
import dev.heming.enstudy.common.entity.FeedBack;
import dev.heming.enstudy.common.result.CommonResult;
import dev.heming.enstudy.common.utils.CommonPage;
import dev.heming.enstudy.service.FeedBackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 反馈控制器
 * @Author Bess Croft
 * @Date 2023/9/23 15:55
 */
@Tag(name = "反馈")
@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedBackController {

    private final FeedBackService feedBackService;

    @GetMapping("/feedbackPage")
    @SaCheckRole(
            value = {
                    RoleConstants.PLATFORM_SUPER_ADMIN,
                    RoleConstants.PLATFORM_ADMIN,
                    RoleConstants.PLATFORM_SELF_PROVISIONER,
                    RoleConstants.PLATFORM_VIEW
            },
            mode = SaMode.OR
    )
    @Operation(summary = "反馈分页列表")
    public CommonResult<CommonPage<FeedBack>> feedbackPage(@RequestParam("pageNum") Integer pageNum,
                                                           @RequestParam("pageSize") Integer pageSize) {
        List<FeedBack> feedBackList = feedBackService.feedbackPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(feedBackList));
    }

}
