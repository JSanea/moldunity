package web.app.moldunity.controller.chat;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import web.app.moldunity.entity.mongo.chat.Chat;
import web.app.moldunity.entity.mongo.chat.ChatMessage;
import web.app.moldunity.service.chat.ChatService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api")
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/chats")
    public Mono<ResponseEntity<List<Chat>>> getChats(){
        return chatService.findChatsByAuthName()
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .onErrorResume(e -> {
                    log.error("Error fetching chats", e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @GetMapping("/chats/{participant}")
    public Mono<ResponseEntity<List<ChatMessage>>> getChatMessages(@PathVariable String participant){
        return chatService.findChatMessagesByParticipants(participant)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    log.error("Error fetching chat messages", e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

}














