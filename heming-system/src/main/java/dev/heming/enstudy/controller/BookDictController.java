package dev.heming.enstudy.controller;

import dev.heming.enstudy.service.BookDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2023/8/26 17:06
 */
@RestController
@RequestMapping("/bookDict")
@RequiredArgsConstructor
public class BookDictController {

    private final BookDictService bookDictService;

}
