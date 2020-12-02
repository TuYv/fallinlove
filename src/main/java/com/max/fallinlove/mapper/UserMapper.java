package com.max.fallinlove.mapper;

import com.max.fallinlove.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByNameAndPwd(String name, String password);

    User selectByNickName(@Param("nickName") String nickName);

    void updateLover(@Param("userId") Integer userId, @Param("loveId") Integer loverId);
}
