package cz.brmlab.wm.wekan.pojo.login;

import lombok.Data;

@Data
public class LoginToken {
    public String id;
    public String token;
    public String tokenExpires;
}
