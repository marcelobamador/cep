package br.com.cep.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cep.dto.SearchCepReturnDto;
import br.com.cep.service.rest.CepRestService;

@Service
public class SearchCepService {
	
	@Autowired
	CepRestService cepRestService;
	
	private static final Logger log = LogManager.getLogger(SearchCepService.class);
	
	public SearchCepReturnDto searchCep(String cep) {
		SearchCepReturnDto result = new SearchCepReturnDto();
		try {
			result = cepRestService.searchCep(cep);
			if (result != null) {
				return result;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setMessageReturn("Erro ao buscar CEP");
			result.setCdReturn(500);
		}
		return result;
	}
}
