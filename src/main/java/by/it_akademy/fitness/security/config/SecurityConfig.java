package by.it_akademy.fitness.security.config;

import by.it_akademy.fitness.storage.api.IUserStorage;
import by.it_akademy.fitness.security.filter.JwtAuthFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final IUserStorage userStorage;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*  ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)*/
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/me/**").hasAnyAuthority("ROLE_USER")
                .antMatchers("/**/registration/**").permitAll()
                .antMatchers("/**/login/**").permitAll()
                .antMatchers("/**/users/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/**/product/**").permitAll()
                .antMatchers("/**/recipe/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers("/**/profile/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/**/audit/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //if you authenticate your user the first time the
                //session will have always the authenticated state
                //and this is the behavior that we don't want to add
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);//there we want to add filter before another filter(jwtAuthFilter before
        //there we told Spring , hey go ahead and use this filter before authentication the User
        //because (in jwtAuthFilter we are checking the JWT and if everything is fine what we do - we
        //set, or we update the context of the security context holder,so we want to execute
        //jwtAuthFilter before UsernamePasswordAuthenticationFilter

        return (SecurityFilterChain) http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        //this method that we want spring to use instead
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
    public BCryptPasswordEncoder passwordEncoder() {
        //return null;
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
                //In this method (loadUserByUsername) we load user by userName
                //the one that we will use the JWT authentication filter
                //in this case we are not fetching user from database
                //we use a static list
                /**/
                //return userDao.findByUsername(email);
                return userStorage.findByLogin(login);
            }
        };
    }
}
