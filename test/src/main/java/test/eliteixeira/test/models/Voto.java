package test.eliteixeira.test.models;

import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Voto")
public class Voto {
	
	@Id
	private String id;
	
	@DBRef
	private Pauta pauta;
	@DBRef
	private Associado associado;
	private int value;
	
	public Voto(Pauta pauta, Associado associado, int value) {
		this.pauta = pauta;
		this.associado = associado;
		this.value = value;
	}
	
	public static String checkVoto(Optional<Pauta> pauta, Optional<Associado> associado, int value, List<Voto> list) {
		if(pauta.isPresent() && associado.isPresent()) {
			//checa se o associado já votou na pauta
			if(list.isEmpty()) {
				//checa o CPF
				int checkCPF = associado.get().checkCPF();
				if(checkCPF == 1) {
					//checa se a pauta esta aberta ainda
					if(pauta.get().getStatus() == 1) {
						return "Voto registrado com sucesso";
					} else {
						return "Pauta Inativa";
					}
				} else {
					//cpf inválido
					return "CPF inválido";
				}
			} else {
				//já votou
				return "Já votou nessa pauta";
			}
			
		} else {
			// dados inválidos
			return "Dados Inválidos";
		}
	}
	
	public String getId() {
		return id;
	}

	public Pauta getPauta() {
		return pauta;
	}
	public Associado getAssociado() {
		return associado;
	}
	public int getValue() {
		return value;
	}
	
	public static String processVoto(List<Voto> list) {
		/*
		 * método para checar o resultado da votação.
		 * como o voto foi convertido para inteiros, usando 1 para SIM e 0 para NÃO
		 * podemos saber o resultado da votação somando todos os valores da lista
		 * caso a soma seja maior do que a metade do tamanho da lista (número de votos)
		 * tiveram mais votos SIM, caso contrário tiveram mais votos NÃO
		 */
		int sum = list.stream().mapToInt(o -> o.getValue()).sum();
		
		if(sum == (list.size()/2)) {
			return "Empatou";
		}
		
		if(sum > (list.size()/2)) {
			return "A maioria votou SIM";
		} else {
			return "A maioria votou NÃO";
		}
		
	}

}
