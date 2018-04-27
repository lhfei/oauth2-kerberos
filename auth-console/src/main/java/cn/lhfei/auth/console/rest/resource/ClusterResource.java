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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import cn.lhfei.auth.console.orm.domain.Cluster;
import cn.lhfei.auth.console.orm.repository.ClusterRepository;
import cn.lhfei.auth.console.rest.model.RestJsonResponse;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 18, 2018
 */
@RestController
public class ClusterResource extends AbstractResource {
	@RequestMapping(value = {"/clusters/{clusterId:\\d{1,}$}"}, method = GET)
	public @ResponseBody RestJsonResponse<Object> read(@PathVariable(required = true) Long clusterId) {
		try {
			List<Cluster> clusters = new ArrayList<>();
			if(clusterId != null) {
				return new RestJsonResponse<>(Status.OK, clusterRepository.findById(clusterId));
			}else {
				clusters = Lists.newArrayList(clusterRepository.findAll());
				return new RestJsonResponse<>(Status.OK, clusters);
			}
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED);
		}
		
	}
	
	@RequestMapping(value = {"/clusters/{clusterId:\\d{1,}$}"}, method = DELETE)
	public @ResponseBody RestJsonResponse<Object> delete(@PathVariable(required = true) Long clusterId) {
		try {
			if(clusterId != null) {
				Cluster cluster = new Cluster();
				cluster.setClusterId(clusterId);
				clusterRepository.delete(cluster);
				
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
	 * @param clusterName
	 * @return
	 */
	@RequestMapping(value = "/clusters", method = GET)
	public @ResponseBody RestJsonResponse<Object> search(@RequestParam(required = false) String uid, 
			@RequestParam(required=false) String clusterName) {
		List<Cluster> clusters;
		try {
			clusters = new ArrayList<>();
			clusterName = StringUtils.trimAllWhitespace(clusterName);
			uid = StringUtils.trimAllWhitespace(uid);
			
			if(null != clusterName) {
				clusters = entitymanager.createQuery("from Cluster where clusterName = :clusterName", Cluster.class)
						.setParameter("clusterName", clusterName).getResultList();
			}else {
				clusters = Lists.newArrayList(clusterRepository.findAll());
			}
			
			return new RestJsonResponse<>(Status.OK, clusters);
			
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/clusters", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = POST)
    public @ResponseBody RestJsonResponse<Object> createUser(@RequestBody Cluster cluster) {
        try {
        	clusterRepository.save(cluster);
			
			LOG.debug("{}", cluster.toString());
			
			return new RestJsonResponse<>(Status.OK);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED, e.getMessage());
		}
		
    }

	
	@Autowired
	private ClusterRepository clusterRepository;
	
	@Autowired
	private EntityManager entitymanager;
}
