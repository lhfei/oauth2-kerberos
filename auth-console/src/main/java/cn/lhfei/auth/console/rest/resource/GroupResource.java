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

import cn.lhfei.auth.console.orm.domain.Group;
import cn.lhfei.auth.console.rest.model.RestJsonResponse;
import cn.lhfei.auth.console.service.GroupService;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 06, 2018
 */
@RestController
public class GroupResource extends AbstractResource {

	@RequestMapping(value = {"/groups/{gid:\\w{5,}$}"}, method = GET)
	public @ResponseBody RestJsonResponse<Object> read(@PathVariable(required = false) String gid) {
		String gidNumber = StringUtils.trimAllWhitespace(gid);
		if(StringUtils.isEmpty(gidNumber)) {
			return new RestJsonResponse<>(Status.OK, groupService.findAll());
		}else {
			return new RestJsonResponse<>(Status.OK, groupService.findOne(gid));
		}
	}
	
	/**
	 * Search Group by <code>GID</code> or <code>CN</code>
	 * 
	 * @param gid
	 * @param cn
	 * @return
	 */
	@RequestMapping(value = "/groups", method = GET)
	public @ResponseBody RestJsonResponse<Object> search(@RequestParam(required = false) String gid, 
			@RequestParam(required=false) String cn) {
		List<Group> groups;
		try {
			groups = new ArrayList<>();
			cn = StringUtils.trimAllWhitespace(cn);
			gid = StringUtils.trimAllWhitespace(gid);
			Group group = new Group();
			group.setCn(cn);
			group.setGidNumber(gid);
			
			groups = groupService.search(group);
			return new RestJsonResponse<>(Status.OK, groups);
			
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED, e.getMessage());
		}
		
		
	}
	
	@RequestMapping(value = {"/groups/{cn:\\w{2,}$}"}, method = DELETE)
	public @ResponseBody RestJsonResponse<Object> delete(@PathVariable(required = true) String cn) {
		try {
			if(!StringUtils.isEmpty(cn)) {
				groupService.delete(cn);
				
				return new RestJsonResponse<>(Status.OK);
			}else {
				return new RestJsonResponse<>(Status.BAD_REQUEST);
			}
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED);
		}
		
	}
	
	@RequestMapping(value = "/groups", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = POST)
    public @ResponseBody RestJsonResponse<Object> create(@RequestBody Group group) {
        try {
			groupService.create(group);
			
			LOG.debug("{}", group.toString());
			
			return new RestJsonResponse<>(Status.OK);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED, e.getMessage());
		}
    }

	@Autowired
	private GroupService groupService;
}
