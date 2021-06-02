package com.sam.mum.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sam.mum.service.CustomerUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Autowired
	GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	
	@Autowired
	CustomerUserDetailService customerUserDetailService;


	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		 http.authorizeRequests()
		.antMatchers("/", "/shop/**", "/register", "/h2-console/**", "/hello","/paypal-home").permitAll()
		.antMatchers("/admin/**").hasAnyRole("ADMIN")
				 .antMatchers("/vendor/registration").hasRole("ADMIN")
				 .antMatchers("/vendor/**").hasAnyRole("VENDOR", "ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
				 .successHandler(myAuthenticationSuccessHandler())
		.failureUrl("/login?error = true")
		.usernameParameter("email")
		.passwordParameter("password")
		.and()
		.oauth2Login()
		.loginPage("/login")
		//.successHandler(googleOAuth2SuccessHandler) //googleOAuth2SuccessHandler
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.and()
		.exceptionHandling()
		.and()
		.csrf()
		.disable();
		
		
		http.headers().frameOptions().disable();
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
		return new MySimpleUrlAuthenticationSuccessHandler();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		//check Password in Encyption way
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customerUserDetailService);
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		
		//Ignore all these mapping 
		
		web.ignoring().antMatchers("/resources/**" , "/static/**", "/images/**" , "/productImages/**", "/css/**"  , "/js/**");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
