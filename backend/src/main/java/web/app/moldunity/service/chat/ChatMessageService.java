package web.app.moldunity.service.chat;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import web.app.moldunity.entity.mongo.chat.ChatMessage;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {
    private final ReactiveMongoTemplate rmt;

    public Mono<List<ChatMessage>> getMessagesByChatId(String id){
        Query q = new Query();
        q.addCriteria(Criteria.where("chatId").is(id));
        return rmt.find(q, ChatMessage.class).collectList();
    }

    public Mono<List<ChatMessage>> getUnreadMessages(){
        return Mono.empty();
    }
}










