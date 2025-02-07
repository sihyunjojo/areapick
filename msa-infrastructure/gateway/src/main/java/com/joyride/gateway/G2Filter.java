package com.joyride.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class G2Filter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 서버에 들어가기 전에 해주는 필터 작업
        System.out.println("pre global filter order -2");

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    // 서버에 들어간 후에 해주는 필터 작업
                    System.out.println("post global filter order -2");
                }));
    }

    // 순번을 리턴해주면 순서에 따라서 진행해줌.
    @Override
    public int getOrder() {

        return -2;
    }
}