package test.eliteixeira.test.controllers;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.eliteixeira.test.models.Associado;
import test.eliteixeira.test.repositories.AssociadoRepository;

@RestController
@RequestMapping("/api/associado")
public class AssociadoController {
	@Autowired
	private AssociadoRepository repository;

	@SuppressWarnings("unchecked")
	@PostMapping("/store")
	public String storeAssociado(@RequestParam(name = "name", required = true) String name,@RequestParam(name = "cpf", required = true) String cpf) {
		Associado associado = new Associado(name, cpf);
		this.repository.save(associado);
		JSONObject jo = new JSONObject();
		String result = "Associado salvo #" + associado.getId();
		jo.put("message", result);
		
		return jo.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/get")
	public String getAssociado(@RequestParam(name = "id", required = true) String id) {
		JSONObject jo = new JSONObject();
		Optional<Associado> result = repository.findById(id);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(result.get());
			jo.put("message", json);
		} catch (JsonProcessingException e) {
			jo.put("message", "Associado não encontrado");
		}
		
		return jo.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/get-all")
	public String getAllAssociado() {
		
		JSONObject jo = new JSONObject();
		List<Associado> result = repository.findAll();
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		if(result.isEmpty() == false) {
			for(Associado item: result) {
				try {
					json += mapper.writeValueAsString(item);
				} catch (JsonProcessingException e) {
					
				}
			}
			jo.put("message", json);
		} else {
			jo.put("message", "Nenhum associado encontrado");
		}
		
		return jo.toString();
	}
	
	@SuppressWarnings("unchecked")
	@DeleteMapping("/delete")
	public String deleteAssociado(@RequestParam(name = "id", required = true) String id) {
		repository.deleteById(id);
		JSONObject jo = new JSONObject();
		jo.put("message", "Associado deletado #" + id);
		return jo.toString();
	}
}
