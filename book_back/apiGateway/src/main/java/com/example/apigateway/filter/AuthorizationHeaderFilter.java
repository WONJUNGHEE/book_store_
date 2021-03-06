package com.example.apigateway.filter;


import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    private Environment env;
    public static class Config {

    }
    public AuthorizationHeaderFilter(Environment env){
        super(Config.class);
        this.env=env;
    }
    @Override
    public GatewayFilter apply(Config config){
        return (exchange, chain)-> {
            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange,"No authorization Header", HttpStatus.UNAUTHORIZED); //401 error
            }
            String authorizationHeader =  request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer","");
            if (!isJwtvalid(jwt)) {
                return onError(exchange,"JWT is not valid", HttpStatus.UNAUTHORIZED); //401 error
            }
            return chain.filter(exchange);
        };
    }
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        return response.setComplete();
    }
    private boolean isJwtvalid(String jwt) {
        boolean result = true;
        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(jwt).getBody()
                    .getSubject();
        } catch (Exception ex) {
            result = false;
        }
        if (subject == null || subject.isEmpty()) {
            result = false;
        }
        return result;
    }
}
