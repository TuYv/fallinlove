package com.max.fallinlove.finance.service.impl;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.common.tools.RedisUtils;
import com.max.fallinlove.finance.entity.Tag;
import com.max.fallinlove.finance.mapper.TagMapper;
import com.max.fallinlove.finance.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @Override
    public List<Tag> getTags(Integer userId) {
        Set<String> set = redisUtils.rangeZset(userId.toString(),0, -1);
        List<Tag> tags = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(set)) {
            tags = tagMapper.selectBatchIds(set);
        }
        return tags;
    }
}
