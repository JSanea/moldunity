package web.app.moldunity.model.dto;


import java.time.LocalDateTime;

public record UserProfile(
        String username,
        String country,
        String location,
        LocalDateTime createdAt
){}
