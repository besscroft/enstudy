package dev.heming.enstudy;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.heming.enstudy.common.entity.Book;
import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.service.BookDictService;
import dev.heming.enstudy.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class HemingSystemApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookDictService bookDictService;

    @Autowired
    private WordService wordService;

    @Test
    void contextLoads() throws IOException {
        List<File> fileList = (List<File>)FileUtils.listFiles(new File("D:\\data\\dict"),null,false);
        Assert.notEmpty(fileList, "读到的内容为空！");
        log.info("文件数量: {}", fileList.size());
        for (int i=0; i<fileList.size(); i++) {
            List<String> lines = FileUtils.readLines(fileList.get(i), StandardCharsets.UTF_8);
            log.info("行数: {}", lines.size());
            Assert.notEmpty(lines, "读到的内容为空！");
            List<Book> bookList = new ArrayList<>();
            for (int j=0; j<lines.size(); j++) {
                try {
                    Book books = objectMapper.readValue(lines.get(j), Book.class);
                    bookList.add(books);
                } catch (Exception e) {
                    log.error("第 {} 行解析失败！", j+1);
                }

            }
            mongoTemplate.insertAll(bookList);
            log.info("插入完成！");
        }
    }

    @Test
    void syncMongoToMySQL() {
        log.info("开始执行任务！");
        List<BookDict> list = bookDictService.list();
        log.info("查到词库: {} 个.", list.size());
        log.info("开始同步！");
        for (BookDict bookDict : list) {
            Query query = new Query(
                    Criteria
                            .where("bookId").is(bookDict.getBookId())
            );
            List<Book> bookList = mongoTemplate.find(query, Book.class);
            log.info("词库: {} 有 {} 条数据.", bookDict.getBookId(), bookList.size());
            List<Word> wordList = new ArrayList<>();
            for (Book book : bookList) {
                Word word = new Word();
                word.setHeadWord(book.getHeadWord());
                word.setWordRank(book.getWordRank());
                word.setWordJsonId(book.getId());
                word.setBookId(bookDict.getBookId());
                wordList.add(word);
            }
            wordService.saveBatch(wordList);
        }
        log.info("同步完成！");
    }

}
