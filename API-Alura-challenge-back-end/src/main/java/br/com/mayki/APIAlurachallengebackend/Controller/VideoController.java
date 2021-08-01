package br.com.mayki.APIAlurachallengebackend.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mayki.APIAlurachallengebackend.Dto.VideoDto;
import br.com.mayki.APIAlurachallengebackend.Erros.ExceptionCampoInvalido;
import br.com.mayki.APIAlurachallengebackend.Form.VideoForm;
import br.com.mayki.APIAlurachallengebackend.Services.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {
	
	@Autowired
	VideoService videoService;
	
	@GetMapping
	public ResponseEntity<List<VideoDto>> listar(@RequestParam(required = false) String search){
		return videoService.listar(search);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VideoDto> buscar(@PathVariable Long id) throws ExceptionCampoInvalido{
		return videoService.buscar(id);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<VideoDto> adicionar(@RequestBody @Valid VideoForm videoForm, UriComponentsBuilder uriComponents) throws ExceptionCampoInvalido{
		return videoService.adicionar(videoForm, uriComponents);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideoDto> editar(@PathVariable Long id, @RequestBody @Valid VideoForm videoForm) throws ExceptionCampoInvalido{
		return videoService.editar(id, videoForm);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) throws ExceptionCampoInvalido{
		return videoService.deletar(id);
	}

}
