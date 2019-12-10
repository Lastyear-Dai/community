package xyz.lastyear.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.lastyear.community.dto.QaginationDTO;
import xyz.lastyear.community.mapper.UserMapper;
import xyz.lastyear.community.model.User;
import xyz.lastyear.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyquestionController {
    @Autowired
    QuestionService questionService;
    @Autowired(required = false)
    UserMapper userMapper;
    @GetMapping("/myquestion/{action}")
    public String myquestion(Model model,
                             HttpServletRequest request,
                             @PathVariable("action")String action,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "number", defaultValue = "10")Integer number){

        User user = (User) request.getSession().getAttribute("user");

        if(user==null){
            model.addAttribute("error","用户未登陆！！！");
            return "redirect:/";
        }

        if("question".equals(action)){
            //我的所有问题
            QaginationDTO list = questionService.Mylist(user.getId(),page,number);
            model.addAttribute("list",list);
        }else{
            //我的回复
        }


        return "myquestion";
    }
}
