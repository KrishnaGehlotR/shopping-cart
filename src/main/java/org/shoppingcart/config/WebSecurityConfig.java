package org.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
// @EnableWebSecurity = @EnableWebMvcSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	MyDBAuthenticationService myDBAuthenticationService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		// For User in database
//		auth.userDetailsService(myDBAuthenticationService);
	}

	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();

		// The pages requires login as EMPLOYEE or MANAGER.
		// If no login, it will redirect to /login page.
		httpSecurity.authorizeRequests().antMatchers("/orderList", "/order", "accountInfo")
				.access("hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGER')");

		// For MANAGER only
		httpSecurity.authorizeRequests().antMatchers("/product").access("hasRole('ROLE_MANAGER')");

		// When the user has logged in as XX.
		// But access a page that requires role YY,
		// AccessDeniedException will throw.
		httpSecurity.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Configure for Login Form
		httpSecurity.authorizeRequests().and().formLogin()//
				// Submit URL of login page.
				.loginProcessingUrl("/j_spring_security_check")// Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/accountInto")//
				.failureUrl("/login?error=true")//
				.usernameParameter("userName")//
				.passwordParameter("password")//
				// Config for Logout Page
				// (Go to home page).
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
	}
}