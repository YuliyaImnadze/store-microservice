package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class KeycloakConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                         ReactiveClientRegistrationRepository clientRegistrationRepository,
                                                         ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges.pathMatchers("/login").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withJwkSetUri("http://localhost:9080/realms/micro-store/protocol/openid-connect/certs")
                .build();
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/login")
                        .and()
                        .method(HttpMethod.POST)
                        .filters(f -> f
                                .addRequestParameter("client_id", "springsecurity")
                                .addRequestParameter("client_secret", "XigGpqqEtBw9Jx5KNWAFk7MRBkJp9TgB")
                                .addRequestParameter("username", "test")
                                .addRequestParameter("password", "test")
                                .addRequestParameter("grant_type", "password"))
                        .uri("http://localhost:9080/realms/micro-store/protocol/openid-connect/token"))

                .build();
    }
}

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/actuator/**"))
//                .authorizeExchange(configurer -> configurer
//                        .pathMatchers("/actuator/**").hasAuthority("SCOPE_metrics"))
//                .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()))
//                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .build();
//    }

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
////                .authorizeHttpRequests(c -> c.requestMatchers("/error").permitAll()
////                        .anyRequest().authenticated());
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .build();
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
////        http.csrf(AbstractHttpConfigurer::disable)
//        http
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
//                .authorizeHttpRequests(c -> c.requestMatchers("/error").permitAll()
//                        .anyRequest().authenticated());
////        http.oauth2Login(Customizer.withDefaults());
//
//        return http.build();
//    }

//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        var converter = new JwtAuthenticationConverter();
//        var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        converter.setPrincipalClaimName("preferred_username");
//        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            var authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
//            var roles = jwt.getClaimAsStringList("spring_sec_roles");
//
//            return Stream.concat(authorities.stream(),
//                            roles.stream()
//                                    .filter(role -> role.startsWith("ROLE_"))
//                                    .map(SimpleGrantedAuthority::new)
//                                    .map(GrantedAuthority.class::cast))
//                    .toList();
//        });
//
//        return converter;
//    }

//    @Bean
//    public AccessDeniedHandler accessDeniedHandler(){
//        return new CustomAccessDeniedHandler();
//    }

