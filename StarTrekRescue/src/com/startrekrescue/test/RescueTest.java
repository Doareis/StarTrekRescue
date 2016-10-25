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
		return  gerador.nextInt(Constants.TAMANHO_PLANICIE);
		
	}

	public void test(){

		Controller controller = new Controller();

		int numeroDeTripulantesQueFaltaEncontrar = Constants.NUMERO_DE_TRIPULANTES;
		
		// teste #1
		verificaListaDeTripulantes(controller.getTripulantes());
		
		while(controller.getNumeroDeTripulantesEcontrados() != Constants.NUMERO_DE_TRIPULANTES){

			int coordenadaX = getCoordenadasLancamento();
			int coordenadaY = getCoordenadasLancamento();

			// achou um tripulante
			if(controller.verificaSeEncontrouTripulante(coordenadaX, coordenadaY) == false){

				// achou tripulante proximo
				if(controller.verificaAdjacencia(coordenadaX, coordenadaY) == false){

					// nao encontrou tripulante (nem ao menos aos arredores)
					controller.setValorPlanicie(coordenadaX, coordenadaY, EnumStatusLocal.SEM_TRIPULANTE); 
				}
			}
			else{
				numeroDeTripulantesQueFaltaEncontrar--;
				
				// teste #2
				assertTrue(numeroDeTripulantesQueFaltaEncontrar >= 0);
			}

		}
		
		// teste #3
		assertEquals(controller.getNumeroDeTripulantesEcontrados(), Constants.NUMERO_DE_TRIPULANTES);
		
		// teste #4
		assertEquals(numeroDeTripulantesQueFaltaEncontrar, 0);
		
		// Teste #5
		verificaSePlanicieFoiMarcadaCorretamente(controller);

		System.out.println(controller.imprimePlanicie());
	}

	/*
	 * verifica se na planicie foram marcados os tripulantes nas posicoes corretas
	 */
	private void verificaSePlanicieFoiMarcadaCorretamente(Controller controller) {
		
		
		for(Tripulante tripulante : controller.getTripulantes()){
			assertEquals(controller.getPlanicie()[tripulante.getLocal().getX()][tripulante.getLocal().getY()], EnumStatusLocal.TRIPULANTE_ENCONTRADO.getCodigo());
		}
		
	}

	private void verificaListaDeTripulantes(List<Tripulante> tripulantes) {
		
		assertEquals(tripulantes.size(), Constants.NUMERO_DE_TRIPULANTES);
		
		// verifica se nao ha posicoes iguais
		for(int i = 0; i < Constants.NUMERO_DE_TRIPULANTES; i++){
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
