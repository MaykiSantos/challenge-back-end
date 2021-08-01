package br.com.mayki.APIAlurachallengebackend.Services;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mayki.APIAlurachallengebackend.Dto.VideoDto;
import br.com.mayki.APIAlurachallengebackend.Entidade.Video;
import br.com.mayki.APIAlurachallengebackend.Erros.ExceptionCampoInvalido;
import br.com.mayki.APIAlurachallengebackend.Form.VideoForm;
import br.com.mayki.APIAlurachallengebackend.Repository.CategoriaRepository;
import br.com.mayki.APIAlurachallengebackend.Repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public ResponseEntity<List<VideoDto>> listar(String search) {
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

	public ResponseEntity<VideoDto> buscar(Long id) throws ExceptionCampoInvalido {
		try {
			Video video = videoRepository.findById(id).get();
			return ResponseEntity.ok(VideoDto.paraDto(video));
		} catch (NoSuchElementException e) {
			throw new ExceptionCampoInvalido("Não encontrado.");
		}
	}

	public ResponseEntity<VideoDto> adicionar(VideoForm videoForm, UriComponentsBuilder uriComponents) throws ExceptionCampoInvalido {
		Video novoVideo = videoForm.paraVideo(videoRepository, categoriaRepository);
		URI uri = uriComponents.path("/videos/{id}").build(novoVideo.getId());
		return ResponseEntity.created(uri).body(VideoDto.paraDto(novoVideo));
	}

	public ResponseEntity<VideoDto> editar(Long id, VideoForm videoForm) throws ExceptionCampoInvalido {
		try {
			Video video = videoRepository.findById(id).get();
			videoForm.atualiza(video,categoriaRepository);
			
			return ResponseEntity.ok(VideoDto.paraDto(video));
		} catch (NoSuchElementException  e) {
			throw new ExceptionCampoInvalido("id do vídeo é inválida");
		}
	}

	public ResponseEntity<?> deletar(Long id) throws ExceptionCampoInvalido {
		try {
			Video video = videoRepository.findById(id).get();
			videoRepository.delete(video);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (NoSuchElementException e) {
			throw new ExceptionCampoInvalido("item não encontrado");
		}
	}
}
