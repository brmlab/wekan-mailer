package cz.brmlab.wm.wekan.pojo.login;

import lombok.Data;

@Data
public class LoginToken {
    private String id;
    private String token;
    private String tokenExpires;
}
