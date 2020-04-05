package xyz.lastyear.community.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import xyz.lastyear.community.dto.ResultDTO;
import xyz.lastyear.community.exceptionError.ExceptionCode;
import xyz.lastyear.community.exceptionError.myExceptionError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class ExceptionError {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Throwable ex, Model model) {
             String ContentType=request.getContentType();
        ResultDTO resultDTO=null;
             if("application/json".equals(ContentType)){
                //返回json
                 if(ex instanceof  myExceptionError){

                     resultDTO= ResultDTO.error((myExceptionError) ex);
                 }else {
                     resultDTO= ResultDTO.error(ExceptionCode.SYSTEM_ERROR);
                 }
                 PrintWriter writer = null;
                 try {
                     writer = response.getWriter();
                     writer.write(JSON.toJSONString(resultDTO));
                     writer.close();
                 } catch (IOException e) {
                                    }


             }else {
                //错误页面
                 if (ex instanceof myExceptionError){
                     model.addAttribute("message",ex.getMessage());
                 }else{
                     model.addAttribute("message",ExceptionCode.SYSTEM_ERROR.getMessage());
                 }
                 return new ModelAndView("error");
             }


        return null;
    }



}
