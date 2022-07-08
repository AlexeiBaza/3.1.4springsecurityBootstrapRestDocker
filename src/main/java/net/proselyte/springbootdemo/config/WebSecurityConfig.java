package net.proselyte.springbootdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(5);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and().formLogin()
                .successHandler(successUserHandler)
                .and()
                .logout()
                .permitAll();
    }
}

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////    @Autowired
////    UserServiceImpl userServiceImpl;
//    @Autowired
//    SuccessUserHandler successUserHandler;
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() { return new BCryptPasswordEncoder(5); }
//
//
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        //старое
//        httpSecurity
//                    .csrf()
//                    .disable()
//                    .authorizeRequests()
//                    //Доступ разрешен всем пользователей
//                    .antMatchers("/").permitAll()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
//                    //Все остальные страницы требуют аутентификации
//                    .anyRequest().authenticated()
//                .and()
//                    //Настройка для входа в систему
//                    .formLogin()
//                    //Перенарпавление на страницы после успешного входа согласно successUserHandler
//                    .successHandler(successUserHandler)
////                    .permitAll()
//                .and()
//                    .logout()
//                    .permitAll();
//    }
//
////    @Autowired
////    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userServiceImpl);
////    }
//}

