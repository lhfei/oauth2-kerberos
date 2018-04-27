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
@Table(name = "CLUSTER_SERVICE")
public class ClusterService extends AbstractDomain {
	private static final long serialVersionUID = -7029510247730272096L;

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	/*public Long getClusterId() {
		return clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}*/

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/*public Set<ServiceComponent> getComponents() {
		return components;
	}

	public void setComponents(Set<ServiceComponent> components) {
		this.components = components;
	}*/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SERVICE_ID")
	private Long serviceId;

	/*@Column(name = "CLUSTER_ID")
	private Long clusterId;*/

	@Column(name = "SERVICE_NAME")
	private String serviceName;

	@Column(name = "SERVICE_VERSION")
	private String version;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
	private Set<ServiceComponent> components;*/

}
