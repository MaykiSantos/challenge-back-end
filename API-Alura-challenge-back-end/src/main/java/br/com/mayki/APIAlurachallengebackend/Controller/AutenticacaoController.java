package br.com.mayki.APIAlurachallengebackend.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mayki.APIAlurachallengebackend.Services.AutenticacaoUsuarioService;
import br.com.mayki.APIAlurachallengebackend.Utilitarios.Erros.ExceptionRecursoNaoEncontrado;
import br.com.mayki.APIAlurachallengebackend.Views.Dto.AutenticacaoDto;
import br.com.mayki.APIAlurachallengebackend.Views.Form.AutenticacaoForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	AutenticacaoUsuarioService autenticacaoService;
	
	@PostMapping
	public ResponseEntity<AutenticacaoDto> geraToken(@RequestBody @Valid AutenticacaoForm form) throws ExceptionRecursoNaoEncontrado{
		return autenticacaoService.geraToken(form);
	}
}
