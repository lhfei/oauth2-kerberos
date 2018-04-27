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

package cn.lhfei.auth.console.orm.domain;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 05, 2018
 */

public class Person extends AbstractDomain {
	private static final long serialVersionUID = 7715693699363290881L;

	@Override
	public String toString() {
		return "{" + "\"cn\": \"" + cn + "\"" + ", \"uidNumber\": \"" + uidNumber + "\"" + ", \"email\": \"" + email
				+ "\"" + ", \"mobile\":" + mobile + "}";
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getUidNumber() {
		return uidNumber;
	}

	public void setUidNumber(String uidNumber) {
		this.uidNumber = uidNumber;
	}

	public String getGidNumber() {
		return gidNumber;
	}

	public void setGidNumber(String gidNumber) {
		this.gidNumber = gidNumber;
	}

	public String getHomeDirectory() {
		return homeDirectory;
	}

	public void setHomeDirectory(String homeDirectory) {
		this.homeDirectory = homeDirectory;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private String uid;
	private String cn;
	private String dn;
	private String uidNumber;
	private String gidNumber;
	private String homeDirectory;
	private String userPassword;

	// optional attributes
	private String email;
	private String mobile;

}
