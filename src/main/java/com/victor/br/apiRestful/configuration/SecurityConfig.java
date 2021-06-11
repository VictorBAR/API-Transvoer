package com.victor.br.apiRestful.configuration;

import com.victor.br.apiRestful.repository.EmpresaRepository;
import com.victor.br.apiRestful.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import static com.victor.br.apiRestful.configuration.SecurityConstaints.SIGN_UP_URL;

//@EnableGlobalMethodSecurity(prePostEnabled = true) //usada para habilitar o pre authorize no controller
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    EmpresaRepository empresaRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
//                .antMatchers(HttpMethod.GET, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated() //autorizar qualquer requisição que esteja autenticada
//                .and()
//                .httpBasic(); //metódo de autenticação
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService));

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

        //                .withUser("victor").password("{noop}victor").roles("USER") // o {noop} é utilizado para não codificar a senha
//                .and()
//                .withUser("admin").password("{noop}123").roles("USER", "ADMIN"); //terá as permissões do admin e do user
    }
}
