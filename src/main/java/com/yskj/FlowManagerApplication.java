package com.yskj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author XiaoSong
 * @date 2019-12-16 16:06
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.song.mapper")
public class FlowManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowManagerApplication.class, args);
    }
}
