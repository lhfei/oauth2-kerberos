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
 * @Created Apr 18, 2018
 */
@Entity
@Table(name = "SERVICE_COMPONENT")
public class ServiceComponent extends AbstractDomain {
	private static final long serialVersionUID = -7029510247730272096L;

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	/*public ClusterService getService() {
		return service;
	}

	public void setService(ClusterService service) {
		this.service = service;
	}*/

	/*public Set<Host> getHosts() {
		return hosts;
	}

	public void setHosts(Set<Host> hosts) {
		this.hosts = hosts;
	}*/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMPONENT_ID")
	private Long componentId;

	@Column(name = "COMPONENT_NAME")
	private String componentName;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_ID", nullable = false)
	private ClusterService service;*/
	
	/*@ManyToMany(fetch = FetchType.LAZY, mappedBy = "components")
	private Set<Host> hosts = new HashSet<Host>();*/

}
