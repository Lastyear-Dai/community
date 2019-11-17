package xyz.lastyear.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.lastyear.community.dto.AccesstokenDTO;
import xyz.lastyear.community.dto.GithubUser;
import xyz.lastyear.community.mapper.UserMapper;
import xyz.lastyear.community.model.User;
import xyz.lastyear.community.provider.Githubprovider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    Githubprovider githubprovider;
    @Autowired(required =  false)
    UserMapper usermapper;
    @Value("${github.redirect.uri}")
    private String redirect_uri;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
@GetMapping("callback")
    public String callback(@RequestParam("code") String  code, @RequestParam("state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
    AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
    accesstokenDTO.setCode(code);
    accesstokenDTO.setState(state);
    accesstokenDTO.setClient_id(client_id);
    accesstokenDTO.setRedirect_uri(redirect_uri);
    accesstokenDTO.setClient_secret(client_secret);
    String access_token=githubprovider.getAccessToken(accesstokenDTO);
    GithubUser githubUser = githubprovider.GetGithubUser(access_token);
    if(githubUser!=null){
        //登陆成功
        User user=new User();
        user.setAccountId(String.valueOf(githubUser.getId()));
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setName(githubUser.getName());
        user.setAvatar_url(githubUser.getAvatar_url());
        usermapper.insert(user);
        response.addCookie(new Cookie("token",token));


        return "redirect:/";
    }else{
        //登陆失败
        return "redirect:/";
    }
    }
}
