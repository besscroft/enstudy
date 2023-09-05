package dev.heming.enstudy.mongo.service;

import dev.heming.enstudy.common.entity.Book;
import dev.heming.enstudy.common.param.book.ActionsParam;
import dev.heming.enstudy.common.param.book.GetBookParam;

import java.util.List;

/**
 * @Description 词库 Service
 * @Author Bess Croft
 * @Date 2023/8/26 15:26
 */
public interface BookService {

    /**
     * 获取单词
     * @param param 参数
     * @return 单词
     */
    Book getBook(GetBookParam param);

    /**
     * 获取当前用户活动词典的下一个单词信息
     * @return 单词信息
     */
    Book getWord();

    /**
     * 获取当前用户活动词典的单词学习列表
     * @return 单词信息列表
     */
    List<Book> getWordList();

    /**
     * 用户学习行为处理
     * @param param 参数
     */
    void actions(ActionsParam param);

}
