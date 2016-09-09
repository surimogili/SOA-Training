package com.example;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class MSLoginSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin().loginPage("/login").permitAll().and().authorizeRequests().anyRequest().authenticated();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authManager);
	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter
	{
		@Autowired
		private AuthenticationManager authManager;
		
		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter()
		{
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			
			KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"),"foobar".toCharArray()).getKeyPair("test");
			
			converter.setKeyPair(keyPair);
			
			return converter;
		}
		
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient("sg").secret("admin@123").authorizedGrantTypes("authorization_code", "refresh_token","password").scopes("openid");
		}
		
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authManager).accessTokenConverter(jwtAccessTokenConverter());
		}
		
		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		}
	}
}


