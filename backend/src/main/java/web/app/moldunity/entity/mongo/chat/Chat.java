package web.app.moldunity.entity.mongo.chat;


import jakarta.validation.constraints.NotBlank;
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
@Document(collection = "chats")
@CompoundIndex(name = "participants_idx", def = "{'username1': 1, 'username2': 1}")
public class Chat {
    @Id
    private ObjectId id = new ObjectId();
    @NotBlank private String username1;
    @NotBlank private String username2;
    private LocalDateTime createdAt;
    @Indexed(direction = IndexDirection.DESCENDING)
    private LocalDateTime lastUpdated;

    public Chat(String u1, String u2){
        var now = LocalDateTime.now();

        if (u1.compareTo(u2) < 0) {
            this.username1 = u1;
            this.username2 = u2;
        } else {
            this.username1 = u2;
            this.username2 = u1;
        }

        this.createdAt = now;
        this.lastUpdated = now;
    }

    public void refreshLastUpdated(){
        this.lastUpdated = LocalDateTime.now();
    }
}
