package xyz.lastyear.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lastyear.community.mapper.UserMapper;
import xyz.lastyear.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {
    @Autowired(required = false)
    UserMapper userMapper;
    public void updateuser(User user, HttpServletRequest request, HttpServletResponse response) {
        Integer creator=Integer.parseInt(user.getAccountId());
        User iduser = userMapper.findByid(creator);
        if(iduser==null){
            userMapper.insert(user);

        }else{
            iduser.setAvatar_url(user.getAvatar_url());
            iduser.setName(user.getName());
            iduser.setToken(user.getToken());
            iduser.setGmtModified(user.getGmtModified());
            iduser.setGmtCreate(user.getGmtCreate());
            iduser.setToken(user.getToken());
            iduser.setAccountId(user.getAccountId());
            userMapper.update(iduser);
        }
        response.addCookie(new Cookie("token",user.getToken()));
    }


}
