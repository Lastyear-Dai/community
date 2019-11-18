package xyz.lastyear.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public List<QuestionDTO> list(){
    List<Question>  list=questionMapper.queryQuestion();
    List<QuestionDTO> questionDTOs= new ArrayList<>();

    for(int i=0;i<list.size();i++){
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.findByid(list.get(i).getCreator());
        BeanUtils.copyProperties(list.get(i),questionDTO);
        questionDTO.setUser(user);
        questionDTOs.add(i,questionDTO);

    }

    return questionDTOs;
}

}
