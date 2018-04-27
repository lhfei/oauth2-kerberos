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

package cn.lhfei.auth.security.krb5.rest;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 23, 2018
 */
public class TerminalConsole {

	public TerminalConsole() {
	}

	public TerminalConsole(String out) {
		this.out = out;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}
	
	private String out;
}
