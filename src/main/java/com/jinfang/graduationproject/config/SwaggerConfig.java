package com.jinfang.graduationproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private List<Parameter> needParameters() {
		// 添加请求参数，我们这里把token作为请求头部参数传入后端
		ParameterBuilder parameterBuilder = new ParameterBuilder();
		List<Parameter> parameters = new ArrayList<>();
		parameterBuilder.name("token").description("令牌")
				.modelRef(new ModelRef("string")).parameterType("header").required(true).build();

		parameters.add(parameterBuilder.build());

		return parameters;
	}

	@Bean
	public Docket createRestApiForSystem() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.jinfang.graduationproject.controller.system"))
				.paths(PathSelectors.any()).build().groupName("系统接口")
				.globalOperationParameters(needParameters());
	}

	@Bean
	public Docket createRestApiForStudent() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.jinfang.graduationproject.controller.student"))
				.paths(PathSelectors.any()).build().groupName("学生接口")
				.globalOperationParameters(needParameters());
	}

	@Bean
	public Docket createRestApiForTeacher() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.jinfang.graduationproject.controller.teacher"))
				.paths(PathSelectors.any()).build().groupName("教师接口")
				.globalOperationParameters(needParameters());
	}

//    @Bean
//    public Docket createRestApi(){
//		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
//				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
//				.build().globalOperationParameters(parameters);
//    }

    private ApiInfo apiInfo(){
    	return new ApiInfoBuilder()
    			.title("毕业设计管理系统 API Doc")
				.contact(new Contact("杭州金房科技有限公司", "", ""))
    			.description("1、专业负责人组建答辩组、设置答辩相关参数、设置评分角色\n" +
						"2、指导教师上报毕业设计（论文）题目，等待学生选则该课题后，接收做该课题的学生\n" +
						"3、学生完成文献综述、外文翻译和开题报告，指导教师检查，并给出评语和评分\n" +
						"4、学生进行开题答辩，由答辩组长给出开提意见及评分\n" +
						"5、学生完成毕业设计（论文），由指导教师检查并，并给出评语和评分\n" +
						"6、学生进行毕业答辩，由答辩组给出成绩和意见")
    			.version("1.0")
    			.build();
    }

}