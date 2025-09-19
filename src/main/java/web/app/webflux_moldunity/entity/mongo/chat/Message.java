package web.app.webflux_moldunity.entity.mongo.chat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "messages")
public class Message {
    @Id
    private ObjectId id = new ObjectId();
    @Indexed
    @NotBlank private String chatId;
    @NotBlank private String sender;
    @NotBlank private String text;

    @NotNull private MessageStatus status;
    @Indexed(direction = IndexDirection.DESCENDING)
    private LocalDateTime createdAt;

    public Message(String cId, String s, String t){
        this.chatId = cId;
        this.sender = s;
        this.text = t;
        this.createdAt = LocalDateTime.now();
    }
}



