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

package cn.lhfei.auth.console.rest.resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import cn.lhfei.auth.console.orm.domain.ClusterService;
import cn.lhfei.auth.console.orm.domain.Person;
import cn.lhfei.auth.console.orm.repository.ClusterServiceRepository;
import cn.lhfei.auth.console.rest.model.RestJsonResponse;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 18, 2018
 */
@RestController
public class ClusterServiceResource extends AbstractResource {
	
	/**
	 * Search Person by <code>UID</code> or <code>CN</code>
	 * 
	 * @param uid
	 * @param cn
	 * @return
	 */
	@RequestMapping(value = "/services", method = GET)
	public @ResponseBody RestJsonResponse<Object> search(@RequestParam(required = false) String uid, 
			@RequestParam(required=false) String cn) {
		List<ClusterService> services;
		try {
			services = new ArrayList<>();
			cn = StringUtils.trimAllWhitespace(cn);
			uid = StringUtils.trimAllWhitespace(uid);
			Person person = new Person();
			person.setUid(uid);
			person.setCn(cn);
			
			services = Lists.newArrayList(clusterServiceRepository.findAll());
			return new RestJsonResponse<>(Status.OK, services);
			
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED, e.getMessage());
		}
	}

	
	@Autowired
	private ClusterServiceRepository clusterServiceRepository;
}
