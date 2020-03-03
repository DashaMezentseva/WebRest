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
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery(
                "select login as principal, password as credentials, true from User where login = ?")
            .authoritiesByUsernameQuery(
                "select u.login as principal, r.name as role from User u, Role r where u.roleId=r.roleId and u.login = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/admin").hasRole("ADMIN")
            .antMatchers("/add/**").hasRole("ADMIN")
            .antMatchers("/edit/**").hasRole("ADMIN")
            .antMatchers("/delete/**").hasRole("ADMIN")
            .antMatchers("/user").hasRole("USER")
            .antMatchers("/registration").permitAll()
            .and()
            .formLogin().loginPage("/login").loginProcessingUrl("/loginCheck")
            .defaultSuccessUrl("/home").failureUrl("/login?error=true")
            .usernameParameter("username").passwordParameter("password").permitAll()
            .and()
            .logout().logoutUrl("/logout")
            .logoutSuccessUrl("/login").permitAll().and().csrf().disable();

    }

}

