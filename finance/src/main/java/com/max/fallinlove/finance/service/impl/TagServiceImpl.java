package com.max.fallinlove.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.common.tools.RedisUtils;
import com.max.fallinlove.finance.entity.Tag;
import com.max.fallinlove.finance.mapper.TagMapper;
import com.max.fallinlove.finance.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author max.tu
 * @since 2021-07-28
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void updateByTagName(Integer userId,String tagName) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper();
        queryWrapper.eq("tag_name", tagName);
        Tag tag = tagMapper.selectOne(queryWrapper);
        if(Objects.isNull(tag)) {
            tag = new Tag();
            tag.setTagName(tagName);
            tagMapper.insert(tag);
        }
        redisUtils.incrementScore(userId.toString(), tag.getId().toString(), 1.0);
    }
}
