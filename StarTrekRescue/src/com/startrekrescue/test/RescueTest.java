package com.startrekrescue.test;

import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import com.startrekrescue.constants.Constants;
import com.startrekrescue.control.Controller;
import com.startrekrescue.control.enumeration.EnumStatusLocal;
import com.startrekrescue.model.bean.Tripulante;

public class RescueTest extends TestCase{

	private int getCoordenadasLancamento(){

		Random gerador = new Random();
		return  gerador.nextInt(Constants.TAMANHO);
		
	}

	public void test(){

		int [][] planicie = new int[Constants.TAMANHO][Constants.TAMANHO];
		Controller controller = new Controller();

		int numeroDeTripulantesQueFaltaEncontrar = Constants.NUMERO_DE_TRIPULANTES;
		List<Tripulante> tripulantes = controller.gerarPosicaoDosTripulantes(Constants.NUMERO_DE_TRIPULANTES);
		
		// teste #1
		verificaListaDeTripulantes(tripulantes);
		
		while(controller.getNumeroDeTripulantesEcontrados() != tripulantes.size()){

			int coordenadaX = getCoordenadasLancamento();
			int coordenadaY = getCoordenadasLancamento();

			// achou um tripulante
			if(controller.verificaSeEncontrouTripulante(coordenadaX, coordenadaY, tripulantes, planicie) == false){

				// achou tripulante proximo
				if(controller.verificaAdjacencia(coordenadaX, coordenadaY, tripulantes, planicie) == false){

					// nao encontrou tripulante (nem ao menos aos arredores)
					planicie[coordenadaX][coordenadaY] = EnumStatusLocal.SEM_TRIPULANTE.valor; 
				}
			}
			else{
				numeroDeTripulantesQueFaltaEncontrar--;
				
				// teste #2
				assertTrue(numeroDeTripulantesQueFaltaEncontrar >= 0);
			}

		}
		
		// teste #3
		assertEquals(controller.getNumeroDeTripulantesEcontrados(), tripulantes.size());
		
		// teste #4
		assertEquals(numeroDeTripulantesQueFaltaEncontrar, 0);
		
		// Teste #5
		verificaSePlanicieFoiMarcadaCorretamente(planicie, tripulantes);

		System.out.println(controller.imprimePlanicie(planicie));
	}

	/*
	 * verifica se na planicie foram marcados os tripulantes nas posicoes corretas
	 */
	private void verificaSePlanicieFoiMarcadaCorretamente(int[][] planicie, List<Tripulante> tripulantes) {
		
		for(Tripulante tripulante : tripulantes){
			assertEquals(planicie[tripulante.getLocal().getX()][tripulante.getLocal().getY()], EnumStatusLocal.TRIPULANTE_ENCONTRADO.valor);
		}
		
	}

	private void verificaListaDeTripulantes(List<Tripulante> tripulantes) {
		
		assertEquals(tripulantes.size(), Constants.NUMERO_DE_TRIPULANTES);
		
		// verifica se nao ha posicoes iguais
		for(int i = 0; i < tripulantes.size(); i++){
			Tripulante tripulanteA = tripulantes.get(i);
			for(int j = i + 1; j < tripulantes.size(); j++){
				Tripulante tripulanteB = tripulantes.get(j);
				
				assertFalse(
						(tripulanteA.getLocal().getX() == tripulanteB.getLocal().getX()) && 
						(tripulanteA.getLocal().getY() == tripulanteB.getLocal().getY()));
			}
		}
		
		
	}
}
