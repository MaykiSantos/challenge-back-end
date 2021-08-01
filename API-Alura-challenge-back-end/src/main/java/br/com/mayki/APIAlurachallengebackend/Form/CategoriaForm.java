package br.com.mayki.APIAlurachallengebackend.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.mayki.APIAlurachallengebackend.Entidade.Categoria;
import br.com.mayki.APIAlurachallengebackend.Repository.CategoriaRepository;

public class CategoriaForm {
	
	@NotNull @Size(min = 6, max = 30)
	private String titulo;
	@Pattern(regexp = "^#[a-fA-f0-9]{6}")
	private String cor;
	
	public CategoriaForm(String titulo, String cor) {
		this.titulo = titulo;
		this.cor = cor;
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

	public Categoria paraCategoria(CategoriaRepository categoriaRepository) {
		Categoria categoria = new Categoria(this.titulo, this.cor);
		
		return categoriaRepository.save(categoria);
	}

	public void atualiza(Categoria c) {
		c.setTitulo(titulo);
		c.setCor(cor);
	}
	
	

}
