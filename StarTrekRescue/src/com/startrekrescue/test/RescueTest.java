package com.startrekrescue.test;

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

		while (controller.getNumeroDeTripulantesEncontrados() != Constants.NUMERO_DE_TRIPULANTES){

			int coordenadaX = getCoordenadasLancamento();
			int coordenadaY = getCoordenadasLancamento();

			controller.lancaSinalizador(coordenadaX, coordenadaY);	
			
			numeroDeTripulantesQueFaltaEncontrar = Constants.NUMERO_DE_TRIPULANTES - controller.getNumeroDeTripulantesEncontrados();
			assertTrue(numeroDeTripulantesQueFaltaEncontrar >= 0);

		}

		// verifica se o numero de tripulantes encontrados eh igual ao numero de tripulates total
		assertEquals(controller.getNumeroDeTripulantesEncontrados(), Constants.NUMERO_DE_TRIPULANTES);

		// se nao falta encontrar nenhum tripulante
		assertEquals(numeroDeTripulantesQueFaltaEncontrar, 0);

		// se a planicie foi marcada corretamente
		verificaSePlanicieFoiMarcadaCorretamente(controller);

	}

	/*
	 * verifica se na planicie foram marcados os tripulantes nas posicoes corretas.
	 */
	private void verificaSePlanicieFoiMarcadaCorretamente(Controller controller) {

		for (Tripulante tripulante : controller.getTripulantes()){
			assertEquals(controller.getPlanicie()[tripulante.getLocal().getX()][tripulante.getLocal().getY()], EnumStatusLocal.TRIPULANTE_ENCONTRADO.getCodigo());
		}

	}


}
