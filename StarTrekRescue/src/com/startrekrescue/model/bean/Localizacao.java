package com.startrekrescue.model.bean;

// classe de localizacao de tripulantes.
public class Localizacao {

	private int x = 0;
	private int y = 0;
	
	public Localizacao(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
