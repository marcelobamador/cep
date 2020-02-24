package br.com.cep;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.cep.dto.SearchCepReturnDto;
import br.com.cep.service.rest.CepRestService;

public class CepRestServiceTest {

	@InjectMocks
	CepRestService cepRestService;

	@Mock
	RestTemplate restTemplate;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void searchCepTest() {
		Mockito.when(restTemplate.getForEntity("https://viacep.com.br/ws/72506412/json/", String.class))
				.thenReturn(new ResponseEntity(returnResult(), HttpStatus.OK));
		cepRestService.searchCep("12345678");
	}

	private SearchCepReturnDto returnResult() {
		SearchCepReturnDto result = new SearchCepReturnDto();
		result.setBairro("TESTE");
		result.setCep("12345678");
		result.setComplemento("TESTE");
		result.setGia("TESTE");
		result.setIbge("TESTE");
		result.setLocalidade("TESTE");
		result.setLogradouro("TESTE");
		result.setNumero(1);
		result.setUf("DF");
		result.setUnidade("TESTE");
		return result;
	}
}
