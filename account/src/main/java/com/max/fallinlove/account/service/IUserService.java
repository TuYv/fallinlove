package com.max.fallinlove.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.max.fallinlove.common.result.Result;
import com.max.fallinlove.account.dto.UserDTO;
import com.max.fallinlove.account.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    Result login(String userName, String password);

    /**
     * 注册
     * @param userDTO
     * @return
     */
    Result register(UserDTO userDTO);

}
