package com.galaxy.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.galaxy.data.common.utils.AesUtil;
import com.galaxy.data.common.utils.RandomUtil;
import com.galaxy.data.entity.TestEntity;
import com.galaxy.data.mapper.TestMapper;
import com.galaxy.data.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yao.qian
 */
@Slf4j
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestEntity> implements TestService {

    private final TestMapper testMapper;

    @Autowired
    public TestServiceImpl(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    private final static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    /**
     * test API
     *
     * @param entity 实体
     */
    @Override
    public int testApi(TestEntity entity) {
        String random = RandomUtil.getRandom(16);
        String password = AesUtil.encryptAes(entity.getPassword(), random);
        entity.setPassword(password);
        entity.setSecretKey(random);
        return testMapper.insert(entity);
    }
}
