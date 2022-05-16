package com.galaxy.data.common.exceptions;

import com.galaxy.data.common.BaseException;
import com.galaxy.data.common.enums.ErrorCodeMessagesEnum;

/**
 * @author yao.qian
 *
 * Business class Exception
 */
public class BusinessException extends BaseException {

    public BusinessException(ErrorCodeMessagesEnum messagesEnum) {
        super(messagesEnum);
    }
}
