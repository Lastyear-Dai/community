package xyz.lastyear.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.lastyear.community.dto.AccesstokenDTO;
import xyz.lastyear.community.dto.GithubUser;
import xyz.lastyear.community.provider.Githubprovider;

@Controller
public class AuthorizeController {
    @Autowired
    Githubprovider githubprovider;
    @Value("${github.redirect.uri}")
    private String redirect_uri;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
@GetMapping("callback")
    public String callback(@RequestParam("code") String  code,@RequestParam("state")String state){
    AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
    accesstokenDTO.setCode(code);
    accesstokenDTO.setState(state);
    accesstokenDTO.setClient_id(client_id);
    accesstokenDTO.setRedirect_uri(redirect_uri);
    accesstokenDTO.setClient_secret(client_secret);
    String access_token=githubprovider.getAccessToken(accesstokenDTO);
    System.out.println(access_token);
    GithubUser githubUser = githubprovider.GetGithubUser(access_token);
    System.out.println(githubUser);
    return "index";
}
}
