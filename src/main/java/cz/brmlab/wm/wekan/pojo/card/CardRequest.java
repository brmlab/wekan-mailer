package cz.brmlab.wm.wekan.pojo.card;

import cz.brmlab.wm.wekan.WekanConfiguration;
import cz.brmlab.wm.wekan.pojo.login.LoginToken;
import lombok.Data;

@Data
public class CardRequest {

    private String title;
    private String description;
    private String authorId;
    private final String swimlaneId = "Default";

}
