package dev.heming.enstudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.common.param.dict.BookDictAddParam;
import dev.heming.enstudy.common.param.dict.BookDictUpdateParam;

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

    /**
     * 新增词典
     * @param param 新增参数
     */
    void addDict(BookDictAddParam param);

    /**
     * 更新词典
     * @param param 更新参数
     */
    void updateDict(BookDictUpdateParam param);

    /**
     * 删除词典
     * @param dictId 词典 id
     */
    void deleteDict(Long dictId);

    /**
     * 获取词典信息
     * @param dictId 词典 id
     * @return 词典信息
     */
    BookDict getDictById(Long dictId);

}
