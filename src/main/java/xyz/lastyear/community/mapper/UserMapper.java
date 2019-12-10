package xyz.lastyear.community.mapper;

import org.apache.ibatis.annotations.*;
import xyz.lastyear.community.model.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatar_url})")
     public  void insert(User user);
    @Select("select * from user where token = #{token}")
    User queryuser(@Param("token") String token);
    @Select("select * from user where account_id = #{creator}")
    User findByid(Integer creator);
    @Select("select * from user where id = #{creator}")
    User Byid(Integer creator);
    @Update("update user set name=#{name} ,token=#{token} ,avatar_url=#{avatar_url},gmt_create=#{gmtCreate},gmt_modified=#{gmtModified} where account_id=#{accountId}")
    void update(User iduser);
}
