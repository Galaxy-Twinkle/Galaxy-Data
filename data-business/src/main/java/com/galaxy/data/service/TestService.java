package com.galaxy.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.galaxy.data.entity.TestEntity;

/**
 * @author yao.qian
 */
public interface TestService extends IService<TestEntity> {

    /**
     * test API
     * @param entity 实体
     * @return  影响条数
     */
    int testApi(TestEntity entity);
}
