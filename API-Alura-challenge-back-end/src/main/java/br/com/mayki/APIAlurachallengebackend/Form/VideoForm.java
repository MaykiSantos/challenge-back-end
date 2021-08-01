package br.com.mayki.APIAlurachallengebackend.Form;

import java.util.NoSuchElementException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.mayki.APIAlurachallengebackend.Entidade.Categoria;
import br.com.mayki.APIAlurachallengebackend.Entidade.Video;
import br.com.mayki.APIAlurachallengebackend.Erros.ExceptionCampoInvalido;
import br.com.mayki.APIAlurachallengebackend.Repository.CategoriaRepository;
import br.com.mayki.APIAlurachallengebackend.Repository.VideoRepository;

public class VideoForm {

	@Size(max = 100) @NotBlank @NotNull
	private String titulo;
	@Size(max = 1000) @NotBlank
	private String descricao;
	@Size(max = 300) @NotBlank @NotNull
	private String url;
	private Long idCategoria;
	
	public VideoForm(String titulo, String descricao, String url, Long idCategoria) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
		this.idCategoria = idCategoria;
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
	
	

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Video paraVideo(VideoRepository videoRepository, CategoriaRepository categoriaRepository) throws ExceptionCampoInvalido {
		Categoria categoria = verificaCategoria(categoriaRepository);
		
		
		Video video = new Video(titulo, descricao, url, categoria);
		
		return videoRepository.save(video);
	}

	public void atualiza(Video v, CategoriaRepository c) throws ExceptionCampoInvalido {
		
		v.setTitulo(titulo);
		v.setDescricao(descricao);
		v.setUrl(url);
		v.setCategoria(verificaCategoria(c));
		
	}
	
	
	private Categoria verificaCategoria(CategoriaRepository categoriaRepository) throws ExceptionCampoInvalido {
		if(this.idCategoria == null) {
			return categoriaRepository.findById(1l).get();
		}else {
			try {
				return categoriaRepository.findById(idCategoria).get();
			} catch (NoSuchElementException e) {
				throw new ExceptionCampoInvalido("Valor recebido no campo idCategoria não é válido");
			}
		}
	}
	
	
	
}
