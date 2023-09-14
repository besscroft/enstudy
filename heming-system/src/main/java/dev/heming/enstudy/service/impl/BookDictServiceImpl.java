package dev.heming.enstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import dev.heming.enstudy.common.converter.BookDictConverterMapper;
import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.common.param.dict.BookDictAddParam;
import dev.heming.enstudy.common.param.dict.BookDictUpdateParam;
import dev.heming.enstudy.mapper.BookDictMapper;
import dev.heming.enstudy.service.BookDictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Description 词典服务实现类
 * @Author Bess Croft
 * @Date 2023/8/26 17:04
 */
@Service
public class BookDictServiceImpl extends ServiceImpl<BookDictMapper, BookDict> implements BookDictService {

    @Override
    public List<BookDict> bookDictPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return this.list();
    }

    @Override
    public void addDict(BookDictAddParam param) {
        BookDict bookDict = BookDictConverterMapper.INSTANCE.AddParamToBookDict(param);
        Assert.isTrue(this.baseMapper.insert(bookDict) > 0, "新增词典失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDict(BookDictUpdateParam param) {
        BookDict oldBookDict = this.baseMapper.selectById(param.getId());
        Assert.notNull(oldBookDict, "未找到要更新的数据！");
        BookDict bookDict = BookDictConverterMapper.INSTANCE.UpdateParamToBookDict(param);
        Assert.isTrue(this.baseMapper.updateById(bookDict) > 0, "更新词典失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDict(Long dictId) {
        Assert.isTrue(this.baseMapper.deleteById(dictId) > 0, "词典删除失败！");
    }

}
