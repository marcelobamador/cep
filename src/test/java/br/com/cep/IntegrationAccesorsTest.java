package br.com.cep;

import org.junit.Test;

import br.com.cep.dto.ReturnDto;
import br.com.cep.dto.SearchCepReturnDto;

public class IntegrationAccesorsTest {

	@Test
	public void accesorsTest() {
		PojoUtil.validateAccessors(ReturnDto.class);
		PojoUtil.validateAccessors(SearchCepReturnDto.class);
	}
}
