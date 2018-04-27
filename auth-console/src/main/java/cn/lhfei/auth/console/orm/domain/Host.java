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

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 18, 2018
 */
@Entity()
@Table(name = "CLUSTER_HOST")
public class Host extends AbstractDomain {
	private static final long serialVersionUID = -7080821107443939225L;

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public Long getClusterId() {
		return clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getIpv4() {
		return ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	public String getIpv6() {
		return ipv6;
	}

	public void setIpv6(String ipv6) {
		this.ipv6 = ipv6;
	}

	public String getCpuCount() {
		return cpuCount;
	}

	public void setCpuCount(String cpuCount) {
		this.cpuCount = cpuCount;
	}

	public String getCpuInfo() {
		return cpuInfo;
	}

	public void setCpuInfo(String cpuInfo) {
		this.cpuInfo = cpuInfo;
	}

	public Long getTotalMem() {
		return totalMem;
	}

	public void setTotalMem(Long totalMem) {
		this.totalMem = totalMem;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	/*public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}*/

	public Set<ServiceComponent> getComponents() {
		return components;
	}

	public void setComponents(Set<ServiceComponent> components) {
		this.components = components;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOST_ID")
	private Long hostId;

	@Column(name = "CLUSTER_ID")
	private Long clusterId;

	@Column(name = "HOST_NAME")
	private String hostName;

	@Column(name = "IPV4")
	private String ipv4;

	@Column(name = "IPV6")
	private String ipv6;

	@Column(name = "CPU_COUNT")
	private String cpuCount;

	@Column(name = "CPU_INFO")
	private String cpuInfo;
	
	@Column(name = "TOTAL_MEM")
	private Long totalMem;

	@Column(name = "OS_TYPE")
	private String osType;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLUSTER_ID", nullable = false)
	private Cluster cluster;*/
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "COMPONENT_HOST", joinColumns = { 
			@JoinColumn(name = "HOST_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "COMPONENT_ID", 
					nullable = false, updatable = false) })
	private Set<ServiceComponent> components;
}
