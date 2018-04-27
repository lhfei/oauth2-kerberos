/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.auth.security.krb5.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import cn.lhfei.auth.security.krb5.shell.ExecuteHandler;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 23, 2018
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	private Logger LOG = LoggerFactory.getLogger(WebSocketConfig.class);
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/ms");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").withSockJS();
	}
	
	@Bean
	public ExecuteHandler executeHandler() {
		LOG.info("KDC Server: ssh {username}@{hostname}", username, hostname);
		return new ExecuteHandler(username, password, hostname);
	}
	
	@Value("${krb5.kdcserver.username}")
	private String username;
	@Value("${krb5.kdcserver.password}")
	private String password;
	@Value("${krb5.kdcserver.hostname}")
	private String hostname;
}
