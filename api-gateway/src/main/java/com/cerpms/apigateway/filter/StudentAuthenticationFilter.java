package com.cerpms.apigateway.filter;

import com.cerpms.apigateway.config.AsyncMethods;
import com.cerpms.apigateway.jwt.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class StudentAuthenticationFilter extends AbstractGatewayFilterFactory<StudentAuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AsyncMethods asyncMethods;

    public StudentAuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing Authorization Header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    String extractUsername = jwtHelper.extractUsername(authHeader);
//                    Search in Student DB if that username matches any Entity
                    String userDetailsFromStudent = (String) asyncMethods.getUserDetailsFromStudent(extractUsername).get();
                    log.info("User Details from Student is " + userDetailsFromStudent);

                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("Unauthorized Access to Application");
                }
            }
            return chain.filter(exchange);
        });
    }


    public static class Config {

    }
}