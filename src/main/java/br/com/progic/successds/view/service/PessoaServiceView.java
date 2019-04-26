package br.com.progic.successds.view.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.progic.successds.view.dto.Pessoa;
import br.com.progic.successds.view.util.RestResponsePage;

@Service
public class PessoaServiceView {
	
	private static final String URL_SERVICE_PESSOAS = "http://localhost:8080/successds/private/pessoas";

	public List<Pessoa> obterListaPessoas() {
		 RestTemplate restTemplate = new RestTemplate();
		 restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("progic","12345"));
		 ParameterizedTypeReference<RestResponsePage<Pessoa>> responseType = new ParameterizedTypeReference<RestResponsePage<Pessoa>>() { };
		 ResponseEntity<RestResponsePage<Pessoa>> result = restTemplate.exchange(URL_SERVICE_PESSOAS, HttpMethod.GET, null, responseType);
		 List<Pessoa> searchResult = result.getBody().getContent();
		 return searchResult;
	}
	
	public void cadastrarPessoa(Pessoa pessoa) {
		 RestTemplate restTemplate = new RestTemplate();
		 HttpEntity<Pessoa> request = new HttpEntity<Pessoa>(pessoa);
		 restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("progic","12345"));
		 restTemplate.exchange(URL_SERVICE_PESSOAS, HttpMethod.POST, request, Pessoa.class);
	}
}
