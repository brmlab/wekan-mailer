package cz.brmlab.wm.wekan.rest;

import cz.brmlab.wm.utils.Exceptions.BrmException;
import cz.brmlab.wm.utils.Exceptions.ExitCode;
import cz.brmlab.wm.wekan.WekanConfiguration;
import cz.brmlab.wm.wekan.pojo.card.PostCardRequest;
import cz.brmlab.wm.wekan.pojo.card.PostCardResponse;
import cz.brmlab.wm.wekan.pojo.login.LoginToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Slf4j
public class CardPost {

    private final LoginToken token;
    private static final String POST_CARD_URL_TEMPLATE = "${host}/api/boards/${boardId}/lists/${listId}/cards";
    private final String postCardUrl;

    public CardPost(LoginToken token, WekanConfiguration configuration) {
        log.trace("{}() - start.", this.getClass().getSimpleName());
        this.token = token;
        postCardUrl = POST_CARD_URL_TEMPLATE.replace("${host}", configuration.getWekanUrl())
                .replace("${boardId}", configuration.getWekanBoard())
                .replace("${listId}", configuration.getWekanList());
        log.debug("Card post URL: {}", postCardUrl);
    }

    public PostCardResponse postCard(String title, String description) throws BrmException {
        log.trace("postCard({}, {}) - start.", title, description);

        PostCardRequest postCardRequest = new PostCardRequest();
        postCardRequest.setAuthorId(token.getId());
        postCardRequest.setTitle(title);
        postCardRequest.setDescription(description);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + token.getToken());
        HttpEntity<PostCardRequest> entity = new HttpEntity<>(postCardRequest, headers);

        log.debug("Sending card: {}", postCardRequest);
        try {
            ResponseEntity<PostCardResponse> responseEntity = restTemplate.exchange(postCardUrl, HttpMethod.POST, entity, PostCardResponse.class);
            PostCardResponse postCardResponse = responseEntity.getBody();
            log.info("Card {} successfully sent - cardId: {}", title, postCardResponse != null ? postCardResponse.get_id() : null);
            return postCardResponse;
        } catch (HttpStatusCodeException ex) {
            log.error("Unable to POST a card, status code: {}", ex.getStatusCode());
            throw new BrmException(ExitCode.POST_ERROR.getReason() + ex.getStatusCode(), ExitCode.POST_ERROR);
        }

    }

}
