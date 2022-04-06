package test.eliteixeira.test.models;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Pauta")
public class Pauta {
	
	@Id
	private String id;
	
	private String name;
	private long timeBegin;
	private long timeEnd;
	private int status;
	
	public Pauta(String name) {
		this.name = name;
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

	public long getBegin() {
		return timeBegin;
	}

	public void begin(int duration) {
		if(duration == 0) {
			duration = 1;
		}
		this.timeBegin = new Date().getTime();
		timeEnd = timeBegin + (duration * 60 * 1000);
		this.status = 1;
	}

	public long getEnd() {
		return timeEnd;
	}
	
	public void end() {
		this.status = 0;
	}
	
	public int getStatus() {
		return status;
	}
	
	
	
	
}
