package cz.brmlab.wm.wekan.rest;

import cz.brmlab.wm.utils.LogMarker.LogMarker;
import cz.brmlab.wm.wekan.WekanConfiguration;
import cz.brmlab.wm.wekan.pojo.login.LoginRequest;
import cz.brmlab.wm.wekan.pojo.login.LoginToken;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class LoginPost {

    private final WekanConfiguration configuration;

    @Getter
    private LoginToken token;

    public LoginPost(WekanConfiguration configuration) {
        this.configuration = configuration;
    }


    public void login() {
        log.trace("login() - start.");

        String loginUrl = configuration.getWekanUrl() + "/users/login";
        log.debug("Wekan login endpoint: {}", loginUrl);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(configuration.getWekanUser());
        loginRequest.setPassword(configuration.getWekanPassword());

        log.info("Sending login request...");
        RestTemplate restTemplate = new RestTemplate();
        token = restTemplate.postForObject(loginUrl, loginRequest, LoginToken.class);
        log.info("Login successful, token obtained.");
        log.debug(LogMarker.SECRET.getMarker(), "Token: {}", token.toString());
    }
}
