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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 23, 2018
 */
@Entity
@Table(name = "KERBEROS_PRINCIPAL")
public class KPrincipal extends AbstractDomain {
	private static final long serialVersionUID = -7450756243980307514L;
	
	public Long getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(Long principalId) {
		this.principalId = principalId;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getPrincipalPath() {
		return principalPath;
	}

	public void setPrincipalPath(String principalPath) {
		this.principalPath = principalPath;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRINCIPAL_ID")
	private Long principalId;
	
	@Column(name = "PRINCIPAL_NAME")
	private String principalName;
	
	@Column(name = "PRINCIPAL_PATH")
	private String principalPath;
}
