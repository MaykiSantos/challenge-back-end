package br.com.mayki.APIAlurachallengebackend.Services;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import br.com.mayki.APIAlurachallengebackend.Models.Entidade.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;

@Service
public class TokenService {

	@Value("${aplicacao.senha}")
	private String chaveSecretaAplicacao;
	@Value("${aplicacao.tempo.duracaoToken}")
	private Long tempoValidateToken;

	public String criaToken(Usuario usuario) {
		Date agora = new Date();
		Date dataExpiracao = new Date(agora.getTime() + tempoValidateToken);

		String token = Jwts.builder()
				.setIssuer("API_Alura_Challenge")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(agora)
				.setExpiration(dataExpiracao)
				.signWith(Keys.hmacShaKeyFor(chaveSecretaAplicacao.getBytes(StandardCharsets.UTF_8)))
				.compact();

		return token;
	}

	public boolean validaToken(String token) {
		try {
			Jws<Claims> claimsJws = Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(chaveSecretaAplicacao.getBytes(StandardCharsets.UTF_8)))
					.build()
					.parseClaimsJws(token);
			
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}

	public Long getIdUsuario(String token) {
		Jws<Claims> claimsJws = Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(chaveSecretaAplicacao.getBytes(StandardCharsets.UTF_8)))
				.build()
				.parseClaimsJws(token);
		return Long.parseLong(claimsJws.getBody().getSubject());
	}

}
