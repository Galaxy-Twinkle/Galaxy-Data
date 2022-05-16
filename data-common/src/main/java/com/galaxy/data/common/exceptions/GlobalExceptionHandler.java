package com.galaxy.data.common.exceptions;

import com.galaxy.data.common.BaseException;
import com.galaxy.data.common.Result;
import com.galaxy.data.common.enums.ErrorCodeMessagesEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Handler Class
 *
 * @author yao.qian
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public Result<BusinessException> businessException(BusinessException e) {
        return new Result<BusinessException>().failed(e);
    }

    @ExceptionHandler(Exception.class)
    public Result<Exception> exception(Exception e) {
        logger.error("{},Throw message :{}", ErrorCodeMessagesEnum.SYSTEM_INTERNAL_ERROR.getMessage(), e);
        return new Result<Exception>().failed(new BaseException(ErrorCodeMessagesEnum.SYSTEM_INTERNAL_ERROR));
    }
}
