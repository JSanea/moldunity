package web.app.moldunity.event;

public record UpdateRefreshTokenEvent(
    String token,
    Long userId
) {}
