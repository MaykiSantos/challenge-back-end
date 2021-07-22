package br.com.mayki.APIAlurachallengebackend.Form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.mayki.APIAlurachallengebackend.Entidade.Video;
import br.com.mayki.APIAlurachallengebackend.Repository.VideoRepository;

public class VideoForm {

	@Size(max = 100) @NotBlank
	private String titulo;
	@Size(max = 1000) @NotBlank
	private String descricao;
	@Size(max = 300) @NotBlank
	private String url;
	
	public VideoForm(String titulo, String descricao, String url) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Video paraVideo(VideoRepository videoRepository) {
		Video video = new Video(titulo, descricao, url);
		
		return videoRepository.save(video);
	}

	public void atualiza(Video v) {
		v.setTitulo(titulo);
		v.setDescricao(descricao);
		v.setUrl(url);
		
	}
	
	
	
}
