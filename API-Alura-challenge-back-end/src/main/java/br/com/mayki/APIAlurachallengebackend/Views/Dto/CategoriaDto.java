package br.com.mayki.APIAlurachallengebackend.Views.Dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.mayki.APIAlurachallengebackend.Models.Entidade.Categoria;

public class CategoriaDto {

	private Long id;
	private String titulo;
	private String cor;

	public CategoriaDto(Long id, String titulo, String cor) {
		this.id = id;
		this.titulo = titulo;
		this.cor = cor;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCor() {
		return cor;
	}

	public static List<CategoriaDto> paraListaDto(List<Categoria> lista) {
		return lista.stream().map(c -> new CategoriaDto(c.getId(), c.getTitulo(), c.getCor()))
				.collect(Collectors.toList());
	}

	public static CategoriaDto paraDto(Categoria c) {
		return new CategoriaDto(c.getId(), c.getTitulo(), c.getCor());
	}

}
