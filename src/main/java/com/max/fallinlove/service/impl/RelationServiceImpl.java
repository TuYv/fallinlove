package com.max.fallinlove.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.max.fallinlove.common.Result;
import com.max.fallinlove.entity.Relation;
import com.max.fallinlove.mapper.RelationMapper;
import com.max.fallinlove.mapper.UserMapper;
import com.max.fallinlove.service.IRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {

    @Autowired
    RelationMapper relationMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * 建立关系
     */
    @Override
    public Result setRelation(Integer userId, Integer toId) {
        //前端模糊查询 传入的必定存在
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("id", toId);
//        int count = userMapper.selectCount(queryWrapper);
//
//        if(count < 1) {
//            return new Result().failed("400", "该用户不存在");
//        }
        //todo 是否要验证之前的
        userMapper.updateLover(userId, toId);

        //todo  没有考虑之前的就有关系的情况
        //todo 后期考虑是否需要relation表
//        Relation relation = new Relation();
//        relation.setUser1Id(userId);
//        relation.setUser2Id(toId);
//
//        relationMapper.insert(relation);

        return new Result().success();
    }
}
