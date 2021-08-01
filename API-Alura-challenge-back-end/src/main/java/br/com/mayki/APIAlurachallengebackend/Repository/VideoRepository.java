package br.com.mayki.APIAlurachallengebackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mayki.APIAlurachallengebackend.Entidade.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

	List<Video> findByCategoria_Id(Long id);

}
