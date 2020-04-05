package xyz.lastyear.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.lastyear.community.dto.QaginationDTO;
import xyz.lastyear.community.dto.QuestionDTO;
import xyz.lastyear.community.mapper.UserMapper;
import xyz.lastyear.community.model.User;
import xyz.lastyear.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {
    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "number",defaultValue = "10")Integer number){

        QaginationDTO list = questionService.list(page,number);

        model.addAttribute("list",list);

        return "index";

    }
    @GetMapping("/exitlogin")
    public String exitlogin(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie token = new Cookie("token", null);
        token.setMaxAge(0);
        response.addCookie(token);
        return "redirect:/";
    }

}
