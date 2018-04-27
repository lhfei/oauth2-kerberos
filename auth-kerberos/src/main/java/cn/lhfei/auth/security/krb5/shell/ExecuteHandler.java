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

package cn.lhfei.auth.security.krb5.shell;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stringtemplate.v4.ST;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 23, 2018
 */
public class ExecuteHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ExecuteHandler.class);
	
	public ExecuteHandler(String username, String host) {
		this.username = username;
		this.host = host;
	}
	
	public ExecuteHandler(String username, String password, String host) {
		this.username = username;
		this.password = password;
		this.host = host;
	}
	
	public ExecuteHandler(String username, String host, int port) {
		this.username = username;
		this.host = host;
		this.port = port;
	}
	
	private UserInfo buildUserInfo() {
		return new UserInfo() {

			@Override
			public String getPassphrase() {
				return null;
			}

			@Override
			public String getPassword() {
				return password;
			}

			@Override
			public boolean promptPassword(String message) {
				return true;
			}

			@Override
			public boolean promptPassphrase(String message) {
				return true;
			}

			@Override
			public boolean promptYesNo(String message) {
				return true;
			}

			@Override
			public void showMessage(String message) {
				LOG.info("Message: {}", message);
			}
			
		};
	}
	
	public StringBuilder execute(String command) throws JSchException {
		StringBuilder builder = new StringBuilder("[root@openldap lhfei]#");
		builder.append("\r\n");
		command = buildCommand(command);
		
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(username, host, port);
			
			session.setUserInfo(buildUserInfo());
			
			session.connect();

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			// X Forwarding
			// channel.setXForwarding(true);

			// channel.setInputStream(System.in);
			channel.setInputStream(null);

			// channel.setOutputStream(System.out);

			// FileOutputStream fos=new FileOutputStream("/tmp/stderr");
			// ((ChannelExec)channel).setErrStream(fos);
			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();

			channel.connect();

			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					//System.out.print(new String(tmp, 0, i));
					builder.append("\r\n");
					builder.append(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					if (in.available() > 0)
						continue;
					LOG.info("exit-status: {}", channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
			session.disconnect();
			
			return builder;
		} catch (IOException e) {
			LOG.warn("Error: {}", e.getMessage(), e);
			
			builder.append("\r\n");
			builder.append(e.getMessage());
		}
		return builder;
	}
	
	private String buildCommand(String command) {
		ST kadmin = new ST("kadmin -padmin/admin -wPolaris@Root#01 -q '<cmd>'");
		kadmin.add("cmd", command);

		return kadmin.render();
	}
	
	private String username;
	private String host;
	private int port = 22;
	private String password;
}
