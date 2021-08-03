package br.com.mayki.APIAlurachallengebackend.Services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mayki.APIAlurachallengebackend.Models.Repository.UsuarioRepository;
import br.com.mayki.APIAlurachallengebackend.Utilitarios.Erros.ExceptionRecursoNaoEncontrado;
import br.com.mayki.APIAlurachallengebackend.Views.Dto.AutenticacaoDto;
import br.com.mayki.APIAlurachallengebackend.Views.Form.AutenticacaoForm;

@Service
public class AutenticacaoUsuarioService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TokenService tokenService;

	@Autowired
	AuthenticationManager authManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			return usuarioRepository.findByemail(username).get();
		} catch (NoSuchElementException e) {
			throw new UsernameNotFoundException("erro ao validar usuario");
		}
	}

	public ResponseEntity<AutenticacaoDto> geraToken(AutenticacaoForm form) throws ExceptionRecursoNaoEncontrado {
		try {
			UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(form.getEmail(),
					form.getSenha());
			Authentication authentication = authManager.authenticate(dadosLogin);

			String token = tokenService.criaToken(usuarioRepository.findByemail(form.getEmail()).get());

			return ResponseEntity.ok(AutenticacaoDto.paraDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			throw new ExceptionRecursoNaoEncontrado("Usuario ou senha inválidos");
		}
	}

}
