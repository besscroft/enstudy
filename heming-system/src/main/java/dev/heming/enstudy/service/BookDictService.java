package dev.heming.enstudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.enstudy.common.entity.BookDict;

import java.util.List;

/**
 * @Description 词典 服务
 * @Author Bess Croft
 * @Date 2023/8/26 17:04
 */
public interface BookDictService extends IService<BookDict> {

    /**
     * 词典分页列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 词典分页列表
     */
    List<BookDict> bookDictPage(Integer pageNum, Integer pageSize);

}
