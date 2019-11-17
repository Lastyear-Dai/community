package xyz.lastyear.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.lastyear.community.mapper.QuestionMapper;
import xyz.lastyear.community.mapper.UserMapper;
import xyz.lastyear.community.model.Question;
import xyz.lastyear.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    QuestionMapper questionMapper;
    @GetMapping("publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("publish")
    public String dopublish(@RequestParam("title")String title, @RequestParam("description")String description,
                            @RequestParam("tag")String tag,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title.equals("")||title==null){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description.equals("")||description==null){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if(tag.equals("")||tag==null){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }


        Cookie[] cookies = request.getCookies();
        User user=null;
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                 user = userMapper.queryuser(token);
                if (user!=null) {
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if(user==null){
            model.addAttribute("error","用户未登陆！！！");
            return "publish";
        }


        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        question.setCreator(user.getId());
        question.setTag(tag);
        questionMapper.Insert(question);
        return "redirect:/";
    }
}
