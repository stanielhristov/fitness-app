package com.example.individualprojectstaniel.config;

import com.example.individualprojectstaniel.repository.UserRepository;
import com.example.individualprojectstaniel.service.Impl.UserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(

        jsr250Enabled = true)
public class SecurityConfiguration {


    private final String rememberMeKey;


    public SecurityConfiguration(@Value("${individualprojectstaniel.remember.me.key}")
                                 String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                        // Define which urls are visible by which users
                        authorizeRequests -> authorizeRequests
                                // All static resources which are situated in js, images, css are available for anyone
                                .requestMatchers(String.valueOf(PathRequest.toStaticResources().atCommonLocations())).permitAll()
                                // Allow anyone to see the home page, the registration page and the login form
                                .requestMatchers("/", "/login", "/login/error", "/register", "/logout", "/resources/**", "/css/**", "/js/**", "/images/**", "/webjars/**", "/favicon.*", "/*/icon-*").permitAll()
                                .requestMatchers("/error").permitAll()
                                // all other requests are authenticated.
                                .anyRequest().authenticated()
                ).formLogin(
                        formLogin -> {
                            formLogin
                                    // redirect here when we access something which is not allowed.
                                    // also this is the page where we perform login.
                                    .loginPage("/login")
                                    // The names of the input fields (in our case in auth-login.html)
                                    .usernameParameter("username")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/homepage");
//                                    .failureForwardUrl("/login/error");
                        }
                ).csrf(AbstractHttpConfigurer::disable)
                .logout(
                        logout -> {
                            logout
                                    // the URL where we should POST something in order to perform the logout
                                    .logoutUrl("/logout")
                                    // where to go when logged out?
                                    .logoutSuccessUrl("/login")
                                    // invalidate the HTTP session
                                    .invalidateHttpSession(true);
                        }
                ).rememberMe(
                        rememberMe -> {
                            rememberMe
                                    .key(rememberMeKey)
                                    .rememberMeParameter("rememberme")
                                    .rememberMeCookieName("rememberme");
                        }
                ).build();
    }


    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_OWNER > ROLE_ADMIN \n ROLE_ADMIN > ROLE_USER \n ADMIN > USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
}