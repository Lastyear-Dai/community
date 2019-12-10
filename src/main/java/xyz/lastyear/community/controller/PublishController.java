package xyz.lastyear.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.lastyear.community.mapper.QuestionMapper;
import xyz.lastyear.community.mapper.UserMapper;
import xyz.lastyear.community.model.Question;
import xyz.lastyear.community.model.User;
import xyz.lastyear.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;
    @GetMapping("publish")
    public String publish(){
        return "publish";
    }

    @GetMapping("publish/{id}")
    public String updatepublish(@PathVariable("id")Integer id,
                                Model model){
        Question question = questionMapper.question(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",id);
        return "publish";
    }
    @PostMapping("publish")
    public String dopublish(@RequestParam("title")String title, @RequestParam("description")String description,
                            @RequestParam("tag")String tag,
                            HttpServletRequest request,
                            Model model,@RequestParam(value = "id",required =false )Integer id){
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


        User user = (User) request.getSession().getAttribute("user");

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
        question.setId(id);
        questionService.updatequestion(id,question);
        return "redirect:/";
    }
}
