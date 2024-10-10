package com.example.demo.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security..userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.Exception.CustomAccessDeniedHandler;
import com.example.demo.Exception.CustomAuthenticationEntryPoint;
import com.example.demo.Exception.CustomRequestRejectedHandler;
import com.example.demo.security.AccessTokenFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AccessTokenFilter accessTokenFilter() {
		return new AccessTokenFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				.authenticationEntryPoint(authenticationEntryPoint()).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()

			//	.antMatchers("/api/auth/**", "/api/nofilter/**","/currency/send-request","/currency/get_currency",
			//	"/currency/deleteCurrency/{id}","/account/send-request","/account/get-account",
			//	"/currency/updateCurrency/{id}", "/currency/findById/{id}","/account/delete-account/{id}",
			//	"/account/findById/{id}","/account/get_all_accounts","/account/get_approved_accounts", "/account/get_pending_accounts"
			//	,"/account/get_rejected_accounts","/currency/get_all_currencies","/currency/get_approved_currencies","/currency/get_pending_currencies",
			//	"/currency/get_rejected_currencies", "/account/findById_account/{id}","/account/update-account/{id}","/account/update-approved-request"
			//	 ,"/currency/findById_currency/{id}","/account/update-approved-request/{id}","/currency/update-approved-currency/{id}").permitAll()
			//	.antMatchers("/test1", "/api/teacher/**").authenticated().anyRequest()
			//	.authenticated();

				.antMatchers("/api/auth/**", "/api/nofilter/**", "/api/**").permitAll().anyRequest().authenticated();

//			.antMatchers("/api/auth/**", "/api/nofilter/**").permitAll().antMatchers("/test1", "/api/teacher/**")
//			.permitAll()
//			.anyRequest().permitAll();

 		http.addFilterBefore(accessTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	RequestRejectedHandler requestRejectedHandler() {
		return new CustomRequestRejectedHandler();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowCredentials(true);
//		corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
//				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
//				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
//		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
//				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "File-Name"));
//		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//		return new CorsFilter(urlBasedCorsConfigurationSource);
//	}


	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
     	//configuration.setAllowedOrigins(Arrays.asList("https://localhost:8080"));
//		configuration.setAllowedOrigins(Arrays.asList("https://10.10.13.152:8080"));
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//		 configuration.setAllowedOrigins(Arrays.asList("https://rms..com:443"));
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		configuration.setAllowedHeaders(Collections.singletonList("*"));
		configuration.setAllowCredentials(true);
		configuration.setExposedHeaders(Arrays.asList("*"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://192.168.43.61:4200"));
//		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
//		configuration.setAllowedHeaders(Collections.singletonList("*"));
//		configuration.setAllowCredentials(true);
//		configuration.setExposedHeaders(Arrays.asList("*"));
//
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
