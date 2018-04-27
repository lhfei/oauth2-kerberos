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

package cn.lhfei.auth.console.service.impl;

import javax.naming.Name;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.ldap.support.LdapNameBuilder;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 07, 2018
 */

public class LdapBaseService {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	protected Marker MARKER = MarkerFactory.getMarker("LDAP");
	
	protected Name getPersonBaseDn() {
		return LdapNameBuilder.newInstance().add("ou", "People").build();
	}

	protected Name buildDn(String uid) {
		return LdapNameBuilder.newInstance().add("ou", "People").add("uid", uid).build();
	}

	protected Name getGroupDn() {
		return LdapNameBuilder.newInstance().add("ou", "Group").build();
	}
	
	protected Name buildGroupDn(String groupName) {
		return LdapNameBuilder.newInstance().add("ou", "Group").add("cn", groupName).build();
	}
}
