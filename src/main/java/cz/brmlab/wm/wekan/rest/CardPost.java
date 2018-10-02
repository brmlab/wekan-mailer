package cz.brmlab.wm.wekan.rest;

import cz.brmlab.wm.wekan.WekanConfiguration;
import cz.brmlab.wm.wekan.pojo.card.CardRequest;
import cz.brmlab.wm.wekan.pojo.card.CardResponse;
import cz.brmlab.wm.wekan.pojo.login.LoginToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class CardPost {

    private static final Logger LOG = LoggerFactory.getLogger(CardPost.class);

    private final LoginToken token;
    private final WekanConfiguration configuration;

    public CardPost(LoginToken token, WekanConfiguration configuration) {
        this.token = token;
        this.configuration = configuration;
    }

    public CardResponse postCard(String title, String description) {
        CardRequest cardRequest = new CardRequest();
        cardRequest.setAuthorId(token.getId());
        cardRequest.setTitle(title);
        cardRequest.setDescription(description);

        String cardUrl = configuration.getWekanUrl()
                + "/api/boards"
                + configuration.getWekanBoard()
                + "/lists/"
                + configuration.getWekanList() + "cards";

        RestTemplate restTemplate = new RestTemplate();
        LOG.debug("Sending card: {}", cardRequest);
        CardResponse cardResponse = restTemplate.postForObject(cardUrl, cardRequest, CardResponse.class);

    }

}
