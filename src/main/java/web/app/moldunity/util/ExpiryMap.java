package web.app.moldunity.util;

import jakarta.validation.constraints.NotNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class ExpiryMap<K, V> {
    private HashMap<K, Expiry<V>> expiryMap = new HashMap<>();
    
    public void put(@NotNull K key, 
                    @NotNull Expiry<V> expiry){
        expiryMap.put(key, expiry);
    }
    
    public Expiry<V> getExpiry(@NotNull K key){
        return expiryMap.get(key);
    }
    
    public V getItem(@NotNull K key){
        var v = expiryMap.get(key);
        if(null != v)
            return v.getSubject();

        return null;
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









