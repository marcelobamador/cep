package br.com.cep.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cep.dto.SearchCepReturnDto;
import br.com.cep.service.GeneralService;
import br.com.cep.service.SearchCepService;

@RestController
@RequestMapping("cep/")
public class ApiController {
	private static final Logger log = LogManager.getLogger(ApiController.class);

	@Autowired
	GeneralService generalService;
	
	@Autowired
	SearchCepService searchCepService;

	@RequestMapping(value = "/search-cep", method = RequestMethod.GET, produces = { APPLICATION_JSON_UTF8_VALUE })
	public SearchCepReturnDto searchCep(String cep) {

		SearchCepReturnDto returnDto = new SearchCepReturnDto();

		try {
			log.info("Iniciando busca do CEP");
			if (generalService.isCepValid(cep)) {
				returnDto = searchCepService.searchCep(cep);
			} else {
				log.error("Erro ao buscar cep");
				returnDto.setMessageReturn("O CEP digitado não é válido");
				returnDto.setCdReturn(400);
			}
		} catch (Exception e) {
			returnDto.setMessageReturn("Serviço indisponivel");
			returnDto.setCdReturn(500);
		}
		return returnDto;
	}
}
