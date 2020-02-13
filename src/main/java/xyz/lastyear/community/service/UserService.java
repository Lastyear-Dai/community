package xyz.lastyear.community.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import xyz.lastyear.community.mapper.UserMapper;
import xyz.lastyear.community.model.User;
import xyz.lastyear.community.model.UserExample;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService {
    @Autowired(required = false)
    UserMapper userMapper;
    public void updateuser(User user, HttpServletRequest request, HttpServletResponse response) {


        UserExample Example = new UserExample();
        Example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(Example);
        User iduser =users.get(0);
        if(iduser==null){
            userMapper.insert(user);

        }else{
            iduser.setAvatarUrl(user.getAvatarUrl());
            iduser.setName(user.getName());
            iduser.setToken(user.getToken());
            iduser.setGmtModified(user.getGmtModified());
            iduser.setGmtCreate(user.getGmtCreate());
            iduser.setToken(user.getToken());
            iduser.setAccountId(user.getAccountId());
            UserExample userExample1 = new UserExample();
            userExample1.createCriteria().andAccountIdEqualTo(user.getAccountId());
            userMapper.updateByExample(iduser,userExample1);

        }
        response.addCookie(new Cookie("token",user.getToken()));
    }


}
