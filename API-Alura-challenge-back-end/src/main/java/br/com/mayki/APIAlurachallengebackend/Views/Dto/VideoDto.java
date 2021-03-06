package br.com.mayki.APIAlurachallengebackend.Views.Dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.mayki.APIAlurachallengebackend.Models.Entidade.Video;

public class VideoDto {

	private Long id;
	private Long idCategoria;
	private String titulo;
	private String descricao;
	private String url;
	
	public VideoDto(Long id, String titulo, String descricao, String url, Long idCategoria) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
		this.idCategoria = idCategoria;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public Long getId() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getUrl() {
		return url;
	}

	public static List<VideoDto> paraListaDto(List<Video> lista) {
		List<VideoDto> listaConvertida = new ArrayList<VideoDto>();
		
		lista.forEach((Video v)->{
			listaConvertida.add(new VideoDto(v.getId(), v.getTitulo(), v.getDescricao(), v.getUrl(), v.getCategoria().getId()));
		});
		
		return listaConvertida;
	}

	public static VideoDto paraDto(Video v) {
		return new VideoDto(v.getId(), v.getTitulo(), v.getDescricao(), v.getUrl(), v.getCategoria().getId());
	}

	public static Page<VideoDto> paraPageDto(Page<Video> lista) {
		return lista.map(v -> new VideoDto(v.getId(), v.getTitulo(), v.getDescricao(), v.getUrl(), v.getCategoria().getId()));
	}
	
	
}
