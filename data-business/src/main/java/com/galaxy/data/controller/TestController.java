package com.galaxy.data.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.galaxy.data.common.Result;
import com.galaxy.data.common.constants.ApiGroupTags;
import com.galaxy.data.common.constants.ApiUrlConstants;
import com.galaxy.data.entity.TestEntity;
import com.galaxy.data.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Common Module
 *
 * @author yao.qian
 */
@RestController
@Api(tags = ApiGroupTags.TEST_API_TAG)
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @ApiOperation(value = "Test Api")
    @GetMapping(ApiUrlConstants.TEST_URL_PREFIX + "/api")
    public Result testApi(@RequestBody TestEntity testEntity) {
        return Result.expect(testService.testApi(testEntity));
    }

    @ApiOperation(value = "Test Api")
    @GetMapping(ApiUrlConstants.TEST_URL_PREFIX + "/list")
    public Result<TestEntity> list(TestEntity testEntity) {
        return Result.data(testService.list(new LambdaQueryWrapper<TestEntity>()));
    }
}
