package br.com.mayki.APIAlurachallengebackend.Models.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mayki.APIAlurachallengebackend.Models.Entidade.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
