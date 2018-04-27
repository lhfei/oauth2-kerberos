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

package cn.lhfei.auth.console.shell.console.shell;

import org.junit.Test;

import com.jcraft.jsch.JSchException;

import cn.lhfei.auth.console.shell.ExecuteHandler;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 23, 2018
 */

public class ExecuteHandlerTest {

	@Test
	public void exec() throws JSchException {
		String username = "root";
		String host = "openldap.lhfei.com";
		ExecuteHandler handler = new ExecuteHandler(username, host);
		
		String command = "kadmin -padmin/admin -wPolaris@Root#01 -q \"listprincs\"";
		command = "kadmin -padmin/admin -wPolaris@Root#01 -q 'getprinc kafka/openldap.lhfei.com@POLARIS.lhfei.COM'";
		command = "kadmin -padmin/admin -wPolaris@Root#01 -q 'addprinc -randkey lhfei/openldap.lhfei.com@POLARIS.lhfei.COM'";
		
		handler.execute(command);
		
		command = "kadmin -padmin/admin -wPolaris@Root#01 -q 'getprinc lhfei/openldap.lhfei.com@POLARIS.lhfei.COM'";
		handler.execute(command);
		
	}
	
	
}
