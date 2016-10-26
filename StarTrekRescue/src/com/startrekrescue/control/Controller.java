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
	
	private int [][] planicie;
	private List<Tripulante> tripulantes;

	public int getNumeroDeTripulantesEncontrados() {
		return numeroDeTripulantesEncontrados;
	}

	public int getNumeroDeSinalizadoresLancados() {
		return numeroDeSinalizadoresLancados;
	}

	public Controller() {
		
		gerarPosicaoDosTripulantes(Constants.NUMERO_DE_TRIPULANTES);
		planicie = new int[Constants.TAMANHO_PLANICIE][Constants.TAMANHO_PLANICIE];
	}
	
	/*
	 *  executa lancamento de sinalizador na coordenada (X, Y).
	 */
	public void lancaSinalizador(int coordenadaX, int coordenadaY) {
		
		// achou um tripulante.
		if (verificaSeEncontrouTripulante(coordenadaX, coordenadaY) == false){

			// achou tripulante proximo.
			if (verificaAdjacencia(coordenadaX, coordenadaY) == false){

				// nao encontrou tripulante (nem ao menos aos arredores).
				setValorPlanicie(coordenadaX, coordenadaY, EnumStatusLocal.SEM_TRIPULANTE); 
			}
		}
	}

	/*
	 * Verifica se a nova posicao gerada aleatoriamente ja nao foi gerada anteriormente.
	 */
	private boolean ehNovaPosicao(List<Tripulante> tripulantes, int x, int y) {

		for (Tripulante tripulante : tripulantes){
			if ((tripulante.getLocal().getX() == x) && (tripulante.getLocal().getY() == y)){
				return false;
			}
		}
		return true;
	}

	/*
	 * Devolve a lista gerada aleatoriamente com a posicao dos tripulantes na planicies.
	 */
	public void gerarPosicaoDosTripulantes(int numeroDeTripulantes){

		tripulantes = new ArrayList<Tripulante>();
		for (int i = 0; i < numeroDeTripulantes; ){
			Random gerador = new Random();
			int x = gerador.nextInt(Constants.TAMANHO_PLANICIE);
			int y = gerador.nextInt(Constants.TAMANHO_PLANICIE);

			// verifica se a posicao gerada ja nao foi selecionada anteriormente.
			if (ehNovaPosicao(tripulantes, x, y)){
				tripulantes.add(new Tripulante(i + 1, new Localizacao(x, y)));
				i++;
			}
		}

	}

	/*
	 * Seta status na planicie.
	 * supoe que as coordenadas estao na planicie, portanto essa validacao eh feita por fora.
	 * Apenas altera o status em dado ponto se o mesmo ja nao foi marcado como contendo tripulante.
	 */
	public void setValorPlanicie(int posX, int posY, EnumStatusLocal status) {

		if (planicie[posX][posY] != EnumStatusLocal.TRIPULANTE_ENCONTRADO.getCodigo())
			planicie[posX][posY] = status.getCodigo();

	}

	/*
	 * Verifica se ha tripulante nas proximidades e faz as devidas marcacoes na planicie.
	 * Devolve true se encontra tripulante nas proximidades ou false caso conrario.
	 */
	public boolean verificaAdjacencia(int x, int y) {
		
		for (Tripulante tripulante : tripulantes){

			int posX = tripulante.getLocal().getX();
			int posY = tripulante.getLocal().getY();

			if ((Math.abs(x - posX) <= 1) && (Math.abs(y - posY) <= 1)){

				// marca as posicoes adjacentes.
				realizaMarcacoesNasProximidades(x, y, posX, posY);
				return true;
			}
		}

		return false;
	}

	/*
	 * Realiza as marcacoes quando encontra tripulantes nas proximidades do
	 * local onde foi lancado o sinalizador. 
	 * Dado a imprecisao do sinalizador, marca apenas locais adjacentes
	 * que sejam em comum com o local do tripulante e do sinalizador.
	 */
	private void realizaMarcacoesNasProximidades(int x, int y, int tripulantePosX, int tripulantePosY) {
		
		int delta = 1;
		
		for (int i = x - 1; i <= x + delta; i++){
			for (int j = y - 1; j <= y + delta; j++){
				
				// verifica os limites da planicie.
				if ((i >= 0 && i < planicie.length) && (j >= 0 && j < planicie.length)){
					
					// se esta nas proximidades do tripulante.
					if ((Math.abs(i - tripulantePosX) <= 1) && (Math.abs(j - tripulantePosY) <= 1)){
						setValorPlanicie(i, j, EnumStatusLocal.TRIPULANTE_NAS_PROXIMIDADES);
					}
				}
			}
		}
	}
	
	/*
	 * Devolve true se encontra a posicao exata de um tripulante e marca sua localizacao na planicie.
	 * Devolve false, caso contrario.
	 * Incrementa o numero de sinalizadores lancados.
	 */
	public boolean verificaSeEncontrouTripulante(int x, int y) {

		numeroDeSinalizadoresLancados++;

		for (Tripulante tripulante : getTripulantes()){

			// verifica se ja nao havia marcado como local contendo tripulante.
			if (planicie[x][y] != EnumStatusLocal.TRIPULANTE_ENCONTRADO.getCodigo()) {

				// verifica  se ha tripulante na localizacao selecionada.
				if ((tripulante.getLocal().getX() == x) && (tripulante.getLocal().getY() == y)){
					setValorPlanicie(x, y, EnumStatusLocal.TRIPULANTE_ENCONTRADO);
					numeroDeTripulantesEncontrados++;
					return true;
				}
			}
		}
		return false;
	}

	public List<Tripulante> getTripulantes() {
		return this.tripulantes;
	}
	
	public int [][] getPlanicie() {
		return this.planicie;
	}

	/*
	 * Passa a planicie de matriz para forma String.
	 */
	public String planicieToString(){

		int tamanhoDaPlanicie = planicie.length;
		StringBuilder planicieStr = new StringBuilder("  ");

		// cabecalho
		for (int i = 1; i <= tamanhoDaPlanicie; i++){
			planicieStr.append(" " + i);
		}
		planicieStr.append("\n");

		for (int i = 0; i < tamanhoDaPlanicie; i++) {

			planicieStr.append(String.format("%2d", i + 1));
			for (int j = 0; j < tamanhoDaPlanicie; j++){

				char info = EnumStatusLocal.getStatusLocal(planicie[i][j]).getValor();
				
				planicieStr.append(" " + info);
			}
			planicieStr.append("\n");
		}

		return planicieStr.toString();
	}

}