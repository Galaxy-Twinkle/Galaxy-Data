package com.galaxy.data.config;

import com.galaxy.data.common.enums.ErrorCodeMessagesEnum;
import io.swagger.annotations.ApiOperation;
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
    public Docket docket1() {
        return getDocket("TestApi", new String[]{
                        "/test.*"
                }, testApiInfo()
        );
    }

    @Bean("docket")
    public Docket docket() {
        return getDocket("ApiInfo", new String[]{
                        "/*"
                }, apiInfo()
        );
    }

    /**
     * 配置文档信息
     */
    private ApiInfo testApiInfo() {
        return new ApiInfo(
                "Yao's SpringBoot Simple Project", "Simple Project", "1.0.0", "",
                new Contact("Yao", "", ""), "", "",
                Collections.singletonList(new StringVendorExtension("Build Your Dreams", "2022-05-12"))
        );
    }


    /**
     * 配置文档信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Simple Project", "Simple Project", "1.0.0", "",
                new Contact("Yao", "", ""), "", "",
                Collections.singletonList(new StringVendorExtension("Build Your Dreams", "2022-05-12"))
        );
    }

    /**
     * @param groupName 组名
     * @param paths     正则路径匹配
     * @return api文档
     */
    private Docket getDocket(String groupName, String[] paths, ApiInfo apiInfo) {
        ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        if (paths == null) {
            apiSelectorBuilder.paths(PathSelectors.any());
        } else {
            StringBuilder pathRegex = new StringBuilder();
            for (String path : paths) {
                pathRegex.append("(").append(path).append(")|");
            }
            apiSelectorBuilder.paths(PathSelectors.regex(pathRegex.substring(0, pathRegex.length() - 1)));
        }

        return apiSelectorBuilder.build()
//                .globalOperationParameters(getPubParam())
                .globalResponseMessage(RequestMethod.GET, getResponseMessage())
                .globalResponseMessage(RequestMethod.POST, getResponseMessage())
                ;
    }

    /**
     * 获得公共的参数
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
        responseMessageList.add(new ResponseMessageBuilder().code(ErrorCodeMessagesEnum.AUTHENTICATION_SUCCESS.getCode()).message(ErrorCodeMessagesEnum.AUTHENTICATION_SUCCESS.getMessage()).build());
        responseMessageList.add(new ResponseMessageBuilder().code(ErrorCodeMessagesEnum.AUTHENTICATION_FAILED.getCode()).message(ErrorCodeMessagesEnum.AUTHENTICATION_FAILED.getMessage()).build());
        responseMessageList.add(new ResponseMessageBuilder().code(ErrorCodeMessagesEnum.NO_PERMISSION.getCode()).message(ErrorCodeMessagesEnum.NO_PERMISSION.getMessage()).build());
        return responseMessageList;
    }
}
