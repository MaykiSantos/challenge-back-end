package br.com.mayki.APIAlurachallengebackend.Views.Dto;

public class AutenticacaoDto {

	private String token;
	private String tipo;
	
	public AutenticacaoDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

	public static AutenticacaoDto paraDto(String tokenGerado, String tipoGerado) {
		return new AutenticacaoDto(tokenGerado, tipoGerado);
	}
	
}
