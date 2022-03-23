package com.ljz.loverspace.utils.exceptionHandler;

import com.ljz.loverspace.utils.exceptionHandler.exception.DefinitionException;
import com.ljz.loverspace.utils.exceptionHandler.exception.ErrorEnum;
import com.ljz.loverspace.utils.resultUtils.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value= DefinitionException.class)
    public ResponseEntity<Result> definitionExceptionHandler(DefinitionException e){
        return ResponseEntity.ok(Result.fail(e));
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<Result> exceptionHandler(Exception e){
        return ResponseEntity.ok(Result.fail(ErrorEnum.UNAUTHORIZED));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Result> defaultErrorHandler(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ErrorEnum.INTERNAL_SERVER_ERROR.getCode())
                .body(Result.fail(ErrorEnum.INTERNAL_SERVER_ERROR));
    }
}
