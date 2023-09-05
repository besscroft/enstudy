package dev.heming.enstudy.controller;

import dev.heming.enstudy.common.result.CommonResult;
import dev.heming.enstudy.common.vo.console.ConsoleVo;
import dev.heming.enstudy.service.WordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 控制台控制器
 * @Author Bess Croft
 * @Date 2023/9/5 14:33
 */
@Tag(name = "控制台控制器")
@RestController
@RequestMapping("/console")
@RequiredArgsConstructor
public class ConsoleController {

    private final WordService wordService;

    @Operation(summary = "控制台统计接口")
    @GetMapping("/getInfo")
    public CommonResult<ConsoleVo> getConsoleInfo() {
        ConsoleVo info = wordService.getConsoleInfo();
        return CommonResult.success(info);
    }

}
