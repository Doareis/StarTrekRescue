package com.startrekrescue.test;

import java.util.List;

import junit.framework.TestCase;

import com.startrekrescue.constants.Constants;
import com.startrekrescue.control.Controller;
import com.startrekrescue.control.enumeration.EnumStatusLocal;
import com.startrekrescue.model.bean.Tripulante;

public class RescueTest extends TestCase{

	private int getCoordenadasLancamento(){

		return -1;
	}

	public void test(){

		int [][] planicie = new int[Constants.TAMANHO][Constants.TAMANHO];
		Controller controller = new Controller();

		int numeroDeTripulantesQueFaltaEncontrar = Constants.NUMERO_DE_TRIPULANTES;
		List<Tripulante> tripulantes = controller.gerarPosicaoDosTripulantes(Constants.NUMERO_DE_TRIPULANTES, Constants.TAMANHO);
		verificaListaDeTripulantes();
		
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
				assertTrue(numeroDeTripulantesQueFaltaEncontrar >= 0);
			}

		}
		
		assertEquals(controller.getNumeroDeTripulantesEcontrados(), tripulantes.size());
		assertEquals(numeroDeTripulantesQueFaltaEncontrar, 0);

	}

	private void verificaListaDeTripulantes() {
		
		assertTrue(true);
		
	}
}
