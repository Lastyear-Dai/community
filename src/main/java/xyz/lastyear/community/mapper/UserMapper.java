package xyz.lastyear.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.lastyear.community.model.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatar_url})")
     public  void insert(User user);
    @Select("select * from user where token = #{token}")
    User queryuser(@Param("token") String token);
    @Select("select * from user where id = #{creator}")
    User findByid(Integer creator);
}
