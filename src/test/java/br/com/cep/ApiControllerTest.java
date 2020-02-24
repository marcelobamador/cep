package br.com.cep;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.cep.api.ApiController;
import br.com.cep.dto.SearchCepReturnDto;
import br.com.cep.service.GeneralService;
import br.com.cep.service.SearchCepService;

public class ApiControllerTest {
	
	@InjectMocks
	ApiController apiController;
	
	@Mock
	SearchCepService searchCepService;
	
	@Mock
	GeneralService generalService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void searchCepTest() throws Exception {
		Mockito.when(generalService.isCepValid("12345678")).thenReturn(true);
		Mockito.when(searchCepService.searchCep("12345678")).thenReturn(returnDto());
		SearchCepReturnDto retorno = apiController.searchCep("12345678");
		assertNotNull(retorno);
	}
	
	@Test
	public void searchCepFalseTest() throws Exception {
		Mockito.when(generalService.isCepValid("12345678")).thenReturn(false);
		Mockito.when(searchCepService.searchCep("12345678")).thenReturn(returnDto());
		SearchCepReturnDto retorno = apiController.searchCep("12345678");
		assertNotNull(retorno);
	}
	
	private SearchCepReturnDto returnDto() {
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
