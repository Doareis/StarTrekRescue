package com.startrekrescue.test;

import java.util.List;

import junit.framework.TestCase;

import com.startrekrescue.constants.Constants;
import com.startrekrescue.control.Controller;
import com.startrekrescue.model.bean.Tripulante;

public class TestaCriacaoDeTripulantes extends TestCase{

	public void test() {
		
		Controller controller = new Controller();
		List<Tripulante> tripulantes = controller.getTripulantes();
		
		assertEquals(tripulantes.size(), Constants.NUMERO_DE_TRIPULANTES);
		
		// verifica se nao ha posicoes iguais.
		for (int i = 0; i < Constants.NUMERO_DE_TRIPULANTES; i++){
			Tripulante tripulanteA = tripulantes.get(i);
			for (int j = i + 1; j < tripulantes.size(); j++){
				Tripulante tripulanteB = tripulantes.get(j);
				
				assertFalse(
						(tripulanteA.getLocal().getX() == tripulanteB.getLocal().getX()) && 
						(tripulanteA.getLocal().getY() == tripulanteB.getLocal().getY()));
			}
		}
		
		
	}
}
