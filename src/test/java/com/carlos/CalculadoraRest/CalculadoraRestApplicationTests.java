package com.carlos.CalculadoraRest;

import com.carlos.CalculadoraRest.dto.ExpressaoRequestDTO;
import com.carlos.CalculadoraRest.dto.ResultadoExpressaoResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.crossstore.HashMapChangeSet;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculadoraRestApplicationTests {

	public static final String BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
			".eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ" +
			".SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@DisplayName("Para expressão 2+2 esperado 4 com sucesso.")
	public void para2Mais2Experado4ComSucesso() throws Exception {

		final ExpressaoRequestDTO expressao = new ExpressaoRequestDTO("2+2");
		final HttpEntity<ExpressaoRequestDTO> request = new HttpEntity<>(expressao, getHeaders());
		final String url = "http://localhost:" + port + "/expressao/calcular";

		ResponseEntity<ResultadoExpressaoResponseDTO> resp =
				restTemplate.postForEntity(url, request, ResultadoExpressaoResponseDTO.class);

		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
		Assertions.assertEquals("4", Objects.requireNonNull(resp.getBody()).getResultado());
	}

	@Test
	@DisplayName("Para expressão 2.2+2.2 esperado 4.4 com sucesso.")
	public void ParaCalculoExpressaoSimplesComNumerosReaisEsperadoSucesso() throws Exception {

		final ExpressaoRequestDTO expressao = new ExpressaoRequestDTO("2.2+2.2");
		final HttpEntity<ExpressaoRequestDTO> request = new HttpEntity<>(expressao, getHeaders());
		final String url = "http://localhost:" + port + "/expressao/calcular";

		ResponseEntity<ResultadoExpressaoResponseDTO> resp =
				restTemplate.postForEntity(url, request, ResultadoExpressaoResponseDTO.class);

		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
		Assertions.assertEquals("4.4", Objects.requireNonNull(resp.getBody()).getResultado());
	}

	@Test
	@DisplayName("Para expressão 2.3*2.3+5 esperado 10.29 com sucesso.")
	public void ParaCalculoExpressaoVariosOperadoresComNumerosReaisEsperadoSucesso() throws Exception {

		final ExpressaoRequestDTO expressao = new ExpressaoRequestDTO("2.3*2.3+5");
		final HttpEntity<ExpressaoRequestDTO> request = new HttpEntity<>(expressao, getHeaders());
		final String url = "http://localhost:" + port + "/expressao/calcular";

		ResponseEntity<ResultadoExpressaoResponseDTO> resp =
				restTemplate.postForEntity(url, request, ResultadoExpressaoResponseDTO.class);

		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
		Assertions.assertEquals("10.29", Objects.requireNonNull(resp.getBody()).getResultado());
	}

	@Test
	@DisplayName("Para expressão 2.33/3 esperado 0.78 com sucesso.")
	public void ParaCalculoExpressaoComDivisaoEsperadoSucesso() throws Exception {

		final ExpressaoRequestDTO expressao = new ExpressaoRequestDTO("2.33/3");
		final HttpEntity<ExpressaoRequestDTO> request = new HttpEntity<>(expressao, getHeaders());
		final String url = "http://localhost:" + port + "/expressao/calcular";

		ResponseEntity<ResultadoExpressaoResponseDTO> resp =
				restTemplate.postForEntity(url, request, ResultadoExpressaoResponseDTO.class);

		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
		Assertions.assertEquals("0.78", Objects.requireNonNull(resp.getBody()).getResultado());
	}

	@Test
	@DisplayName("Para expressão 1/0 esperado erro.")
	public void ParaCalculoExpressaoComDivisaoPorZeroErro() throws Exception {

		final ExpressaoRequestDTO expressao = new ExpressaoRequestDTO("1/0");
		final HttpEntity<ExpressaoRequestDTO> request = new HttpEntity<>(expressao, getHeaders());
		final String url = "http://localhost:" + port + "/expressao/calcular";

		ResponseEntity<String> resp =
				restTemplate.postForEntity(url, request, String.class);

		Assertions.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		Assertions.assertEquals("Erro ao tentar calcular a expressão: 1/0.", resp.getBody());
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", BEARER_TOKEN);
		return headers;
	}

}
