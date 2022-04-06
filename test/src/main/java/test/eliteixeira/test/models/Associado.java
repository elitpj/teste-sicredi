package test.eliteixeira.test.models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.client.RestTemplate;


@Document(collection="Associado")
public class Associado {
	
	@Id
	private String id;
	
	private String name;
	private String cpf;
	
	public Associado(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public int checkCPF() {
		
		//usa uma api externa para checar o cpf do associado
		String url = "https://user-info.herokuapp.com/users/"+this.cpf;
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject(url, String.class);
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(result);
			
			if(json.get("status") == "ABLE_TO_VOTE") {
				return 1;
			} else {
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

}
