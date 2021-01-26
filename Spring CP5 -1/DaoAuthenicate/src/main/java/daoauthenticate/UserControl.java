package daoauthenticate;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserControl {

	@RequestMapping("/")
	public String Welcome() {
		return "Welcome";
	}

	@RequestMapping("/testaop")
	public String TestAop(Principal principal) {
		return "Welcomeaop";
	}

	@RequestMapping("/login")
	public String login() {
			return "login";
	}

	@RequestMapping("/logout-success")
	public String logout() {
			return "logout";

	}
}
