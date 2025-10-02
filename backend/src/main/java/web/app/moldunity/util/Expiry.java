package web.app.moldunity.util;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.Instant;

public class Expiry<V> {
    private final V subject;
    private final Long ttl;
    private final Instant createdAt;

    public Expiry(@NotNull V subject, Long ttl) {
        this.subject = subject;
        this.createdAt = Instant.now();
        this.ttl = Math.max(ttl, 1L);
    }

    public boolean isExpired(){
        return Duration.between(createdAt, Instant.now()).toMinutes() >= this.ttl;
    }

    public V getSubject() {
        return subject;
    }
}
