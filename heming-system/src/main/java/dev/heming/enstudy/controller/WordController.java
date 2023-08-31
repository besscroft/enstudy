package dev.heming.enstudy.controller;

import dev.heming.enstudy.service.WordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 单词控制器
 * @Author Bess Croft
 * @Date 2023/8/31 21:38
 */
@Tag(name = "单词")
@RestController
@RequestMapping("/word")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

}
