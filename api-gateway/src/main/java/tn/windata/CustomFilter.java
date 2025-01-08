package tn.windata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

@Component
public class CustomFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Filtrage avant appel du microservice
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
        logger.info("Authorization Header: " + authorization);

        // Continuer avec la requête
        return chain.filter(exchange);
    }
}

