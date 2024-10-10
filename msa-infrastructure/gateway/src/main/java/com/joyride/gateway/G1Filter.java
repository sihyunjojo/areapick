package com.joyride.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Component
public class G1Filter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 요청에 대한 로그 기록
        String requestPath = exchange.getRequest().getURI().getPath();
        exchange.getRequest().getMethod();
        String method = exchange.getRequest().getMethod().toString();
        log.info("Incoming request: {} {}", method, requestPath);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // 응답에 대한 로그 기록
            int statusCode = Objects.requireNonNull(exchange.getResponse().getStatusCode()).value();
            log.info("Outgoing response: {} for {}", statusCode, requestPath);
        }));

        // 서버에 들어가기 전에 해주는 필터 작업
//        System.out.println("pre global filter order -1");
//
//        return chain.filter(exchange)
//                .then(Mono.fromRunnable(() -> {
//                // 서버에 들어간 후에 해주는 필터 작업
//                    System.out.println("post global filter order -1");
//                }));
    }

    // 순번을 리턴해주면 순서에 따라서 진행해줌.
    @Override
    public int getOrder() {

        return -1;
    }
}