package barcodeproduct.service.BarCodeProduct.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthentificationEntryPoint authEntryPoint;

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password, enable from user where username=?")
                .authoritiesByUsernameQuery("select username, role_name from users_roles where username=?");
    }

    /**
     * Configure l'accès des pages
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/v1/products","/v1/users").permitAll()
                .antMatchers("/v1/users/**").access("hasRole('USER')")
                .and()
                .httpBasic()
                .authenticationEntryPoint(authEntryPoint);
                /*.antMatchers("/administration/**").access("hasRole('ADMIN')")
                .and()
                .formLogin()
                .loginPage("/account/login")
                .permitAll()
                .usernameParameter("email").passwordParameter("password")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/account/login?logout");*/
    }

}
