package xyz.lastyear.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lastyear.community.exceptionError.ExceptionCode;
import xyz.lastyear.community.exceptionError.myExceptionError;
import xyz.lastyear.community.mapper.CommentMapper;
import xyz.lastyear.community.model.Comment;

@Service
public class CommentService {
@Autowired(required = false)
    private  CommentMapper commentMapper;
    public void insert(Comment comment) {
        if (comment.getParentId() == null||comment.getParentId()==0) {
            throw  new myExceptionError(ExceptionCode.UNCHECKED);
        }else{
            commentMapper.insert(comment);
        }
    }
}
