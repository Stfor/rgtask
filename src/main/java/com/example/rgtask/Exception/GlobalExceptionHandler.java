package com.example.rgtask.Exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.utils.MsgCodeUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * 全局异常处理器 把异常到controller层 这里会获取看看异常对不对,全都交给全局异常处理 和swagger没关系
 *
 * 异常得到期望的返回格式，这里就需要用到了@ControllerAdvice 或者RestControllerAdvice（如果全部异常处理返回json，
 * 那么可以使用 @RestControllerAdvice 代替 @ControllerAdvice ，
 * 这样在方法上就可以不需要添加 @ResponseBodyServiceException
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    //可以放入自定义异常类 ， 不一定是要Exception ，可以是继承了运行时异常的类
    @ExceptionHandler(TokenException.class)
    public Object handleException1(Exception e) {
        //异常具体位置
        StackTraceElement[] stackTrace = e.getStackTrace();
        CommonResult result = new CommonResult().init();
        result.fail(MsgCodeUtils.MSG_CODE_JWT_TOKEN_ISNULL);
        return result;
    }

//    @ExceptionHandler(JWTDecodeException.class)
//    public Object handleException2(Exception e) {
//        //异常具体位置
//        StackTraceElement[] stackTrace = e.getStackTrace();
//        CommonResult result = new CommonResult().init();
//        result.fail(MsgCodeUtils.MSG_CODE_JWT_MALFORMED);
//        return result;
//    }

}
