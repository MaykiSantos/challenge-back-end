package br.com.mayki.APIAlurachallengebackend.Controller;

import java.util.List;

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
import br.com.mayki.APIAlurachallengebackend.Erros.ExceptionNaoModificado;
import br.com.mayki.APIAlurachallengebackend.Erros.ExceptionRecursoNaoEncontrado;
import br.com.mayki.APIAlurachallengebackend.Form.CategoriaForm;
import br.com.mayki.APIAlurachallengebackend.Services.CategoriaService;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<CategoriaDto>> listar() {
		return categoriaService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> buscar(@PathVariable Long id) throws ExceptionRecursoNaoEncontrado {
		return categoriaService.buscar(id);
	}

	@GetMapping("/{id}/videos")
	public ResponseEntity<List<VideoDto>> buscarPorCategoria(@PathVariable Long id)
			throws ExceptionRecursoNaoEncontrado {
		return categoriaService.buscarPorCategoria(id);
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
