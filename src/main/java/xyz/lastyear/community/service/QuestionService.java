package xyz.lastyear.community.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lastyear.community.dto.QaginationDTO;
import xyz.lastyear.community.dto.QuestionDTO;
import xyz.lastyear.community.mapper.QuestionMapper;
import xyz.lastyear.community.mapper.UserMapper;
import xyz.lastyear.community.model.Question;
import xyz.lastyear.community.model.QuestionExample;
import xyz.lastyear.community.model.User;

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


    int count=(int)questionMapper.countByExample(new QuestionExample());//数据总个数

    if(count%number==0){
         qaginationDTO.setPages(count/number);
    }else{
        qaginationDTO.setPages(count/number+1);
    }


    Integer limit=(page-1)*number;


    List<Question> list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(limit, number));

    System.out.println(list.get(1).getViewCount());
    System.out.println(list.get(1).getDescription());
    System.out.println(list.get(1).getCreator());
    System.out.println(list.get(1).getGmtCreate());
    System.out.println(list.get(1).getId());
    System.out.println(list.get(1).getLikeCount());

    List<QuestionDTO> questionDTOs= new ArrayList<>();
    Integer  creator;
    for(int i=0;i<list.size();i++){
        QuestionDTO questionDTO = new QuestionDTO();
         User user = userMapper.selectByPrimaryKey(list.get(i).getCreator());

        BeanUtils.copyProperties(list.get(i),questionDTO);
        questionDTO.setUser(user);
        System.out.println(questionDTO);
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
//数据总个数
    QuestionExample questionExample = new QuestionExample();
    questionExample.createCriteria().andIdEqualTo(id);
    int count=(int) questionMapper.countByExample(questionExample);


    if(count%number==0){
        qaginationDTO.setPages(count/number);
    }else{
        qaginationDTO.setPages(count/number+1);
    }


    Integer limit=(page-1)*number;

      List<Question>  list=questionMapper.selectByExampleWithRowbounds(questionExample,
            new RowBounds(limit,number));
    List<QuestionDTO> questionDTOs= new ArrayList<>();

    for(int i=0;i<list.size();i++){
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.selectByPrimaryKey(list.get(i).getCreator());
        BeanUtils.copyProperties(list.get(i),questionDTO);
        questionDTO.setUser(user);
        questionDTOs.add(i,questionDTO);

    }
    qaginationDTO.setQuestionDTOs(questionDTOs);
    return qaginationDTO;
}

public QuestionDTO question(Integer id){
    QuestionDTO questionDTO = new QuestionDTO();
    Question question = questionMapper.selectByPrimaryKey(id);
    BeanUtils.copyProperties(question,questionDTO);
    User user = userMapper.selectByPrimaryKey(question.getCreator());
    questionDTO.setUser(user);
    return questionDTO;
}

    public void updatequestion(Integer id, Question question){
        if(id!=null){
            QuestionExample questionExample2 = new QuestionExample();
            questionExample2.createCriteria().andIdEqualTo(question.getId());
                       questionMapper.updateByExample(question,questionExample2);
        }else{

            questionMapper.insert(question);
        }
    }
}
