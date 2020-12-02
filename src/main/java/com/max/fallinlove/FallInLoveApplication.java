package com.max.fallinlove;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@ComponentScan(basePackages={"com.max"})
@MapperScan("com.max.fallinlove.mapper")
public class FallInLoveApplication {

    public static void main(String[] args) {
        SpringApplication.run(FallInLoveApplication.class, args);
    }

}
