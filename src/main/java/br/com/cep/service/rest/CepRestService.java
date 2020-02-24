package br.com.cep.service.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import br.com.cep.dto.SearchCepReturnDto;

@Service
public class CepRestService {

	private static final Logger log = LogManager.getLogger(CepRestService.class);

	@Value("${cep.endpoint}")
	private String cepRestUrl;

	private RestTemplate restTemplate;
	
	@Autowired
	private Gson gson;

	public SearchCepReturnDto searchCep(String cep) {

		SearchCepReturnDto result = new SearchCepReturnDto();

		try {
			init();
			URI uri = UriBuilder.fromPath(cepRestUrl + cep + "/json/").build();
			ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				Type listType = new TypeToken<SearchCepReturnDto>() {
				}.getType();
				result = gson.fromJson(response.getBody(), listType);
				if (result == null) {
					return result = null;
				} else {
					return result;
				}
			} else {
				throw new Exception("Serviço temporariamente indisponível.");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	protected void init() throws Exception {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}
	}
}
