package cn.app.source.exception;

import cn.app.source.util.AppException;
import cn.app.source.util.Result;
import cn.app.source.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @author: haodongdong
 *  @Date: 2020/7/22 16:21
 *  @Description: 全局异常处理
 */
@RestControllerAdvice
@Order(0)
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 处理所有参数校验时抛出的异常
     *
     * @param appException
     * @return
     */
    @ExceptionHandler(value = AppException.class)
    public Result handleBindException(AppException appException) {
        log.error("自定义异常:{}",appException.getMessage());
        return new Result(appException.getResultCode(),appException.getMessage());

    }

    /**
     * 系统异常捕获处理
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        log.error("全局异常:{}",e.getMessage(),e);
        return new Result(ResultCode.FAIL,"操作失败");
    }


}