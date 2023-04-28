
package com.samplebank.config;

import com.samplebank.security.JWTAuthorizationFilter;
import com.samplebank.security.error.RestAccessDeniedHandler;
import com.samplebank.utilities.GeneralConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author simiyu
 */
@Configuration
@EnableWebSecurity
public class WebSecurityFilterChainConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    private final AuthenticationEntryPoint authEntryPoint;

    public WebSecurityFilterChainConfig(AuthenticationProvider authenticationProvider, JWTAuthorizationFilter jwtAuthorizationFilter,
            @Qualifier("delegatedAuthenticationEntryPoint") AuthenticationEntryPoint authEntryPoint) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers(GeneralConstants.CLIENT_ENDPOINT+"**").hasRole("CLIENT");
                    auth.requestMatchers(GeneralConstants.ADMIN_ENDPOINT+"**").hasRole("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->{
                    logout.logoutUrl("/auth/logout");
                    logout.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
                })
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authEntryPoint)
                        .accessDeniedHandler(new RestAccessDeniedHandler()));

        return http.build();
    }





}
