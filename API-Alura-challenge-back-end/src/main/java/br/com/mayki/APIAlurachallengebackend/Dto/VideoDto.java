package br.com.mayki.APIAlurachallengebackend.Dto;

import java.util.ArrayList;
import java.util.List;

import br.com.mayki.APIAlurachallengebackend.Entidade.Video;

public class VideoDto {

	private Long id;
	private String titulo;
	private String descricao;
	private String url;
	
	public VideoDto(Long id, String titulo, String descricao, String url) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
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
			listaConvertida.add(new VideoDto(v.getId(), v.getTitulo(), v.getDescricao(), v.getUrl()));
		});
		
		return listaConvertida;
	}

	public static VideoDto paraDto(Video v) {
		return new VideoDto(v.getId(), v.getTitulo(), v.getDescricao(), v.getUrl());
	}
	
	
}
