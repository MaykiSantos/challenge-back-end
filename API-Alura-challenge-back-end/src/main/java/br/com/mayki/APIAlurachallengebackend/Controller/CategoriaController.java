package br.com.mayki.APIAlurachallengebackend.Controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

@RestController
@RequestMapping("categorias")
public class CategoriaController {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	VideoRepository videoRepository;

	@GetMapping
	public ResponseEntity<List<CategoriaDto>> listar() {
		List<Categoria> lista = categoriaRepository.findAll();

		return ResponseEntity.ok(CategoriaDto.paraListaDto(lista));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> buscar(@PathVariable Long id) throws ExceptionRecursoNaoEncontrado {
		try {
			Categoria categoria = categoriaRepository.findById(id).get();
			return ResponseEntity.ok(CategoriaDto.paraDto(categoria));

		} catch (NoSuchElementException e) {
			throw new ExceptionRecursoNaoEncontrado("Não Encontrado");
		}
	}

	@GetMapping("/{id}/videos")
	public ResponseEntity<List<VideoDto>> buscarPorCategoria(@PathVariable Long id)
			throws ExceptionRecursoNaoEncontrado {
		List<Video> lista = videoRepository.findByCategoria_Id(id);
		if (lista.isEmpty()) {
			throw new ExceptionRecursoNaoEncontrado("Não Encontrado");
		}
		return ResponseEntity.ok(VideoDto.paraListaDto(lista));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDto> adicionar(@RequestBody @Valid CategoriaForm categoriaForm,
			UriComponentsBuilder uriComponents) {
		Categoria categoria = categoriaForm.paraCategoria(categoriaRepository);
		URI uri = uriComponents.path("/caregorias/{id}").build(categoria.getId());

		return ResponseEntity.created(uri).body(CategoriaDto.paraDto(categoria));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoriaDto> editar(@PathVariable Long id, @RequestBody CategoriaForm categoriaForm)
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

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id)
			throws ExceptionRecursoNaoEncontrado, ExceptionNaoModificado {

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
