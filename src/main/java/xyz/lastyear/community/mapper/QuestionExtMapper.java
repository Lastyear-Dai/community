package xyz.lastyear.community.mapper;
import xyz.lastyear.community.model.Question;

public interface QuestionExtMapper {

    int upadtecout(Question record);
    int upadtecomment_count(Question count);
}