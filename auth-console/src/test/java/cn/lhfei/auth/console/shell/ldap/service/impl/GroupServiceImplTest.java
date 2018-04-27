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

package cn.lhfei.auth.console.shell.ldap.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.stringtemplate.v4.ST;

import cn.lhfei.auth.console.orm.domain.Group;
import cn.lhfei.auth.console.service.GroupService;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 05, 2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/application-context.xml" })
public class GroupServiceImplTest {
	private static final Logger LOG = LoggerFactory.getLogger(GroupServiceImplTest.class);

	@Test
	public void test() {
		String s = "ladp*d>d{as}12sd}1a^sd&as_we!ad@";
		LOG.info("Replace: {}", s);
		
		s.replaceAll("\\W", "");
		
		LOG.info("Replaced: {}", s);
		
		
		ST hello = new ST("ldapsearch -x -b \"ou=People,dc=jd,dc=com\" '(&(objectClass=account)(|<cn><uid>))' ");
        hello.add("cn", ""); //(cn=*ldapuser01*)
        hello.add("uid", "(|(uid=*lhfe*))"); //(|(uid=*lhfe*))
        System.out.println(hello.render());
	}
	
	@Test
	public void findAll() {
		List<Group> groups = groupService.findAll();

		for (Group group : groups) {
			LOG.info("{}", group.toString());
		}
	}
	
	@Test
	public void findOne() {
		String cn = "IT";
		Group group = groupService.findOne(cn);
		
		LOG.info("{}", group.toString());
	}
	
	@Test
	public void addMember2Group() {
		String groupName = "IT";
		String uid = "lhfei";
		
		groupService.addMember2Group(groupName, uid);
	}
	
	@Test
	public void removeMemberFromGroup() {
		String groupName = "IT";
		String uid = "lhfei";
		
		groupService.removeMemberFromGroup(groupName, uid);
	}

	@Autowired
	private GroupService groupService;
}
