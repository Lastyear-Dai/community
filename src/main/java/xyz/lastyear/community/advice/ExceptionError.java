package xyz.lastyear.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import xyz.lastyear.community.exceptionError.myExceptionError;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionError {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model) {
              if (ex instanceof myExceptionError){
                  model.addAttribute("message",ex.getMessage());
              }else{
                  model.addAttribute("message","服务器冒烟了~~~");
              }


        return new ModelAndView("error");
    }



}
