package com.codegym.cms.config;

import com.codegym.cms.service.appuser.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class AppConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAppUserService iAppUserService;

    @Autowired
    CustomerSuccessHandle customerSuccessHandle;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService((UserDetailsService) iAppUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/home").permitAll()
                .and()
                .authorizeRequests().antMatchers("/category").hasAnyRole("USER", "ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/products/**").hasAnyRole("USER", "ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/products").hasAnyRole("USER")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
                .and()
                .formLogin().successHandler(customerSuccessHandle)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .exceptionHandling().accessDeniedPage("/donthaveaccess");
        httpSecurity.csrf().disable();
    }
}
