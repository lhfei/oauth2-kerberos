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

package cn.lhfei.auth.server.rest.resource;

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

import cn.lhfei.auth.server.ldap.domain.Person;
import cn.lhfei.auth.server.ldap.service.PersonService;
import cn.lhfei.auth.server.rest.model.RestJsonResponse;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 05, 2018
 */
@RestController
public class PersonResource extends AbstractResource {

	@RequestMapping(value = {"/persons/{uid:\\w{5,}$}"}, method = GET)
	public @ResponseBody RestJsonResponse<Object> read(@PathVariable(required = true) String uid) {
		try {
			List<Person> persons = new ArrayList<>();
			String gidNumber = StringUtils.trimAllWhitespace(uid);
			if(StringUtils.isEmpty(gidNumber)) {
				persons = personService.findAll();
				
				return new RestJsonResponse<>(Status.OK, persons);
			}else {
				Person person = personService.findOne(uid);
				
				return new RestJsonResponse<>(Status.OK, person);
			}
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED);
		}
		
	}
	
	@RequestMapping(value = {"/persons/{uid:\\w{5,}$}"}, method = DELETE)
	public @ResponseBody RestJsonResponse<Object> delete(@PathVariable(required = true) String uid) {
		try {
			if(!StringUtils.isEmpty(uid)) {
				personService.delete(uid);
				
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
	@RequestMapping(value = "/persons", method = GET)
	public @ResponseBody RestJsonResponse<Object> search(@RequestParam(required = false) String uid, 
			@RequestParam(required=false) String cn) {
		List<Person> persons;
		try {
			persons = new ArrayList<>();
			cn = StringUtils.trimAllWhitespace(cn);
			uid = StringUtils.trimAllWhitespace(uid);
			Person person = new Person();
			person.setUid(uid);
			person.setCn(cn);
			
			persons = personService.search(person);
			return new RestJsonResponse<>(Status.OK, persons);
			
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED, e.getMessage());
		}
		
		
	}
	
	@RequestMapping(value = "/groups/{gidNumber:^[1-9]\\d{3,}$}/persons", method = GET)
	public @ResponseBody RestJsonResponse<List<Person>> findByGroup(@PathVariable String gidNumber) {
		try {
			List<Person> persons = new ArrayList<>();
			persons = personService.findByGroup(gidNumber);
			
			return new RestJsonResponse<>(Status.OK, persons);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/persons", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = POST)
    public @ResponseBody RestJsonResponse<Object> createUser(@RequestBody Person person) {
        try {
			personService.create(person);
			
			LOG.debug("{}", person.toString());
			
			return new RestJsonResponse<>(Status.OK);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			return new RestJsonResponse<>(Status.EXPECTATION_FAILED, e.getMessage());
		}
		
    }

	@Autowired
	private PersonService personService;
}
