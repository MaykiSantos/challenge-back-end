package br.com.mayki.APIAlurachallengebackend.Services;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mayki.APIAlurachallengebackend.Dto.CategoriaDto;
import br.com.mayki.APIAlurachallengebackend.Dto.VideoDto;
import br.com.mayki.APIAlurachallengebackend.Entidade.Categoria;
import br.com.mayki.APIAlurachallengebackend.Entidade.Video;
import br.com.mayki.APIAlurachallengebackend.Erros.ExceptionNaoModificado;
import br.com.mayki.APIAlurachallengebackend.Erros.ExceptionRecursoNaoEncontrado;
import br.com.mayki.APIAlurachallengebackend.Form.CategoriaForm;
import br.com.mayki.APIAlurachallengebackend.Repository.CategoriaRepository;
import br.com.mayki.APIAlurachallengebackend.Repository.VideoRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	VideoRepository videoRepository;

	public ResponseEntity<List<CategoriaDto>> listar() {
		List<Categoria> lista = categoriaRepository.findAll();

		return ResponseEntity.ok(CategoriaDto.paraListaDto(lista));
	}

	public ResponseEntity<CategoriaDto> buscar(Long id) throws ExceptionRecursoNaoEncontrado {
		try {
			Categoria categoria = categoriaRepository.findById(id).get();
			return ResponseEntity.ok(CategoriaDto.paraDto(categoria));

		} catch (NoSuchElementException e) {
			throw new ExceptionRecursoNaoEncontrado("Não Encontrado");
		}
	}

	public ResponseEntity<List<VideoDto>> buscarPorCategoria(Long id) throws ExceptionRecursoNaoEncontrado {
		List<Video> lista = videoRepository.findByCategoria_Id(id);
		if (lista.isEmpty()) {
			throw new ExceptionRecursoNaoEncontrado("Não Encontrado");
		}
		return ResponseEntity.ok(VideoDto.paraListaDto(lista));
	}

	public ResponseEntity<CategoriaDto> adicionar(CategoriaForm categoriaForm,
			UriComponentsBuilder uriComponents) {
		Categoria categoria = categoriaForm.paraCategoria(categoriaRepository);
		URI uri = uriComponents.path("/caregorias/{id}").build(categoria.getId());

		return ResponseEntity.created(uri).body(CategoriaDto.paraDto(categoria));
	}

	public ResponseEntity<CategoriaDto> editar(Long id, CategoriaForm categoriaForm)
			throws ExceptionRecursoNaoEncontrado, ExceptionNaoModificado {
		if (id == 1) {
			throw new ExceptionNaoModificado("Recurso não pode ser Alterado pois faz parte das regras do sistema");
		}

		try {
			Categoria categoria = categoriaRepository.findById(id).get();
			categoriaForm.atualiza(categoria);

			return ResponseEntity.ok(CategoriaDto.paraDto(categoria));
		} catch (NoSuchElementException e) {
			throw new ExceptionRecursoNaoEncontrado("Não Encontrado");
		}
	}

	public ResponseEntity<?> deletar(Long id) throws ExceptionNaoModificado, ExceptionRecursoNaoEncontrado {
		if (id == 1) {
			throw new ExceptionNaoModificado("Recurso não pode ser deletado pois faz parte das regras do sistema");
		}

		try {
			Categoria categoria = categoriaRepository.findById(id).get();
			if (categoria.getVideo().size() != 0) {
				Categoria livre = categoriaRepository.findById(1l).get();
				List<Video> videos = categoria.getVideo();

				videos.forEach(v -> v.setCategoria(livre));
			}
			categoriaRepository.delete(categoria);

			return ResponseEntity.ok("Categoria excluida com sucesso");
		} catch (NoSuchElementException e) {
			throw new ExceptionRecursoNaoEncontrado("Recurso não Encontrado");
		}
	}
}
