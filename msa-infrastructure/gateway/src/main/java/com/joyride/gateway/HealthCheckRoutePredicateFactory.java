package com.joyride.gateway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

@Component
public class HealthCheckRoutePredicateFactory extends AbstractRoutePredicateFactory<HealthCheckRoutePredicateFactory.Config> {

    public HealthCheckRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            // Health Check 로직 추가
            String path = exchange.getRequest().getURI().getPath();
            return path.equals(config.getPath());
        };
    }

    @Setter
    @Getter
    public static class Config {
        private String path;

    }
}
