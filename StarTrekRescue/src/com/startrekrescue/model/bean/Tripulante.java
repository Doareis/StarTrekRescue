package com.startrekrescue.model.bean;

public class Tripulante {

	private int id;
	private Localizacao local;
	
	public Tripulante(int id, Localizacao local){
		this.id = id;
		this.local = local;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Localizacao getLocal() {
		return local;
	}
	public void setLocal(Localizacao local) {
		this.local = local;
	}
	
	
}
