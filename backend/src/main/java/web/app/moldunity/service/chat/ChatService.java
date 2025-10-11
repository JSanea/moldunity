package web.app.moldunity.service.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import web.app.moldunity.model.entity.mongo.chat.Chat;
import web.app.moldunity.model.entity.mongo.chat.ChatMessage;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ReactiveMongoTemplate rmt;
    private final ChatMessageService cms;

    public Mono<Chat> createChat(String username1, String username2){
        return rmt.save(new Chat(username1, username2));
    }

    public Mono<List<ChatMessage>> findChatMessagesByParticipants(String participant){
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(authentication -> {
                            String username = (String)authentication.getPrincipal();
                            return findChatByParticipants(username, participant)
                                    .switchIfEmpty(Mono.error(new RuntimeException("Chat not found or access denied")))
                                    .flatMap(chat -> cms.getMessagesByChatId(chat.getId().toHexString()));
                        }
                )
                .doOnError(e -> {
                    log.error("Error to get Chat by id", e);
                });
    }

    public Mono<List<Chat>> findChatsByAuthName(){
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(authentication -> {
                    String username = (String)authentication.getPrincipal();
                    Query query = new Query();
                    query.addCriteria(new Criteria().orOperator(
                            Criteria.where("username1").is(username),
                            Criteria.where("username2").is(username)
                    ));
                    return Mono.defer(() -> rmt.find(query, Chat.class).collectList());
                })
                .doOnError(e -> {
                    log.error("Error to get user chats", e);
                });
    }

    private Mono<Chat> findChatByParticipants(String username1, String username2){
        String u1 = username1;
        String u2 = username2;

        if(u1.compareTo(u2) > 0){
            String temp = u1;
            u1 = u2;
            u2 = temp;
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("username1").is(u1).and("username2").is(u2));

        return rmt.findOne(query, Chat.class);
    }
}

































