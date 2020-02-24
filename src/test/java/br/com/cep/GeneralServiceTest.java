package br.com.cep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.com.cep.service.GeneralService;

public class GeneralServiceTest {
	
	@InjectMocks
	GeneralService generalService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void isCepValidTest() throws Exception {
		Boolean result = generalService.isCepValid(null);
		assertEquals(result, false);
	}
	
	@Test
	public void isCepValid1Test() throws Exception {
		Boolean result = generalService.isCepValid("123456789");
		assertEquals(result, false);
	}
	
	@Test
	public void isCepValid2Test() throws Exception {
		Boolean result = generalService.isCepValid("12345678");
		assertEquals(result, true);
	}
}
