package com.suse.netcenter.util;

import com.suse.netcenter.dto.Msg;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Tangerg
 * @create 2019-03-28 15:19
 * 全局异常处理切面
 */
@RestControllerAdvice
public class GloablExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器发生错误，请稍后再试";
        }
        return Msg.fail().addMsg(msg);
    }
}
