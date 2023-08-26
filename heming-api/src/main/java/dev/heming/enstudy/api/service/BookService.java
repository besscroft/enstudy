package dev.heming.enstudy.api.service;

import dev.heming.enstudy.common.entity.Book;
import dev.heming.enstudy.common.param.book.GetBookParam;

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

}
