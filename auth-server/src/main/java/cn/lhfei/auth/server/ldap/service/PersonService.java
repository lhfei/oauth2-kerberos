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

package cn.lhfei.auth.server.ldap.service;

import java.util.List;

import org.springframework.ldap.core.support.BaseLdapNameAware;

import cn.lhfei.auth.server.ldap.domain.Person;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 05, 2018
 */

public interface PersonService extends BaseLdapNameAware {

	void create(Person person) throws Exception;

	List<Person> findAll() throws Exception;

	Person findOne(String uid) throws Exception;

	List<Person> findByGroup(String gidNumber) throws Exception;

	List<Person> search(Person person) throws Exception;

	void update(Person p) throws Exception;

	void updateLastName(Person p) throws Exception;

	void delete(String uid) throws Exception;
}
