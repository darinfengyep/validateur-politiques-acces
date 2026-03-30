
package com.darin.validateurpolitiquesacces.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigurationSecurite {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/connexion", "/connexion.html", "/api/health", "/error", "/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/acces/utilisateurs").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/acces/decider/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/acces/historique", "/api/acces/regles", "/api/acces/tableau-bord", "/api/session").hasAnyRole("ADMIN", "ANALYSTE")
                .requestMatchers(HttpMethod.POST, "/api/acces/valider").hasAnyRole("ADMIN", "ANALYSTE")
                .requestMatchers("/", "/index.html", "/favicon.ico").authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/connexion")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/deconnexion")
                .logoutSuccessUrl("/connexion?logout")
                .permitAll()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails analyste = User.withUsername("analyste")
                .password(encoder.encode("analyste123"))
                .roles("ANALYSTE")
                .build();

        return new InMemoryUserDetailsManager(admin, analyste);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
