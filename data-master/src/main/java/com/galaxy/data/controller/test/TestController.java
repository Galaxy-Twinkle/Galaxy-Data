package com.galaxy.data.controller.test;

import com.galaxy.data.common.Result;
import com.galaxy.data.common.constants.ApiGroupTagConstants;
import com.galaxy.data.common.constants.ApiUrlConstants;
import com.galaxy.data.system.entity.TestEntity;
import com.galaxy.data.system.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Common Module
 *
 * @author yao.qian
 */
@Api(tags = ApiGroupTagConstants.TEST_API_TAG)
@RestController(value = ApiUrlConstants.BACKSTAGE_URL_PREFIX + "/user")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @ApiOperation(value = "Test Api")
    @PostMapping("/create")
    public Result testApi(@RequestBody TestEntity testEntity) {
        return Result.expect(testService.testApi(testEntity));
    }

    @ApiOperation(value = "获取测试用户列表")
    @GetMapping("/list")
    public Result<TestEntity> list(TestEntity entity) {
        return Result.data(testService.list());
    }
}
