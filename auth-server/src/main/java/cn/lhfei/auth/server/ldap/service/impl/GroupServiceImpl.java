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

package cn.lhfei.auth.server.ldap.service.impl;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.LdapName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.stringtemplate.v4.ST;

import cn.lhfei.auth.server.ldap.domain.Group;
import cn.lhfei.auth.server.ldap.service.GroupService;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 06, 2018
 */
@Service("groupService")
public class GroupServiceImpl extends LdapBaseService implements GroupService {
	
	@Override
	public void create(Group group) {
		Name dn = buildGroupDn(group.getCn());
		LOG.info("RDNs: {}", dn.toString());

		ldapTemplate.bind(dn, null, buildAttributes(group));
	}
	
	@Override
	public Group findOne(String cn) {
		return ldapTemplate.lookup(
				buildGroupDn(cn), 
				new String[] {"cn", "gidNumber", "memberUid", "modifyTimestamp", "createTimestamp"}, 
				new GroupContextMapper());
	}

	@Override
    public void setBaseLdapPath(LdapName baseLdapPath) {
        this.baseLdapPath = baseLdapPath;
    }

	@Override
    public List<Group> findAll(){
        return ldapTemplate.search(
                query().where("objectclass").is("posixGroup"),
                new GroupContextMapper());
    }
	
	@Override
	public List<Group> search(Group group) throws Exception {
		if (null == group || 
				(StringUtils.isEmpty(group.getCn()) && StringUtils.isEmpty(group.getGidNumber()))) {
			
			return findAll();
		}

		ST filters = new ST("(&(objectClass=posixGroup)(&<cn><uid>))");
		String cn = StringUtils.trimAllWhitespace(group.getCn()); 
		String gid = StringUtils.trimAllWhitespace(group.getGidNumber());
		cn = StringUtils.isEmpty(cn) ? "" : cn;
		gid = StringUtils.isEmpty(gid) ? "" : gid;
		
		cn = cn.replaceAll("\\W", "");
		gid = gid.replaceAll("\\W", "");
		
		// append 'cn' filter
		if(StringUtils.isEmpty(cn)) {
			filters.add("cn", "");
		}else {
			filters.add("cn", "(cn=*" + cn + "*)");
		}
		
		// append 'gidNumber' filter
		if(StringUtils.isEmpty(gid)) {
			filters.add("uid", "");
		}else {
			filters.add("uid", "(|(gidNumber=*" + gid + "*))");
		}
		
		LOG.info(MARKER, "Search Filter: {}", filters.render());
		
		return ldapTemplate.search(getGroupDn(), filters.render(), new GroupContextMapper());
	}

	@Override
	public void delete(String cn) {
		ldapTemplate.unbind(buildGroupDn(cn));
	}
	
	@Override
    public void addMember2Group(String groupName, String uid) {
        Name groupDn = buildGroupDn(groupName);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.addAttributeValue("memberUid", uid);

        ldapTemplate.modifyAttributes(ctx);
    }

	@Override
    public void removeMemberFromGroup(String groupName, String uid) {
        Name groupDn = buildGroupDn(groupName);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.removeAttributeValue("memberUid", uid);

        ldapTemplate.modifyAttributes(ctx);
    }
	
	private Attributes buildAttributes(Group group) {
		Attributes attrs = new BasicAttributes();
		BasicAttribute ocAttr = new BasicAttribute("objectclass");
		ocAttr.add("top");
		ocAttr.add("posixGroup");
		
		String gidNumber = "1000";	// normal group
		if(!StringUtils.isEmpty(group.getGidNumber())){
			gidNumber = group.getGidNumber();
		}
		// required attributes
		attrs.put(ocAttr);
		attrs.put("cn", group.getCn());
		attrs.put("gidNumber", gidNumber);

		// optional attributes
		attrs.put("description", group.getDescription());

		return attrs;
	}

    private static class GroupContextMapper extends AbstractContextMapper<Group> {
    	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
        public Group doMapFromContext(DirContextOperations context) {
            Group group = new Group();
            group.setCn(context.getStringAttribute("cn"));
            group.setGidNumber(context.getStringAttribute("gidNumber"));
            group.setDescription(context.getStringAttribute("description"));
            String[] memberIds = context.getStringAttributes("memberUid");
            if(null != memberIds) {
            	Set<String> members = new HashSet<String>();
            	Collections.addAll(members, memberIds);
            	group.setMembers(members);
            }
            
			try { // read internal attributes
				Date create;
				Date modify;
				SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
				String cs = context.getStringAttribute("createTimestamp");
				String ms = context.getStringAttribute("modifyTimestamp");
				if(null != cs) {
					create = sf.parse(cs);
					group.setCreateTime(create);
				}
				if(null != ms) {
					modify = sf.parse(ms);
					group.setModifyTime(modify);
				}
			} catch (ParseException e) {
				LOG.warn(e.getMessage(), e);
			}
            
            return group;
        }
    }
    
	public LdapName getBaseLdapPath() {
		return baseLdapPath;
	}
	
	@Autowired
    private LdapTemplate ldapTemplate;
    private LdapName baseLdapPath;

}
