package com.song;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ComponentScan(basePackages = {"com.song.model", "com.song.entity"})
public class SwaggerConf {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("工作流程服务接口（流程、过程、过程人员、过程条件判断） APIs")
                .description("工作流服务接口.")
                .version("1.2")
                .termsOfServiceUrl("http://222.184.233.10:8089/pm2/flow-manager")
                .license("假装这里有license")
                .licenseUrl("http://222.184.233.10:8089/pm2/flow-manager")
                .build();
    }
}
