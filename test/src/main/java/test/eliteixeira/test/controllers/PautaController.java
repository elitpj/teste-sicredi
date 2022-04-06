package test.eliteixeira.test.controllers;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.eliteixeira.test.models.Pauta;
import test.eliteixeira.test.repositories.PautaRepository;

@RestController
@RequestMapping("/api/pauta")
public class PautaController {
	
	@Autowired
	private PautaRepository repository;

	@SuppressWarnings("unchecked")
	@PostMapping("/store")
	public String storePauta(@RequestParam(name = "name", required = true) String name) {
		Pauta pauta = new Pauta(name);
		this.repository.save(pauta);
		
		JSONObject jo = new JSONObject();
		String result = "Pauta salva #" + pauta.getId();
		jo.put("message", result);
		
		return jo.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/get")
	public String getPauta(@RequestParam(name = "id", required = true) String id) {
		
		JSONObject jo = new JSONObject();
		Optional<Pauta> result = repository.findById(id);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(result.get());
			jo.put("message", json);
		} catch (JsonProcessingException e) {
			jo.put("message", "Pauta não encontrada");
		}
		
		return jo.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/get-all")
	public String getAllPauta() {
		
		JSONObject jo = new JSONObject();
		List<Pauta> result = repository.findAll();
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		if(result.isEmpty() == false) {
			for(Pauta item: result) {
				try {
					json += mapper.writeValueAsString(item);
				} catch (JsonProcessingException e) {
					
				}
			}
			jo.put("message", json);
		} else {
			jo.put("message", "Nenhuma pauta encontrada");
		}
		
		return jo.toString();
	}
	
	@SuppressWarnings("unchecked")
	@DeleteMapping("/delete")
	public String deletePauta(@RequestParam(name = "id", required = true) String id) {
		repository.deleteById(id);
		JSONObject jo = new JSONObject();
		jo.put("message", "Pauta deletada #" + id);
		return jo.toString();
	}
	
	@SuppressWarnings("unchecked")
	@PatchMapping("/activate")
	public String activatePauta(@RequestParam(name = "id", required = true) String id,
			@RequestParam(name = "duration", required = false, defaultValue = "0") int duration) {
		
		JSONObject jo = new JSONObject();
		Optional<Pauta> pauta =  repository.findById(id);
		if(pauta.isPresent()) {
			pauta.get().begin(duration);
			this.repository.save(pauta.get());
			
			jo.put("message", "Pauta #"+pauta.get().getId()+" ativada");
			return jo.toString();
		} else {
			jo.put("message", "Pauta não encontrada");
		}
		return jo.toString();
	}
}
