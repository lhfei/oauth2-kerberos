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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import cn.lhfei.auth.security.krb5.shell.ExecuteHandler;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created Apr 23, 2018
 */
@Controller
public class CommandController {

	private static final Logger LOG = LoggerFactory.getLogger(CommandController.class);

    @MessageMapping("/command")
    @SendTo("/topic/cmd")
    public TerminalConsole greeting(Terminal terminal) throws Exception {
        Thread.sleep(1000); // simulated delay
        
        StringBuilder sb = executeHandler.execute(terminal.getCommand());
        
        LOG.info(sb.toString());
        
        return new TerminalConsole(sb.toString());
    }

    @Autowired
    private ExecuteHandler executeHandler;
}
