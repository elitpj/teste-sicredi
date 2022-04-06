package test.eliteixeira.test.controllers;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.eliteixeira.test.models.Pauta;
import test.eliteixeira.test.repositories.PautaRepository;

import test.eliteixeira.test.models.Voto;
import test.eliteixeira.test.repositories.VotoRepository;

import test.eliteixeira.test.models.Associado;
import test.eliteixeira.test.repositories.AssociadoRepository;

@RestController
@RequestMapping("/api/voto")
public class VotoController {
	
	@Autowired
	private PautaRepository repositoryPauta;
	@Autowired
	private VotoRepository repositoryVoto;
	@Autowired
	private AssociadoRepository repositoryAssociado;
	
	@SuppressWarnings("unchecked")
	@PostMapping("/store")
	public String storeVoto(
			@RequestParam(name = "idAssociado", required = true) String idAssociado,
			@RequestParam(name = "idPauta", required = true) String idPauta,
			@RequestParam(name = "vote", required = true) String vote) {
		
		Optional<Associado> associado = this.repositoryAssociado.findById(idAssociado);
		Optional<Pauta> pauta = this.repositoryPauta.findById(idPauta);
		
		JSONObject jo = new JSONObject();
		
		//caso não encontre a pauta ou associado
		if(!pauta.isPresent() && !associado.isPresent()) {
			jo.put("message", "Dados Incorretos");
			
			return jo.toString();
		}
		
		// recebe lista com votos do associado na pauta para verificar se o associado já votou
		List<Voto> listVoto = this.repositoryVoto.getVotoByAssociadoAndPauta(associado.get().getId(), pauta.get().getId());
		
		int value = (vote.equals("Sim")) ? 1 : 0;
		String result = Voto.checkVoto(pauta, associado, value, listVoto);
		if(result == "Voto registrado com sucesso") {
			Voto voto  = new Voto(pauta.get(), associado.get(), value);
			this.repositoryVoto.save(voto);
		}
		
		jo.put("message", result);
		
		return jo.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/result")
	public String result(@RequestParam(name = "id", required = true) String id) {
		
		JSONObject jo = new JSONObject();
		String result = "Dados Inválidos";
		Optional<Pauta> pauta = this.repositoryPauta.findById(id);
		if(pauta.isPresent()) {
			List<Voto> list = repositoryVoto.getVotoByPauta(pauta.get().getId());
			result = Voto.processVoto(list);
		}
		
		jo.put("message", result);
		
		return jo.toString();
	}

}
