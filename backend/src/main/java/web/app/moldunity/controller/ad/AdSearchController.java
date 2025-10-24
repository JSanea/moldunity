package web.app.moldunity.controller.ad;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import web.app.moldunity.model.dto.ad.AdPage;
import web.app.moldunity.service.ad.AdSearchService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AdSearchController {
    private final AdSearchService adSearchService;

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AdPage>> search(@RequestParam("query")String query,
                                               @RequestParam("page") Long page){

        if (query.isBlank() || page < 1) return Mono.just(ResponseEntity.badRequest().build());
        query = query.trim();
        query += ":*";
        return adSearchService.search(query, page)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    log.error("Error to search ads: {}", e.getMessage(), e);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

    @GetMapping(value = "/search/{subcategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AdPage>>searchBySubcategory(@RequestParam("query")String query,
                                                  @RequestParam("page") Long page){
        if (query.isBlank() || page < 1) return Mono.just(ResponseEntity.badRequest().build());
        query = query.trim();
        query += ":*";
        return Mono.empty();
    }
}
