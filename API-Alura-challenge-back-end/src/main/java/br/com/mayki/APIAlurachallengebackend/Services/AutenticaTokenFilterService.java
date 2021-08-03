package br.com.mayki.APIAlurachallengebackend.Services;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.mayki.APIAlurachallengebackend.Models.Entidade.Usuario;
import br.com.mayki.APIAlurachallengebackend.Models.Repository.UsuarioRepository;

@Service
public class AutenticaTokenFilterService extends OncePerRequestFilter{
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//verifica se o token existe;
		//recupera token do cabeçario
		//validar token
		String token = recuperaToken(request);
		boolean validaToken = tokenService.validaToken(token);
		
		if(validaToken) {
			//permite acesso do usuario no spring
			Long idUsuario = tokenService.getIdUsuario(token);
			Usuario usuario = usuarioRepository.findById(idUsuario).get();
			
			UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(autenticacao);
		}
		
		
		filterChain.doFilter(request, response);
	}

	private String recuperaToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
			return null;
		}
		return header.substring(7, header.length());
	}

	
}
