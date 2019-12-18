package com.song;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author XiaoSong
 * @date 2019-12-16 16:06
 */
@SpringBootApplication
@MapperScan("com.song.mapper")
public class FlowManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowManagerApplication.class, args);
    }
}
