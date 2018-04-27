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

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import cn.lhfei.auth.console.orm.domain.Host;
import cn.lhfei.auth.console.orm.repository.HostRepository;
import cn.lhfei.auth.console.rest.model.RestJsonResponse;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 18, 2018
 */
@RestController
public class HostResource extends AbstractResource {
	
	@RequestMapping(value = {"/clusters/{clusterId:\\d{1,}$}/hosts"}, method = GET)
	public @ResponseBody RestJsonResponse<Object> read(@PathVariable(required = true) Long clusterId) {
		try {
			List<Host> hosts = new ArrayList<>();
			if(clusterId != null) {
				
				hosts = entitymanager.createQuery("From Host where clusterId = :clusterId", Host.class)
						.setParameter("clusterId", clusterId).getResultList();
				
				return new RestJsonResponse<>(Status.OK, Lists.newArrayList(hostRepository.findById(clusterId)));
			}else {
				hosts = Lists.newArrayList(hostRepository.findAll());
				return new RestJsonResponse<>(Status.OK, hosts);
			}
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED);
		}
		
	}
	
	@RequestMapping(value = {"/hosts/{hostId:\\d{1,}$}"}, method = DELETE)
	public @ResponseBody RestJsonResponse<Object> delete(@PathVariable(required = true) Long hostId) {
		try {
			if(hostId != null) {
				Host host = new Host();
				host.setHostId(hostId);
				hostRepository.delete(host);
				
				return new RestJsonResponse<>(Status.OK);
			}else {
				return new RestJsonResponse<>(Status.BAD_REQUEST);
			}
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * Search Person by <code>UID</code> or <code>CN</code>
	 * 
	 * @param uid
	 * @param cn
	 * @return
	 */
	@RequestMapping(value = "/hosts", method = GET)
	public @ResponseBody RestJsonResponse<Object> search(@RequestParam(required = false) String uid, 
			@RequestParam(required=false) String cn) {
		List<Host> hosts;
		try {
			hosts = new ArrayList<>();
			
			hosts = Lists.newArrayList(hostRepository.findAll());
			return new RestJsonResponse<>(Status.OK, hosts);
			
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/hosts", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = POST)
    public @ResponseBody RestJsonResponse<Object> createUser(@RequestBody Host host) {
        try {
        	hostRepository.save(host);
			
			LOG.debug("{}", host.toString());
			
			return new RestJsonResponse<>(Status.OK);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED, e.getMessage());
		}
		
    }

	
	@Autowired
	private HostRepository hostRepository;
	@Autowired
	private EntityManager entitymanager;
}
