package br.com.mayki.APIAlurachallengebackend.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
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

import br.com.mayki.APIAlurachallengebackend.Dto.VideoDto;
import br.com.mayki.APIAlurachallengebackend.Entidade.Video;
import br.com.mayki.APIAlurachallengebackend.Erros.ExceptionCampoInvalido;
import br.com.mayki.APIAlurachallengebackend.Form.VideoForm;
import br.com.mayki.APIAlurachallengebackend.Repository.CategoriaRepository;
import br.com.mayki.APIAlurachallengebackend.Repository.VideoRepository;

@RestController
@RequestMapping("/videos")
public class VideoController {
	
	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<VideoDto>> listar(@RequestParam(required = false) String search){
		List<Video> lista = null;
		if(search == null) {
			lista = videoRepository.findAll();
		}else {
			Video video = new Video();
			video.setTitulo(search);
			
			ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
			
			lista = videoRepository.findAll(Example.of(video, matcher));
		}
		return ResponseEntity.ok(VideoDto.paraListaDto(lista));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VideoDto> buscar(@PathVariable Long id) throws ExceptionCampoInvalido{
		try {
			Video video = videoRepository.findById(id).get();
			return ResponseEntity.ok(VideoDto.paraDto(video));
		} catch (NoSuchElementException e) {
			throw new ExceptionCampoInvalido("Não encontrado.");
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<VideoDto> adicionar(@RequestBody @Valid VideoForm videoForm) throws ExceptionCampoInvalido{
		Video novoVideo = videoForm.paraVideo(videoRepository, categoriaRepository);
		return ResponseEntity.ok(VideoDto.paraDto(novoVideo));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideoDto> editar(@PathVariable Long id, @RequestBody @Valid VideoForm videoForm) throws ExceptionCampoInvalido{
		try {
			Video video = videoRepository.findById(id).get();
			videoForm.atualiza(video,categoriaRepository);
			
			return ResponseEntity.ok(VideoDto.paraDto(video));
		} catch (NoSuchElementException  e) {
			throw new ExceptionCampoInvalido("id do vídeo é inválida");
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) throws ExceptionCampoInvalido{
		try {
			Video video = videoRepository.findById(id).get();
			videoRepository.delete(video);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (NoSuchElementException e) {
			throw new ExceptionCampoInvalido("item não encontrado");
		}
	}

}
