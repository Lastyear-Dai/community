package xyz.lastyear.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lastyear.community.enume.CommentTypeEnum;
import xyz.lastyear.community.exceptionError.ExceptionCode;
import xyz.lastyear.community.exceptionError.myExceptionError;
import xyz.lastyear.community.mapper.CommentMapper;
import xyz.lastyear.community.mapper.QuestionExtMapper;
import xyz.lastyear.community.mapper.QuestionMapper;
import xyz.lastyear.community.model.Comment;
import xyz.lastyear.community.model.Question;
import xyz.lastyear.community.model.QuestionExample;

@Service
public class CommentService {
@Autowired(required = false)
    private  CommentMapper commentMapper;
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private QuestionExtMapper questionExtMapper;
    public void insert(Comment comment) {
        if (comment.getParentId() == null||comment.getParentId()==0) {
            throw  new myExceptionError(ExceptionCode.UNCHECKED);
        }
        //判断评论类型是否正确或存在
        if(comment.getType()==null|| !CommentTypeEnum.compare(comment.getType())){
            throw  new myExceptionError(ExceptionCode.INSERT_CODE_ERROR);
        }
        //判断是回复还是评论，然后插入评论或回复
        if(comment.getType()==CommentTypeEnum.COMMENT.getCode()){
        //回复评论
            Comment dbid = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbid==null){
                throw  new myExceptionError(ExceptionCode.INSERT_CODE_NULL);
            }
            commentMapper.insert(comment);
        }else {
        //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null){
                throw  new myExceptionError(ExceptionCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.upadtecomment_count(question);
        }

    }
}
