package br.com.mayki.APIAlurachallengebackend.Controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoriaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveriaRetornar200AoTrazerTodasAsCategoriasCadastradas() throws Exception {
		URI uri = new URI("/categorias");

		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void deveriaRetornar200AoTrazarUmaCategoria() throws Exception {
		URI uri = new URI("/categorias/1");

		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void deveriaRetornar201AoCriarUmaNovaCategoria() throws Exception {
		URI uri = new URI("/categorias");

		mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("{\r\n    \"titulo\": \"terror\",\r\n    \"cor\": \"#D13817\"\r\n}"))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void deveriaRetornar404AoTentarDeletarCategariaPrincipal() throws Exception {
		URI uri = new URI("/categorias/1");

		mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void deveriaRetornar200AoAtualizarUmaCategoria() throws Exception {
		URI uri = new URI("/categorias/3");

		mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("{\r\n    \"titulo\": \"terror\",\r\n    \"cor\": \"#D13817\"\r\n}"))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void deveriaRetornar200AoDeletarCategoria() throws Exception {
		URI uri = new URI("/categorias/2");

		mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void deveriaRetornar404AoDeletarCategoriaInesistente() throws Exception {
		URI uri = new URI("/categorias/22");

		mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void deveriaRetornar200AoTrazarTodosOsVideosDeUmaCategoriaEspecifica() throws Exception {
		URI uri = new URI("/categorias/1/videos");

		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void deveriaRetornar404AoTrazarTodosOsVideosDeUmaCategoriaNãoExistente() throws Exception {
		URI uri = new URI("/categorias/10/videos");

		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void deveriaRetornar406AoAtualizarUmaCategoriaComTituloInvalido() throws Exception {
		URI uri = new URI("/categorias/3");

		mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "    \"titulo\": \"\",\r\n" + "    \"cor\": \"#D13817\"\r\n" + "}"))
				.andExpect(MockMvcResultMatchers.status().is(406));
	}

	@Test
	public void deveriaRetornar406AoAtualizarUmaCategoriaComCorInvalido() throws Exception {
		URI uri = new URI("/categorias/3");

		mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "    \"titulo\": \"terror\",\r\n" + "    \"cor\": \"#D13\"\r\n" + "}"))
				.andExpect(MockMvcResultMatchers.status().is(406));
	}

}
