package com.max.fallinlove.account.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.common.result.Result;
import com.max.fallinlove.common.result.ResultUtils;
import com.max.fallinlove.account.dto.UserDTO;
import com.max.fallinlove.account.entity.User;
import com.max.fallinlove.account.mapper.UserMapper;
import com.max.fallinlove.account.service.IUserService;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.max.fallinlove.base.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class UserServiceImpl<T> extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private UserMapper userMapper;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9$@!.%*#_~?&]{8,16}$");

    //todo 注释了登录注册的数据验证功能 后期放开
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
            UserDTO dto = new UserDTO();
            BeanUtil.copyProperties(user,dto,true);
            String token = JwtUtil.createToken(user.getId().toString());
            log.info("生成的token为：" + token);
            dto.setToken(token);
            return ResultUtils.success(dto);
        }else {
            return ResultUtils.fail("200", "数据不存在");
        }
    }

    /**
     * 注册
     */
    @Override
    public Result register(UserDTO userDTO) {
        /*if (!verifyMessage(userDTO.userName,userDTO.password)) {
           return new Result().failed(200, "账号密码不符合要求");
        }*/
        User user = userMapper.selectByNickName(userDTO.getNickName());
        if(Objects.nonNull(user)) {
            return ResultUtils.fail("200", "该昵称已存在");
        } else {
            user = new User();
            BeanUtil.copyProperties(userDTO, user, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
            userMapper.insert(user);
        }

        return ResultUtils.success();
    }

    /**
     * 验证注册信息是否符合要求
     * @param userName
     * @param password
     * @return
     */
    private boolean verifyMessage(String userName, String password) {
        Matcher m = NAME_PATTERN.matcher(userName);
        if (m.matches()) {
            m = PASSWORD_PATTERN.matcher(password);
            return m.matches();
        }
        return false;
    }
}
