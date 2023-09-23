package dev.heming.enstudy.controller.api;

import dev.heming.enstudy.common.constant.MessageConstants;
import dev.heming.enstudy.common.param.feedback.FeedBackParam;
import dev.heming.enstudy.common.result.CommonResult;
import dev.heming.enstudy.service.FeedBackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 反馈控制器
 * @Author Bess Croft
 * @Date 2023/9/23 15:56
 */
@Tag(name = "反馈 API")
@RestController
@RequestMapping("/feedbackApi")
@RequiredArgsConstructor
public class ApiFeedBackController {

    private final FeedBackService feedBackService;

    @PostMapping("/submit")
    @Operation(summary = "反馈提交接口")
    public CommonResult<Void> submit(@RequestBody @Valid FeedBackParam param) {
        feedBackService.submit(param);
        return CommonResult.success(MessageConstants.SUCCESS);
    }

}
