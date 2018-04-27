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

package cn.lhfei.auth.client.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 12, 2018
 */
@RestController
public class HomeResource {
	private Logger LOG = LoggerFactory.getLogger(HomeResource.class);
	
	@GetMapping("/user")
	public String index() throws JsonProcessingException {
		LOG.info("==");
		ObjectMapper mapper = new ObjectMapper();

		SecurityContextHolder.getContext().getAuthentication();

		return mapper.writeValueAsString(SecurityContextHolder.getContext().getAuthentication());
	}
}
