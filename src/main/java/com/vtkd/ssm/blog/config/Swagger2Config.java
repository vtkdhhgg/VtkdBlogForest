package com.vtkd.ssm.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 配置类
 * 1. 导入依赖
 * 2. 创建 配置类
 * 3. 将配置类注册到spring 中
 *
 * 注解:
 * @Configuration 告诉spring我是个配置类
 * @CompentScan("") 就是要扫描的控制类地址
 * @EnableSwagger2 加载Swagger2 需要的 bean
 */
@Configuration
@EnableSwagger2
@ComponentScan("com.vtkd.ssm.blog.controller")
public class Swagger2Config {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2) // 指定api类型为swagger2
                .apiInfo(apiInfo())                    //指定文档汇总信息
                .enable(true)     //是否启用Swagger
                .select()
                .paths(PathSelectors.any())           // 指定展示所有controller
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("MyBlog_Forest Api 测试")                  //文档页标题
                .description("君上博客的API,用来测试和管理")        //详细信息
                .contact(
                        new Contact(
                                "君上",
                                "http://lcoalhost:8080:/xxx",
                                "148110067@qq.com")
                )                                                           // 联系人信息
                .termsOfServiceUrl("http://127.0.0.1:8888:/")   //网站地址
                .license("© 2022vtkd. All 暂时没有执照.")
                .version("1.0")                                 //文档版本号
                .build();
    }
}
