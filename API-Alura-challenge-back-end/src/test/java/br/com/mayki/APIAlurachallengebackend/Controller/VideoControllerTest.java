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
class VideoControllerTest {
	
	@Autowired
	private MockMvc mock;

	@Test
	public void deveriaRetornar200AoTrazerTodosOsVideosCadastrados() throws Exception {
		URI uri = new URI("/videos");

		mock.perform(MockMvcRequestBuilders.get(uri))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornar200AoTrazerUmVideoEspecifico() throws Exception {
		URI uri = new URI("/videos/2");

		mock.perform(MockMvcRequestBuilders.get(uri))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornar404AoTrazerUmVideoEspecificoQuNaoEstaCadastrado() throws Exception {
		URI uri = new URI("/videos/30");

		mock.perform(MockMvcRequestBuilders.get(uri))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void deveriaRetornar201AoAdicionarUmNovoVideoComCategoriaVazia() throws Exception {
		URI uri = new URI("/videos");

		mock.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"idCategoria\": \"\",\r\n"
						+ "    \"titulo\": \"Carreiras Hipster em Tecnologia\",\r\n"
						+ "    \"descricao\": \"O mercado de tecnologia tem crescido bastante, diversas profissões foram surgindo e com elas várias dúvidas. É melhor ser especialista ou generalista? Como se tornar o(a) profissional que as empresas estão procurando? Paulo Silveira responde!\",\r\n"
						+ "    \"url\": \"https://www.youtube.com/watch?v=AG8cYHCbGcg&t=34s&ab_channel=AluraCursosOnline\"\r\n"
						+ "}"))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	@Test
	public void deveriaRetornar404AoAdicionarUmNovoVideoComCategoriaComValorInvalido() throws Exception {
		URI uri = new URI("/videos");

		mock.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"idCategoria\": \"10\",\r\n"
						+ "    \"titulo\": \"Carreiras Hipster em Tecnologia\",\r\n"
						+ "    \"descricao\": \"O mercado de tecnologia tem crescido bastante, diversas profissões foram surgindo e com elas várias dúvidas. É melhor ser especialista ou generalista? Como se tornar o(a) profissional que as empresas estão procurando? Paulo Silveira responde!\",\r\n"
						+ "    \"url\": \"https://www.youtube.com/watch?v=AG8cYHCbGcg&t=34s&ab_channel=AluraCursosOnline\"\r\n"
						+ "}"))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void deveriaRetornar406AoAdicionarUmNovoVideoComCampoTituloIvalido() throws Exception {
		URI uri = new URI("/videos");

		mock.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"idCategoria\": \"\",\r\n"
						+ "    \"titulo\": \"\",\r\n"
						+ "    \"descricao\": \"O mercado de tecnologia tem crescido bastante, diversas profissões foram surgindo e com elas várias dúvidas. É melhor ser especialista ou generalista? Como se tornar o(a) profissional que as empresas estão procurando? Paulo Silveira responde!\",\r\n"
						+ "    \"url\": \"https://www.youtube.com/watch?v=AG8cYHCbGcg&t=34s&ab_channel=AluraCursosOnline\"\r\n"
						+ "}"))
				.andExpect(MockMvcResultMatchers.status().is(406));
	}
	
	@Test
	public void deveriaRetornar200AoEditarVideoJaCadastrado() throws Exception {
		URI uri = new URI("/videos/2");

		mock.perform(MockMvcRequestBuilders.put(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"idCategoria\": \"3\",\r\n"
						+ "    \"titulo\": \"Valor do Titulo Alterado\",\r\n"
						+ "    \"descricao\": \"O mercado de tecnologia tem crescido bastante, diversas profissões foram surgindo e com elas várias dúvidas. É melhor ser especialista ou generalista? Como se tornar o(a) profissional que as empresas estão procurando? Paulo Silveira responde!\",\r\n"
						+ "    \"url\": \"https://www.youtube.com/watch?v=AG8cYHCbGcg&t=34s&ab_channel=AluraCursosOnline\"\r\n"
						+ "}"))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornar404AoEditarVideoJaCadastradoComCategoriaInvalida() throws Exception {
		URI uri = new URI("/videos/4");

		mock.perform(MockMvcRequestBuilders.put(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"idCategoria\": \"20\",\r\n"
						+ "    \"titulo\": \"Valor do Titulo Alterado\",\r\n"
						+ "    \"descricao\": \"O mercado de tecnologia tem crescido bastante, diversas profissões foram surgindo e com elas várias dúvidas. É melhor ser especialista ou generalista? Como se tornar o(a) profissional que as empresas estão procurando? Paulo Silveira responde!\",\r\n"
						+ "    \"url\": \"https://www.youtube.com/watch?v=AG8cYHCbGcg&t=34s&ab_channel=AluraCursosOnline\"\r\n"
						+ "}"))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void deveriaRetornar200AoDeletarVideoCadastrado() throws Exception {
		URI uri = new URI("/videos/5");

		mock.perform(MockMvcRequestBuilders.delete(uri))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

}
