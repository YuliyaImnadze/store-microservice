//package com.example.apigateway;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
//import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfiguration {
//
//    private final ClientRegistrationRepository clientRegistrationRepository;
//
//
//    public WebSecurityConfiguration(ClientRegistrationRepository clientRegistrationRepository) {
//        this.clientRegistrationRepository = clientRegistrationRepository;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.oauth2Login(Customizer.withDefaults());
//        http.logout(logout -> logout
//                .logoutSuccessHandler(oidcClientInitiatedLogoutSuccessHandler()));
//        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//                .anyRequest().authenticated());
//        http.headers(Customizer.withDefaults());
//        http.anonymous(AbstractHttpConfigurer::disable);
//        http.csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//    }
//
//    @Bean
//    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
//        return (authorities) -> {
//            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
//            authorities.forEach(authority -> {
//                //TODO Map roles
////				if (authority instanceof OidcUserAuthority){
////					OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) authority;
////					JSONArray keycloakRoles = (JSONArray) oidcUserAuthority.getAttributes().get("roles");
////					keycloakRoles.forEach(role -> mappedAuthorities.add(new SimpleGrantedAuthority((String) role)));
////				} else {
////					mappedAuthorities.add(authority);
////				}
//            });
//            return mappedAuthorities;
//        };
//    }
//
//    OidcClientInitiatedLogoutSuccessHandler oidcClientInitiatedLogoutSuccessHandler() {
//        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
//        successHandler.setPostLogoutRedirectUri("http://localhost:8080/");
//        return successHandler;
//    }
//}