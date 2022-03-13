package am.azaryan.config;

import am.azaryan.security.UserAuthAuthSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserAuthAuthSecurityService userAuthAuthSecurityService;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("/sign-up", "/login").permitAll()
//                .antMatchers("/project/create", "/project/delete/{id}", "/project/all-projects").hasAnyRole("TEAM_LEADER")
//                .antMatchers("/project/team-member-projects", "/log/create", "/log/delete/{id}", "/log/logs-per-user").hasAnyRole("TEAM_MEMBER");
                .antMatchers("/project/create", "/project/delete/{id}", "/project/all-projects", "/user/upload", "/user/get-profile-pic/{picture}",
                        "/project/team-member-projects", "/log/create", "/log/delete/{id}", "/log/logs-per-user").authenticated()
                .anyRequest().authenticated();
        http.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userAuthAuthSecurityService)
                .passwordEncoder(passwordEncoder());
    }

}

