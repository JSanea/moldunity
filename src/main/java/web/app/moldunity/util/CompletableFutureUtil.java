package web.app.moldunity.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class CompletableFutureUtil {
    public static <T> ResponseEntity<T> exceptionWrapper(CompletableFuture<T> completableFuture){
        try {
            return new ResponseEntity<>(completableFuture.get(), HttpStatus.OK);
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static <T> ResponseEntity<Boolean> removeExceptionWrapper(CompletableFuture<T> completableFuture){
        try {
            if (completableFuture.get() == null){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
