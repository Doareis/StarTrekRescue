package com.startrekrescue.control.enumeration;

/*
 * Enum com os possiveis status de cada posicao na planicie
 * Guarda o codigo e o respectivo caracter para representacao grafica
 */
public enum EnumStatusLocal {

	SEM_VERIFICACAO(0, '_'),
	TRIPULANTE_ENCONTRADO(1, 'X'),
	TRIPULANTE_NAS_PROXIMIDADES(2, '!'),
	SEM_TRIPULANTE(3, '*');

	private int codigo;
	private char valor;

	public static EnumStatusLocal getStatusLocal(int codigo){
		
		for(EnumStatusLocal statusLocal : values()){
			if(statusLocal.getCodigo() == codigo){
				return statusLocal;
			}
		}
		return null;
	}

	EnumStatusLocal(int codigo, char valor) {
		this.codigo = codigo;
		this.valor = valor;
	}

	public int getCodigo() {
		return codigo;
	}

	public char getValor() {
		return valor;
	}

}
