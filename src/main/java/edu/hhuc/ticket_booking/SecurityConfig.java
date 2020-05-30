package edu.hhuc.ticket_booking;
import edu.hhuc.ticket_booking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    AccountService service;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(service);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests() // 开启 HttpSecurity 配置
                .antMatchers("/", "/register").permitAll()
                .anyRequest().authenticated() // 用户访问其它URL都必须认证后访问（登录后访问）
                .and().formLogin().loginPage("/login")
                .loginProcessingUrl("/dologin")
                .usernameParameter("name")
                .passwordParameter("password")
                //.successHandler((req, resp, auth)->resp.sendRedirect("/"))
                .failureHandler(new AuthenticationFailureHandler(){
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        AuthenticationException e)
                            throws IOException, ServletException{
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        if(e instanceof LockedException){
                            out.write("错误代码 401, 账户已被锁定, 登录失败");
                        }
                        else if(e instanceof BadCredentialsException){
                            out.write("错误代码 401, 账户名或密码输入错误，登录失败!");
                        }
                        else if(e instanceof DisabledException){
                            out.write("错误代码 401, 账户被禁用，登录失败!");
                        }
                        else if(e instanceof AccountExpiredException){
                            out.write("错误代码 401, 账户已过期，登录失败!");
                        }
                        else if(e instanceof CredentialsExpiredException){
                            out.write("错误代码 401, 密码已过期，登录失败!");
                        }
                        else{ out.write("错误代码 401, 登录失败!"); }
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .and().csrf().disable(); // 关闭csrf
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
