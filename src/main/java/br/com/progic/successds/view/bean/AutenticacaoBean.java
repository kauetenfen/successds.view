package br.com.progic.successds.view.bean;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class AutenticacaoBean {
	
	public String getNomeUsuarioLogado() {		
		return SecurityContextHolder.getContext().getAuthentication().getName();		
	}
	
	public String logout() {
		System.out.println("logouting");
		return "/logout";
	}

}
