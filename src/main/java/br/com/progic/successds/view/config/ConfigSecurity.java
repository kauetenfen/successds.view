package br.com.progic.successds.view.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

import br.com.progic.successds.api.service.MyUserDetailsService;


@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    
		// require all requests to be authenticated except for the resources
	    http.authorizeRequests().antMatchers("/javax.faces.resource/**", "/images/**").permitAll()
	    	.anyRequest().authenticated()
			.and().requestCache().requestCache(new NullRequestCache())
			.and().httpBasic();
	    
	    // login
	    http.formLogin().loginPage("/login.xhtml").permitAll()
	    	.successForwardUrl("/index.xhtml")
	        .failureUrl("/login.xhtml?error=true")
	        .defaultSuccessUrl("/index.xhtml");
	    
	    // logout
	    http.logout().logoutSuccessUrl("/login.xhtml");
	    // not needed as JSF 2.2 is implicitly protected against CSRF
	    http.csrf().disable();
	    
	  }

		@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());	        
	    }
	  
		@Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(myUserDetailsService);
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }
	  
		@Bean
		public PasswordEncoder passwordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
	

}
