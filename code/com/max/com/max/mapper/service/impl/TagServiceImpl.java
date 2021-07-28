package com.max.com.max.mapper.service.impl;

import com.max.com.max.mapper.entity.Tag;
import com.max.com.max.mapper.mapper.TagMapper;
import com.max.com.max.mapper.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
