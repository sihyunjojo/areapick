package com.joyride.gateway;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration  // Spring 설정 클래스로, 이 클래스가 Spring 설정으로 사용됨을 나타냅니다.
@EnableWebFluxSecurity  // Spring WebFlux 기반의 보안 설정을 활성화하는 어노테이션입니다.
public class ActuatorSecurityConfig {

    // /actuator/gateway 같은 다른 Actuator 엔드포인트에 접근하려면, 설정상 ACTUATOR_ADMIN 역할을 가진 사용자만 접근할 수 있습니다.
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // CSRF 보호를 비활성화하고 보안 필터 체인을 설정합니다.
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)  // CSRF 보호를 비활성화합니다.
                .authorizeExchange(exchanges -> exchanges
                        // /actuator/health 및 /actuator/info 엔드포인트에 대해 모든 사용자 접근을 허용합니다.
                        .matchers(EndpointRequest.to("health", "info")).permitAll()
                        // 나머지 모든 Actuator 엔드포인트에 대해 ACTUATOR_ADMIN 역할을 가진 사용자만 접근을 허용합니다.
                        .matchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR_ADMIN")
                        // 그 외 모든 요청에 대해 모든 사용자 접근을 허용합니다.
                        .anyExchange().permitAll()
                )
                // 기본 인증(Basic Authentication)을 사용하도록 설정합니다.
                .httpBasic(httpBasic -> {});

        return http.build();  // 보안 필터 체인을 빌드합니다.
    }
}
