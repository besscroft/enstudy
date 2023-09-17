package dev.heming.enstudy.common.constant;

/**
 * @Description 缓存常量
 * @Author Bess Croft
 * @Date 2023/8/25 12:32
 */
public interface CacheConstants {

    /** 用户信息 */
    String USER = "user:userId:";

    /** 用户信息 */
    String USER_INFO = "user:username:";

    /** 单词列表信息 */
    String WORD_LIST = "word:bookId:";

    /** 获取控制台统计信息 */
    String CONSOLE_INFO = "console:info";

    /** 所有词典 */
    String ALL_BOOK_DICT = "book:dict:all";

    /** 当前用户的活动词典 */
    String USER_BOOK_DICT = "book:dict:userId:";

    /** 获取当前用户错题本 */
    String USER_WRONG_WORK = "book:wrong:userId:";

    /** 获取用户今日记录 */
    String TODAY = "today:userId:";

    /** 单词详情 */
    String BOOK_WORD = "bookDict:#{bookId}:word:#{wordId}";

    /** 词典信息 */
    String BOOK_INFO = "book:info:bookId:";

    /** 单词信息 */
    String WORD_INFO = "word:info:wordId:";

}
