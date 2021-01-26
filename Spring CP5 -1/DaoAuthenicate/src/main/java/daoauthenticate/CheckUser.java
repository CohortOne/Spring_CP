package daoauthenticate;

import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CheckUser {

	private Object principal;
	private String user;

	@Around("execution(public String TestAop(..))")
	public String checkLoggedUser(ProceedingJoinPoint joinpoint) throws Throwable {

		principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		if (principal instanceof UserDetails) {
			user = ((UserDetails) principal).getUsername();
		} else {
			user = principal.toString();
		}

		if (user.equals("sridhar")) {
		//	System.out.println("success");
			return "redirect:/error.html";
		}
		else {
		//	System.out.println("not success");
			return (String)joinpoint.proceed();
		}
	}

}
