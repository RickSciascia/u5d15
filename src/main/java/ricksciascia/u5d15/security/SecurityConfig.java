package ricksciascia.u5d15.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
//        disabilito form integrato login FE
        httpSecurity.formLogin(fL -> fL.disable());
//        disabilito csrf sec, non ci serve lavoriamo con token
        httpSecurity.csrf(csrf-> csrf.disable());
//        disabilito il session management, lavoriamo con i token
        httpSecurity.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        disabilito il comportamento di default di Spring Security che blinda tutte le rotte rispondendo a tutte le req con 401
        httpSecurity.authorizeHttpRequests(r->r.requestMatchers("/**").permitAll());
//        costruisco oggetto e ritorno
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder getBCrypt() {
//        bean che mi serve per poter usare bcrypt
        return new BCryptPasswordEncoder(12);
    }
}
