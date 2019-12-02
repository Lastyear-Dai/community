package xyz.lastyear.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.lastyear.community.model.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void Insert(Question question);
    @Select("select * from question limit #{limit},#{number}")
    List<Question> queryQuestion(Integer limit, Integer number);
    @Select("select * from question where creator = #{id}  limit #{limit},#{number}")
    List<Question> queryMyQuestion(Integer id,Integer limit, Integer number);
    @Select("select count(*) from question")
    Integer countQuestion();
    @Select("select count(*) from question where creator = #{id}")
    Integer countMyQuestion(Integer id);
}
