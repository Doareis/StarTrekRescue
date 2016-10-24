package com.startrekrescue.control;

import java.util.ArrayList;
import java.util.List;

import com.startrekrescue.model.bean.Tripulante;

public class Controller {

	private int numeroDeSinalizadoresLancados = 0;
	private int numeroDeTripulantesEncontrados = 0;

	public int getNumeroDeTripulantesEcontrados() {
		return numeroDeTripulantesEncontrados;
	}
	
	public int getNumeroDeSinalizadoresLancados() {
		return numeroDeSinalizadoresLancados;
	}
	
	/*
	 * Devolve a lista gerada aleatoriamente com a posicao dos tripulantes na planicies
	 */
	public List<Tripulante> gerarPosicaoDosTripulantes(int numeroDeTripulantes, int tamanhoPlanicie){

		List<Tripulante> tripulantes = new ArrayList<Tripulante>();

		
		return tripulantes;
	}

	/*
	 * Verifica se ha tripulante nas proximidades e faz as devidas marcacoes na planicie
	 * Devolve true se encontra tripulante nas proximidades
	 * false caso conrario
	 */
	public boolean verificaAdjacencia(int x, int y, List<Tripulante> tripulantes, int[][] planicie) {
		
		return false;
	}

	/*
	 * Devolve true se encontra a posicao exata de um tripulante e marca sua localizacao na planicie
	 * Devolve false, caso contrario
	 * Incrementa o numero de sinalizadores lancados
	 */
	public boolean verificaSeEncontrouTripulante(int x, int y, List<Tripulante> tripulantes, int[][] planicie) {

		return false;
	}

	public String imprimePlanicie(int [][]planicie ){

		String planicieStr = "";
		
		return planicieStr;
	}

	/*
	 * recupera a entrada do usuario e faz o tratamento caso seja fornecido dado invalido
	 */
	public int getInputFromUser(String string) {

		return -1;
	}

}