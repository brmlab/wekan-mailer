package cz.brmlab.wm.wekan.pojo.login;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {

    private String username;
    private String password;

}
