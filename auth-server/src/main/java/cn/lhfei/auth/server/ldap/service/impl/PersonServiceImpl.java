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

import java.util.List;

import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.stringtemplate.v4.ST;

import cn.lhfei.auth.server.ldap.domain.Person;
import cn.lhfei.auth.server.ldap.service.PersonService;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 05, 2018
 */
@Service("personService")
public class PersonServiceImpl extends LdapBaseService implements PersonService {

	private Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Override
	public void setBaseLdapPath(LdapName baseLdapPath) {
		this.baseLdapPath = baseLdapPath;
	}

	@Override
	public void create(Person person) {
		Name dn = buildDn(person.getUid());
		LOG.info("RDNs: {}", dn.toString());

		ldapTemplate.bind(dn, null, buildAttributes(person));
	}

	@Override
	public List<Person> findAll() {
		EqualsFilter filter = new EqualsFilter("objectClass", "account");
		return ldapTemplate.search(LdapUtils.emptyLdapName(), filter.encode(), new PersonContextMapper());
	}

	@Override
	public Person findOne(String uid) {
		Name dn = LdapNameBuilder.newInstance().add("ou", "People").add("uid", uid).build();
		return ldapTemplate.lookup(dn, new PersonContextMapper());
	}

	@Override
	public List<Person> findByGroup(String gidNumber) {
		LdapQuery q = query().where("objectclass").is("account").and("gidNumber").is(gidNumber);
		return ldapTemplate.search(q, new PersonContextMapper());
	}
	
	@Override
	public List<Person> search(Person person) {
		if (null == person || 
				(StringUtils.isEmpty(person.getCn()) && StringUtils.isEmpty(person.getUid()))) {
			
			return findAll();
		}
		
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectClass", "account"));
		
		ST filters = new ST("(&(objectClass=account)(|<cn><uid>))");
		String cn = StringUtils.trimAllWhitespace(person.getCn()); 
		String uid = StringUtils.trimAllWhitespace(person.getUid());
		cn = StringUtils.isEmpty(cn) ? "" : cn;
		uid = StringUtils.isEmpty(uid) ? "" : uid;
		
		cn = cn.replaceAll("\\W", "");
		uid = uid.replaceAll("\\W", "");
		
		// append 'cn' filter
		if(StringUtils.isEmpty(cn)) {
			filters.add("cn", "");
		}else {
			filters.add("cn", "(cn=*" + cn + "*)");
		}
		
		// append 'uid' filter
		if(StringUtils.isEmpty(uid)) {
			filters.add("uid", "");
		}else {
			filters.add("uid", "(|(uid=*" + uid + "*))");
		}
		
		LOG.info(MARKER, "Search Filter: {}", filters.render());
		
		return ldapTemplate.search(getPersonBaseDn(), filters.render(), new PersonContextMapper());
		
		//return ldapTemplate.search(q, new PersonContextMapper());
	}

	@Override
	public void update(Person p) {
		ldapTemplate.rebind(buildDn(p.getUid()), null, buildAttributes(p));
	}

	@Override
	public void updateLastName(Person p) {
		Attribute attr = new BasicAttribute("cn", p.getCn());
		ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
		ldapTemplate.modifyAttributes(buildDn(p.getUid()), new ModificationItem[] { item });
	}

	@Override
	public void delete(String uid) {
		ldapTemplate.unbind(buildDn(uid));
	}

	public LdapName getBaseLdapPath() {
		return baseLdapPath;
	}
	
	private Attributes buildAttributes(Person p) {
		Attributes attrs = new BasicAttributes();
		BasicAttribute ocAttr = new BasicAttribute("objectclass");
		ocAttr.add("top");
		ocAttr.add("account");
		ocAttr.add("posixAccount");
		ocAttr.add("shadowAccount");
		ocAttr.add("extensibleObject");
		
		String gidNumber = "1000";	// normal group
		if(!StringUtils.isEmpty(p.getGidNumber())){
			gidNumber = p.getGidNumber();
		}
		// required attributes
		attrs.put(ocAttr);
		attrs.put("cn", p.getCn());
		attrs.put("uid", p.getUid());
		attrs.put("uidNumber", p.getUidNumber());
		attrs.put("gidNumber", gidNumber);
		attrs.put("homeDirectory", "/home/" + p.getUid());
		attrs.put("userPassword", p.getUserPassword());

		// optional attributes
		attrs.put("mail", p.getEmail());
		attrs.put("mobile", p.getMobile());
		attrs.put("description", p.getDescription());

		return attrs;
	}
	
	private class PersonContextMapper extends AbstractContextMapper<Person> {
		public Person doMapFromContext(DirContextOperations ctx) {
			Person p = new Person();
			p.setCn(ctx.getStringAttribute("cn"));
			p.setUid(ctx.getStringAttribute("uid"));
			p.setUidNumber(ctx.getStringAttribute("uidNumber"));
			p.setGidNumber(ctx.getStringAttribute("gidNumber"));
			p.setEmail(ctx.getStringAttribute("mail"));
			p.setMobile(ctx.getStringAttribute("mobile"));
			p.setDescription(ctx.getStringAttribute("description"));
			return p;
		}
	}
	

	@Autowired
	private LdapTemplate ldapTemplate;
	private LdapName baseLdapPath;
}
