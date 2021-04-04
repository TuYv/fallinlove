package com.max.fallinlove.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.max.fallinlove.constants.RedisConstants;
import com.max.fallinlove.entity.Account;
import com.max.fallinlove.mapper.AccountMapper;
import com.max.fallinlove.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max.tu
 * @since 2021-03-23
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Autowired
    JedisPool jedisPool;

/*    @Override
    public boolean saveOrUpdate(Account account) {
        if (super.saveOrUpdate(account)) {
            Jedis jedis = jedisPool.getResource();

            String result = null;
            result = jedis.setex(RedisConstants.TOTLE_AMOUNT + account.getId(), RedisConstants.EXPIRE_30_MINUTE, account.getTotalAmount().toString());
            assert result != null;
            return result.equals("OK");

        }
        return false;
    }*/
}
