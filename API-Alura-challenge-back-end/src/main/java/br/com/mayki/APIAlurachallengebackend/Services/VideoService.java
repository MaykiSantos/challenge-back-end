package br.com.mayki.APIAlurachallengebackend.Services;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mayki.APIAlurachallengebackend.Models.Entidade.Video;
import br.com.mayki.APIAlurachallengebackend.Models.Repository.CategoriaRepository;
import br.com.mayki.APIAlurachallengebackend.Models.Repository.VideoRepository;
import br.com.mayki.APIAlurachallengebackend.Utilitarios.Erros.ExceptionCampoInvalido;
import br.com.mayki.APIAlurachallengebackend.Views.Dto.VideoDto;
import br.com.mayki.APIAlurachallengebackend.Views.Form.VideoForm;

@Service
public class VideoService {

	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public ResponseEntity<Page<VideoDto>> listar(String search, Pageable pageable) {
		Page<Video> lista = null;
		if(search == null) {
			lista = videoRepository.findAll(pageable);
		}else {
			Video video = new Video();
			video.setTitulo(search);
			
			ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
			
			lista = videoRepository.findAll(Example.of(video, matcher), pageable);
		}
		return ResponseEntity.ok(VideoDto.paraPageDto(lista));
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

	public ResponseEntity<List<VideoDto>> listarFree() {
		//Retorna os ultimos 10 videos cadastrados
		List<Video> listaFree = videoRepository.findFree(10);
		
		return ResponseEntity.ok(VideoDto.paraListaDto(listaFree));
	}
}
