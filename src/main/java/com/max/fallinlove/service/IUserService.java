package com.max.fallinlove.service;

import com.max.fallinlove.common.Result;
import com.max.fallinlove.dto.UserDTO;
import com.max.fallinlove.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
