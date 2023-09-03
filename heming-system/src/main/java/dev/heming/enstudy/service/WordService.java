package dev.heming.enstudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.enstudy.common.entity.Word;

import java.util.List;

/**
 * @Description 单词 服务
 * @Author Bess Croft
 * @Date 2023/8/31 21:34
 */
public interface WordService extends IService<Word> {

    /**
     * 根据词库 id 获取单词列表
     * @param bookId 词库 id
     * @return 单词列表
     */
    List<Word> getWordListByBookId(String bookId);

}
