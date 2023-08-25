package dev.heming.enstudy.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import dev.heming.enstudy.common.param.LoginParam;
import dev.heming.enstudy.common.result.CommonResult;
import dev.heming.enstudy.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2023/8/24 23:05
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @SaIgnore
    @PostMapping("/login")
    @Operation(summary = "登录")
    public CommonResult<SaTokenInfo> login(@RequestBody @Valid LoginParam param) {
        SaTokenInfo tokenInfo = userService.login(param.getUsername(), param.getPassword());
        return CommonResult.success(tokenInfo);
    }

    @GetMapping("/info")
    @Operation(summary = "获取已登录用户信息")
    public CommonResult<Map<String, Object>> info() {
        Map<String, Object> info = userService.info();
        return CommonResult.success(info);
    }

}
