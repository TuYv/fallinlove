package com.max.fallinlove.gateway.common;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 限流配置
 * @author max
 * @date 2021/6/9 20:32
 */
@Configuration
public class RedisRateLimiterConfig {
    /**
     * 按客户端IP限流
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName());
    }
}
