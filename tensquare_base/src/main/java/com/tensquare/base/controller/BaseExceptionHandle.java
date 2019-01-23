package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 吴亚斌
 * create : 2019-01-18 19:08
 * description
 */
@RestControllerAdvice
public class BaseExceptionHandle {

   @ExceptionHandler(value = Exception.class)
   public Result exception(Exception e){
       e.printStackTrace();
       return new Result(false,StatusCode.ERROR,e.getMessage());
   }
}
