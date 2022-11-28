package com.xibuguigu.yygh.common.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin")
                .apiInfo(adminApiInfo())
                .select()
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }


    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("后台管理系统-API文档")
                .description("本文档描述了后台管理系统微服务接口定义")
                .version("1.0")
                .build();
    }


    @Bean
    public Docket cmnApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("cmn")
                .apiInfo(cmnInfo())
                .select()
                //只显示api路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/cmn/.*")))
                .build();
    }

    private ApiInfo cmnInfo(){
        return new ApiInfoBuilder()
                .title("网站-API文档")
                .description("本文档描述了网站微服务接口定义")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket frontConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("front")
                .apiInfo(frontInfo())
                .select()
                //只显示api路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/front/.*")))
                .build();
    }

    private ApiInfo frontInfo(){
        return new ApiInfoBuilder()
                .title("网站-API文档")
                .description("本文档描述了预约挂号统一平台用户端接口的定义")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket innerConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("inner")
                .apiInfo(innerInfo())
                .select()
                //只显示api路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/inner/.*")))
                .build();
    }

    private ApiInfo innerInfo(){
        return new ApiInfoBuilder()
                .title("网站-API文档")
                .description("本文档描述了预约挂号统一平台用户端接口的定义")
                .version("1.0")
                .build();
    }
}