package com.galaxy.data.common.utils;

import cn.hutool.core.lang.UUID;

/**
 * @author yao.qian
 * <p>
 * 随机数工具类
 */
public class RandomUtil {

    /**
     * 偷懒，直接搞uuid的
     *
     * @param size
     * @return
     */
    public static String getRandom(int size) {
        String random = "";
        do {
            random = random.concat(UUID.randomUUID()
                    .toString(true)
                    .replaceAll("-", ""));
        } while (random.length() < size);
        return random.substring(0, size);
    }
}
