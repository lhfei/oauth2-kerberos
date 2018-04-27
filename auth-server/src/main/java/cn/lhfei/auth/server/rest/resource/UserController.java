package cn.lhfei.auth.server.rest.resource;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/whoami")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
    
    @RequestMapping(value = {"/login", "/login.html"})
    public String login() {
    	return "login";
    }
}
