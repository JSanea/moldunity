package web.app.moldunity.util;

import jakarta.validation.constraints.NotNull;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class ExpiryMap<K, V> {
    private HashMap<K, Expiry<V>> expiryMap = new HashMap<>();
    
    public void put(@NotNull K key, 
                    @NotNull Expiry<V> expiry){
        expiryMap.put(key, expiry);
    }
    
    public Expiry<V> getExpiry(@NotNull K key){
        return expiryMap.get(key);
    }
    
    public Optional<V> get(@NotNull K key){
        var v = expiryMap.get(key);
        return null != v ? Optional.ofNullable(v.getSubject()) : Optional.empty();
    }

    public void remove(@NotNull K key){
        expiryMap.remove(key);
    }

    public void clear(){
        expiryMap.clear();
    }

    public Set<K> getKeySet(){
        return expiryMap.keySet();
    }

    @Scheduled(fixedRate = 60_000L)
    private void deleteExpired(){
        for(K key : expiryMap.keySet()){
            if(expiryMap.get(key).isExpired()){
                expiryMap.remove(key);
            }
        }
    }
}









