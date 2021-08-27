package com.llschool.servicebase.exceptionhandler;

import com.llschool.commonutils.RR;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//指定出现什么异常执行这个方法
    @ResponseBody //为了返回数据
    public RR error(Exception e) {
        e.printStackTrace();
        return RR.error().message("执行了全局异常处理..");
    }

    // 特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public RR error(ArithmeticException e){
        e.printStackTrace();
        return RR.error().message("执行了ArithmeticException异常处理");
    }

    //自定义异常
    @ExceptionHandler(LlschoolException.class)
    @ResponseBody
    public RR error (LlschoolException e){
        e.printStackTrace();
        log.error(e.getMsg());//将异常信息写到日志文件
        return RR.error().message(e.getMsg());
    }
}
