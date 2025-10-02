package web.app.moldunity.entity.mongo.chat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "messages")
@CompoundIndex(name = "members_idx", def = "{'sender': 1, 'receiver': 1}")
public class ChatMessage {
    @Id
    private ObjectId id = new ObjectId();
    @Indexed
    @NotBlank private String chatId;
    @NotBlank private String sender;
    @NotBlank private String receiver;
    @NotBlank private String text;
    @NotNull private ChatMessageStatus status;
    @Indexed(direction = IndexDirection.DESCENDING)
    private LocalDateTime createdAt;

    public ChatMessage(String chatId, String sender, String receiver, String text){
        this.chatId = chatId;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }
}



