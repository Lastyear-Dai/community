package xyz.lastyear.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lastyear.community.dto.CommentDTO;
import xyz.lastyear.community.mapper.CommentMapper;
import xyz.lastyear.community.model.Comment;

@Controller
public class CommentController {
    @Autowired(required = false)
    private CommentMapper commentMapper;
    @RequestMapping("/comment")
    @ResponseBody
    public Object insertComment(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setCommentator(1);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLiceCount(0L);
        comment.setContent(commentDTO.getContent());
        commentMapper.insert(comment);
        return null ;
    }

}
