package com.max.fallinlove.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DatePattern;
import com.max.fallinlove.common.Result;
import com.max.fallinlove.dto.UserDTO;
import com.max.fallinlove.entity.User;
import com.max.fallinlove.mapper.UserMapper;
import com.max.fallinlove.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class UserServiceImpl<T> extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private UserMapper userMapper;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9$@!.%*#_~?&]{8,16}$");

    /**
     * 登录
     */
    @Override
    public Result login(String userName, String password) {
        /*if (!verifyMessage(userName,password)) {
           return new Result().failed(200, "账号密码不符合要求");
        }*/
        User user = userMapper.selectByNameAndPwd(userName, password);
        if (Objects.nonNull(user)) {
            return new Result().success(user);
        }else {
            return new Result().failed(200, "数据不存在");
        }
    }

    /**
     * 注册
     */
    @Override
    public Result register(UserDTO userDTO) {
        User user = userMapper.selectByNickName(userDTO.getNickName());
        if(Objects.nonNull(user)) {
            return new Result().failed(200, "该昵称已存在");
        } else {
            user = new User();
            BeanUtil.copyProperties(userDTO, user, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
            userMapper.insert(user);
        }

        return new Result().success();
    }

    private boolean verifyMessage(String userName, String password) {
        Matcher m = NAME_PATTERN.matcher(userName);
        if (m.matches()) {
            m = PASSWORD_PATTERN.matcher(password);
            return m.matches();
        }
        return false;
    }
}
