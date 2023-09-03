package dev.heming.enstudy.controller.api;

import dev.heming.enstudy.common.constant.MessageConstants;
import dev.heming.enstudy.common.param.dict.DictChoiceParam;
import dev.heming.enstudy.common.result.CommonResult;
import dev.heming.enstudy.service.UserBookDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 词库
 * @Author Bess Croft
 * @Date 2023/9/3 19:44
 */
@Tag(name = "词库")
@RequestMapping("/dict")
@RestController
@RequiredArgsConstructor
public class DictController {

    private final UserBookDictService userBookDictService;

    @PostMapping("/choice")
    @Operation(summary = "用户选择词典接口")
    public CommonResult<Void> choice(@RequestBody @Valid DictChoiceParam param) {
        userBookDictService.choice(param);
        return CommonResult.success(MessageConstants.SUCCESS);
    }

}
