package com.poly;

import com.poly.entity.Account;
import com.poly.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    AccountService accountService;


    //    Lấy dữ liệu trong db
    @Bean
    public UserDetailsService authentication() throws Exception {
       return (username -> {
            try {
                Account user = accountService.findById(username);
                String pw = getPasswordEncoder().encode(user.getPassword());
                String[] role = user.getAuthorities().stream()
                        .map(er -> er.getRole().getId())
                        .collect(Collectors.toList())
                        .toArray(new String[0]);
                return User.withUsername(username)
                        .password(pw)
                        .roles(role)
                        .build();
            } catch (NoSuchElementException e) {
                throw new UsernameNotFoundException(username + "Not found");
            }
        });
    }

    //    Phân quyền
    @Bean
    public SecurityFilterChain authorization(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(req -> req.requestMatchers("/order/**").authenticated()
                .requestMatchers("/assets/admin/**").hasAnyRole("STAF", "DIRE")
                .requestMatchers("/rest/authorities").hasRole("DIRE")
                .anyRequest().permitAll())

                .formLogin(log -> log.loginPage("/security/login/form")
                        .loginProcessingUrl("/security/login")
                        .defaultSuccessUrl("/security/login/success", false)
                        .failureUrl("/security/login/error"))

                .rememberMe(remember -> remember.tokenValiditySeconds(86400))

                .exceptionHandling(ex -> ex.accessDeniedPage("/security/unauthoried"))

                .logout(logout -> logout.logoutUrl("/security/logoff")
                        .logoutSuccessUrl("/security/logoff/success"))
        .sessionManagement(ss -> ss.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        return http.build();
    }

    //    Mã hóa mật khẩu
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    Cho phép truy xuất RestAPI từ bên ngoài
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }

}
