package dev.heming.enstudy.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import dev.heming.enstudy.common.constant.MessageConstants;
import dev.heming.enstudy.common.constant.RoleConstants;
import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.common.param.word.WordAddParam;
import dev.heming.enstudy.common.param.word.WordUpdateParam;
import dev.heming.enstudy.common.result.CommonResult;
import dev.heming.enstudy.common.utils.CommonPage;
import dev.heming.enstudy.service.WordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/wordPage")
    @SaCheckRole(
            value = {
                    RoleConstants.PLATFORM_SUPER_ADMIN,
                    RoleConstants.PLATFORM_ADMIN,
                    RoleConstants.PLATFORM_SELF_PROVISIONER,
                    RoleConstants.PLATFORM_VIEW
            },
            mode = SaMode.OR
    )
    @Operation(summary = "单词分页列表")
    public CommonResult<CommonPage<Word>> wordPage(@RequestParam("pageNum") Integer pageNum,
                                                   @RequestParam("pageSize") Integer pageSize) {
        List<Word> wordPageVoList = wordService.wordPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(wordPageVoList));
    }

    @PostMapping("/addWord")
    @SaCheckRole(
            value = {
                    RoleConstants.PLATFORM_SUPER_ADMIN,
                    RoleConstants.PLATFORM_ADMIN,
                    RoleConstants.PLATFORM_SELF_PROVISIONER
            },
            mode = SaMode.OR
    )
    @Operation(summary = "新增单词")
    public CommonResult<Void> addWord(@RequestBody @Valid WordAddParam param) {
        wordService.addWord(param);
        return CommonResult.success(MessageConstants.ADD_SUCCESS);
    }

    @PutMapping("/updateWord")
    @SaCheckRole(
            value = {
                    RoleConstants.PLATFORM_SUPER_ADMIN,
                    RoleConstants.PLATFORM_ADMIN,
                    RoleConstants.PLATFORM_SELF_PROVISIONER
            },
            mode = SaMode.OR
    )
    @Operation(summary = "更新单词")
    public CommonResult<Void> updateWord(@RequestBody @Valid WordUpdateParam param) {
        wordService.updateWord(param);
        return CommonResult.success(MessageConstants.UPDATE_SUCCESS);
    }

    @Operation(summary = "单词删除接口")
    @SaCheckRole(
            value = {
                    RoleConstants.PLATFORM_SUPER_ADMIN,
                    RoleConstants.PLATFORM_ADMIN,
                    RoleConstants.PLATFORM_SELF_PROVISIONER
            },
            mode = SaMode.OR
    )
    @DeleteMapping("/delete/{wordId:[\\d]+}")
    public CommonResult<Void> delete(@PathVariable(name = "wordId") Long wordId) {
        wordService.deleteWord(wordId);
        return CommonResult.success(MessageConstants.DELETE_SUCCESS);
    }

}
