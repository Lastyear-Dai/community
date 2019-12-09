package xyz.lastyear.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lastyear.community.dto.QaginationDTO;
import xyz.lastyear.community.dto.QuestionDTO;
import xyz.lastyear.community.mapper.QuestionMapper;
import xyz.lastyear.community.mapper.UserMapper;
import xyz.lastyear.community.model.Question;
import xyz.lastyear.community.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
@Autowired(required = false)
    UserMapper userMapper;
@Autowired(required = false)
    QuestionMapper questionMapper;
public QaginationDTO list(Integer page, Integer number){
    QaginationDTO qaginationDTO = new QaginationDTO();
   /*当前页码*/
    qaginationDTO.setPage(page);
    /*每页的个数*/
    qaginationDTO.setNumber(number);
    /*总页数*/
    int count=questionMapper.countQuestion();//数据总个数

    if(count%number==0){
         qaginationDTO.setPages(count/number);
    }else{
        qaginationDTO.setPages(count/number+1);
    }


    Integer limit=(page-1)*number;
    List<Question>  list=questionMapper.queryQuestion(limit,number);
    List<QuestionDTO> questionDTOs= new ArrayList<>();

    for(int i=0;i<list.size();i++){
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.findByid(list.get(i).getCreator());
        BeanUtils.copyProperties(list.get(i),questionDTO);
        questionDTO.setUser(user);
        questionDTOs.add(i,questionDTO);

    }
    qaginationDTO.setQuestionDTOs(questionDTOs);
    return qaginationDTO;
}

public QaginationDTO Mylist(Integer id,Integer page,Integer number){
    QaginationDTO qaginationDTO = new QaginationDTO();
    /*当前页码*/
    qaginationDTO.setPage(page);
    /*每页的个数*/
    qaginationDTO.setNumber(number);
    /*总页数*/
    int count=questionMapper.countMyQuestion(id);//数据总个数

    if(count%number==0){
        qaginationDTO.setPages(count/number);
    }else{
        qaginationDTO.setPages(count/number+1);
    }


    Integer limit=(page-1)*number;
    List<Question>  list=questionMapper.queryMyQuestion(id,limit,number);
    List<QuestionDTO> questionDTOs= new ArrayList<>();

    for(int i=0;i<list.size();i++){
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.findByid(list.get(i).getCreator());
        BeanUtils.copyProperties(list.get(i),questionDTO);
        questionDTO.setUser(user);
        questionDTOs.add(i,questionDTO);

    }
    qaginationDTO.setQuestionDTOs(questionDTOs);
    return qaginationDTO;
}

public QuestionDTO question(Integer id){
    QuestionDTO questionDTO = new QuestionDTO();
    Question question = questionMapper.question(id);
    BeanUtils.copyProperties(question,questionDTO);
    User user = userMapper.findByid(question.getCreator());
    questionDTO.setUser(user);
    return questionDTO;
}

}
