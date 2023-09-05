package dev.heming.enstudy.controller;

import dev.heming.enstudy.service.UserWrongWordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 用户错题本控制器
 * @Author Bess Croft
 * @Date 2023/8/31 21:38
 */
@Tag(name = "用户错题本")
@RestController
@RequestMapping("/userWrongWord")
@RequiredArgsConstructor
public class UserWrongWordController {

    private final UserWrongWordService userWrongWordService;

}
