package br.com.mayki.APIAlurachallengebackend.Utilitario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.mayki.APIAlurachallengebackend.Utilitario.Erros.ExceptionCampoInvalido;

@RestControllerAdvice
public class TrataErro {
	
	private Map<String, String> erros = new HashMap<String, String>();
	
	@Autowired
	MessageSource messageSource;
	
	
	@ExceptionHandler(ExceptionCampoInvalido.class)
	public ResponseEntity<?> trataExceptionCampoInvalido(ExceptionCampoInvalido e){
		this.erros = Map.of("erro", e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.erros);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> trataMethodArgumentNotValidException(MethodArgumentNotValidException e){
		
		List<FieldError> fieldErrors = e.getFieldErrors();
		fieldErrors.forEach(itemErro -> {
			this.erros.put(itemErro.getField(), messageSource.getMessage(itemErro, LocaleContextHolder.getLocale()));
		});
		System.out.println("tratei erro");
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(this.erros);
	}

}
