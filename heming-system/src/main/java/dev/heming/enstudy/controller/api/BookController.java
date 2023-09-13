package dev.heming.enstudy.controller.api;

import dev.heming.enstudy.common.constant.MessageConstants;
import dev.heming.enstudy.common.param.book.ActionsParam;
import dev.heming.enstudy.mongo.service.BookService;
import dev.heming.enstudy.common.entity.Book;
import dev.heming.enstudy.common.param.book.GetBookParam;
import dev.heming.enstudy.common.result.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/getBook")
    @Operation(summary = "根据条件获取单词")
    public CommonResult<Book> getBook(@RequestBody @Valid GetBookParam param) {
        return CommonResult.success(bookService.getBook(param));
    }

    @GetMapping("/getWord")
    @Operation(summary = "获取单个单词")
    public CommonResult<Book> getWord() {
        return CommonResult.success(bookService.getWord());
    }

    @GetMapping("/getWorkWord")
    @Operation(summary = "获取错题本单个单词")
    public CommonResult<Book> getWorkWord() {
        return CommonResult.success(bookService.getWorkWord());
    }

    @GetMapping("/getWordList")
    @Operation(summary = "获取单词列表")
    public CommonResult<List<Book>> getWordList() {
        return CommonResult.success(bookService.getWordList());
    }

    @PostMapping("/actions")
    @Operation(summary = "单词反馈接口")
    public CommonResult<Void> actions(@RequestBody @Valid ActionsParam param) {
        bookService.actions(param);
        return CommonResult.success(MessageConstants.SUCCESS);
    }

    @PostMapping("/failActions")
    @Operation(summary = "错题本单词反馈接口")
    public CommonResult<Void> failActions(@RequestBody @Valid ActionsParam param) {
        bookService.failActions(param);
        return CommonResult.success(MessageConstants.SUCCESS);
    }

}
