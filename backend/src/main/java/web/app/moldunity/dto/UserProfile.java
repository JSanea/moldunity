package web.app.moldunity.dto;


import java.time.LocalDateTime;

public record UserProfile(
        String username,
        String country,
        String location,
        LocalDateTime createdAt
){}
