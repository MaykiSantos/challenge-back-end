package br.com.mayki.APIAlurachallengebackend.Models.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mayki.APIAlurachallengebackend.Models.Entidade.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
