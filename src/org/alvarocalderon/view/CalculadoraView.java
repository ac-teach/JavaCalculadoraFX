
package org.alvarocalderon.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.alvarocalderon.controller.CalculadoraController;

public class CalculadoraView {
    private VBox view;
    private Label display;
    private CalculadoraController controller;

    public CalculadoraView() {
        controller = new CalculadoraController();
        
        // Contenedor principal
        view = new VBox(15);
        
        view.setPadding(new Insets(20));
        view.setAlignment(Pos.CENTER);
        view.setStyle("-fx-background-color: #4A5D23;"); // Fondo Verde Oliva Oscuro

        // Pantalla de la calculadora
        display = new Label("0");
        display.setFont(Font.font("Consolas", FontWeight.BOLD, 40));
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setPrefSize(250, 70);
        // Pantalla con un tono oliva muy claro y texto oscuro
        display.setStyle("-fx-background-color: #E8EDE0; -fx-text-fill: #2C3518; -fx-padding: 10px; -fx-background-radius: 5px;");

        // Cuadrícula para los botones
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        int row = 0;
        int col = 0;

        for (String text : buttons) {
            Button btn = new Button(text);
            btn.setPrefSize(55, 55);
            btn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            
            // Asignación de la paleta Verde Oliva según el tipo de botón
            if (text.matches("[0-9]")) {
                // Números: Verde Oliva intermedio
                btn.setStyle("-fx-background-color: #6B8E23; -fx-text-fill: white; -fx-background-radius: 5px; -fx-cursor: hand;");
            } else if (text.equals("C")) {
                // Botón Limpiar: Tono más rojizo/tierra para destacar
                btn.setStyle("-fx-background-color: #8B4513; -fx-text-fill: white; -fx-background-radius: 5px; -fx-cursor: hand;");
            } else if (text.equals("=")) {
                // Botón Igual: Verde amarillo brillante
                btn.setStyle("-fx-background-color: #9ACD32; -fx-text-fill: #2C3518; -fx-background-radius: 5px; -fx-cursor: hand;");
            } else {
                // Operadores: Tono Oliva puro
                btn.setStyle("-fx-background-color: #556B2F; -fx-text-fill: white; -fx-background-radius: 5px; -fx-cursor: hand;");
            }

            // Evento: Al hacer clic, pasamos el texto del botón y el label al controlador
            btn.setOnAction(e -> controller.processInput(text, display));

            grid.add(btn, col, row);

            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        // Agregar pantalla y botones al contenedor principal
        view.getChildren().addAll(display, grid);
    }
    
   
    // Método para devolver la vista construida a la clase Main
    public VBox getView() {
        return view;
    }
}