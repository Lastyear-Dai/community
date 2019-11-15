package xyz.lastyear.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import xyz.lastyear.community.dto.AccesstokenDTO;
import xyz.lastyear.community.dto.GithubUser;

import java.io.IOException;

@Component
public class Githubprovider {
public String getAccessToken(AccesstokenDTO accesstokenDTO){

     MediaType mediaType= MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accesstokenDTO));
    Request request = new Request.Builder()
            .url("https://github.com/login/oauth/access_token")
            .post(body)
            .build();
    try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
        String accessToken=string.split("&")[0].split("=")[1];
        return accessToken;
    } catch (IOException e) {
        e.printStackTrace();
    }

    return null;
}

public GithubUser GetGithubUser(String accessToken)  {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .url("https://api.github.com/user?access_token="+accessToken)
            .build();
    try (Response response = client.newCall(request).execute()) {
        String string=response.body().string();
        System.out.println(string);
        GithubUser githubUser=JSON.parseObject(string,GithubUser.class);
        return githubUser;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}



}
