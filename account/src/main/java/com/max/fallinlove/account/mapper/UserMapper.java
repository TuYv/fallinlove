package com.max.fallinlove.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.fallinlove.account.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author max.tu
 * @since 2021-07-23
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByNameAndPwd(String name, String password);

    User selectByNickName(@Param("nickName") String nickName);

    void updateLover(@Param("userId") Integer userId, @Param("loveId") Integer loverId);
}
