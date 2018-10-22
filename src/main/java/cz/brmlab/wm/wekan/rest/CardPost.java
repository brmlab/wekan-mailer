package cz.brmlab.wm.wekan.rest;

import cz.brmlab.wm.wekan.WekanConfiguration;
import cz.brmlab.wm.wekan.pojo.card.CardRequest;
import cz.brmlab.wm.wekan.pojo.card.CardResponse;
import cz.brmlab.wm.wekan.pojo.login.LoginToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class CardPost {

    private final LoginToken token;
    private final WekanConfiguration configuration;

    public CardPost(LoginToken token, WekanConfiguration configuration) {
        log.trace("{}() - start.", this.getClass().getSimpleName());
        this.token = token;
        this.configuration = configuration;
    }

    public CardResponse postCard(String title, String description) {
        log.trace("postCard({}, {}) - start.", title, description);

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
        log.debug("Sending card: {}", cardRequest);
        CardResponse cardResponse = restTemplate.postForObject(cardUrl, cardRequest, CardResponse.class);

        log.info("Card {} successfully sent - cardId: {}", title, cardResponse.getId());
        return cardResponse;
    }

}
