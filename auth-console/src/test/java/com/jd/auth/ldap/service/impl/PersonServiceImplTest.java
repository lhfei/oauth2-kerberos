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

package com.jd.auth.ldap.service.impl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.auth.console.orm.domain.Person;
import cn.lhfei.auth.console.service.PersonService;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 05, 2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/application-context.xml" })
public class PersonServiceImplTest {
	private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImplTest.class);

	@Before
	public void setUp() {
		/*
		 * BaseLdapPathBeanPostProcessor baseLdapPathBeanPostProcessor = new
		 * BaseLdapPathBeanPostProcessor();
		 * baseLdapPathBeanPostProcessor.setBasePath("dc=jd,dc=com");
		 * 
		 * LdapName ldapName = LdapNameBuilder.newInstance("dc=jd,dc=com").build();
		 * 
		 * userService.setBaseLdapPath(ldapName);
		 */
	}

	@Test
	public void ceate() throws Exception {
		Person person = new Person();
		String cm = "ldapuser0";
		int num = 7;
		String id = cm + "" + num;

		person.setCn(id);
		person.setUid(id);
		person.setUidNumber("" + (1000 + num));
		person.setGidNumber("" + 1000);
		person.setUserPassword("Lhfei");
		person.setEmail(id + "@jd.com");

		personService.create(person);
	}

	@Test
	public void findAll() throws Exception {
		List<Person> persons = personService.findAll();

		for (Person p : persons) {
			LOG.info("{}", p.toString());
		}
	}

	@Test
	public void findByUid() throws Exception {
		String uid = "ldapuser01";
		Person p = personService.findOne(uid);

		LOG.info("{}", p.toString());
	}

	@Test
	public void findByGroup() throws Exception {
		String gidNumber = "1000";
		List<Person> persons = personService.findByGroup(gidNumber);

		for (Person p : persons) {
			LOG.info("{}", p.toString());
		}
	}
	
	@Test
	public void search() throws Exception {
		Person person = new Person();
		person.setCn("ldap");
		person.setUid("lhf");
		
		List<Person> persons = personService.search(person);
		for (Person p : persons) {
			LOG.info("{}", p.toString());
		}
		
		// just filter by cn
		person.setUid(null);
		persons = personService.search(person);

		for (Person p : persons) {
			LOG.info("{}", p.toString());
		}
		
		// just filter by uid
		person.setCn(null);
		person.setUid("lhf");
		persons = personService.search(person);

		for (Person p : persons) {
			LOG.info("{}", p.toString());
		}
	}
	
	@Test
	public void delete() throws Exception {
		String uid = "ldapuser16";
		
		personService.delete(uid);
	}

	@Autowired
	private PersonService personService;
}
