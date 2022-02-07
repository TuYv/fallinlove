package com.max.fallinlove.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.max.fallinlove.finance.entity.Tag;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author max.tu
 * @since 2021-07-28
 */
public interface ITagService extends IService<Tag> {
    /**
     * 更新tag表并zset redis
     * @param userId
     * @param tagName
     */
    void updateByTagName(Integer userId,String tagName);

    /**
     * 根据userID获取tag列表
     * @param userId
     * @return
     */
    List<Tag> getTags(Integer userId);
}
