package dev.heming.enstudy.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.heming.enstudy.common.constant.SystemConstants;
import dev.heming.enstudy.common.entity.UserBookDict;
import dev.heming.enstudy.common.param.dict.DictChoiceParam;
import dev.heming.enstudy.mapper.UserBookDictMapper;
import dev.heming.enstudy.service.UserBookDictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @Description 用户活动词库 服务实现类
 * @Author Bess Croft
 * @Date 2023/8/31 21:37
 */
@Service
public class UserBookDictServiceImpl extends ServiceImpl<UserBookDictMapper, UserBookDict> implements UserBookDictService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void choice(DictChoiceParam param) {
        long userId = StpUtil.getLoginIdAsLong();
        List<UserBookDict> userBookDictList = this.baseMapper.selectAllByUserId(userId);
        if (CollectionUtils.isEmpty(userBookDictList)) {
            // 如果没有数据
            UserBookDict userBookDict = new UserBookDict();
            userBookDict.setUserId(userId);
            userBookDict.setBookId(param.getBookId());
            this.save(userBookDict);
            // TODO 生成学习汇总数据
        } else {
            // 有数据
            Optional<UserBookDict> bookDict = userBookDictList.stream()
                    .filter(userBookDict -> Objects.equals(userBookDict.getBookId(), param.getBookId()))
                    .findFirst();
            // 先全部设置为不启用
            this.baseMapper.updateActionNoByUserId(userId);
            if (bookDict.isEmpty()) {
                // 如果为空，插新数据
                UserBookDict userBookDict = new UserBookDict();
                userBookDict.setUserId(userId);
                userBookDict.setBookId(param.getBookId());
                this.save(userBookDict);
                // TODO 生成学习汇总数据
            } else {
                // 更新
                UserBookDict dict = bookDict.get();
                dict.setAction(SystemConstants.STATUS_OK);
                this.updateById(dict);
            }
        }
    }

}
