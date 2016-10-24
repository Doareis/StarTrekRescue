package com.startrekrescue.control.enumeration;

public enum EnumStatusLocal {
	
	TRIPULANTE_ENCONTRADO(1),
	TRIPULANTE_NAS_PROXIMIDADES(2),
	SEM_TRIPULANTE(3);
	
	public int valor;
	EnumStatusLocal(int valor) {
		this.valor = valor;
	}
}
