package com.startrekrescue.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.startrekrescue.constants.Constants;
import com.startrekrescue.control.enumeration.EnumStatusLocal;
import com.startrekrescue.model.bean.Localizacao;
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
	 * Verifica se a nova posicao gerada aleatoriamente ja nao foi gerada anteriormente
	 */
	private boolean ehNovaPosicao(List<Tripulante> tripulantes, int x, int y) {

		for(Tripulante tripulante : tripulantes){
			if((tripulante.getLocal().getX() == x) && (tripulante.getLocal().getY() == y)){
				return false;
			}
		}
		return true;
	}

	/*
	 * Devolve a lista gerada aleatoriamente com a posicao dos tripulantes na planicies
	 */
	public List<Tripulante> gerarPosicaoDosTripulantes(int numeroDeTripulantes){

		List<Tripulante> tripulantes = new ArrayList<Tripulante>();
		for(int i = 0; i < numeroDeTripulantes; ){
			Random gerador = new Random();
			int x = gerador.nextInt(Constants.TAMANHO);
			int y = gerador.nextInt(Constants.TAMANHO);

			// verifica se a posicao gerada ja nao foi selecionada anteriormente
			if(ehNovaPosicao(tripulantes, x, y)){
				tripulantes.add(new Tripulante(i + 1, new Localizacao(x, y)));
				i++;
			}
		}

		return tripulantes;
	}

	/*
	 * seta status na planicie
	 * supoe que as coordenadas estao na planicie, portato essa validacao eh feita por fora
	 */
	public void setValorPlanicie(int[][] planicie, int posX, int posY, EnumStatusLocal status) {
		
		if(planicie[posX][posY] != EnumStatusLocal.TRIPULANTE_ENCONTRADO.valor)
			planicie[posX][posY] = status.valor;
		
	}
	
	/*
	 * Verifica se ha tripulante nas proximidades e faz as devidas marcacoes na planicie
	 * Devolve true se encontra tripulante nas proximidades
	 * false caso conrario
	 */
	public boolean verificaAdjacencia(int x, int y, List<Tripulante> tripulantes, int[][] planicie) {
		numeroDeSinalizadoresLancados++;

		for(Tripulante tripulante : tripulantes){

			// verifica se ja nao havia marcado como local contendo tripulante
			if(planicie[x][y] != EnumStatusLocal.TRIPULANTE_ENCONTRADO.valor) {
				
				// verifica  se ha tripulante na localizacao selecionada
				if((tripulante.getLocal().getX() == x) && (tripulante.getLocal().getY() == y)){
					setValorPlanicie(planicie, x, y, EnumStatusLocal.TRIPULANTE_ENCONTRADO);
					numeroDeTripulantesEncontrados++;
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Devolve true se encontra a posicao exata de um tripulante e marca sua localizacao na planicie
	 * Devolve false, caso contrario
	 * Incrementa o numero de sinalizadores lancados
	 */
	public boolean verificaSeEncontrouTripulante(int x, int y, List<Tripulante> tripulantes, int[][] planicie) {

		numeroDeSinalizadoresLancados++;

		for(Tripulante tripulante : tripulantes){

			// verifica se ja nao havia marcado como local contendo tripulante
			if(planicie[x][y] != EnumStatusLocal.TRIPULANTE_ENCONTRADO.valor) {
				
				// verifica  se ha tripulante na localizacao selecionada
				if((tripulante.getLocal().getX() == x) && (tripulante.getLocal().getY() == y)){
					setValorPlanicie(planicie, x, y, EnumStatusLocal.TRIPULANTE_ENCONTRADO);
					numeroDeTripulantesEncontrados++;
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Passa a planicie de matriz para forma String
	 */
	public String imprimePlanicie(int [][]planicie ){

		int tamanhoDaPlanicie = planicie.length;
		String planicieStr = "  ";

		// cabecalho
		for(int i = 1; i <= tamanhoDaPlanicie; i++)
			planicieStr = String.format("%s %d ", planicieStr, i);
		planicieStr += "\n";
		
		for(int i = 0; i < tamanhoDaPlanicie; i++) {
			
			planicieStr += String.format("%2d", i + 1);
			for(int j = 0; j < tamanhoDaPlanicie; j++){
				String info = "_";

				// tripulante encontrado
				if(planicie[i][j] == EnumStatusLocal.TRIPULANTE_ENCONTRADO.valor)
					info = "x";

				// tripulante na area adjacente
				else if(planicie[i][j] == 2)
					info = "!";

				// local nao contem tripulante adjacente
				else if(planicie[i][j] == 3)
					info = "*";

				planicieStr = String.format("%s %s ", planicieStr, info);
			}
			planicieStr += "\n";
		}

		return planicieStr;
	}

	/*
	 * recupera a entrada do usuario e faz o tratamento caso seja fornecido dado invalido
	 */
	public int getInputFromUser(String string) {

		return -1;
	}

}