package com.midsummra.esupdater.common.exception;

import com.midsummra.esupdater.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public Result getExceptions(Exception e){
        log.error(e.getMessage());
        return Result.err("发生未知错误");
    }
}
