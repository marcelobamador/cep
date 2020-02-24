package br.com.cep.service;

import org.springframework.stereotype.Service;

@Service
public class GeneralService {

	public boolean isCepValid(String cep) throws Exception {
		if (cep == null || cep.isEmpty()) {
			return false;
		}
		if (cep.length() > 8) {
			return false;
		}
		return true;
	}
}
