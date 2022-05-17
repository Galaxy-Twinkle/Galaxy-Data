package com.galaxy.data.common.config;

import com.galaxy.data.common.enums.ErrorCodeMessagesEnum;
import com.galaxy.data.common.enums.SwaggerGroupEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yao.qian
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean("testDocket")
    public Docket testDocket() {
        return getDocket(SwaggerGroupEnum.SWAGGER_GROUP_TEST.getRemark(), new String[]{
                        ".*"
                }, testApiInfo(), "com.galaxy.data.controller.test"
        );
    }

    @Bean("backstageDocket")
    public Docket backstageDocket() {
        return getDocket(SwaggerGroupEnum.SWAGGER_GROUP_BACKSTAGE.getRemark(), new String[]{
                        ".*"
                }, backstageApiInfo(), "com.galaxy.data.controller.backstage"
        );
    }


    @Bean("appDocket")
    public Docket appDocket() {
        return getDocket(SwaggerGroupEnum.SWAGGER_GROUP_APP.getRemark(), new String[]{
                        ".*"
                }, appApiInfo(), "com.galaxy.data.controller.app"
        );
    }

    /**
     * 测试配置文档信息
     */
    private ApiInfo testApiInfo() {
        return new ApiInfo(
                "Galaxy Data Test Api", " Test Api", "1.0.0", "",
                new Contact("galaxy", "www.galaxy.com", "galaxy.stars@qq.com"), "", "",
                Collections.singletonList(new StringVendorExtension("Build Your Galaxy", "2022-05-12"))
        );
    }


    /**
     * 后台配置文档信息
     */
    private ApiInfo backstageApiInfo() {
        return new ApiInfo(
                "Galaxy Data Backstage Api", "Backstage Api", "1.0.0", "",
                new Contact("galaxy", "www.galaxy.com", "galaxy.stars@qq.com"), "", "",
                Collections.singletonList(new StringVendorExtension("Build Your Galaxy", "2022-05-12"))
        );
    }


    /**
     * App配置文档信息
     */
    private ApiInfo appApiInfo() {
        return new ApiInfo(
                "Galaxy Data App Api", "App Api", "1.0.0", "",
                new Contact("galaxy", "www.galaxy.com", "galaxy.stars@qq.com"), "", "",
                Collections.singletonList(new StringVendorExtension("Build Your Galaxy", "2022-05-12"))
        );
    }

    /**
     * @param groupName 组名
     * @param paths     正则路径匹配
     * @return api文档
     */
    private Docket getDocket(String groupName, String[] paths, ApiInfo apiInfo, final String basePackage) {
        ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage));
        if (paths == null) {
            builder.paths(PathSelectors.any());
        } else {
            StringBuilder pathRegex = new StringBuilder();
            for (String path : paths) {
                pathRegex.append("(").append(path).append(")|");
            }
            builder.paths(PathSelectors.regex(pathRegex.substring(0, pathRegex.length() - 1)));
        }

        return builder.build()
//                .globalOperationParameters(getPubParam())
                .globalResponseMessage(RequestMethod.GET, getResponseMessage())
                .globalResponseMessage(RequestMethod.POST, getResponseMessage());
    }

    /**
     * 获得公共的参数
     *
     * @return 公共参数列表
     */
    private List<Parameter> getPubParam() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("X-Auth-Token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

    private List<ResponseMessage> getResponseMessage() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        ErrorCodeMessagesEnum[] values = ErrorCodeMessagesEnum.values();
        for (ErrorCodeMessagesEnum value : values) {
            responseMessageList.add(new ResponseMessageBuilder().code(value.getCode()).message(value.getMessage()).build());
        }
        return responseMessageList;
    }
}
