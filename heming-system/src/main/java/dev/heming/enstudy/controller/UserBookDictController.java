package dev.heming.enstudy.controller;

import dev.heming.enstudy.service.UserBookDictService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 用户活动词典控制器
 * @Author Bess Croft
 * @Date 2023/8/31 21:38
 */
@Tag(name = "用户活动词典")
@RestController
@RequestMapping("/userBookDict")
@RequiredArgsConstructor
public class UserBookDictController {

    private final UserBookDictService userBookDictService;

}
