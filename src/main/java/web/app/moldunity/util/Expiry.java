package web.app.moldunity.util;


import java.time.Duration;
import java.time.Instant;

public class Expiry<V> {
    private V subject;
    private Long ttl;
    private Instant createdAt;
    private boolean expired = false;

    public Expiry(V subject, Long ttl) {
        this.subject = subject;
        this.createdAt = Instant.now();

        if (ttl < 1){
            this.ttl = 1L;
        }else{
            this.ttl = ttl;
        }
    }

    public boolean isExpired(){
        if(!expired) {
            expired = Duration.between(createdAt, Instant.now()).toMinutes() >= this.ttl;
        }
        return expired;
    }

    public V getSubject() {
        return subject;
    }
}
