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

		if(planicie[posX][posY] != EnumStatusLocal.TRIPULANTE_ENCONTRADO.getCodigo())
			planicie[posX][posY] = status.getCodigo();

	}

	/*
	 * Verifica se ha tripulante nas proximidades e faz as devidas marcacoes na planicie
	 * Devolve true se encontra tripulante nas proximidades
	 * false caso conrario
	 */
	public boolean verificaAdjacencia(int x, int y, List<Tripulante> tripulantes, int[][] planicie) {
		for(Tripulante tripulante : tripulantes){

			int posX = tripulante.getLocal().getX();
			int posY = tripulante.getLocal().getY();

			if((Math.abs(x - posX) <= 1) && (Math.abs(y - posY) <= 1)){

				// marca as posicoes adjacentes
				realizaMarcacoesNasProximidades(planicie, x, y, posX, posY);
				return true;
			}
		}

		return false;
	}

	/*
	 * Realiza as marcacoes quando encontra tripulantes nas proximidades do
	 * local onde foi lancado o sinalizador. Dado a imprecisao do sinalizador, marca apenas locais adjacentes
	 * que sejam em comum com o local do tripulante e do sinalizador
	 */
	private void realizaMarcacoesNasProximidades(int[][] planicie, int x, int y, int tripulantePosX, int tripulantePosY) {
		
		int delta = 1;
		
		for(int i = x - 1; i <= x + delta; i++)
			for(int j = y - 1; j <= y + delta; j++){
				
				// verifica os limites da planicie
				if((i >= 0 && i < planicie.length) && (j >= 0 && j < planicie.length)){
					
					// se esta nas proximidades do tripulante
					if((Math.abs(i - tripulantePosX) <= 1) && (Math.abs(j - tripulantePosY) <= 1)){
						setValorPlanicie(planicie, i, j, EnumStatusLocal.TRIPULANTE_NAS_PROXIMIDADES);
					}
				}
			}
				
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
			if(planicie[x][y] != EnumStatusLocal.TRIPULANTE_ENCONTRADO.getCodigo()) {

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

				char info = EnumStatusLocal.getStatusLocal(planicie[i][j]).getValor();
				
				planicieStr = String.format("%s %c ", planicieStr, info);
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