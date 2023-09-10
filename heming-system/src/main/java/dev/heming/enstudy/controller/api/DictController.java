package dev.heming.enstudy.controller.api;

import dev.heming.enstudy.common.constant.MessageConstants;
import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.common.param.dict.DictChoiceParam;
import dev.heming.enstudy.common.result.CommonResult;
import dev.heming.enstudy.common.vo.statistics.TodayVo;
import dev.heming.enstudy.common.vo.userBookDict.UserBookDictVo;
import dev.heming.enstudy.common.vo.userWrongWord.UserWrongWordVo;
import dev.heming.enstudy.service.UserBookDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 词典
 * @Author Bess Croft
 * @Date 2023/9/3 19:44
 */
@Tag(name = "词典")
@RequestMapping("/dict")
@RestController
@RequiredArgsConstructor
public class DictController {

    private final UserBookDictService userBookDictService;

    @PostMapping("/choice")
    @Operation(summary = "用户选择词典接口")
    public CommonResult<Void> choice(@RequestBody @Valid DictChoiceParam param) {
        userBookDictService.choice(param);
        return CommonResult.success(MessageConstants.SUCCESS);
    }

    @GetMapping("/getDict")
    @Operation(summary = "获取所有词典接口")
    public CommonResult<List<BookDict>> getDict() {
        List<BookDict> dict = userBookDictService.getAllDict();
        return CommonResult.success(dict);
    }

    @GetMapping("/getUserDict")
    @Operation(summary = "获取活动词典")
    public CommonResult<UserBookDictVo> getUserDict() {
        UserBookDictVo userDict = userBookDictService.getUserDict();
        return CommonResult.success(userDict);
    }

    @GetMapping("/getWrongBook")
    @Operation(summary = "获取错题本信息")
    public CommonResult<UserWrongWordVo> getWrongBook() {
        UserWrongWordVo wrongBook = userBookDictService.getWrongBook();
        return CommonResult.success(wrongBook);
    }

    @GetMapping("/getToday")
    @Operation(summary = "获取今日记录")
    public CommonResult<TodayVo> getToday() {
        TodayVo today = userBookDictService.getToday();
        return CommonResult.success(today);
    }

}
