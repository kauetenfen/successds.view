package br.com.progic.successds.view.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import br.com.progic.successds.view.dto.Pessoa;
import br.com.progic.successds.view.service.PessoaServiceView;

@Component(value="pessoaBean")
@RequestScope
public class PessoaBean {
	
	@Autowired
	PessoaServiceView pessoaService;
	
	private Pessoa pessoa;
	List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	
	@PostConstruct
	public void InicializarBean() {
		this.atualizarListaPessoas();
		pessoa = new Pessoa();
	}
	
	public void cadastrarPessoa() {
		FacesContext context = FacesContext.getCurrentInstance();
		pessoaService.cadastrarPessoa(pessoa);		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();		
		
		context.addMessage(null, new FacesMessage("Sucesso!",  "Olá, " + currentPrincipalName + ", o cadastro da pessoa '" + pessoa.getNome() + "' foi concluído!") );
		limparFormulario();
		atualizarListaPessoas();	
	}
	
	public void limparFormulario() {
		pessoa = new Pessoa();
	}
	
	public void atualizarListaPessoas() {
		listaPessoas = pessoaService.obterListaPessoas();
	}
	
	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public boolean existePessoaCadastrada() {
		return listaPessoas.size() > 0;
	}
	
}
