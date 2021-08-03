package br.com.mayki.APIAlurachallengebackend.Models.Repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mayki.APIAlurachallengebackend.Models.Entidade.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

	Page<Video> findByCategoria_Id(Long id, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM video v ORDER BY v.id DESC LIMIT :q")
	List<Video> findFree(@Param("q") Integer quantidade);


}
