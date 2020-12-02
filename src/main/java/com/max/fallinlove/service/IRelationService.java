package com.max.fallinlove.service;

import com.max.fallinlove.common.Result;
import com.max.fallinlove.entity.Relation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
public interface IRelationService extends IService<Relation> {

    /**
     * 建立关系
     * @param userId
     * @param toId
     * @return
     */
    Result setRelation(Integer userId, Integer toId);


}
