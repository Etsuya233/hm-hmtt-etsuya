package com.heima.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 配置类注解，表明该类为配置类
@Configuration
// 开启Swagger2注解
@EnableSwagger2
public class SwaggerConfiguration {

	// 定义一个Bean，用于配置Swagger Docket
	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				// 设置API信息
				.apiInfo(buildApiInfo())
				// 选择扫描的API(Controller)基础包
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.heima"))
				// 扫描所有路径
				.paths(PathSelectors.any())
				.build();
	}

	// 构建API信息
	private ApiInfo buildApiInfo() {
		// 定义联系人信息
		Contact contact = new Contact("黑马程序员", "", "");
		return new ApiInfoBuilder()
				// 设置API标题
				.title("黑马头条-平台管理API文档")
				// 设置API描述
				.description("黑马头条后台api")
				// 设置联系人信息
				.contact(contact)
				// 设置API版本
				.version("1.0.0")
				.build();
	}
}
