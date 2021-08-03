package br.com.mayki.APIAlurachallengebackend.Controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.mayki.APIAlurachallengebackend.Services.CategoriaService;
import br.com.mayki.APIAlurachallengebackend.Utilitarios.Erros.ExceptionNaoModificado;
import br.com.mayki.APIAlurachallengebackend.Utilitarios.Erros.ExceptionRecursoNaoEncontrado;
import br.com.mayki.APIAlurachallengebackend.Views.Dto.CategoriaDto;
import br.com.mayki.APIAlurachallengebackend.Views.Dto.VideoDto;
import br.com.mayki.APIAlurachallengebackend.Views.Form.CategoriaForm;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<Page<CategoriaDto>> listar(@PageableDefault(page = 0, size=5) Pageable pageable) {
		return categoriaService.listar(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> buscar(@PathVariable Long id) throws ExceptionRecursoNaoEncontrado {
		return categoriaService.buscar(id);
	}

	@GetMapping("/{id}/videos")
	public ResponseEntity<Page<VideoDto>> buscarPorCategoria(@PathVariable Long id, @PageableDefault(page = 0, size=5) Pageable pageable)
			throws ExceptionRecursoNaoEncontrado {
		return categoriaService.buscarPorCategoria(id, pageable);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDto> adicionar(@RequestBody @Valid CategoriaForm categoriaForm,
			UriComponentsBuilder uriComponents) {
		return categoriaService.adicionar(categoriaForm, uriComponents);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoriaDto> editar(@PathVariable Long id,
			@RequestBody @Valid CategoriaForm categoriaForm)
			throws ExceptionRecursoNaoEncontrado, ExceptionNaoModificado {
		return categoriaService.editar(id, categoriaForm);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id)
			throws ExceptionRecursoNaoEncontrado, ExceptionNaoModificado {

		return categoriaService.deletar(id);
	}

}
