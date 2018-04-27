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

package cn.lhfei.auth.console.service;

import java.util.List;

import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.stereotype.Service;

import cn.lhfei.auth.console.orm.domain.Group;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 06, 2018
 */
@Service("groupService")
public interface GroupService extends BaseLdapNameAware {
	void create(Group group) throws Exception;
	
	Group findOne(String cn);

	List<Group> findAll();
	
	List<Group> search(Group group) throws Exception;
	
	void delete(String cn) throws Exception;

	void addMember2Group(String groupName, String uid);

	void removeMemberFromGroup(String groupName, String uid);

}
