package dev.heming.enstudy.controller.api;

import cn.dev33.satoken.annotation.SaIgnore;
import dev.heming.enstudy.mongo.service.BookService;
import dev.heming.enstudy.common.entity.Book;
import dev.heming.enstudy.common.param.book.GetBookParam;
import dev.heming.enstudy.common.result.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 单词
 * @Author Bess Croft
 * @Date 2023/8/26 15:22
 */
@Tag(name = "单词")
@RequestMapping("/book")
@RestController
@RequiredArgsConstructor

public class BookController {

    private final BookService bookService;

    @SaIgnore
    @PostMapping("/getBook")
    public CommonResult<Book> getBook(@RequestBody @Valid GetBookParam param) {
        return CommonResult.success(bookService.getBook(param));
    }

}
