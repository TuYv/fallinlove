package com.max.fallinlove.finance.repository;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.max.fallinlove.common.tools.RedisUtils;
import com.max.fallinlove.finance.entity.Tag;
import com.max.fallinlove.finance.mapper.TagMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 标签仓储服务
 *
 * @author Rick
 * @date 2022-02-07 17:49
 */
@Repository
public class TagRepository {

    @Resource
    private TagMapper tagMapper;
    @Resource
    private RedisUtils redisUtils;

    /**
     * 根据tagName更新redis
     * @param userId
     * @param tagName
     */
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

    /**
     * 根据userID获取tag列表
     * @param userId
     * @return
     */
    public List<Tag> getTags(Integer userId) {
        Set<String> set = redisUtils.rangeZset(userId.toString(),0, -1);
        List<Tag> tags = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(set)) {
            tags = tagMapper.selectBatchIds(set);
        }
        return tags;
    }
}
