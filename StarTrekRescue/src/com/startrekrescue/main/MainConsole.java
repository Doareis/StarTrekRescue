package com.startrekrescue.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.startrekrescue.constants.Constants;
import com.startrekrescue.control.Controller;

/*
 * Classe para execucao do projeto via console.
 */
public class MainConsole {

	/*
	 * recupera a entrada do usuario e faz o tratamento caso seja fornecido dado invalido.
	 */
	private int getInputFromUser(Scanner scanner, String mensagem, int valorMaximo) {

		System.out.println(mensagem);

		try{
			int entrada = scanner.nextInt() - 1;
			if (entrada < 0 || entrada > valorMaximo - 1)
				throw new InputMismatchException();
			
			return entrada; 
		}catch (InputMismatchException e){
			System.out.println("Coordenada inválida, forneça um valor entre 1 e 10");
		}

		return -1;
	}
	
	private void executa(Controller controller){
		
		Scanner scanner = new Scanner(System.in);
		
		try{
			while (controller.getNumeroDeTripulantesEncontrados() != Constants.NUMERO_DE_TRIPULANTES){
	
				System.out.println(controller.planicieToString());
	
				int coordenadaX = getInputFromUser(scanner, "Forneça coordenada X", Constants.TAMANHO_PLANICIE);
				if (coordenadaX == -1) 
					continue;
				
				int coordenadaY = getInputFromUser(scanner, "Forneça coordenada Y", Constants.TAMANHO_PLANICIE);
				if (coordenadaY == -1) 
					continue;
	
				controller.lancaSinalizador(coordenadaX, coordenadaY);
	
			}
			
			System.out.println(controller.planicieToString());
			
			// todos os tripulantes foram encontrados.
			System.out.println(String.format("Todos os tripulantes foram encontrados com %d sinalizadores!", 
					controller.getNumeroDeSinalizadoresLancados()));
			
		}finally{
			scanner.close();
		}

	}
	
	// metodo princial.
	public static void main(String []args){
		
		MainConsole main = new MainConsole();
		Controller controller = new Controller();
		main.executa(controller);
		
	}
	
}


