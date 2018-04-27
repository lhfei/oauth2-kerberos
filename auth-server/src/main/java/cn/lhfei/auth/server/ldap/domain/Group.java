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

package cn.lhfei.auth.server.ldap.domain;

import java.util.HashSet;
import java.util.Set;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 05, 2018
 */
public class Group extends AbstractDomain {
	private static final long serialVersionUID = 6287349989498190951L;

	public Group() {
	}

	public Group(String cn, Set<String> members) {
		this.cn = cn;
		this.members = members;
	}

	public Group(Name dn, String cn, Set<String> members) {
		this.cn = cn;
		this.members = members;
	}

	@Override
	public String toString() {
		return "{" + "\"cn\": \"" + cn +"\" , \"gidNumber\": \"" 
				+gidNumber+ "\"}";
	}

	public Set<String> getMembers() {
		return members;
	}

	public void setMembers(Set<String> members) {
		this.members = members;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public void addMember(String member) {
		if (this.members == null) {
			this.members = new HashSet<>();
		}
		members.add(member);
	}

	public void removeMember(String member) {
		members.remove(member);
	}

	public String getGidNumber() {
		return gidNumber;
	}

	public void setGidNumber(String gidNumber) {
		this.gidNumber = gidNumber;
	}

	@Attribute(name = "cn")
	private String cn;
	private Set<String> members;
	private String gidNumber;

}
