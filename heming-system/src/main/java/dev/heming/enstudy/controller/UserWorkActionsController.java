package dev.heming.enstudy.controller;

import dev.heming.enstudy.service.UserWorkActionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 用户学习行为控制器
 * @Author Bess Croft
 * @Date 2023/8/31 21:38
 */
@Tag(name = "用户学习行为")
@RestController
@RequestMapping("/userWorkActions")
@RequiredArgsConstructor
public class UserWorkActionsController {

    private final UserWorkActionsService userWorkActionsService;

}
