package com.nixsolutions.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery(
                "select login, password, 1 from user where login=?")
            .authoritiesByUsernameQuery(
                "select u.login, r.name from user u, role r where u.roleId=r.roleId and u.login=?");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/", "/login", "/registration")
//            .permitAll()
//            .antMatchers("/edit", "/admin", "/edit/*", "/add", "/delete")
//            .hasAuthority("ADMIN").antMatchers("/user").hasAuthority("USER")
//            .and().formLogin().loginPage("/login")
//            .loginProcessingUrl("/loginCheck")
//            .defaultSuccessUrl("/home").failureUrl("/login?error=true").
//            usernameParameter("username").passwordParameter("password").permitAll().
//            and().logout()
//            .logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true)
//            .permitAll().and().csrf().disable();
//
//    }

    ///////////////////

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/add/**").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/edit/**").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/delete/**").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/user/**").access("hasRole('ROLE_USER')")
            .and()
            .formLogin().loginPage("/login").loginProcessingUrl("/loginCheck")
            .defaultSuccessUrl("/home").failureUrl("/login?error=true")
            .usernameParameter("username").passwordParameter("password").permitAll()
            .and()
            .logout().logoutUrl("/logout")
            .logoutSuccessUrl("/login").permitAll().and().csrf().disable();

    }

}

