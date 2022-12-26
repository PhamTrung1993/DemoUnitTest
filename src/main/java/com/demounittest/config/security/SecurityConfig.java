package com.demounittest.config.security;


import com.demounittest.config.custom.CustomAccessDeniedHandler;
import com.demounittest.config.custom.RestAuthenticationEntryPoint;
import com.demounittest.config.filter.JwtAuthenticationFilter;
import com.demounittest.model.Coach;
import com.demounittest.model.RoleName;
import com.demounittest.model.Roles;
import com.demounittest.service.coach.CoachService;
import com.demounittest.service.coach.ICoachService;
import com.demounittest.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public ICoachService userService() {
        return new CoachService();
    }

    @Autowired
    private CoachService coachService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @PostConstruct
    public void init() {
        List<Coach> coaches = (List<Coach>) coachService.findAll();
        List<Roles> roleList = (List<Roles>) roleService.findAll();
        if (roleList.isEmpty()) {
            Roles roleAdmin = new Roles();
            roleAdmin.setId(1L);
            roleAdmin.setName(RoleName.ROLE_ADMIN.toString());
            roleService.save(roleAdmin);
            Roles roleCoach = new Roles();
            roleCoach.setId(2L);
            roleCoach.setName(RoleName.ROLE_COACH.toString());
            roleService.save(roleCoach);
        }
        if (coaches.isEmpty()) {
            Coach admin = new Coach();
            Set<Roles> roles = new HashSet<>();
            roles.add(new Roles(1L, RoleName.ROLE_ADMIN.toString()));
            admin.setEmail("admin");
            admin.setCoachId("");
            admin.setName("");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRoles(roles);
            coachService.save(admin);
        }
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(coachService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html/**",
                "/configuration/**",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());
        http.authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/categories/**").access("hasAnyRole('COACH','ADMIN')")
                .antMatchers(HttpMethod.POST,
                        "/categories").access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.PUT,
                        "/categories/**").access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.DELETE,
                        "/categories/**").access("hasRole('ADMIN')")
                .and().csrf().disable()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}
