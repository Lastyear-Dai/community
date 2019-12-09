package xyz.lastyear.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lastyear.community.mapper.UserMapper;
import xyz.lastyear.community.model.User;

@Service
public class UserService {
    @Autowired(required = false)
    UserMapper userMapper;
    public void updateuser(User user) {
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
            userMapper.update(iduser);
        }
    }


}
