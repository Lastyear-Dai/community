package xyz.lastyear.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lastyear.community.dto.CommentDTO;
import xyz.lastyear.community.dto.ResultDTO;
import xyz.lastyear.community.exceptionError.ExceptionCode;
import xyz.lastyear.community.mapper.CommentMapper;
import xyz.lastyear.community.model.Comment;
import xyz.lastyear.community.model.User;
import xyz.lastyear.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired(required = false)
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;
    @RequestMapping("/comment")
    @ResponseBody
    public Object insertComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        User user =(User) request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.error(ExceptionCode.NOT_LOGIN);
                    }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLiceCount(0L);
        comment.setContent(commentDTO.getContent());
        commentService.insert(comment);

        return ResultDTO.error(ExceptionCode.INSERT_SUCCESS);
    }

}
