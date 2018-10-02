package cz.brmlab.wm.wekan.rest;

import cz.brmlab.wm.wekan.WekanConfiguration;
import cz.brmlab.wm.wekan.pojo.login.LoginRequest;
import cz.brmlab.wm.wekan.pojo.login.LoginToken;
import lombok.Getter;
import org.springframework.web.client.RestTemplate;

public class LoginPost {

    private final WekanConfiguration configuration;

    @Getter
    private LoginToken token;

    public LoginPost(WekanConfiguration configuration) {
        this.configuration = configuration;
    }


    public void login() {

        String loginUrl = configuration.getWekanUrl() + "/users/login";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(configuration.getWekanUser());
        loginRequest.setPassword(configuration.getWekanPassword());

        RestTemplate restTemplate = new RestTemplate();
        token = restTemplate.postForObject(loginUrl, loginRequest, LoginToken.class);

        System.out.println(token);
    }
}
