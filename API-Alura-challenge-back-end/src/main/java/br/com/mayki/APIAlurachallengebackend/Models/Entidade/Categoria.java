package br.com.mayki.APIAlurachallengebackend.Models.Entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Categoria {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30, nullable = false)
	private String titulo;
	@Column(length = 30, nullable = false)
	private String cor;
	@OneToMany(mappedBy = "categoria" ,fetch = FetchType.LAZY)
	private List<Video> video;
	
	public Categoria() {}
	
	public Categoria(String titulo, String cor) {
		this.titulo = titulo;
		this.cor = cor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public List<Video> getVideo() {
		return video;
	}

	public void setVideo(List<Video> video) {
		this.video = video;
	}
	
	
}
