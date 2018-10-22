package cz.brmlab.wm.wekan.pojo.card;

import lombok.Data;

@Data
public class CardRequest {

    private String title;
    private String description;
    private String authorId;
    private final String swimlaneId = "Default";

}
