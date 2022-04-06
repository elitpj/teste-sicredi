package test.eliteixeira.test;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import test.eliteixeira.test.models.Pauta;
import test.eliteixeira.test.models.Voto;
import test.eliteixeira.test.repositories.PautaRepository;
import test.eliteixeira.test.repositories.VotoRepository;

@Component
public class ScheduleCron {
	
	@Autowired
	private PautaRepository repositoryPauta;
	
	@Autowired
	private VotoRepository repositoryVoto;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Scheduled(cron = "0 0/1 * * * *")
	public void executar() {
		
		//recebe a lista de pautas abertas
		List<Pauta> list = repositoryPauta.getActivePauta();
		long now = new Date().getTime();
		
		for(Pauta item : list){
			List<Voto> listVoto = repositoryVoto.getVotoByPauta(item.getId());
            if(now >= item.getEnd()) {
            	item.end();
            	this.repositoryPauta.save(item);

            	//avisa usando kafka
            	this.kafkaProducer.send("Pauta #"+item.getId()+" finalizou. "+Voto.processVoto(listVoto));
            	
            }
        }
		
    }

}
