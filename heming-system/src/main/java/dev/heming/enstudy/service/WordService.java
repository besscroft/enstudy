package dev.heming.enstudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.enstudy.common.entity.Book;
import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.common.param.word.WordAddParam;
import dev.heming.enstudy.common.param.word.WordUpdateParam;
import dev.heming.enstudy.common.vo.console.ConsoleVo;

import java.util.List;

/**
 * @Description 单词 服务
 * @Author Bess Croft
 * @Date 2023/8/31 21:34
 */
public interface WordService extends IService<Word> {

    /**
     * 根据词典 id 获取单词列表
     * @param bookId 词典 id
     * @return 单词列表
     */
    List<Word> getWordListByBookId(String bookId);

    /**
     * 单词分页
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return 单词列表
     */
    List<Word> wordPage(Integer pageNum, Integer pageSize);

    /**
     * 获取控制台统计信息
     * @return 控制台统计信息
     */
    ConsoleVo getConsoleInfo();

    /**
     * 获取单词
     * @param wordId 单词表 id
     * @param bookId 词典 id
     * @return 单词数据
     */
    Book getWordByWordIdAndBookId(Long wordId, String bookId);

    /**
     * 新增单词
     * @param param 新增参数
     */
    void addWord(WordAddParam param);

    /**
     * 更新单词
     * @param param 更新参数
     */
    void updateWord(WordUpdateParam param);

    /**
     * 删除单词
     * @param wordId 单词 id
     */
    void deleteWord(Long wordId);

}
