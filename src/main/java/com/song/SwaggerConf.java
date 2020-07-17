package com.song;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConf {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.song"))//这里配置swagger扫描的规则，可以是包/类注解/方法注解
                .paths(PathSelectors.any())//筛选路径，可是any/正则表达式/antPattern
                .build();
    }
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("工作流程服务接口（流程、过程、过程人员、过程条件判断） APIs")
                .description("工作流服务接口.")
                .termsOfServiceUrl("http://222.184.233.10:8089/pm2/flow-manager")
                .contact(new Contact("小宋","","963398090@qq.com"))
                .version("1.2")
                .build();
    }
}
