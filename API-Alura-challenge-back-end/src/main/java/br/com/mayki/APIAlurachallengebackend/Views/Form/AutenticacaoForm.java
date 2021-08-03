package br.com.mayki.APIAlurachallengebackend.Views.Form;

import javax.validation.constraints.NotNull;

public class AutenticacaoForm {

	@NotNull
	private String email;
	@NotNull
	private String senha;
	
	public AutenticacaoForm(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
}
