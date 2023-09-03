package dev.heming.enstudy.mongo.service.impl;

import dev.heming.enstudy.mongo.service.BookService;
import dev.heming.enstudy.common.entity.Book;
import dev.heming.enstudy.common.param.book.GetBookParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @Description 词库 Service 实现类
 * @Author Bess Croft
 * @Date 2023/8/26 15:26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final MongoTemplate mongoTemplate;

    @Override
    public Book getBook(GetBookParam param) {
        Query query = new Query(
                Criteria
                        .where("bookId").is("CET4luan_2")
                        .and("wordRank").is(param.getWordRank())
                        .and("headWord").is(param.getHeadWord())
        );
        Book result = mongoTemplate.findOne(query, Book.class);
        return result;
    }

}
