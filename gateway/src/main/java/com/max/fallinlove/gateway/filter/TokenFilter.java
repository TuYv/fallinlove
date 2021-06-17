package com.max.fallinlove.gateway.filter;

import com.max.fallinlove.base.utils.JwtUtil;
import com.max.fallinlove.gateway.common.RedisOperater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * token认证过滤器
 * @author max
 * @date 2021/6/15 20:20
 */
@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisOperater redisOperater;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //设置白名单
        if(exchange.getRequest().getURI().getPath().equals("/account/user/login")) {
            return chain.filter(exchange);
        }
        String token = Objects.requireNonNull(exchange.getRequest().getHeaders().get("Authorization")).get(0);
        String id = Objects.requireNonNull(exchange.getRequest().getHeaders().get("id")).get(0);
        log.info("获取到的token为:" + token);
        String idInToken = JwtUtil.verifyToken(token);
        if (id.equals(idInToken)) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
