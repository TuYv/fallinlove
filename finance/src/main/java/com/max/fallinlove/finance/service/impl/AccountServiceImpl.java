package com.max.fallinlove.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.finance.entity.Account;
import com.max.fallinlove.finance.mapper.AccountMapper;
import com.max.fallinlove.finance.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

//    @Autowired
//    JedisPool jedisPool;

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
