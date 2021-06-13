package br.com.bandtec.osirisapi.security;

import br.com.bandtec.osirisapi.repository.UsuarioRepository;
import br.com.bandtec.osirisapi.service.AutenticacaoService;
import br.com.bandtec.osirisapi.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@AllArgsConstructor
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    private final AutenticacaoService autenticacaoService;

    private final TokenService tokenService;

    private final UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // Configurações de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // Configurações de autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Forma de liberar endpoints das controllers
                .antMatchers("/auth").permitAll()
//                .antMatchers("/usuarios").permitAll()
                .antMatchers("/usuarios/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(
                        new AutenticacaoViaTokenFilter(tokenService, usuarioRepository),
                        UsernamePasswordAuthenticationFilter.class)
                .cors();
    }

    // Configurações de recursos estáticos (js, css, imagens, etc..)
    // Serve para aplicações com backend integrado com frontend
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", CORS_CONFIGURATION);
        return source;
    }

    private static final CorsConfiguration CORS_CONFIGURATION = new CorsConfiguration();

    static {
        CORS_CONFIGURATION.setAllowCredentials(true);
        CORS_CONFIGURATION.setAllowedOrigins(Collections.singletonList("*"));
        CORS_CONFIGURATION.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        CORS_CONFIGURATION.setAllowedHeaders(Arrays.asList("Accept",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "Authorization",
                "Content-Type",
                "Origin",
                "X-Requested-With"));
        CORS_CONFIGURATION.setExposedHeaders(Collections.singletonList("Content-Disposition"));
        CORS_CONFIGURATION.setMaxAge(3600L);
    }

}
