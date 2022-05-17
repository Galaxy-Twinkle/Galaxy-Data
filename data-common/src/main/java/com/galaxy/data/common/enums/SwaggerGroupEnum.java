package com.galaxy.data.common.enums;

import com.galaxy.data.common.constants.ApiUrlConstants;

/**
 * Swagger分组
 *
 * @author yao.qian
 */
public enum SwaggerGroupEnum {

    /**
     * 测试
     */
    SWAGGER_GROUP_TEST(ApiUrlConstants.TEST_URL_PREFIX, "百炼成钢（测试）"),

    /**
     * 后台
     */
    SWAGGER_GROUP_BACKSTAGE(ApiUrlConstants.BACKSTAGE_URL_PREFIX, "运筹帷幄（后台）"),

    /**
     * APP
     */
    SWAGGER_GROUP_APP(ApiUrlConstants.APP_URL_PREFIX, "鹤立鸡群（APP）");


    /**
     * 路径
     */
    private final String url;

    /**
     * 描述
     */
    private final String remark;

    SwaggerGroupEnum(String url, String remark) {
        this.url = url;
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public String getRemark() {
        return remark;
    }
}
