package br.com.progic.successds.view.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = -4985404433325256535L;

	public Pessoa() {
	}
	
	public Pessoa(String nome, Date dataNascimento, String sexo) {
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
	}
	
	private Long codigo;
	private String nome;
	private Date dataNascimento;
	private String sexo;
	private String signo;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSigno() {
		return signo;
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
