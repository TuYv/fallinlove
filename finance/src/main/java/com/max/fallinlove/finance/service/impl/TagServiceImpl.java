package com.max.fallinlove.finance.service.impl;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.common.tools.RedisUtils;
import com.max.fallinlove.finance.entity.Tag;
import com.max.fallinlove.finance.mapper.TagMapper;
import com.max.fallinlove.finance.repository.TagRepository;
import com.max.fallinlove.finance.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource TagRepository tagRepository;

    @Override
    public void updateByTagName(Integer userId,String tagName) {
        if (StringUtils.isNotEmpty(tagName)) {
            tagRepository.updateByTagName(userId, tagName);
        }
    }

    @Override
    public List<Tag> getTags(Integer userId) {
        return tagRepository.getTags(userId);
    }
}
