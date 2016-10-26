package com.startrekrescue.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.startrekrescue.constants.Constants;
import com.startrekrescue.control.Controller;

// classe de execucao com interface grafica.
public class MainUI extends Application {

	private String executaJogada(int coordenadaX, int coordenadaY, Controller controller){

		controller.lancaSinalizador(coordenadaX, coordenadaY);
		return controller.planicieToString();

	}

	public int validaEntrada(String entrada) {

		try{
			int valor = Integer.parseInt(entrada) - 1;
			if (valor < 0 || valor > Constants.TAMANHO_PLANICIE - 1)
				throw new Exception();

			return valor;

		}catch (Exception e){
			System.out.println("Coordenada inválida, forneça um valor entre 1 e 10");
		}
		return -1;
	}

	@Override
	public void start(Stage primaryStage) {

		// texto para a planicie.
		final Text planicieStr = new Text();
		planicieStr.setFill(Color.GREENYELLOW);
		planicieStr.setFont(Font.font("Verdana", 22));

		// texto para mensagens a usuario.
		final Text msg = new Text();
		msg.setFill(Color.RED);
		msg.setFont(Font.font("Verdana", 12));

		final Controller controller = new Controller();
		
		final TextField coordenadaX = new TextField();
		final TextField coordenadaY = new TextField();

		Button btn = new Button();
		btn.setText("Enviar");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

					int x = validaEntrada(coordenadaX.getText());
					int y = validaEntrada(coordenadaY.getText());

					if (x >=0 && y >= 0){
						planicieStr.setText(executaJogada(x, y, controller));
					
						// verifica se todos os tripulantes foram encontrados.
						if (controller.getNumeroDeTripulantesEncontrados() == Constants.NUMERO_DE_TRIPULANTES){
							msg.setText(String.format("\n\nTodos os tripulantes foram encontrados com %d sinalizadores!", 
									controller.getNumeroDeSinalizadoresLancados()));
						}
					}
					else
						planicieStr.setText("Entrada inválida");

					coordenadaX.clear();
					coordenadaY.clear();
				}
		});

		GridPane gridLancamento = new GridPane();
		gridLancamento.add(coordenadaX, 0, 0);
		gridLancamento.add(coordenadaY, 1, 0);
		gridLancamento.add(btn, 2, 0);
		
		StackPane root = new StackPane();
		root.getChildren().add(gridLancamento);
		root.getChildren().add(planicieStr);
		root.getChildren().add(msg);

		StackPane.setAlignment(gridLancamento, Pos.TOP_CENTER);
		StackPane.setAlignment(planicieStr, Pos.CENTER);
		StackPane.setAlignment(msg, Pos.BOTTOM_CENTER);

		String image = String.format("file:%s\\img\\planicie.jpg", System.getProperty("user.dir"));

		root.setStyle("-fx-background-color: black; "
				+ "-fx-background-image: url('" + image + "'); " +
				"-fx-background-position: center center; " +
				"-fx-background-repeat: stretch;");

		Scene scene = new Scene(root, 400, 400);

		primaryStage.setTitle("Star Trek Rescue");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	// metodo principal
	public static void main(String[] args) {
		launch(args);
	}

}