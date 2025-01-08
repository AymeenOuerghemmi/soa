package tn.windata;

import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

@Component
public class PostFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Enregistrez le temps de début dans le filtre pré-exécution, si ce n'est pas déjà fait
        exchange.getAttributes().put("startTime", System.currentTimeMillis());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // Récupérez le temps de début et assurez-vous que c'est un Long
            Long startTime = exchange.getAttribute("startTime");
            if (startTime != null) {
                long responseTime = System.currentTimeMillis() - startTime;
                System.out.println("Time taken: " + responseTime + "ms");
            }
        }));
    }
}


