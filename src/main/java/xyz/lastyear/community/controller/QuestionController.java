package xyz.lastyear.community.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.lastyear.community.dto.QuestionDTO;
import xyz.lastyear.community.model.Question;
import xyz.lastyear.community.service.QuestionService;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
      @GetMapping("question/{id}")
    public String question(@PathVariable("id")Integer id, Model model){
          QuestionDTO question = questionService.question(id);
          //累计用户阅读问题的次数
          questionService.udatequestionCreator(id);

          model.addAttribute("question",question);
          return "question";
    }
}
