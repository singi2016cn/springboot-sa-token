package top.singi.demo;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotLoginException.class)
    public SaResult handlerLoginException(NotLoginException e) {
        log.error(e.getMessage());
        return SaResult.error("权鉴失败，请重新登录后再试");
    }

    // 全局异常拦截
    @ExceptionHandler(Exception.class)
    public SaResult handlerException(Exception e) {
        return SaResult.error(e.getMessage());
    }
}
