package br.com.cep;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.cep.dto.SearchCepReturnDto;
import br.com.cep.service.SearchCepService;
import br.com.cep.service.rest.CepRestService;

public class SearchCepServiceTest {
	
	@InjectMocks
	SearchCepService searchCepService;
	
	@Mock
	CepRestService cepRestService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void searchCepTest() {
		Mockito.when(cepRestService.searchCep("12345678")).thenReturn(returnCepResult());
		SearchCepReturnDto dto = searchCepService.searchCep("12345678");
		assertNotNull(dto);
	}
	
	@Test
	public void searchCepExceptionTest() {
		Mockito.when(cepRestService.searchCep(null)).thenThrow(Exception.class);
		SearchCepReturnDto dto = searchCepService.searchCep(null);
		assertEquals(dto.getCdReturn(), 500);
	}
	
	private SearchCepReturnDto returnCepResult() {
		SearchCepReturnDto dto = new SearchCepReturnDto();
		dto.setBairro("TESTE");
		dto.setCep("12345678");
		dto.setComplemento("TESTE");
		dto.setGia("TESTE");
		dto.setIbge("TESTE");
		dto.setLocalidade("TESTE");
		dto.setLogradouro("TESTE");
		dto.setNumero(1);
		dto.setUf("DF");
		dto.setUnidade("TESTE");
		return dto;
	}
}
