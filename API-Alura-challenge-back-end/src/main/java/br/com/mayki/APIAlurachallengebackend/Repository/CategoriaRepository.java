package br.com.mayki.APIAlurachallengebackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mayki.APIAlurachallengebackend.Entidade.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
