package web.app.moldunity.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import web.app.moldunity.service.AdService;


@Component
@RequiredArgsConstructor
public class OwnershipFilter implements WebFilter {
    private final AdService adService;

    @Override
    @NonNull
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        HttpMethod method = request.getMethod();

        if (checkPath(path, method)) {
            Long adId = Long.parseLong(path.substring(path.lastIndexOf('/') + 1));

            return adService.isOwner(adId)
                    .flatMap(isOwner -> {
                        if (!isOwner) {
                            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                            return exchange.getResponse().setComplete();
                        }else {
                            return chain.filter(exchange);
                        }
                    });
        }

        return chain.filter(exchange);
    }

    private boolean checkPath(String path, HttpMethod method){
        return (path.matches("^api/images/ads/\\d+$")  && method == HttpMethod.POST)
                || (path.matches("^api/republish/ads/\\d+$") && method == HttpMethod.POST)
                || (path.matches("^api/edit/ads/\\d+$") && method == HttpMethod.PUT)
                || (path.matches("^api/edit/ads/\\d+$") && method == HttpMethod.DELETE);
    }
}



















