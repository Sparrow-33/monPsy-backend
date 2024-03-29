package com.example.demo.config;

import com.example.demo.config.usersDetails.CustomAuthenticationProvider;
import com.example.demo.config.usersDetails.DoctorDetailsService;
import com.example.demo.config.usersDetails.PatientDetailsService;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.model.dao.UserDao;
import com.example.demo.model.entities.AppUser;
import com.example.demo.model.entities.Doctor;
import com.example.demo.model.repo.DoctorRepo;
import com.example.demo.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity

public class SecurityConfig  {
    private final JwtAuthFilter jwtAuthFilter;
    private final UserService userService;
    private final PatientDetailsService patientDetailsService;
    private final DoctorDetailsService doctorDetailsService;
    private final DoctorRepo doctorRepo;

    @Autowired
    @Lazy
    public SecurityConfig(JwtAuthFilter jwtAuthFilter,
                          UserService userService,
                          DoctorDetailsService doctorDetailsService,
                          PatientDetailsService patientDetailsService,
                          DoctorRepo doctorRepo) {

        this.jwtAuthFilter = jwtAuthFilter;
        this.userService = userService;
        this.doctorDetailsService = doctorDetailsService;
        this.patientDetailsService = patientDetailsService;
        this.doctorRepo = doctorRepo;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**","/common/**")
                .permitAll()
                .and()
                .authorizeHttpRequests(aut ->
                        aut.requestMatchers("/patient/**").hasAuthority("PATIENT")
                )
                .authorizeHttpRequests(aut ->
                        aut.requestMatchers("/doctor/**").hasAuthority("DOCTOR")
                )
                .authorizeHttpRequests(aut ->
                        aut.requestMatchers("/secretaire/**").hasAuthority("SECRETERE")
                )
                .authorizeHttpRequests(aut ->
                        aut.requestMatchers("/admin/**").hasAuthority("ADMIN")
                )
                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                if (email.endsWith("-PATIENT")) {
                    String emailOnly = email.substring(0, email.length() - 8);
                    AppUser user = userService.findUserByEmail(emailOnly);
                    if (user == null) {
                        throw new InvalidCredentialsException("invalid credentials");
                    }
                    return new User(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("PATIENT")));

                } else if (email.endsWith("-DOCTOR"))  {
                    String emailOnly = email.substring(0, email.length() - 7);
                    System.out.println(emailOnly);
                    Doctor user = doctorRepo.getDoctorByEmail(emailOnly);
                    if (user == null) {
                        throw new InvalidCredentialsException("invalid credentials");
                    }

                    return new User(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("DOCTOR")));
                }

                throw new InvalidCredentialsException("invalid credentials");

            }
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);

    }
}
