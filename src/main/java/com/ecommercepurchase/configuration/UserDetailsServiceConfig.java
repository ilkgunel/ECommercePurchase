package com.ecommercepurchase.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsServiceConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("john@doe.com").password(passwordEncoder().encode("john-doe")).roles("SALES_PERSON")
                .username("jane@doe.com").password(passwordEncoder().encode("jane-doe")).roles("SALES_PERSON")
                .username("michael@schumacher.com").password(passwordEncoder().encode("michael-schumacher")).roles("F1_DRIVER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfigurationBuilder) throws Exception {
        return authenticationConfigurationBuilder.getAuthenticationManager();
    }
}
