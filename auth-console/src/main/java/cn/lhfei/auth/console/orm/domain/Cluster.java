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

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 18, 2018
 */
@Entity()
@Table(name = "CLUSTER_INSTANCE", uniqueConstraints = {@UniqueConstraint(columnNames = "CLUSTER_NAME")})
public class Cluster extends AbstractDomain {
	private static final long serialVersionUID = 2869329818400279517L;

	public Long getClusterId() {
		return clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getClusterInfo() {
		return clusterInfo;
	}

	public void setClusterInfo(String clusterInfo) {
		this.clusterInfo = clusterInfo;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getSecurityType() {
		return securityType;
	}

	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}

	public Set<ClusterService> getServices() {
		return services;
	}

	public void setServices(Set<ClusterService> services) {
		this.services = services;
	}

	/*public Set<Host> getHosts() {
		return hosts;
	}

	public void setHosts(Set<Host> hosts) {
		this.hosts = hosts;
	}*/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLUSTER_ID")
	private Long clusterId;
	
	@Column(name = "UID")
	private String uid;
	
	@Column(name = "RESOURCE_ID")
	private Long resourceId;
	
	@Column(name = "CLUSTER_INFO")
	private String clusterInfo;
	
	@Column(name = "CLUSTER_NAME")
	private String clusterName;
	
	@Column(name = "SECURITY_TYPE")
	private String securityType;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "CLUSTER_SERVICES", joinColumns = { 
			@JoinColumn(name = "CLUSTER_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "SERVICE_ID", 
					nullable = false, updatable = false) })
	private Set<ClusterService> services;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "cluster")
	private Set<Host> hosts;*/
}
